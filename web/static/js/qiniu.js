var qiniu = {
    data() {
        return {
            milk: {
                ak: "",
                sk: "",
                bucket: "",
            },
        };
    },
    methods: {
        onSubmit() {
            if (this.milk.ak === '' || this.milk.sk === '' || this.milk.bucket === '') {
                this.$message.error("请先填写 AK, SK, Bucket");
                return;
            }
            const params = new URLSearchParams();
            params.append('id', this.$root.uid);
            params.append('accessKey', this.milk.ak);
            params.append('secretKey', this.milk.sk);
            params.append('bucket', this.milk.bucket);
            axios.post('/api/qiniu', params).then(response => {
                if (response.data.message === 'success') {
                    this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                } else {
                    this.$message.error("修改失败");
                }
            }).catch(error => {
                this.$message.error(error);
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    },
    template: `
    <el-form ref="qiniuForm" :model="milk" label-width="90px" style="width: 500px">
        <el-form-item label="Access Key" prop="ak">
            <el-input v-model="milk.ak" type="text"></el-input>
        </el-form-item>
        <el-form-item label="Secret Key" prop="sk">
            <el-input v-model="milk.sk" type="text"></el-input>
        </el-form-item>
        <el-form-item label="Bucket" prop="bucket">
            <el-input v-model="milk.bucket" type="text"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">立即修改</el-button>
            <el-button @click="resetForm('qiniuForm')">取消</el-button>
        </el-form-item>
    </el-form>
    `
};
export default qiniu