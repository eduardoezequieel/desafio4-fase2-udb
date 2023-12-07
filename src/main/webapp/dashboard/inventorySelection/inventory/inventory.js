const materialSelect = document.querySelector('#cbMaterial');
const [issnContainer, isbnContainer] = document.querySelectorAll('#issn-container, #isbn-container');

const changeFormMode = (materialType) => {
    issnContainer.classList.add('d-none');
    isbnContainer.classList.add('d-none');

    const issnInput = issnContainer.querySelector('input');
    const isbnInput = isbnContainer.querySelector('input');

    issnInput.required = false;
    isbnInput.required = false;

    switch (materialType) {
        case 'Revista':
            issnContainer.classList.remove('d-none');
            issnInput.required = true;
            break;
        case 'Libro':
            isbnContainer.classList.remove('d-none');
            isbnInput.required = true;
            break;
        default:
            break;
    }
};

document.addEventListener('DOMContentLoaded', function () {
    changeFormMode(null);
});
materialSelect.addEventListener('change', function (e) {
    const value = e.target.value;
    const selectedOption = document.querySelector(`option[value="${value}"]`);
    const materialType = selectedOption.getAttribute("material-type");

    changeFormMode(materialType);
});






