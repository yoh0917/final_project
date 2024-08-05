document.addEventListener("DOMContentLoaded", function() {
    const buttons = document.querySelectorAll(".year-button");
    buttons.forEach(button => {
        button.addEventListener("click", function() {
            const year = this.getAttribute("data-year");
            fetch(`/DashBoard/orders/getYearlyDailyRevenue/${year}`)
                .then(response => response.json())
                .then(data => {
                    const ctx = document.getElementById("yearlyDailyRevenueChart").getContext("2d");
                    const labels = data.map(item => item.yyyyMMdd);
                    const amounts = data.map(item => item.totalAmount);
                    new Chart(ctx, {
                        type: 'line',
                        data: {
                            labels: labels,
                            datasets: [{
                                label: 'Daily Revenue',
                                data: amounts,
                                borderColor: 'rgba(75, 192, 192, 1)',
                                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                                fill: false
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
                })
                .catch(error => console.error('Error:', error));
        });
    });
});
