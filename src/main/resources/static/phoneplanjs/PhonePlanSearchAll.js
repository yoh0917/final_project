$(document).ready(function () {
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
        loadPlans(currentTelCompany, currentContractType, currentPlanID);
    }

    function filterContract(contract) {
        currentContractType = contract;
        loadPlans(currentTelCompany, currentContractType, currentPlanID);
    }

    function clearFilters() {
        currentTelCompany = '';
        currentContractType = '';
        currentPlanID = '';
        $('.searchID').val(''); 
        loadPlans(currentTelCompany, currentContractType, currentPlanID);
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
    }

    function filterByID(planID) {
        currentPlanID = planID;
        $('.companyFilter').removeClass("filterActive");
        $('.contractFilter').removeClass("filterActive");
        loadPlans(currentTelCompany, currentContractType, currentPlanID);
    }

 function updatePlan(planID) {
    planID = Number(planID).toString();

    const form = document.createElement('form');
    form.method = 'POST';
    form.action = '/sellphone/DashBoard/phoneplans/showFormForUpdate';

    const input = document.createElement('input');
    input.type = 'hidden';
    input.name = "planID";
    input.value = planID;

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

    function loadPlans(telCompany, contractType, planID) {
        $('tbody tr').each(function () {
            let telMatch = (telCompany === '' || $(this).find('.telCompany').text().trim() === telCompany);
            let contractMatch = (contractType === '' || $(this).find('.contractType').text().trim() === contractType);
            let idMatch = (planID === '' || $(this).find('.planID').text().trim().toLowerCase().indexOf(planID.toLowerCase()) !== -1);

            if (telMatch && contractMatch && idMatch) {
                $(this).removeClass('hidden');
            } else {
                $(this).addClass('hidden');
            }
        });
    }

    setupEventListeners();
    loadPlans(currentTelCompany, currentContractType, currentPlanID);

    $('.refreshBtn').on('click', function () {
        $('.contractFilter').removeClass("filterActive");
        $('.companyFilter').removeClass("filterActive");
        removeHidden($('tbody tr'));
        $('tbody tr').removeClass("hidden2")
    })

    $('.companyFilter').on('click', function () {
        console.log($(this).attr('value'));

        let twm = $('.twmBtn').attr('value');
        let chm = $('.chmBtn').attr('value');
        let fet = $('.fetBtn').attr('value');

        $('.contractFilter').removeClass("filterActive");
        $('.companyFilter').removeClass("filterActive");

        removeHidden($('tbody tr'));
        $('tbody tr').removeClass("hidden2")

        switch ($(this).attr('value')) {
            case twm:
                $(this).addClass("filterActive");
                $('.telCompany').each(function () {
                    let tel = $(this).text().trim();
                    if (tel != twm) {
                        addHidden($(this).parent());
                    }
                });
                console.log(twm);

                break;
            case chm:

                $(this).addClass("filterActive");
                $('.telCompany').each(function () {
                    let tel = $(this).text().trim();
                    if (tel != chm) {
                        addHidden($(this).parent());
                    }
                });

                console.log(chm);
                break;
            case fet:

                $(this).addClass("filterActive");
                $('.telCompany').each(function () {
                    let tel = $(this).text().trim();
                    if (tel != fet) {
                        addHidden($(this).parent());
                    }
                });
                console.log(fet);
                break;
        }

    })

    $('.contractFilter').on('click', function () {
        console.log("contractFilter");

        let acq = $('.acquisitionBtn').attr('value');
        let portability = $('.numPortabilityBtn').attr('value');
        let retention = $('.retentionBtn').attr('value');

        $('.contractFilter').removeClass("filterActive");;
        $('tbody tr').removeClass("hidden2");

        switch ($(this).attr('value')) {
            case acq:

                $(this).addClass("filterActive");
                $('.contractType').each(function () {
                    let contract = $(this).text().trim();
                    if (contract != acq) {
                        $(this).parent().addClass("hidden2");
                    }
                });

                console.log(acq);
                break;
            case portability:

                $(this).addClass("filterActive");
                $('.contractType').each(function () {
                    let contract = $(this).text().trim();
                    if (contract != portability) {
                        $(this).parent().addClass("hidden2");
                    }
                });

                console.log(portability);
                break;
            case retention:

                $(this).addClass("filterActive");
                $('.contractType').each(function () {
                    let contract = $(this).text().trim();
                    if (contract != retention) {
                        $(this).parent().addClass("hidden2");
                    }
                });

                console.log(retention);
                break;
        }
    })

    $('.searchID').keyup(function () {
        console.log("searchID")
        $('.companyFilter').removeClass("filterActive");
        $('.contractFilter').removeClass("filterActive");
        $('tbody tr').removeClass('hidden');
        $('tbody tr').removeClass('hidden2');
        if ($(this).val() != "") {
            let planID = $(this).val().trim().toLowerCase();
            $('.planID').each(function () {
                let ID = $(this).text().trim().toLowerCase();
                if (ID.indexOf(planID) === -1) {
                    $(this).closest('tr').addClass("hidden");
                }
            });
        }
    });

 humane.theme = 'bigbox';

        if (!sessionStorage.getItem('welcomeMessageShown')) {
            humane.log("歡迎回來");
            sessionStorage.setItem('welcomeMessageShown', 'true');
        }
});
