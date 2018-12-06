import stuProfile from "./stuProfile.js"
import tchProfile from "./tchProfile.js"
import userSide from "./userSide.js";
import qiniu from "./qiniu.js";
import changePwd from "./changePwd.js";

const router = new VueRouter({
    routes: [{
        path: '/',
        redirect: '/stuProfile', //设置默认指向的路径
    },
        {
            path: '/stuProfile',
            component: stuProfile
        },
        {
            path: '/tchProfile',
            component: tchProfile
        },
        {
            path: '/updatePwd',
            component: changePwd
        },
        {
            path: '/qiniu',
            component: qiniu
        }
    ]
});

new Vue({
    el: '#app',
    router,
    data: {
        uid: 1,
        isTch: true,
    },
    components: {
        userSide,
    }
});