var qiniu = {
    data() {
        return {
            milk: {
                ak: "",
                sk: "",
                bucket: "",
            }
        };
    },
    methods: {
        onSubmit() {
            console.log(this.milk);
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    },
    template: `
    <el-form ref="qiniuForm" :model="milk" label-width="90px" style="width: 500px">
        <el-form-item label="Access Key" prop="ak">
            <el-input v-model="milk.ak" type="password"></el-input>
        </el-form-item>
        <el-form-item label="Secret Key" prop="sk">
            <el-input v-model="milk.sk" type="password"></el-input>
        </el-form-item>
        <el-form-item label="Bucket" prop="bucket">
            <el-input v-model="milk.bucket" type="password"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">立即修改</el-button>
            <el-button @click="resetForm('qiniuForm')">取消</el-button>
        </el-form-item>
    </el-form>
    `
}
export default qiniu