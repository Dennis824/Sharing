
function validateName() {
    var name=document.getElementById("name").value;
    var help=document.getElementById("name_help");

    if(name.length<2||name.length>15){
        help.innerHTML="Name should be from 2 to 20 characters";
        return false;
    }
    else {
        help.innerHTML="";

        return true;
    }

}

function validatePhone() {
    var regex = new RegExp("/\\+?[1-9][0-9]{7,14}/");
    var value=document.getElementById("phone").value;
    var help=document.getElementById("phone_help");

    if(regex.exec(value)==null){
        help.innerHTML="wrong phone number format";
        return false;
    }
    else {
        help.innerHTML="";

        return true;
    }

}


function validatePassword() {
    var password=document.getElementById("password").value;
    var help=document.getElementById("password_help");

    if(password.length<5||password.length>20){
        help.innerHTML="Password should be from 5 to 20 characters";
        return false;
    } else {
        help.innerHTML="";
        return true;
    }
}


function confirmPassword() {
    var password=document.getElementById("password").value;
    var password2=document.getElementById("password2").value;
    var help=document.getElementById("password_help2");

    if(password!==password2){
        help.innerHTML="Passwords should be equal";
        return false;
    } else {
        help.innerHTML="";
        return true;
    }
}
