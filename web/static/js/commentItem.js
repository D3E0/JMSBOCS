import {detectFactory, timeAgo} from './util.js'
import commentReplyItem from "./commentReplyItem.js";

let commentItem = {
    components: {
        commentReplyItem
    },
    props: {
        comment: {
            commentId: '',
            courseId: '',
            commentTime: '',
            commentContent: '',
            userId: '',
            username: '',
            replyComments: '',
            userAgent: '',
        }
    }
    , computed: {
        cTimeAgo: function () {
            return timeAgo(this.comment.commentTime)
        },
        ua: function () {
            return detectFactory(this.comment.userAgent);
        }
    },
    methods: {
        listenSubReply(obj) {
            obj.rid = this.comment.commentId;
            this.$emit('onReply', obj);
        }
    },
    template: `
      <div class="vcard">
        <img class="vimg" src="https://gravatar.loli.net/avatar/?d=mm" alt="头像">
        <div class="vh">
            <div class="vhead">
                <span class="vnick">{{comment.username}}</span>
                <span class="vsys">{{ua.browser + " " + ua.version}}</span>
                <span class="vsys">{{ua.os + " " + ua.osVersion}}</span>
            </div>
            <div class="vmeta">
                <span class="vtime"> {{cTimeAgo}}</span>
                <el-button type="text" class="vat"
                           @click="$emit('onReply', {
                                            pid:comment.commentId,
                                            rid:comment.commentId,
                                            username:comment.username
                                        })">回复
                </el-button>
            </div>
            <div class="vcontent">
                {{comment.commentContent}}
            </div>
            <div class="vquote">
                <comment-reply-item v-for="item in comment.replyComments"
                                    :key="item.commentId"
                                    :reply="item"
                                    v-on:onSubReply="listenSubReply">
                </comment-reply-item>
            </div>
        </div>
    </div>
    `
};
export default commentItem