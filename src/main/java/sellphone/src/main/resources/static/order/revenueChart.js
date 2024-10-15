document.addEventListener('DOMContentLoaded', function () {
    var ctx = document.getElementById('revenueChart').getContext('2d');
    var revenueChart = new Chart(ctx, {
        type: 'line', // or 'bar'
        data: {
            labels: [],
            datasets: [{
                label: 'Total Amount',
                data: [],
                borderColor: 'rgba(75, 192, 192, 1)',
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    function updateChart(labels, data) {
        revenueChart.data.labels = labels;
        revenueChart.data.datasets[0].data = data;
        revenueChart.update();
    }

    window.loadYearData = function (year) {
        // 根據選定的年份更新圖表數據
        var labels = [];
        var data = [];

        if (year === 2022) {
            labels = ['202201', '202202', '202203', '202204', '202205', '202206', '202207', '202208', '202209', '202210', '202211', '202212'];
            data = [999866, 705248, 870022, 903164, 690276, 667756, 931211, 841201, 414003, 713699, 545075, 1169436];
        } else if (year === 2023) {
            labels = ['202301', '202302', '202303', '202304', '202305'];
            data = [537147, 683240, 738510, 856543, 1266892];
        }
        // 為2024年添加相應的數據
        updateChart(labels, data);
    }

    window.loadMonthData = function (month) {
        // 根據選定的月份更新圖表數據
        var labels = [];
        var data = [];

        // 添加月份對應數據
        switch (month) {
            case 1:
                labels = ['202201', '202301'];
                data = [999866, 537147];
                break;
            case 2:
                labels = ['202202', '202302'];
                data = [705248, 683240];
                break;
            // 繼續為其餘月份添加數據
        }
        updateChart(labels, data);
    }
});
