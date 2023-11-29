const buttonPassword = document.querySelector('#toggle-password');

buttonPassword.addEventListener('click', function () {
    const input = document.querySelector('input[name="password"]');
    if (input.type === "password") {
        input.type = "text";
    } else {
        input.type = "password";
    }
});