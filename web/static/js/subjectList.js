var SubjectList = {
    computed: {
        tableData() {
            let offset = (this.page - 1) * 10;
            let end = Math.min(this.list.length, offset + 10);
            console.info("change " + offset + " " + end + " " + this.page);
            if (offset === end) {
                console.info("bug");
            }
            return this.list.slice(offset, end);
        }, isTch() {
            return this.$root.isTch;
        }
    },
    data() {
        return {
            loading: true,
            list: [],
            count: '',
            page: 1,
            courseIndex: 0,
        }
    },
    created() {
        this.fetchData();
    },
    methods: {
        f(x, y) {
            let path = `${this.$root.path}/course/${y.courseId}`;
            console.info(path);
            window.open(path, '_blank');
        }, onEdit(x) {
            this.courseIndex = x;
            this.openMsgBox();
        }, handleDelete() {
            this.loading = true;
            axios.delete('/api/subject', {
                params: {
                    userId: this.$root.uid,
                    courseId: this.courseIndex,
                }
            }).then(response => {
                if (response.data.message === 'success') {
                    this.$message({
                        message: '删除成功',
                        type: 'success'
                    });
                } else {
                    this.$message.error("提交失败");
                }
                this.list.splice(this.courseIndex, 1);
            }).catch(error => {
                this.$message.error(error);
            }).finally(() => {
                this.loading = false;
            });
        }, openMsgBox() {
            this.$confirm('确定删除本门课程?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                console.info("confirm");
                this.handleDelete();
            }).catch(error => {
                console.info(error);
                this.$message({
                    type: 'info',
                    message: '已取消'
                });
            });
        },
        fetchData() {
            this.loading = true;
            axios.get('/api/subjectList', {
                params: {
                    userId: this.$root.uid,
                }
            }).then(response => {
                this.list = response.data.data;
                this.count = response.data.count;
            }).catch(error => {
                this.$message.error(error);
            }).finally(() => {
                this.loading = false;
            });
        },
    },
    template: `
        <div>
        <el-row>
            <el-col :span="24" class="panel-title" v-if="isTch">已开课程</el-col>
            <el-col :span="24" class="panel-title" v-if="!isTch">已选课程</el-col>
        </el-row>
        <el-table :data="tableData" style="width: 100%"
                  :show-header="false" v-loading="loading">
            <el-table-column min-width="180px">
                <template slot-scope="scope">
                    <img src="/static/img/class.png" height="60px" 
                    alt="课程图片" style="vertical-align: baseline">
                    <div class="item">
                        <div class="title">
                            <!--<router-link :to="'/course/' + scope.row.courseId">-->
                            <!--{{scope.row.courseName}}-->
                            <!--</router-link>-->
                            <el-button type="text" style="font-size: 18px" @click="f(scope.$index,scope.row)">
                                {{scope.row.courseName}}
                            </el-button>
                        </div>
                        <div class="time">
                            <span class="ctime">
                                <i class="el-icon-date mr5"></i>{{scope.row.academicYear}} 学年
                            </span>
                            <span class="ctime">
                                <i class="el-icon-time mr5"></i>第 {{scope.row.semester}} 学期
                            </span>
                            <span class="ctime">
                                <i class="el-icon-service mr5"></i>{{scope.row.teacherName}}
                            </span>
                        </div>
                    </div>
                </template>
            </el-table-column>
            <el-table-column align="right">
                <template slot-scope="scope">
                    <div style="float: right">
                        <el-tag type="success" class="mr10">进行中</el-tag>
                        <!--<el-button size="mini" type="danger"-->
                        <!--@click="onEdit(scope.$index)">编辑-->
                        <!--</el-button>-->
                    </div>
                </template>
            </el-table-column>
        </el-table>
        <div style="margin-top: 10px;margin-left: 13px">
            <el-pagination layout="total, prev, pager, next"
                           :current-page.sync="page"
                           :total.sync="list.length">
            </el-pagination>
        </div>
    </div>
    `
};
export default SubjectList