import home from "./home.js"

console.info(userInfo);

new Vue({
    el: '#app',
    data() {
        return {
            userInfo,
        }
    },
    components: {
        home,
    }
});