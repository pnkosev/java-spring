const URLS = {
    items: '/api/item',
};

const toString = i => {
    let columns =
        `<td>${i.name}</td>
        <td>${i.slot.toString()}</td>
        <td>${i.stamina}</td>
        <td>${i.strength}</td>
        <td>${i.attack}</td>
        <td>${i.defense}</td>`;

    columns += i.owned
        ? `<td>
                <form id="yo${i.name}" action=/api/item/${i.name} method="post">
                <button class="btn btn-info" disabled>Buy</button>
                </form>
            </td>`
        : `<td>
                <form id="yo${i.name}" action=/api/item/${i.name} method="post">
                <button class="btn btn-info">Buy</button>
                </form>
            </td>`;

    return `<tr>${columns}</tr>`;
};

const tableBody = document.getElementById('table-item-body');

fetch(URLS.items)
    .then(response => response.json())
    .then(data => {
        let result = '';
        data.forEach(i => {
            result += toString(i);
        });

        tableBody.innerHTML = result;
    });

const buyItem = e => {
    const URL = e.target.getAttribute('action');

    fetch(URL, { method: 'post' })
        .then(() => {
            window.location = '/item/merchant';
        });

    e.preventDefault();
    return false;
};

window.onload = () => {
    const forms = document.querySelectorAll('form');
    forms.forEach(f => f.addEventListener('submit', buyItem));
};


