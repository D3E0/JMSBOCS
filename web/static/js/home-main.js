import home from "./home.js"

console.info(user);

new Vue({
    el: '#app',
    data() {
        return {
            userInfo: user
        }
    },
    components: {
        home,
    }
});