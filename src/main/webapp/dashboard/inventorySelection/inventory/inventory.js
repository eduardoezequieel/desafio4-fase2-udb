const materialSelect = document.querySelector('#cbMaterial');

materialSelect.addEventListener('change', function(e) {
    const value = e.target.value;

    const selectedOption = document.querySelector(`option[value="${value}"]`);

    const materialType = selectedOption.getAttribute("material-type");
    console.log(materialType);
});