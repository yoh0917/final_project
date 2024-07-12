$(function () {
    let currentTelCompany = '';
    let currentContractType = '';
    let currentPlanID = '';

    function addHidden(selector) {
        selector.addClass('hidden');
    }

    function removeHidden(selector) {
        selector.removeClass('hidden');
    }

    function filterCompany(company) {
        currentTelCompany = company;
        loadPlans(0, 8, currentTelCompany, currentContractType, currentPlanID);
    }

    function filterContract(contract) {
        currentContractType = contract;
        loadPlans(0, 8, currentTelCompany, currentContractType, currentPlanID);
    }

    function clearFilters() {
        currentTelCompany = '';
        currentContractType = '';
        currentPlanID = '';
        $('.searchID').val(''); // 清空輸入框的值
        loadPlans(0, 8, currentTelCompany, currentContractType, currentPlanID);
    }

    function setupEventListeners() {
        $('.refreshBtn').on('click', function () {
            clearFilters();
        });

        $('.companyFilter').on('click', function () {
            let company = $(this).val();
            filterCompany(company);
        });

        $('.contractFilter').on('click', function () {
            let contract = $(this).val();
            filterContract(contract);
        });

        $('.searchID').on('keyup', function () {
            filterByID($(this).val());
        });

        $('tbody').on('click', '.updateBtn', function () {
            updatePlan($(this).closest('tr').find('.planID').text());
        });

        $('tbody').on('click', '.deleteBtn', function (event) {
            event.preventDefault();
            deletePlan($(this).closest('tr').find('.planID').text());
        });

        $('.insertBtn').on('click', function () {
            window.location.href = './DashBoard/phoneplans/create';
        });

        $('.pagination').on('click', 'a', function (event) {
            event.preventDefault();
            let page = $(this).data('page');
            loadPlans(page, 8, currentTelCompany, currentContractType, currentPlanID);
        });
    }

    function filterByID(planID) {
        currentPlanID = planID;
        $('.companyFilter').removeClass("filterActive");
        $('.contractFilter').removeClass("filterActive");
        loadPlans(0, 8, currentTelCompany, currentContractType, currentPlanID);
    }

    function updatePlan(planID) {
        planID = Number(planID).toString();

        const form = document.createElement('form');
        form.method = 'POST';
        form.action = '/sellphone/DashBoard/phoneplans/update';

        const methodField = document.createElement('input');
        methodField.type = 'hidden';
        methodField.name = '_method';
        methodField.value = 'PUT';

        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = "planID";
        input.value = planID;

        form.appendChild(methodField);
        form.appendChild(input);
        document.body.appendChild(form);
        form.submit();
    }

    function deletePlan(planID) {
        if (confirm("你確定要刪除該列嗎？")) {
            const url = `/sellphone/DashBoard/phoneplans/delete/${planID}`;
            fetch(url, {
                method: 'DELETE'
            }).then(response => {
                if (response.ok) {
                    window.location.reload();
                } else {
                    response.text().then(text => {
                        alert("Failed to delete the product.");
                    });
                }
            }).catch(error => {
                alert("Failed to delete the product.");
            });
        }
    }

    function loadPlans(page, size, telCompany, contractType, planID) {
        $.ajax({
            url: '/sellphone/DashBoard/phoneplans/ajax',
            data: {
                page: page,
                size: size,
                telCompany: telCompany,
                contractType: contractType,
                planID: planID
            },
            success: function (data) {
                let tableBody = $('tbody');
                tableBody.empty();
                data.content.forEach(plan => {
                    tableBody.append(`
                        <tr>
                            <td class="planID">${plan.planID}</td>
                            <td>${plan.planName}</td>
                            <td>${plan.phoneNumber}</td> <!-- 添加電話號碼欄位 -->
                            <td>${plan.telCompany}</td>
                            <td>${plan.contractType}</td>
                            <td>${plan.contractDuration}</td>
                            <td>${plan.generation}</td>
                            <td>${plan.dataUsage}</td>
                            <td>${plan.dataTransferRate}</td>
                            <td>${plan.intraNetCall}</td>
                            <td>${plan.interNetCall}</td>
                            <td>${plan.localCall}</td>
                            <td>${plan.discount}</td>
                            <td>${plan.gift}</td>
                            <td>
                                <form action="/sellphone/DashBoard/phoneplans/showFormForUpdate" method="post">
                                    <input type="hidden" name="planID" value="${plan.planID}">
                                    <button type="submit" class="updateBtn">修改</button>
                                </form>
                            </td>
                            <td>
                                <a href="/sellphone/DashBoard/phoneplans/delete/${plan.planID}"><button class="deleteBtn">刪除</button></a>
                            </td>
                        </tr>
                    `);
                });
                updatePagination(data.currentPage, data.totalPages);
            }
        });
    }

    function updatePagination(currentPage, totalPages) {
        let pagination = $('.pagination ul');
        pagination.empty();
        pagination.append(`
            <li class="${currentPage == 0 ? 'disabled' : ''}">
                <a href="#" data-page="${currentPage - 1}">上一頁</a>
            </li>
        `);
        for (let i = 0; i < totalPages; i++) {
            pagination.append(`
                <li class="${i == currentPage ? 'active' : ''}">
                    <a href="#" data-page="${i}">${i + 1}</a>
                </li>
            `);
        }
        pagination.append(`
            <li class="${currentPage == totalPages - 1 ? 'disabled' : ''}">
                <a href="#" data-page="${currentPage + 1}">下一頁</a>
            </li>
        `);
    }

    setupEventListeners();
    loadPlans(0, 8, currentTelCompany, currentContractType, currentPlanID);
});
