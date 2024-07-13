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
            $("#" + group + " .btn").removeClass("active");
            $("#" + group + " .btn").each(function() {
                if ($(this).text() === value) {
                    $(this).addClass("active");
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
                alert("請選擇所有選項！");
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
                    console.log(response);
                    alert("查詢成功！");
                    displayBestPlans(response);
                },
                error: function(error) {
                    console.error(error);
                    alert("查詢失敗！");
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

            thead.append("<tr><th>方案代碼</th><th>方案名稱</th><th>電信公司</th><th>合約類別</th><th>合約期限</th><th>連線世代</th><th>流量</th><th>流量速率</th><th>網內互打</th><th>網外互打</th><th>市話</th><th>折扣</th><th>禮物</th></tr>");
            plans.forEach(function(plan) {
                var row = $("<tr></tr>");
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

        function clearForm() {
            $("#planForm")[0].reset();
            $("#result").empty();
            $("#planName").prop('disabled', true).empty();
            $("#contractType").prop('disabled', true).empty();
            $("#generation").prop('disabled', true).empty();
            $("#dataUsage").prop('disabled', true).empty();
            $("#telCompany .btn").removeClass("active");
        }

        $(document).ready(function() {
            $("#planName").prop('disabled', true);
            $("#contractType").prop('disabled', true);
            $("#generation").prop('disabled', true);
            $("#dataUsage").prop('disabled', true);
        });