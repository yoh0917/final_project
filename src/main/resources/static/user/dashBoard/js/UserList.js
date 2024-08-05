const serverContext = 'http://localhost:8081/sellphone'
//window.onload = function() {
//            changeRowColor('row2', 'lightblue');
//};


function findUser(userId, row) {
    url = serverContext + '/findUserViewByUserId';
    object = { userId, userId };
    axios.get(url, {
        params: object
    })
        .then(res => {

            let statusDescrib = res.data.statusDescrib;
            row.find('.status').text(statusDescrib);
            console.log(row.find('.status').text(statusDescrib));
            row.find(".blockBtn").toggle();
            row.find(".unblockBtn").toggle();
        })
        .catch(err => {
            console.log(err)
        })

}

function blockUser(button) {
    Swal.fire({
        title: '確定封鎖該使用者嗎?',
        icon: 'warning',
        text: '封鎖將造成使用者無法發布文章',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '封鎖',
        cancelButtonText: '取消'
    }).then((result) => {
        if (result.isConfirmed) {
            let row = $(button).closest('tr');
            let userId = row.children().first().text().toString();
            $.ajax({
                url: serverContext + '/UserBlockStatus',
                method: 'GET',
                data: { userId: userId },
                success: function () {
                    Swal.fire({
                        title: '該使用者已被封鎖',
                        text: '如需解除封鎖，點選黃色按鈕即可',
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: '確認'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            findUser(userId, row);
                        }
                    });
                }
            });
        }
    });
}

function unblockUser(button) {
    Swal.fire({
        title: '確定解除封鎖該使用者嗎?',
        text: '解除封鎖將讓使用者可以發布文章',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '確定',
        cancelButtonText: '取消'
    }).then((result) => {
        if (result.isConfirmed) {
            let row = $(button).closest('tr');
            let userId = row.children().first().text().toString();
            $.ajax({
                url: serverContext + '/UserBlockStatus',
                method: 'GET',
                data: { "userId": userId },
                success: function () {
                    Swal.fire({
                        title:'該使用者已被解除封鎖',
                        text: '如需再次封鎖，點選紅色按鈕即可',
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: '確認'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            findUser(userId, row);
                        }
                    });
                }
            });
        }
    });
}

function deleteUser(button) {
    Swal.fire({
        title: '確定刪除嗎?',
        text: "這個操作無法回復!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '刪除',
        cancelButtonText: '取消'
    }).then((result) => {
        if (result.isConfirmed) {
            let row = $(button).closest('tr');
            let userId = row.children().first().text().toString();
            row.remove();
            $.ajax({
                url: serverContext + '/UserDelete',
                method: 'GET',
                data: { userId: userId },
                success: function () {
                    Swal.fire({
                        title: '訊息',
                        text: '該使用者已被刪除。',
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: '確認'
                    }).then((result) => {
                        if (result.isConfirmed) {

                        }
                    });
                }
            });
        }
    });
}



$(function () {
    $('.userlistTable').DataTable({
        scrollCollapse: true,
        scrollY: '600px',
    });

})