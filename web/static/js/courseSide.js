let courseSide = {
    template: `
       <el-menu :default-active="$route.path" class="el-menu-vertical-demo"
             :router="true">
        <el-menu-item index="/announcement" :route="{path:'/announcement'}">
            <i class="el-icon-menu"></i>
            <span slot="title">课程公告</span>
        </el-menu-item>
        <el-menu-item index="/resource" :route="{path:'/resource'}">
            <i class="el-icon-menu"></i>
            <span slot="title">课程资源</span>
        </el-menu-item>
        <el-menu-item index="/comment" :route="{path:'/comment'}">
            <i class="el-icon-menu"></i>
            <span slot="title">课程讨论</span>
        </el-menu-item>
        <el-menu-item index="/userhome" :route="{path:'/userhome'}">
            <i class="el-icon-menu"></i>
            <span slot="title">联系老师</span>
        </el-menu-item>
    </el-menu>
    `
};
export default courseSide