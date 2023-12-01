const buttonPassword = document.querySelector('#toggle-password');

buttonPassword.addEventListener('click', function () {
    const input = document.querySelector('input[name="password"]');
    const button = document.querySelector('#toggle-password');

    if (input.type === "password") {
        button.innerHTML = '<i class="bi bi-eye-slash-fill"></i>';
        input.type = "text";
    } else {
        button.innerHTML = '<i class="bi bi-eye-fill"></i>';
        input.type = "password";
    }
})