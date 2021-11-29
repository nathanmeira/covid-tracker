const userId = localStorage.getItem('userID');
const userName = localStorage.getItem('username');
const userType = localStorage.getItem('userType');

function showDropdown() {
    const dropdownAdmin = document.querySelector('#drop-admin');
    const dropdownMembro = document.querySelector('#drop-membro');

    if ( userType === 1 || userType === '1') {
        dropdownAdmin.classList.remove('hide');
        dropdownMembro.classList.add('hide');
    } else {
        dropdownMembro.classList.remove('hide');
        dropdownAdmin.classList.add('hide');
    }
}

function openedSession(){
    if ((userId && userName && userType) !== null) {
        window.location.href = "/tracker.html";
    }
}

function checkSection(){
    if (userId == null || userName == null || userType == null) {
        localStorage.removeItem('userID');
        localStorage.removeItem('username');
        localStorage.removeItem('userType');
        window.location.href = "/index.html";
    }
}

function logOut(){
    localStorage.removeItem('userID');
    localStorage.removeItem('username');
    localStorage.removeItem('userType');
    window.location.href = "/index.html";
}

const capitalize = (str) => {
    if(typeof str === 'string') {
        return str.replace(/^\w/, c => c.toUpperCase());
    } else {
        return '';
    }
};