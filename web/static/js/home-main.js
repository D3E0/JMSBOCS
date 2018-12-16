import home from "./home.js"

console.info(user);
console.info(path);

new Vue({
    el: '#app',
    data() {
        return {
            userInfo: user,
            path:path,
        }
    },
    components: {
        home,
    }
});