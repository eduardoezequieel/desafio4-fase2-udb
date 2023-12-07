const tableButtons = document.querySelectorAll('.table-item');

const removeItem = (id) => {
    const item = document.querySelector(`button[data-inventory-id="${id}"]`);
    const parent = item.parentNode;
    parent.parentNode.removeChild(parent);
};

tableButtons.forEach((button) => {
    button.addEventListener('click', (e) => {
        const inventoryId = e.target.getAttribute('inventory-id');
        
        const existingButton = document.querySelector(`button[data-inventory-id="${inventoryId}"]`);
        
        if (existingButton) {
            alert('El elemento ya ha sido agregado a la lista.');
            return;
        }
        
        const row = document.querySelector(`#row-${inventoryId}`);
        
        const [code, title, materialType, library ] = 
            Array.from(row.querySelectorAll('td')).map((td) => td.textContent);
        
        const newItem = `
            <div class="d-flex align-items-center justify-content-between border rounded p-3">
                <p class="m-0 p-0"><b>${inventoryId}</b> - ${title} - ${code}</p>
                <button type="button" data-inventory-id="${inventoryId}" class="btn btn-danger btn-sm" onclick="removeItem('${inventoryId}')">X</button>
            </div>
        `;
        
        const container = document.querySelector('#selected-materials-container');
        container.innerHTML += newItem;
    });
});

document.querySelector('form').addEventListener('submit', (e) => {
    e.preventDefault();
    
    const selectedMaterials = Array.from(document.querySelectorAll('#selected-materials-container > div > button'))
        .map((button) => button.getAttribute('data-inventory-id')).join(','); 
    
    const input = document.querySelector('#selected-materials');
    input.value = selectedMaterials;
    
    e.target.submit();
});