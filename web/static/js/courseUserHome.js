let courseUserHome = {
    data() {
        return {
            loading: false,
            uid: this.$root.uid,
            isTch: this.$root.isTch,
            cid: this.$root.courseId,
            user: {
                userId: '',
                username: '',
                telephone: '',
                email: '',
                office: '',
            }
        }
    }, created() {
        this.fetchData();
    }, methods: {
        fetchData() {
            this.loading = true;
            axios.get('/api/course/teacher', {
                params: {
                    courseId: this.cid
                }
            }).then(response => {
                this.loading = false;
                this.user = response.data.data;
            }).catch(error => {
                this.$message.error(error);
            });
        },
    },
    template: `
        <div class="profile" v-loading="loading">
        <img src="/static/img/teacher.png" alt="头像" style="height: 250px">
        <p class="name">{{user.username}}</p>
        <p>办公室: {{user.office}}</p>
        <p>联系方式:{{user.telephone}}</p>
        <p>邮箱:{{user.email}}</p>
    </div>
    `
};
export default courseUserHome