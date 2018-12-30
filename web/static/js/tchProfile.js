var tchProfile = {
    data() {
        return {
            loading: false,
            tch: {
                userId: "",
                username: "",
                email: "",
                telephone: "",
                bucket: "",
                office: '',
                qiniuId: ''
            },
        };
    },
    created() {
        this.fetchData()
    },
    methods: {
        fetchData() {
            this.loading = true;
            axios.get('/api/user', {
                params: {
                    id: this.$root.uid,
                }
            }).then(response => {
                this.tch = response.data.data;
            }).catch(error => {
                this.$message.error(error);
            }).finally(() => {
                this.loading = false;
            });
        },
        onSubmit() {
            let reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
            if (this.tch.email === '' || !reg.test(this.tch.email)) {
                this.$message.error('请输入正确的邮箱');
                return;
            } else if (this.tch.telephone === '') {
                this.$message.error('请输入手机号');
                return;
            } else if (this.tch.office === '') {
                this.$message.error('请输入办公地址');
                return;
            }

            const params = new URLSearchParams();
            params.append('id', this.tch.userId);
            params.append('phone', this.tch.telephone);
            params.append('email', this.tch.email);
            params.append('office', this.tch.office);
            this.loading = true;
            axios.post('/api/user/tch', params).then(response => {
                if (response.data.message === 'success') {
                    this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                    this.fetchData();
                } else {
                    this.$message.error("修改失败");
                }
            }).catch(error => {
                this.$message.error(error);
            }).finally(() => {
                this.loading = false;
            });
        },
        resetForm() {
            this.fetchData();
        }
    },
    computed: {
        isDfltBucket: function () {
            return this.tch.qiniuId === 1;
        }
    },
    template: `
    <el-form ref="profileForm" :model="tch"
             v-loading="loading" label-width="80px" style="width: 500px">
        <el-form-item label="工号">
            <el-input v-model="tch.userId" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="姓名">
            <el-input v-model="tch.username" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="Bucket">
            <el-row>
                <el-col :span="12">
                    <el-input v-model="tch.bucket" :disabled="true"></el-input>
                </el-col>
                <el-col :span="12" style="text-align: left;">
                  <span class="tip">
                      <template v-if="isDfltBucket">当前使用公有云</template>
                      <template v-else>当前使用私有云</template>
                  </span>
                </el-col>
            </el-row>
        </el-form-item>
        <el-form-item label="办公地址">
            <el-input v-model="tch.office"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="telephone">
            <el-input v-model="tch.telephone"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
            <el-input v-model="tch.email"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">立即修改</el-button>
            <el-button @click="resetForm">取消</el-button>
        </el-form-item>
    </el-form>
    `
};
export default tchProfile