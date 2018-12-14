import login from "./login.js"

console.info(path);

new Vue({
    el: '#app',
    data() {
        return {
            path,
        }
    },
    components: {
        login,
    }
});