$(function () {
    $('.userlistTable').DataTable({
		 scrollCollapse: true,
    	 scrollY: '600px',
	});

    const serverContext = 'http://localhost:8081/sellphone'
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

    $('.blockBtn').on('click', function () {
        Swal.fire({
            title: '確定封鎖該使用者嗎?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '封鎖',
            cancelButtonText: '取消'
        }).then((result) => {
            if (result.isConfirmed) {

                let row = $(this).closest('tr');
                let userId = row.children().first().text().toString();
                $.ajax({
                    url: serverContext + '/UserBlockStatus',
                    method: 'GET',
                    data: { userId: userId },
                    success: function () {
                        Swal.fire({
                            title: '訊息',
                            text: '該使用者已被封鎖。',
                            confirmButtonColor: '#3085d6',
                            cancelButtonColor: '#d33',
                            confirmButtonText: '確認'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                findUser(userId, row)
                            }
                        })
                    }

                })

            }
        })
    })

    $('.unblockBtn').on('click', function () {
        Swal.fire({
            title: '確定解除封鎖該使用者嗎?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '確定',
            cancelButtonText: '取消'
        }).then((result) => {
            if (result.isConfirmed) {

                let row = $(this).closest('tr')
                let userId = row.children().first().text().toString();

                $.ajax({
                    url: serverContext + '/UserBlockStatus',
                    method: 'GET',
                    data: { "userId": userId },
                    success: function () {
                        Swal.fire({
                            title: '訊息',
                            text: '該使用者已被解除封鎖。',
                            confirmButtonColor: '#3085d6',
                            cancelButtonColor: '#d33',
                            confirmButtonText: '確認'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                findUser(userId, row)
                            }
                        })
                    },

                })

            }
        })
    })

    $('.deleteBtn').on('click', function () {
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

                let row = $(this).closest('tr')
                let userId = row.children().first().text().toString();
                $(this).closest('tr').remove()
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
                        })

                    },
                })

            }
        })
    })
})