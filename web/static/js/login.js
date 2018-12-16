let login = {
    data() {
        return {
            loading: false,
            form: {id: '', pass: ''},
            showEyes: false,
        }
    }, methods: {
        onSubmit() {
            if (this.form.id === '' || this.form.pass === '') {
                this.$message.error("请先填写用户名、密码");
                return;
            }
            const params = new URLSearchParams();
            params.append('id', this.form.id);
            params.append('pass', this.form.pass);
            this.loading = true;
            axios.post('/login', params).then(response => {
                if (response.data.message === 'success') {
                    this.$message({
                        message: '登陆成功',
                        type: 'success'
                    });
                    window.location.href = this.$root.path + '/home'
                } else {
                    this.$message.error("登陆失败");
                }
            }).catch(error => {
                this.$message.error(error);
            }).finally(() => {
                this.loading = false;
            });
        }, handleBlur() {
            this.showEyes = false;
        }, handleFocus() {
            this.showEyes = true;
        }, onReset(formName) {
            this.$refs[formName].resetFields();
        }
    },
    template: `
       <div class="content" v-loading="loading">
        <div id="owl-login"
             v-bind:class="{password: showEyes }">
            <div class="hand"></div>
            <div class="hand hand-r"></div>
            <div class="arms">
                <div class="arm"></div>
                <div class="arm arm-r"></div>
            </div>
        </div>
        <el-form ref="form" :model="form" label-width="70px">
            <el-form-item label="用户名" prop="id">
                <el-input v-model="form.id"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="pass">
                <el-input v-model="form.pass" type="password"
                          @blur="handleBlur" @focus="handleFocus">
                </el-input>
            </el-form-item>
            <el-form-item style="text-align: center; ">
                <el-button style="width: 100px;" size="medium"
                           type="primary" @click="onSubmit">登陆
                </el-button>
                <el-button size="medium" @click="onReset('form')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
    `
};
export default login