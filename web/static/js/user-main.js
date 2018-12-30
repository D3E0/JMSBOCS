import stuProfile from "./stuProfile.js"
import tchProfile from "./tchProfile.js"
import userSide from "./userSide.js";
import qiniu from "./qiniu.js";
import changePwd from "./changePwd.js";
import notify from "./notify.js";

console.info(user);
console.info(path);

//设置默认指向的路径
let dfltPath = {
    path: '/',
    redirect: '/stuProfile',
};
if (user.isTch) {
    dfltPath.redirect = '/tchProfile'
}
const router = new VueRouter({
    routes: [dfltPath, {
        path: '/stuProfile',
        component: stuProfile
    }, {
        path: '/tchProfile',
        component: tchProfile
    }, {
        path: '/updatePwd',
        component: changePwd
    }, {
        path: '/qiniu',
        component: qiniu
    }, {
        path: '/notify',
        component: notify
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
            path: path
        }
    },
    components: {
        userSide,
    }
});