$(document).ready(function() {
    var table = $('#orderTable').DataTable({
        "autoWidth": false,
        "columnDefs": [
            { "width": "20%", "targets": 0 },
            { "width": "20%", "targets": 1 },
            { "width": "20%", "targets": 2 },
            { "width": "20%", "targets": 3 },
            { "width": "20%", "targets": 4 }
        ]
    });

    // 明細按鈕
    $('#orderTable').on('click', '.customer-details-btn', function () {
        var row = $(this).closest('tr');
        var orderId = row.attr('id');

        if (row.next().hasClass('details-row')) {
            row.next().remove();
            return;
        }

        $.ajax({
            url: '/sellphone/customerOrder/details/' + orderId,
            type: 'GET',
            success: function (data) {
                var details = '<tr class="details-row"><td colspan="5"><ul>';
                data.forEach(function(detail) {
                    details += '<li>產品ID: ' + detail.productId + ' 產品: ' + detail.productName + ' 數量: ' + detail.quantity + ' 價格: ' + detail.price + '</li>';
                });
                details += '</ul></td></tr>';
                row.after(details);
            },
            error: function () {
                alert('無法獲取訂單明細');
            }
        });
    });
});
