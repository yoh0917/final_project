const serverContext = 'http://localhost:8081/sellphone'

function editEmail() {

	$('#edit_email_link').toggle()
	$('#show_email').toggle()
	$('#edit_email').toggle()
	$('#edit_email').val($('#show_email').text())
	$('#edit_email').focus()
	$('#confirm_email').toggle()

}

function updateEmail() {
	let email = $('#edit_email').val()
	url = serverContext + '/UserEmailEdit';
	console.log(email)

	axios.post(url, {
		email: email
	})
		.then(res => {
			let email = res.data
			editEmail()
			$('#show_email').text(email)
		})
		.catch(err => {
			console.log(err)
		})

}

function editContactNum() {

	$('#edit_contactNum_link').toggle()
	$('#show_contactNum').toggle()
	$('#edit_contactNum').toggle()
	$('#edit_contactNum').val($('#show_contactNum').text())
	$('#edit_contactNum').focus()

	$('#confirm_contactNum').toggle()

}

function updateContactNum() {
	let contactNum = $('#edit_contactNum').val()
	url = serverContext + '/UserContactNumEdit';
	axios.post(url, {
		contactNum: contactNum
	})
		.then(res => {
			let contactNum = res.data
			editContactNum()
			$('#show_contactNum').text(contactNum)
		})
		.catch(err => {
			console.log(err)
		})
}

$(function () {
	$('#edit_email').on('focus', function () {
		this.select();
	})
	$('#edit_contactNum').on('focus', function () {
		this.select();
	})
})