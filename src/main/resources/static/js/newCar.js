
function validateNumber() {
    var value=document.getElementById("number").value;
    var help=document.getElementById("number_help");

    var regex = new RegExp('^[A-Za-z0-9]{9}$');
    var match = regex.exec(value);

    if(match==null){
        help.innerHTML="Wrong format, A-Za-z0-9 acceptable";
        return false;
    }
    else {
        help.innerHTML="";
        return true;
    }
}

function toUpperCase(inputObject) {
    inputObject.value=inputObject.value.toUpperCase();
}

function validateForm() {
    return validateNumber();
}