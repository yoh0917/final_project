$(document).ready(function() {
    var table = $('#orderTable').DataTable({
        "autoWidth": false,
        "columnDefs": [
            { "width": "10%", "targets": 0 },
            { "width": "10%", "targets": 1 },
            { "width": "10%", "targets": 2 },
            { "width": "10%", "targets": 3 },
            { "width": "10%", "targets": 4 },
            { "width": "15%", "targets": 5 },
            { "width": "10%", "targets": 6 },
            { "width": "15%", "targets": 7 },
            { "width": "10%", "targets": 8 }
        ]
    });

    // 明細按鈕
    $('#orderTable').on('click', '.details-btn', function () {
        var row = $(this).closest('tr');
        var rowData = table.row(row).data();
        var orderId = rowData[0];

        if (row.next().hasClass('details-row')) {
            row.next().remove();
            return;
        }

        $.ajax({
            url: '/sellphone/DashBoard/orders/details/' + orderId,
            type: 'GET',
            success: function (data) {
                var details = '<tr class="details-row"><td colspan="9"><ul>';
                data.forEach(function(detail) {
                    details += '<li>產品ID: ' + detail.productId + ' 產品: ' + detail.productName+ ' 數量: ' + detail.quantity + ' 價格: ' + detail.price + '</li>';
                });
                details += '</ul></td></tr>';
                row.after(details);
            },
            error: function () {
                alert('無法獲取訂單明細');
            }
        });
    });

    // 編輯按鈕
    $('#orderTable').on('click', '.edit-btn', function () {
        var row = $(this).closest('tr');
        var rowData = table.row(row).data();

        $('#editOrderId').val(rowData[0]);
        $('#editUserId').val(rowData[1]);
        $('#editUserName').val(rowData[2]);
        $('#editStatus').val(rowData[3]);
        $('#editTotalAmount').val(rowData[4]);
        $('#editCreateDate').val(rowData[5]);
        $('#editPayStatus').val(rowData[6]);
        $('#editPayDate').val(rowData[7]);

        $('#editModal').modal('show');
    });

    // 保存編輯
    $('#saveEditBtn').on('click', function () {
        var orderId = $('#editOrderId').val();
        var updatedData = {
            orderId: orderId,
            userId: $('#editUserId').val(),
            userName: $('#editUserName').val(),
            status: $('#editStatus').val(),
            totalAmount: $('#editTotalAmount').val(),
            createDate: $('#editCreateDate').val(),
            payStatus: $('#editPayStatus').val(),
            payDate: $('#editPayDate').val()
        };

        $.ajax({
            url: '/sellphone/DashBoard/orders/update',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(updatedData),
            success: function (response) {
                $('#editModal').modal('hide');
                table.row('#' + orderId).data([
                    updatedData.orderId,
                    updatedData.userId,
                    updatedData.userName,
                    updatedData.status,
                    updatedData.totalAmount,
                    updatedData.createDate,
                    updatedData.payStatus,
                    updatedData.payDate,
                    '<div class="edit-delete-btns"><button type="button" class="btn details-btn">明細</button><button type="button" class="btn edit-btn">編輯</button><button type="button" class="btn btn-secondary delete-btn">刪除</button></div>'
                ]).draw();
                alert('更新成功');
            },
            error: function () {
                alert('更新失敗');
            }
        });
    });

    // 刪除按鈕
    $('#orderTable').on('click', '.delete-btn', function () {
        var row = $(this).closest('tr');
        var rowData = table.row(row).data();
        var orderId = rowData[0];

        $('#deleteOrderId').val(orderId);
        $('#deleteModal').modal('show');
    });

    // 確認刪除
    $('#confirmDeleteBtn').on('click', function () {
        var orderId = $('#deleteOrderId').val();
        $.ajax({
            url: '/sellphone/DashBoard/orders/delete',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ orderId: orderId }),
            success: function (response) {
                $('#deleteModal').modal('hide');
                table.row('#' + orderId).data().status = 'D'; // 更新訂單狀態為 "D"
                table.row('#' + orderId).invalidate().draw(); // 重新繪製表格
                alert('訂單狀態已更新為刪除');
            },
            error: function () {
                alert('刪除失敗');
            }
        });
    });

});
