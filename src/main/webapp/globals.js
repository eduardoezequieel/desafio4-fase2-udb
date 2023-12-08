function initDataTable(name) {
    $(`#${name}`).DataTable({
        "language": {
            "url": "//cdn.datatables.net/plug-ins/1.10.24/i18n/Spanish.json"
        },
    });
}

function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for(let i = 0; i <ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function logout() {
    document.cookie = "user_library=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    window.location.href = '/login';
}

document.addEventListener('DOMContentLoaded', ()=>{
    const user = getCookie('user_library').split(',');
    const isOnDashboardSite = window.location.href.includes('dashboard');
    
    if (isOnDashboardSite && user.length > 1) {
        const name = user[2].substring(6)
            .replace(/'/g, '')
            .replace(/\+/g, ' ');

        const role = user[5].substring(15)
            .replace(/'/g, '')
            .replace(/"/g, '');
        
        const nameElement = document.querySelector('#username-lbl');
        const roleElement = document.querySelector('#role-lbl');
        
        nameElement.innerHTML = name;
        roleElement.innerHTML = role;
        
        const sidebarButtons = document.querySelectorAll('.buttons-container > .d-none');
        
        const [users, inventory, loans, returns] = sidebarButtons;
        
        if (role === 'Administrador') {
            sidebarButtons.forEach(button => button.classList.remove('d-none'));
        } else {
            loans.classList.remove('d-none');
        }
        
        
    } else if (isOnDashboardSite) {
        window.location.href = '/login';
    }
});