import subjectCreate from "./subjectCreate.js"
import SubjectList from "./subjectList.js";
import SubjectManage from "./subjectManage.js";
import SubjectSide from "./subjectSide.js";

console.info(userInfo);

const router = new VueRouter({
    routes: [{
        path: '/',
        redirect: '/list',
    }, {
        path: '/list',
        component: SubjectList
    }, {
        path: '/create',
        component: subjectCreate
    }, {
        path: '/manage',
        component: SubjectManage
    }]
});

new Vue({
    el: '#app',
    router,
    data: userInfo,
    components: {
        SubjectSide,
    }
});