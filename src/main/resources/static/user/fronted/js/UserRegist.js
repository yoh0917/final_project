$(function () {

    const serverContext = 'http://localhost:8081/sellphone'

    function ajaxCheckHint(param, node, url) {
        console.log("parameter:" + param)
        object = { param: param }
        axios.get(url, {
            params: object
        })
            .then(res => {
                node.next().text(res.data)
            })
            .catch(err => {
                console.log(err)
            })
    }

    let hasInput = false;
    function passwordCheck(event) {
        if ($('#passwordinput').val() == '' && $('#passwordcheck').val() == '') {
            $('#passwordalert').text("密碼不可空白");;
            console.log("no data");
            return;
        }


        if ($(event.target).attr('id') == $('#passwordcheck').attr('id')) {
            console.log("test")
            hasInput = true;
        }
        console.log(hasInput)
        if (hasInput) {
            if ($('#passwordinput').val() != $('#passwordcheck').val()) {
                $('.submitBtn').prop('disabled', true).removeClass('active')
                $('#passwordalert').text("密碼與確認密碼不正確");
            } else {
                $('.submitBtn').prop('disabled', false).addClass('active')
                $('#passwordalert').text("");
            }
        }
    }


    $('#contactnuminput').blur(function () {
        url = serverContext + "/checkContactNum"
        param = $(this).val()
        ajaxCheckHint(param, $(this), url)
    })

    $('#accountinput').blur(function () {
        url = serverContext + "/checkUserAccount"
        param = $(this).val()
        ajaxCheckHint(param, $(this), url)
    })

    $('#emailinput').blur(function () {
        console.log("tsadeas");
        url = serverContext + "/checkEmailValid"
        param = $(this).val();
        ajaxCheckHint(param, $(this), url);
    })

    $('#passwordinput').blur(function (e) {
        passwordCheck(e);
    })
    $('#passwordcheck').blur(function (e) {
        passwordCheck(e);
    })

    $('#register-form').on('submit', function (e) {
        e.preventDefault();
        url = serverContext + '/CheckRegist'
        const data = new FormData(this)
        axios.post(url, {
            userName: data.get('username'),
            userAccount: data.get('userAccount'),
            password: data.get("password"),
            contactNum: data.get("contactNumber"),
            email: data.get("email"),
            birthday: data.get("birthday")
        })
            .then(res => {
                Swal.fire({
                    title: '註冊成功',
                    text: '還沒做驗證功能。',
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: '確認'
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = serverContext + '/UserLogin'

                    }
                })
            })
            .catch(err => {
                Swal.fire({
                    title: '註冊失敗',
                    text: '請確認輸入資料是否正確。',
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: '確認'
                })
            })
    })

    $('#leofill').on('click', function () {
        $('#input_phone_num').val('leo1234');
        $('#input_password').val('123456');
    })

    $('#garyfill').on('click', function () {
        $('#input_phone_num').val('Gary1234');
        $('#input_password').val('123456');
    })

    $('#adminfill').on('click', function () {
        $('#input_phone_num').val('admin');
        $('#input_password').val('admin');
    })
})