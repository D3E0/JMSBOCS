let subjectSide = {
    props: ['uid', 'isTch'],
    computed: {},
    template: `
       <el-menu :default-active="$route.path" class="el-menu-vertical-demo"
             :router="true">
        <el-menu-item index="/list" :route="{path:'/list'}">
            <i class="el-icon-menu"></i>
            <span slot="title">我的课程</span>
        </el-menu-item>
        <el-menu-item index="/create" :route="{path:'/create'}" v-if="isTch" >
            <i class="el-icon-menu"></i>
            <span slot="title">创建课程</span>
        </el-menu-item>
        <el-menu-item index="/manage" :route="{path:'/manage'}" v-if="isTch">
            <i class="el-icon-menu"></i>
            <span slot="title">课程管理</span>
        </el-menu-item>
    </el-menu>
    `
};
export default subjectSide