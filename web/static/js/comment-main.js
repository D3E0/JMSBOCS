import comment from "./comment.js"

new Vue({
    el: '#app',
    data: {
        uid: 2,
        isTch: true,
        courseId: 2
    },
    components: {
        comment
    }
});