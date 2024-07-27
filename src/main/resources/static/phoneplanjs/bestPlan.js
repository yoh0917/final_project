     $(document).ready(function() {
            var swiper = new Swiper(".mySwiper", {
                effect: "coverflow",
                grabCursor: true,
                centeredSlides: true,
                slidesPerView: "auto",
                coverflowEffect: {
                    rotate: 50,
                    stretch: 0,
                    depth: 100,
                    modifier: 1,
                    slideShadows: true,
                },
                pagination: {
                    el: ".swiper-pagination",
                },
            });

            window.updateSwiperImages = function(company) {
                var images = [];
                switch(company) {
                    case '台灣大哥大':
                        images = [
                            {src: '../phoneplancss/台灣大哥大.jpg', company: '台灣大哥大'},
                            {src: '../phoneplancss/中華電信.jpg', company: '中華電信'},
                            {src: '../phoneplancss/遠傳.jpg', company: '遠傳'}
                        ];
                        break;
                    case '中華電信':
                        images = [
                            {src: '../phoneplancss/中華電信.jpg', company: '中華電信'},
                            {src: '../phoneplancss/台灣大哥大.jpg', company: '台灣大哥大'},
                            {src: '../phoneplancss/遠傳.jpg', company: '遠傳'}
                        ];
                        break;
                    case '遠傳':
                        images = [
                            {src: '../phoneplancss/遠傳.jpg', company: '遠傳'},
                            {src: '../phoneplancss/台灣大哥大.jpg', company: '台灣大哥大'},
                            {src: '../phoneplancss/中華電信.jpg', company: '中華電信'}
                        ];
                        break;
                }

                swiper.removeAllSlides();
                images.forEach(image => {
                    swiper.appendSlide(`<div class="swiper-slide" data-company="${image.company}"><img src="${image.src}" /></div>`);
                });

                selectOption('telCompany', company);
            };

            $(document).on('click', '.swiper-slide', function() {
                var company = $(this).data('company');
                selectOption('telCompany', company);
                updateSwiperImages(company);
            });

            $("#planName").prop('disabled', true);
            $("#contractType").prop('disabled', true);
            $("#generation").prop('disabled', true);
            $("#dataUsage").prop('disabled', true);
        });

        function addCustomer() {
            var telCompany = $("#telCompany .btn.active").text();
            var planName = $("#planName .btn.active").text();
            var contractType = $("#contractType .btn.active").text();
            var generation = $("#generation .btn.active").text();
            var dataUsage = $("#dataUsage .btn.active").text();

            if (!telCompany || !planName || !contractType || !generation || !dataUsage) {
                alert("請選擇所有選項！");
                return;
            }

            var url = '/sellphone/DashBoard/customers/create' + 
                    '?telCompany=' + encodeURIComponent(telCompany) + 
                    '&planName=' + encodeURIComponent(planName) + 
                    '&contractType=' + encodeURIComponent(contractType) + 
                    '&generation=' + encodeURIComponent(generation) + 
                    '&dataUsage=' + encodeURIComponent(dataUsage);

            window.location.href = url;
        }

        function selectOption(group, value) {
            $("#" + group + " .btn").removeClass("active").css("background-color", "#333");
            $("#" + group + " .btn").each(function() {
                if ($(this).text() === value) {
                    $(this).addClass("active").css("background-color", "#dc3545");
                }
            });
            loadOptions(group, value);
        }

        function loadOptions(currentId, currentValue) {
            var nextId;
            if (currentId === "telCompany") {
                nextId = "planName";
            } else if (currentId === "planName") {
                nextId = "contractType";
            } else if (currentId === "contractType") {
                nextId = "generation";
            } else if (currentId === "generation") {
                nextId = "dataUsage";
            }

            if (nextId) {
                $.ajax({
                    url: '/sellphone/DashBoard/phoneplans/getOptions',
                    type: 'GET',
                    data: {
                        currentId: currentId,
                        currentValue: currentValue
                    },
                    success: function(response) {
                        var nextGroup = $("#" + nextId);
                        nextGroup.empty();
                        response.forEach(function(option) {
                            nextGroup.append('<button type="button" class="btn" onclick="selectOption(\'' + nextId + '\', \'' + option + '\')">' + option + '</button>');
                        });
                        nextGroup.prop('disabled', false);
                    },
                    error: function(error) {
                        console.error(error);
                        alert("獲取選項失敗！");
                    }
                });
            }
        }

        function getBestPlan() {
            var telCompany = $("#telCompany .btn.active").text();
            var planName = $("#planName .btn.active").text();
            var contractType = $("#contractType .btn.active").text();
            var generation = $("#generation .btn.active").text();
            var dataUsage = $("#dataUsage .btn.active").text();

            if (!telCompany || !planName || !contractType || !generation || !dataUsage) {
                iziToast.warning({
                    title: '提醒',
                    message: '需要全部都選歐',
                    position: 'center'
                });
                return;
            }

            $.ajax({
                url: '/sellphone/DashBoard/phoneplans/getBestPlan',
                type: 'GET',
                data: {
                    telCompany: telCompany,
                    planName: planName,
                    contractType: contractType,
                    generation: generation,
                    dataUsage: dataUsage
                },
                success: function(response) {
                    if (response.length > 0) {
                        iziToast.success({
                            title: '有你心中的方案',
                            message: '恭喜你的方案查詢成功!',
                            position: 'bottomRight'
                        });
                    } else {
                        iziToast.error({
                            title: '查詢無結果',
                            message: '暫時目前無您的最佳方案',
                            position: 'topRight'
                        });
                    }
                    displayBestPlans(response);
                    $('html, body').animate({
                        scrollTop: $("#result").offset().top
                    }, 500);
                },
                error: function(error) {
                    console.error(error);
                    iziToast.error({
                        title: '查詢失敗',
                        message: '無法查詢您的最佳方案',
                        position: 'topRight'
                    });
                }
            });
        }

        function displayBestPlans(plans) {
            var resultDiv = $("#result");
            resultDiv.empty();

            if (plans.length === 0) {
                resultDiv.append("<p>沒有找到符合條件的方案。</p>");
                return;
            }

            var table = $("<table></table>").addClass("result-table");
            var thead = $("<thead></thead>");
            var tbody = $("<tbody></tbody>");

            thead.append("<tr><th></th><th>方案代碼</th><th>方案名稱</th><th>電信公司</th><th>合約類別</th><th>合約期限</th><th>連線世代</th><th>流量</th><th>流量速率</th><th>網內互打</th><th>網外互打</th><th>市話</th><th>折扣</th><th>禮物</th></tr>");
            plans.forEach(function(plan) {
                var row = $("<tr></tr>");
                row.append($("<td></td>").append('<input type="checkbox" class="plan-checkbox" value="' + plan.planID + '">'));
                row.append($("<td></td>").text(plan.planID));
                row.append($("<td></td>").text(plan.planName));
                row.append($("<td></td>").text(plan.telCompany));
                row.append($("<td></td>").text(plan.contractType));
                row.append($("<td></td>").text(plan.contractDuration));
                row.append($("<td></td>").text(plan.generation));
                row.append($("<td></td>").text(plan.dataUsage));
                row.append($("<td></td>").text(plan.dataTransferRate));
                row.append($("<td></td>").text(plan.intraNetCall));
                row.append($("<td></td>").text(plan.interNetCall));
                row.append($("<td></td>").text(plan.localCall));
                row.append($("<td></td>").text(plan.discount));
                row.append($("<td></td>").text(plan.gift));
                tbody.append(row);
            });

            table.append(thead);
            table.append(tbody);
            resultDiv.append(table);
        }

        function exportSelectedPlans() {
            var selectedPlans = [];
            $(".plan-checkbox:checked").each(function() {
                selectedPlans.push($(this).val());
            });

            if (selectedPlans.length === 0) {
                iziToast.info({
                    title: '提醒',
                    message: '請選擇至少一個方案',
                    position: 'bottomLeft'
                });
                return;
            }

            var url = '/sellphone/DashBoard/phoneplans/export/excel?planIDs=' + selectedPlans.join(",");
            fetch(url)
                .then(response => response.blob())
                .then(blob => {
                    const a = document.createElement('a');
                    a.href = URL.createObjectURL(blob);
                    a.download = 'phone_plans.xlsx';
                    document.body.appendChild(a);
                    a.click();
                    document.body.removeChild(a);
                })
                .catch(error => console.error('Error:', error));
        }

        function clearForm() {
            $("#planForm")[0].reset();
            $("#result").empty();
            $("#planName").prop('disabled', true).empty();
            $("#contractType").prop('disabled', true).empty();
            $("#generation").prop('disabled', true).empty();
            $("#dataUsage").prop('disabled', true).empty();
            $("#telCompany .btn").removeClass("active").css("background-color", "#333");
        }

        function toggleChatWindow() {
            var chatWindow = document.querySelector('.chat-window');
            if (chatWindow.style.display === 'none' || chatWindow.style.display === '') {
                chatWindow.style.display = 'block';
            } else {
                chatWindow.style.display = 'none';
            }
        }

        function sendQuestion() {
            var question = document.getElementById('chatInput').value;
            var fileField = document.getElementById('chatFile');
            var formData = new FormData();

            if (fileField.files.length > 0) {
                formData.append('file', fileField.files[0]);
            } else {
                formData.append('file', new Blob(), 'empty.txt');
            }

            formData.append('question', question);

            fetch('/sellphone/gemini-pro-vision', {
                method: 'POST',
                body: formData
            })
            .then(response => response.text())
            .then(result => {
                var chatBody = document.getElementById('chatBody');
                var questionElement = document.createElement('div');
                questionElement.className = 'chat-message';
                questionElement.textContent = '我: ' + question;
                chatBody.appendChild(questionElement);

                var answerElement = document.createElement('div');
                answerElement.className = 'chat-message';
                answerElement.textContent = '客服: ' + result;
                chatBody.appendChild(answerElement);

                document.getElementById('chatInput').value = ''; // 清空输入框
                document.getElementById('chatFile').value = ''; // 清空文件输入框
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }