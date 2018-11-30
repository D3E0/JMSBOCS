import Proline from "./proline.js";

let proline = new Proline();
proline.init({
    el: '.notify',
    path: window.location.pathname,
    uid: 1,
});

