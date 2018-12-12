var userSide = {
    computed: {
        dfltView: function() {
            if (this.isTch) {
                return 'tchProfile';
            } else {
                return 'stuProfile'
            }
        }
    },
    props: ['uid', 'isTch'],
    template: `
    <el-menu :default-active="dfltView" class="el-menu-vertical-demo"
             :router="true">
        <el-menu-item :index="dfltView">
            <i class="el-icon-menu"></i>
            <span slot="title">个人资料</span>
        </el-menu-item>
        <el-menu-item index="updatePwd">
            <i class="el-icon-menu"></i>
            <span slot="title">密码修改</span>
        </el-menu-item>
        <el-menu-item index="notify">
            <i class="el-icon-menu"></i>
            <span slot="title">消息中心</span>
        </el-menu-item>
        <el-menu-item index="qiniu" v-if="isTch">
            <i class="el-icon-menu"></i>
            <span slot="title">七牛云</span>
        </el-menu-item>
    </el-menu>
    `
};
export default userSide