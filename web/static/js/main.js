import userProfile from "./userProfile.js"
import userSide from "./userSide.js";
import qiniu from "./qiniu.js";
import changePwd from "./changePwd.js";

const router = new VueRouter({
    routes: [{
            path: '/',
            redirect: '/profile', //设置默认指向的路径
        },
        {
            path: '/profile',
            component: userProfile
        },
        {
            path: '/update',
            component: changePwd
        },
        {
            path: '/qiniu',
            component: qiniu
        }
    ]
})

let store = {
    uid: "1160299042",
    name: "腿长发",
    type: 'teacher'
};


new Vue({
    el: '#app',
    router,
    data: {
        store: store
    },
    components: {
        userSide,
    }
})