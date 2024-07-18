$(function () {

    const serverContext = 'http://localhost:8081/sellphone'

    // ============== Regit Page ===============
    // ============== Regist check (contactNum, email, account ===============

    let checkContactNum = false;
    let checkEmail = false;
    let checkAccount = false;
    let checkPassword = false;
    function registEnable() {
        if ((checkContactNum &&
            checkEmail &&
            checkAccount &&
            checkPassword) == true
        ) {
            $('.submitBtn').prop('disabled', false).addClass('active')
        } else {
            $('.submitBtn').prop('disabled', true).removeClass('active')
        }
    }

    // ==== contactnum check ====
    $('#contactnuminput').blur(function () {
        checkContactNum = false;
        url = serverContext + "/checkContactNum"
        param = $(this).val()
        object = { param: param }
        axios.get(url, { params: object })
            .then(res => {
                $(this).next().text(res.data)
                console.log(res.data.length);
                if (res.data.length == 0) {
                    checkContactNum = true;
                }
                registEnable()
                console.log(checkContactNum);
            })
            .catch(err => {
                console.log(err)
            })

    })

    // ==== account check ====
    $('#accountinput').blur(function () {
        checkAccount = false;
        url = serverContext + "/checkUserAccount"
        param = $(this).val()
        object = { param: param }
        axios.get(url, { params: object })
            .then(res => {
                $(this).next().text(res.data)
                console.log(res.data);
                if (res.data.length == 0) {
                    checkAccount = true;
                }
                registEnable()
                console.log(checkAccount);
            })
            .catch(err => {
                console.log(err)
            })

    })


    // ==== email check ====
    $('#emailinput').blur(function () {
        checkEmail = false;
        url = serverContext + "/checkEmailValid"
        param = $(this).val();
        object = { param: param }
        axios.get(url, { params: object })
            .then(res => {
                $(this).next().text(res.data)
                console.log(res.data);
                if (res.data.length == 0) {
                    checkEmail = true;
                }
                registEnable()
                console.log(checkEmail);
            })
            .catch(err => {
                console.log(err)
            })

    })

    // ==== password check ====

    $('#passwordinput').blur(function (e) {
        passwordCheck(e);
    })
    $('#passwordcheck').blur(function (e) {
        passwordCheck(e);
    })

    let hasInput = false;
    function passwordCheck(event) {
        checkPassword = false;
        if ($('#passwordinput').val() == '' && $('#passwordcheck').val() == '') {
            $('#passwordalert').text("密碼不可空白");;
            return;
        }

        if ($(event.target).attr('id') == $('#passwordcheck').attr('id')) {
            console.log("test")
            hasInput = true;
        }

        console.log(hasInput)
        if (hasInput) {
            if ($('#passwordinput').val() != $('#passwordcheck').val()) {
                // $('.submitBtn').prop('disabled', true).removeClass('active')
                $('#passwordalert').text("密碼與確認密碼不正確");
            } else {
                checkPassword = true;
                // $('.submitBtn').prop('disabled', false).addClass('active')
                $('#passwordalert').text("");
            }
        }
        console.log(checkPassword);
        registEnable()
    }


    // ============== Regist submit ===============
    $('#register-form').on('submit', function (e) {
        e.preventDefault();

        $('.submitBtn').html(
            '<div class="spinner-border m-1" role="status">' +
            '<span class="visually-hidden">Loading...</span>' +
            '</div>'
        );


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
                    text: '請前往email收取驗證信。',
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
                $('.submitBtn').html('註冊'
                );
            })
    })


    // ============== Login Page ===============
    // ============== auto fill for login page ===============
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