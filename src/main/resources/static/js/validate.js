let reg1 = /^[A-Za-zА-Яа-яЁё]{1,12}$/;
let reg2 = /^[1-9]{1}$|^[1-2]{1}[0-9]{1}$|^30$/;
let reg3 = /^[1-9]\d{0,3}$/;

let inp1 = document.querySelector('#type');
let inp2 = document.querySelector('#age');
let inp3 = document.querySelector('#price');
let span1 = document.querySelector('.span1');
let span2 = document.querySelector('.span2');
let span3 = document.querySelector('.span3');

document.querySelector('.btn').onclick = function (e) {
    if (!validate(reg1, inp1.value)) {
        e.preventDefault();
        notValid(inp1, span1, 'Введите тип из 1-12 букв');
    }
    if (!validate(reg2, inp2.value)) {
        e.preventDefault();
        notValid(inp2, span2, 'Введите возраст 1-30 лет');
    }
    if (!validate(reg3, inp3.value)) {
        e.preventDefault();
        notValid(inp3, span3, 'Введите цену 1-9999$');
    }
};

function validate(regex, inp) {
    return regex.test(inp);
}

function notValid(inp, el, mess) {
    inp.classList.add('is-invalid');
    el.innerHTML = mess;
}