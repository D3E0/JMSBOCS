var SubjectManage = {
    computed: {
        tableData() {
            let offset = (this.page - 1) * 10;
            let end = Math.min(this.list.length, offset + 10);
            console.info("change " + offset + " " + end + " " + this.page);
            if (offset === end) {
                console.info("bug");
            }
            return this.list.slice(offset, end);
        },
        excelTableData() {
            let offset = (this.excelPage - 1) * 8;
            let end = Math.min(this.excelData.length, offset + 8);
            console.info("change " + offset + " " + end + " " + this.excelPage);
            if (offset === end) {
                console.info("bug");
            }
            return this.excelData.slice(offset, end);
        }
    }, created() {
        this.fetchData()
    },
    data() {
        return {
            dialogImgVisible: false,
            loading: true,
            dialogTableVisible: false,
            multipleSelection: [],
            subject: {id: '', name: ''},
            page: 1,
            excelPage: 1,
            list: [],
            rawList: [],
            rawCount: '',
            excelData: [],
            options: [],
        }
    },
    methods: {
        fetchData() {
            this.fetchCourseData();
        }, fetchStudentData() {
            this.loading = true;
            axios.get('/api/subject/user', {
                params: {
                    courseId: this.subject.id
                }
            }).then(response => {
                this.loading = false;
                this.rawCount = response.data.count;
                this.rawList = this.list = response.data.data.map((x) => {
                    return {id: x.userId, name: x.username, major: x.specialty}
                });
            }).catch(error => {
                this.$message.error(error);
            });
        }, fetchCourseData() {
            this.loading = true;
            axios.get('/api/subjectList', {
                params: {
                    userId: this.$root.uid,
                }
            }).then(response => {
                this.loading = false;
                this.options = response.data.data.map((x) => {
                    return {id: x.courseId, name: x.courseName};
                });
            }).catch(error => {
                this.$message.error(error);
            });
        },
        onSingleDelete(x) {
            this.list.splice(x, 1);
        }, onMultiDelete() {
            for (let i in this.multipleSelection) {
                let x = this.multipleSelection[i].id;
                let index = this.list.findIndex((currentValue) => {
                    return currentValue.id === x;
                });
                this.list.splice(index, 1);
            }
        }, handleSelectionChange(val) {
            this.multipleSelection = val;
        }, clearSelection() {
            this.$refs.multipleTable.clearSelection();
        }, handleExceed() {
            this.$message.warning('最多选择 1 个文件');
        }, handleChange(file) {
            this._file(file.raw);
        }, onConfirm() {
            this.list = this.list.concat(this.excelData);
            this.onCancel();
        }, onCancel() {
            this.excelData = [];
            this.dialogTableVisible = false
        }, onReset() {
            console.info("reset");
            this.fetchStudentData();
            this.$refs.fileUpload.clearFiles();
            this.$refs.multipleTable.clearSelection();
        }, onSubmit() {
            this.openMsgBox();
        }, doSubmit() {
            this.loading = true;
            let x = {
                courseId: this.subject.id,
                studentList: [],
            };
            x.courseId = this.subject.id;
            x.studentList = this.list.map(x => {
                return {userId: x.id, username: x.name, specialty: x.major}
            });
            axios.post('/api/subject/user', x).then(response => {
                let x = response.data.data;
                console.info(x);
                let msg = "添加 " + x.addStu + ", 删除 " + x.deleteStu;
                this.openMsg(msg);
            }).catch(error => {
                this.$message.error(error);
            }).finally(() => {
                this.loading = false;
                this.onReset();
            });
        }, openMsg(x) {
            this.$message({
                showClose: true,
                message: x,
                duration: 0
            });
        }, openMsgBox() {
            this.$confirm('确定提交修改?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                console.info("confirm");
                this.doSubmit();
            }).catch(error => {
                console.info(error);
                this.$message({
                    type: 'info',
                    message: '已取消'
                });
            });
        },
        _file(file) {
            const reader = new FileReader();
            reader.onload = (e) => {
                const bstr = e.target.result;
                const wb = XLSX.read(bstr, {type: 'binary'});
                const wsname = wb.SheetNames[0];
                const ws = wb.Sheets[wsname];
                const data = XLSX.utils.sheet_to_json(ws, {header: 1});
                this.excelData = data.map(function (x) {
                    return {id: x[0], name: x[1], major: x[2]};
                });
                this.dialogTableVisible = true;
            };
            reader.readAsBinaryString(file);
        }
    },
    template: `
          <div>
        <el-row style="height: 70px">
            <el-col :span="10">
                <span class="formLabel">课程</span>
                <el-select v-model="subject.id" placeholder="请先选择课程" size="small"
                           @change="fetchStudentData">
                    <el-option v-for="item in options" :key="item.id"
                               :label="item.name" :value="item.id">
                    </el-option>
                </el-select>
            </el-col>
            <el-col :span="10">
                <el-upload action="" :on-change="handleChange"
                           ref="fileUpload"
                           :auto-upload="false" :limit="1"
                           :on-exceed="handleExceed"
                           :disabled="subject.id === ''"
                           accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
                    <el-button size="small" type="primary" slot="trigger" 
                    :disabled="subject.id === ''">
                        批量导入学生
                    </el-button>
                      <el-button type="primary" icon="el-icon-question" size="small"
                               style="padding: 9px 5px; margin-left: -7px;border-left-color: hsla(0,0%,100%,.5);"
                               :disabled="subject.id === ''" @click="dialogImgVisible=true">
                    </el-button>
                </el-upload>
            </el-col>
        </el-row>
          <el-dialog title="学生名单参考格式" :visible.sync="dialogImgVisible">
            <img src="/static/img/help.png" alt="学生名单参考格式">
        </el-dialog>
        <div style="margin-left: 60px">
            <el-button size="small" @click="onMultiDelete"
                       :disabled="multipleSelection.length === 0">
                删除选中学生
            </el-button>
            <el-button size="small" @click="clearSelection"
                       :disabled="multipleSelection.length === 0">
                取消选择
            </el-button>
            <el-button size="small" type="info" @click="onReset">重 置</el-button>
            <el-button size="small" type="primary" @click="onSubmit"
                       :disabled="this.subject.id === ''">提交修改
            </el-button>
        </div>

        <el-table ref="multipleTable"
                  :data="tableData"
                  tooltip-effect="dark"
                  style="width: 100%"
                  v-loading="loading"
                  @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="id" label="学号"></el-table-column>
            <el-table-column prop="name" label="姓名"></el-table-column>
            <el-table-column prop="major" label="专业班级"></el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button size="mini" type="danger"
                               @click="onSingleDelete(scope.$index)">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <div style="margin-top: 10px;margin-left: 13px">
            <el-pagination ref="pagination"
                           layout="total, prev, pager, next"
                           :current-page.sync="page"
                           :total.sync="list.length">
            </el-pagination>
        </div>

        <el-dialog title="要导入的学生名单" :visible.sync="dialogTableVisible">
            <el-table :data="excelTableData">
                <el-table-column property="id" label="学号"></el-table-column>
                <el-table-column property="name" label="姓名"></el-table-column>
                <el-table-column property="major" label="专业班级"></el-table-column>
            </el-table>
            <div style="margin-top: 10px;margin-left: 13px">
                <el-pagination ref="pagination2"
                               layout="total, prev, pager, next"
                               :current-page.sync="excelPage"
                               :total="excelData.length">
                </el-pagination>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button @click="onCancel">取 消</el-button>
                <el-button type="primary" @click="onConfirm">确 定</el-button>
            </div>
        </el-dialog>
    </div>
    `
};
export default SubjectManage