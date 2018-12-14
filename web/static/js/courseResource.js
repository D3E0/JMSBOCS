let courseResource = {
    computed: {
        tableData() {
            let offset = (this.page - 1) * 5;
            let end = Math.min(this.list.length, offset + 5);
            console.info("change " + offset + " " + end + " " + this.page);
            if (offset === end) {
                console.info("bug");
            }
            return this.list.slice(offset, end);
        },
    },
    data() {
        return {
            loading: false,
            isTch: this.$root.isTch,
            courseId: this.$root.courseId,
            course: {
                courseName: '',
                academicYear: '',
                semester: '',
            },
            list: [],
            selectedId: 0,
            selectedIndex: 0,
            page: 1,
        }
    }, created() {
        this.fetchData();
        this.fetchCourseData();
    }, methods: {
        fetchData() {
            this.loading = true;
            axios.get('/api/resourceList', {
                params: {
                    courseId: this.courseId
                }
            }).then(response => {
                this.list = response.data.data;
            }).catch(error => {
                this.$message.error(error);
            }).finally(() => {
                this.loading = false;
            });
        }, fetchCourseData() {
            this.loading = true;
            axios.get('/api/subject', {
                params: {
                    id: this.courseId
                }
            }).then(response => {
                this.course = response.data.data;
            }).catch(error => {
                this.$message.error(error);
            }).finally(() => {
                this.loading = false;
            });
        },
        handleDownload(index, obj) {
            axios.get('/api/resource', {
                params: {
                    id: obj.courseResourceId
                }
            }).then(response => {
                console.info(response.data.data);
                let a = document.createElement("a");
                a.download = obj.courseResourceFilename;
                a.href = response.data.data.downloadUrl;
                a.click();
            }).catch(error => {
                this.$message.error(error);
            }).finally(() => {
                this.loading = false;
            });
        }, handleDelete() {
            this.loading = true;
            axios.delete('/api/resource', {
                params: {
                    id: this.selectedId
                }
            }).then(response => {
                if (response.data.message === 'success') {
                    this.$message({
                        message: '删除成功',
                        type: 'success'
                    });
                }
                this.list.splice(this.selectedIndex, 1);
            }).catch(error => {
                this.$message.error(error);
            }).finally(() => {
                this.loading = false;
            });
        }, onDelete(index, row) {
            this.selectedId = row.courseResourceId;
            this.selectedIndex = index;
            this.openMsgBox();
        }, openMsgBox() {
            this.$confirm('确定删除该资源?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                console.info("confirm");
                this.handleDelete();
            }).catch((error) => {
                console.info(error);
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        }, handleExceed() {
            this.$message.warning('最多选择 1 个文件');
        }, beforeUpload(file) {
            console.info("submit");
            this.loading = true;
            axios.get('/api/token', {
                params: {
                    id: this.courseId,
                }
            }).then(response => {
                let token = response.data.data;
                this.doUpload(file, token);
            }).catch(error => {
                this.$message.error(error);
                this.loading = false;
            });
            return false;
        }, doUpload(file, token) {
            let key = `resource-${this.course.courseName}-${this.course.academicYear}-${this.course.semester}/${file.name}`;
            console.info(key);
            let observable = qiniu.upload(file, key, token);
            let subscription = observable.subscribe((() => {
            }), (error) => {
                console.info(error);
                this.$message.error(error);
                this.loading = false;
            }, (res) => {
                console.info(res);
                const params = new URLSearchParams();
                params.append('courseId', this.courseId);
                params.append('filename', file.name);
                axios.post('/api/resource', params).then(response => {
                    if (response.data.message === 'success') {
                        this.$message({
                            message: '上传成功',
                            type: 'success'
                        });
                    } else {
                        this.$message.error("上传失败");
                    }
                }).catch(error => {
                    this.$message.error(error);
                }).finally(() => {
                    this.loading = false;
                    this.fetchData();
                });
            });
        }
    },
    template: `
        <div>
        <div style="height: 80px;" v-if="isTch">
            <el-upload ref="upload" :limit="1" action=""
                       :before-upload="beforeUpload"
                       :on-exceed="handleExceed">
                <el-button slot="trigger" size="small" type="primary">上传文件</el-button>
            </el-upload>
        </div>
        <el-table :data="tableData" style="width: 100%"
                  v-loading="loading" :show-header="false">
            <el-table-column min-width="200px">
                <template slot-scope="scope">
                    <img src="/static/img/file.png" height="60px" 
                    alt="课程图片" style="vertical-align: baseline">
                    <div class="item">
                        <div class="title" style="overflow: hidden">
                            <el-button type="text" style="font-size: 18px">
                                {{scope.row.courseResourceFilename}}
                            </el-button>
                        </div>
                        <div class="time">
                    <span class="ctime">
                    <i class="el-icon-date mr5"></i>
                    {{new Date(scope.row.uploadTime).toLocaleString('chinese', {hour12: false})}}
                    </span>
                        </div>
                    </div>
                </template>
            </el-table-column>
            <el-table-column>
                <template slot-scope="scope">
                    <div style="float: right">
                        <el-button size="small" type="primary"
                                   @click="handleDownload(scope.$index, scope.row)">下载
                        </el-button>
                        <el-button size="small" type="danger" v-if="isTch"
                                   @click="onDelete(scope.$index, scope.row)">删除
                        </el-button>
                    </div>
                </template>
            </el-table-column>
        </el-table>
        <div style="margin-top: 10px;margin-left: 13px">
            <el-pagination layout="total, prev, pager, next" :current-page.sync="page"
                           :total="list.length" :page-size="5">
            </el-pagination>
        </div>
    </div>
    `
};
export default courseResource