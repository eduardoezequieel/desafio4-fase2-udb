const identificationCode = localStorage.getItem('id');

const anchor = document.querySelector('#approve-anchor');
const newHref = `${anchor.getAttribute('href')}&code=${identificationCode}`;

anchor.setAttribute('href', newHref);