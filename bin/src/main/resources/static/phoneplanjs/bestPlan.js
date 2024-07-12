$(function () {
    function getBestPlan() {
        var telCompany = $("#telCompany").val();
        var contractType = $("#contractType").val();
        var generation = $("#generation").val();
        var dataUsage = $("#dataUsage").val();

        $.ajax({
            url: '/sellphone/api/getBestPlan',
            type: 'GET',
            data: {
                telCompany: telCompany,
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

        thead.append("<tr><th>方案代碼</th><th>方案名稱</th><th>電話號碼</th><th>電信公司</th><th>合約類別</th><th>合約期限</th><th>連線世代</th><th>流量</th><th>流量速率</th><th>網內互打</th><th>網外互打</th><th>市話</th><th>折扣</th><th>禮物</th></tr>");
        plans.forEach(function(plan) {
            var row = $("<tr></tr>");
            row.append($("<td></td>").text(plan.planID));
            row.append($("<td></td>").text(plan.planName));
            row.append($("<td></td>").text(plan.phoneNumber));
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
    }

    window.getBestPlan = getBestPlan;
    window.clearForm = clearForm;
});
