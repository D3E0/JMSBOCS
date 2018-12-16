import courseUserHome from "./courseUserHome.js"
import courseAnnouncement from "./courseAnnouncement.js";
import courseResource from "./courseResource.js";
import comment from "./comment.js";
import courseSide from "./courseSide.js";

console.info(user);
console.info(path);
console.info(courseId);

const router = new VueRouter({
    routes: [{
        path: '/',
        redirect: '/announcement',
    }, {
        path: '/announcement',
        component: courseAnnouncement
    }, {
        path: '/resource',
        component: courseResource
    }, {
        path: '/comment',
        component: comment
    }, {
        path: '/userhome',
        component: courseUserHome
    }]
});

new Vue({
    el: '#app',
    router,
    data() {
        return {
            uid: user.uid,
            isTch: user.isTch,
            name: user.name,
            path: path,
            courseId: courseId,
    }
    },
    components: {
        courseSide,
    }
});