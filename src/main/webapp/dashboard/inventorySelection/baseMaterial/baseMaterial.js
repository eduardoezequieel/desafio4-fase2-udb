const updateFormState = (value) => {
    const inputs = document.querySelectorAll('#author-container, #editorial-container, #date-container, #duration-container, #song-number-container');
    const [author, editorial, date, duration, songNumber] = inputs;

    const updateRequiredAttribute = (container) => {
        const inputChildren = container.querySelectorAll('input');
        if (container.classList.contains('d-none')) {
            inputChildren.forEach(input => input.removeAttribute('required'));
        } else {
            inputChildren.forEach(input => input.setAttribute('required', ''));
        }
    };

    inputs.forEach(input => {
        input.classList.add('d-none');
        updateRequiredAttribute(input);
    });

    switch (value) {
        case '1':
        case '2':
            author.classList.remove('d-none');
            editorial.classList.remove('d-none');
            date.classList.remove('d-none');
            break;
        case '3':
            editorial.classList.remove('d-none');
            date.classList.remove('d-none');
            break;
        case '4':
            duration.classList.remove('d-none');
            songNumber.classList.remove('d-none');
            break;
    }

    [author, editorial, date, duration, songNumber].forEach(updateRequiredAttribute);
};

document.addEventListener('DOMContentLoaded', ()=>{
    const materialTypeSelect = document.getElementById('cbMaterialType');
    updateFormState(materialTypeSelect.value);

    materialTypeSelect.addEventListener('change', (e)=> {
        updateFormState(e.target.value);
    });
});
