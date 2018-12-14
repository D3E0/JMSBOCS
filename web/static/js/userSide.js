var userSide = {
    computed: {
        dfltView: function () {
            if (this.isTch) {
                return '/tchProfile';
            } else {
                return '/stuProfile'
            }
        }
    },
    props: ['uid', 'isTch'],
    template: `
    <el-menu :default-active="$route.path" class="el-menu-vertical-demo"
             :router="true">
        <el-menu-item :index="dfltView">
            <i class="el-icon-menu"></i>
            <span slot="title">个人资料</span>
        </el-menu-item>
        <el-menu-item index="/updatePwd" :route="{path:'/updatePwd'}">
            <i class="el-icon-menu"></i>
            <span slot="title">密码修改</span>
        </el-menu-item>
        <el-menu-item index="/notify" :route="{path:'/notify'}">
            <i class="el-icon-menu"></i>
            <span slot="title">消息中心</span>
        </el-menu-item>
        <el-menu-item index="/qiniu" v-if="isTch" :route="{path:'/qiniu'}">
            <i class="el-icon-menu"></i>
            <span slot="title">七牛云</span>
        </el-menu-item>
    </el-menu>
    `
};
export default userSide