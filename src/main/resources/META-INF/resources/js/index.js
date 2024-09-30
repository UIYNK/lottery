function moveFocus(event, nextElementId) {
    const input = event.target;
    document.getElementById(nextElementId).focus();
}

function convertToPersian(inputField) {
    const persianDigits = ['۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹'];
    inputField.value = inputField.value.replace(/[0-9]/g, function (digit) {
        return persianDigits[digit];
    });
}