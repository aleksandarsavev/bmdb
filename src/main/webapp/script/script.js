/**
 * 
 */

function validateRegisterForm() {
    var password = document.forms["register_form"].password.value;
    var conf_password = document.forms["register_form"].conf_password.value;
    if(password!=conf_password){
    	alert("Password doesn't match")
    	return false;
    }
    return true;
}