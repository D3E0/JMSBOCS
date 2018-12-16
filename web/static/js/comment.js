import commentItem from "./commentItem.js";

let comment = {
    components: {
        commentItem
    },
    data() {
        return {
            loading: true,
            placeholder: '请输入内容',
            content: '',
            list: '',
            count: '',
            obj: {
                pid: '',
                rid: '',
                username: '',
            }
        }
    },
    created() {
        this.fetchData();
    },
    methods: {
        fetchData() {
            this.loading = true;
            axios.get('/api/comment', {
                params: {
                    courseId: this.$root.courseId,
                }
            }).then(response => {
                this.list = response.data.data;
                this.count = response.data.count;
            }).catch(error => {
                this.$message.error(error);
            }).finally(() => {
                this.loading = false;
            });
        }, onReset() {
            this.content = '';
            this.placeholder = '请输入内容';
            this.obj = {rid: '', pid: '', username: ''};
        }, onSubmit() {
            console.info(this.obj);
            const params = new URLSearchParams();
            params.append('userId', this.$root.uid);
            params.append('commentContent', this.content);
            if (this.obj.pid !== '') {
                params.append('rootCommentId', this.obj.rid);
                params.append('replyCommentId', this.obj.pid);
            } else {
                params.append('courseId', this.$root.courseId);
            }
            this.loading = true;
            axios.post('/api/comment', params).then(response => {
                if (response.data.message === 'success') {
                    this.$message({
                        message: '提交成功',
                        type: 'success'
                    });
                    this.fetchData();
                } else {
                    this.$message.error("提交失败");
                }
            }).catch(error => {
                this.$message.error(error);
            }).finally(() => {
                this.loading = false;
                this.onReset();
            });
        }, listenReply(obj) {
            this.placeholder = `@${obj.username}，`;
            this.obj = obj;
            this.$refs.test.focus();
            console.info(obj);
        }
    },
    template: `
     <div v-loading="loading">
        <div class="vwrap">
            <el-input type="textarea" :rows="7"
                      ref="test"
                      :placeholder="placeholder"
                      v-model="content"
                      style="position: relative;padding: 10px 5px;">
            </el-input>
            <div style="padding-top: 15px;">
                <el-row>
                    <el-col :span="24" style="text-align: right">
                        <el-button type="primary" size="small" @click="onSubmit"
                                   :disabled="content === ''">提交评论
                        </el-button>
                        <el-button size="small" @click="onReset">重置</el-button>
                    </el-col>
                </el-row>
            </div>
        </div>
        <el-row style="padding-top: 10px; padding-bottom: 20px">
            <el-col :span="24">
                <span style="font-weight: 600;font-size: 1.25rem;">{{count}}</span> 评论
            </el-col>
        </el-row>
        <commentItem v-for="item in list"
                     :key="item.commentId"
                     :comment="item"
                     v-on:onReply="listenReply">
        </commentItem>
    </div>
    `
};
export default comment