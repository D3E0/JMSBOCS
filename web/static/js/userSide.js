var userSide = {
    computed: {
        isTch: function() {
            return this.$root.store.type === "teacher";
        }
    },
    template: `
    <el-menu default-active="profile" class="el-menu-vertical-demo"
             :router="true">
        <el-menu-item index="profile">
            <i class="el-icon-menu"></i>
            <span slot="title">个人资料</span>
        </el-menu-item>
        <el-menu-item index="update">
            <i class="el-icon-menu"></i>
            <span slot="title">密码修改</span>
        </el-menu-item>
        <el-menu-item index="qiniu" v-if="isTch">
            <i class="el-icon-menu"></i>
            <span slot="title">七牛云</span>
        </el-menu-item>
    </el-menu>
    `
};
export default userSide