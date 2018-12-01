var userProfile = {
    data() {
        return {
            user: {
                id: "1160299042",
                name: "腿长发",
                major: "",
                email: "12306@qq.com",
                tel: "10086",
                bucket: "default",
                office: '留和路318号',
                type: 'teacher'
            },
            profileRules: {
                email: [
                    { type: 'email', required: true, message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
                ],
                tel: [
                    { required: true, message: '请输入手机号', trigger: 'blur' },
                ],
            }
        };
    },
    methods: {
        onSubmit(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    alert('submit!');
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
    },
    computed: {
        isTch: function() {
            return this.user.type === 'teacher';
        },
        isDfltBucket: function() {
            return true;
        }
    },
    template: `
    <el-form ref="profileForm" :model="user" status-icon :rules="profileRules"
             label-width="80px" style="width: 500px">
        <el-form-item :label="isTch?'工号':'学号'">
            <el-input v-model="user.id" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="姓名">
            <el-input v-model="user.name" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="Bucket" v-if="isTch">
            <el-row>
                <el-col :span="12">
                    <el-input v-model="user.bucket" :disabled="true"></el-input>
                </el-col>
                <el-col :span="12" style="text-align: left;">
                  <span class="tip">
                      <template v-if="isDfltBucket">当前使用公有云</template>
                       <template v-else>当前使用私有云</template>
                  </span>
                </el-col>
            </el-row>

        </el-form-item>
        <el-form-item label="办公地址" v-if="isTch">
            <el-input v-model="user.office"></el-input>
        </el-form-item>
        <el-form-item label="专业班级" v-if="!isTch">
            <el-input v-model="user.major" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="tel">
            <el-input v-model="user.tel"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
            <el-input v-model="user.email"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit('profileForm')">立即创建</el-button>
            <el-button @click="resetForm('profileForm')">取消</el-button>
        </el-form-item>
    </el-form>
    `
}
export default userProfile