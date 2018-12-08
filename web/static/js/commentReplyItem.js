import {timeAgo, detectFactory} from './util.js'

let commentReplyItem = {
    props: {
        reply: {
            commentId: '',
            commentTime: '',
            commentContent: '',
            userId: '',
            username: '',
            replyComments: '',
            rootCommentId: '',
            replyUserId: '',
            replyUsername: '',
            userAgent: '',
        }
    }
    , computed: {
        cTimeAgo: function () {
            return timeAgo(this.reply.commentTime)
        }, ua: function () {
            return detectFactory(this.reply.userAgent);
        }
    },
    template: `
       <div class="vcard">
        <img class="vimg" src="https://gravatar.loli.net/avatar/?d=mm" alt="头像">
        <div class="vh">
            <div class="vhead">
                <span class="vnick">{{reply.username}}</span>
                <span class="vsys">{{ua.browser + " " + ua.version}}</span>
                <span class="vsys">{{ua.os + " " + ua.osVersion}}</span>
            </div>
            <div class="vmeta">
                <span class="vtime">{{cTimeAgo}}</span>
                <el-button type="text" class="vat"
                           @click="$emit('onSubReply', {
                                            pid:reply.commentId,
                                            username:reply.username
                                        })">回复
                </el-button>
            </div>
            <div class="vcontent">
                <p>
                    <el-button type="text" style="color: #555">@{{reply.replyUsername}}
                    </el-button>
                </p>
                {{reply.commentContent}}
            </div>
        </div>
    </div>
    `
};
export default commentReplyItem