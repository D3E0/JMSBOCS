var SubjectCreate = {
    data() {
        return {
            loading: false,
            subject: {
                academic: '2018',
                semester: '1',
                name: '',
                description: ''
            },
            list: {}
        };
    }, methods: {
        onSubmit() {
            if (this.subject.name === '' || this.subject.description === '') {
                this.$message.error("请先填写课程名称和描述");
                return;
            }
            this.loading = true;
            const params = new URLSearchParams();
            params.append('id', this.$root.uid);
            params.append('name', this.subject.name);
            params.append('description', this.subject.description);
            params.append('academic', this.subject.academic);
            params.append('semester', this.subject.semester);
            axios.post('/api/subject', params).then(response => {
                if (response.data.message === 'success') {
                    this.$message({
                        message: '添加成功',
                        type: 'success'
                    });
                    this.resetForm('subjectForm');
                } else {
                    this.$message.error("添加失败");
                }
            }).catch(error => {
                this.$message.error(error);
            }).finally(() => {
                this.loading = false;
            });
        }, resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    },
    template: `
       <el-form ref="subjectForm" :model="subject" v-loading="loading"
             label-width="90px" style="width: 500px">
        <el-form-item label="课程名称" prop="name">
            <el-input v-model="subject.name" type="text"></el-input>
        </el-form-item>
        <el-form-item label="课程描述" prop="description">
            <el-input v-model="subject.description" type="text"></el-input>
        </el-form-item>
        <el-form-item label="开设学年" prop="academic">
            <el-select v-model="subject.academic" placeholder="请选择" disabled>
                <el-option value="2018" label="2018"></el-option>
                <el-option value="2019" label="2019"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="开设学期" prop="semester">
            <el-select v-model="subject.semester" placeholder="请选择" disabled>
                <el-option value="1" label="1"></el-option>
                <el-option value="2" label="2"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">提交</el-button>
            <el-button @click="resetForm('subjectForm')">重置</el-button>
        </el-form-item>
    </el-form>
    `
};
export default SubjectCreate