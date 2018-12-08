import stuProfile from "./stuProfile.js"
import tchProfile from "./tchProfile.js"
import userSide from "./userSide.js";
import qiniu from "./qiniu.js";
import changePwd from "./changePwd.js";
import notify from "./notify.js";

console.info(userInfo);
//设置默认指向的路径
let dfltPath = {
    path: '/',
    redirect: '/stuProfile',
};
if (userInfo.isTch) {
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
    data: userInfo,
    components: {
        userSide,
    }
});