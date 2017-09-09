$(document).ready(function () {
	$('#submitButton').on('click', function (event) {
		var password = $('#password');
		var confirmPassword = $('#confirmPassword');
		var username = $('#username');
		var email = $('#email');
		var bggUsername = $('#bggUsername');
		var regKey = $('#regKey');
		var firstName = $("#firstName");
		var lastName = $("#lastName");
		
		if(password.val() != confirmPassword.val()) {
			showValidationErrorToast("Passwords don't match.")
			event.preventDefault();
		}
		
		if(!password.val()) {
			showValidationErrorToast("Password is required.");
			event.preventDefault();
		}
		
		if(!confirmPassword.val()) {
			showValidationErrorToast("You must confirm your password.");
			event.preventDefault();
		}
		
		if(!username.val()) {
			showValidationErrorToast("Username is required.");
			event.preventDefault();
		}
		
		if(!email.val()) {
			showValidationErrorToast("Email is required.");
			event.preventDefault();
		}
		
		if(!regKey.val()) {
			showValidationErrorToast("Registration key is required.");
			event.preventDefault();
		}
		
		if(!firstName.val()) {
			showValidationErrorToast("First name is required.");
			event.preventDefault();
		}
		
		if(!lastName.val()) {
			showValidationErrorToast("Last name is required.");
			event.preventDefault();
		}
	});
	
	function showValidationErrorToast(message) {
		$.toast({
			text: message,
			showHideTransition: 'slide',
			hideAfter: 'false',
			icon: 'error'
		});		
	}
});	