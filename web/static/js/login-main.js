import login from "./login.js"

console.info(path);

axios.defaults.baseURL = path;
axios.defaults.withCredentials = true;

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