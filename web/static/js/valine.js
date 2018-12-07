const defaultComment = {
    comment: '',
    rid: '',
    pid: '',
    uid: '',
    cid: '',
    username: 'Anonymous',
    url: '',
    ua: navigator.userAgent,
    like: 0,
};
const page = {
    start: 0,
    size: 10
};

const shorten = (str) => str.trim().replace(/>\s+</g, '><');

class Valine {

    constructor() {
    }

    init(option) {
        let _root = this;
        _root.el = document.querySelectorAll(option.el)[0];
        _root.el.classList.add('v');
        console.info(option);
        defaultComment.uid = option.user_id;
        defaultComment.cid = option.course_id;
        let placeholder = option.placeholder;

        _root.el.innerHTML = shorten(`
            <div class="vwrap">
                <div class="vedit">
                    <textarea class="veditor vinput" placeholder="${placeholder}"></textarea>
                    <div class="vctrl">
                        <span class="vemoji-btn">Emoji</span> |
                        <span class="vpreview-btn">Preview</span>
                    </div>
                    <div class="vemojis" style="display:none;"></div>
                </div>
                <div class="vcontrol">
                    <div class="col col-20" title="MarkDown is Support">
                        <a href="https://segmentfault.com/markdown" target="_blank">
                            <svg class="markdown" viewBox="0 0 16 16" version="1.1" width="16" height="16"
                            aria-hidden="true">
                                <path fill-rule="evenodd"
                                d="M14.85 3H1.15C.52 3 0 3.52 0 4.15v7.69C0 12.48.52 13 1.15 13h13.69c.64 0 1.15-.52 1.15-1.15v-7.7C16 3.52 15.48 3 14.85 3zM9 11H7V8L5.5 9.92 4 8v3H2V5h2l1.5 2L7 5h2v6zm2.99.5L9.5 8H11V5h2v3h1.5l-2.51 3.5z"></path>
                            </svg>
                        </a>
                    </div>
                    <div class="col col-80 text-right">
                        <button type="button" title="Cmd|Ctrl+Enter" class="vsubmit vbtn">回复</button>
                    </div>
                </div>
            </div>
            <div class="vinfo"><div class="vcount col"></div></div>
            <div class="vloading"></div>
            <div class="vempty" style="display:none;"></div>
            <div class="vlist"></div>
            <div class="vpage txt-center"></div>
            `);

        // Empty Data
        let vempty = _root.el.querySelector('.vempty');
        _root.nodata = {
            show(txt) {
                vempty.innerHTML = txt || `还没有评论哦，快来抢沙发吧!`;
                vempty.setAttribute('style', 'display:block;');
            },
            hide() {
                vempty.setAttribute('style', 'display:none;');
            }
        };

        // loading
        let _spinner = `<div class="spinner"><div class="r1"></div><div class="r2"></div><div class="r3"></div><div class="r4"></div><div class="r5"></div></div>`;
        let vloading = _root.el.querySelector('.vloading');
        vloading.innerHTML = _spinner;
        _root.loading = {
            show() {
                vloading.setAttribute('style', 'display:block;');
                _root.nodata.hide();
            },
            hide() {
                vloading.setAttribute('style', 'display:none;');
                _root.el.querySelectorAll('.vcard').length === 0 && _root.nodata.show();
            }
        };

        defaultComment.url = (option.path || location.pathname).replace(/index\.(html|htm)/, '');
        _root.bind(option);
    }

    /**
     * Bind Event
     */
    bind(option) {
        let _root = this;

        // 过长的评论做折叠处理
        let expandEvt = (el) => {
            if (el.offsetHeight > 180) {
                el.classList.add('expand');
                Event.on('click', el, (e) => {
                    el.setAttribute('class', 'vcontent');
                })
            }
        };
        // 获取评论
        let commonQuery = (cb) => {
            axios.get('/api/comments').then(function (response) {
                console.info(response.data.data);
                query(1, response.data.data);
            }).catch(function (error) {
                console.log(error);
            });
        };
        commonQuery();
        // 渲染评论
        let query = (pageNo = 1, rets) => {
            _root.loading.show();
            console.info(rets);
            let len = rets.length;
            if (len) {
                _root.el.querySelector('.vlist').innerHTML = '';
                for (let i = 0; i < len; i++) {
                    insertDom(rets[i])
                }
                _root.el.querySelector('.vcount').innerHTML = `<span class="vnum">${len}</span> 评论`;
            }
            _root.loading.hide();
            let _vpage = _root.el.querySelector('.vpage ');
            // _vpage.innerHTML = "sdasdasd";
            _vpage.innerHTML = shorten(`<button class = "v vbtn">查看更多</div>`);
        };

        let insertDom = (ret) => {
            let mt = !!ret.replyCommentId; // is reply
            let _vlist = _root.el.querySelector('.vlist');
            // 要挂载的节点 一级评论挂载到 vlist 上
            let tar = _vlist;
            if (mt) {
                let rid = ret.replyCommentId;
                let rel = document.getElementById(rid);
                if (!rel) {
                    // 父节点未渲染
                    return;
                }
                let pid = ret.rootCommentId;
                let pel = document.getElementById(pid);
                // 二级评论挂载到 对应的一级评论上
                tar = pel.querySelector(".vquote");
            }

            let _vcard = document.createElement('div');
            _vcard.setAttribute('class', 'vcard');
            _vcard.setAttribute('id', ret.commentId);
            let _img = `<img class="vimg" src='https://gravatar.loli.net/avatar/?d=mm'>`
            _vcard.innerHTML = shorten(`
                ${_img}
                <div class="vh" rootid="${ret.rootCommentId || ret.commentId}">
                    <div class="vhead">
                        <span class="vnick">D3E0</span>
                        <span class="vsys">Chrome 69.0.3497.81</span>
                        <span class="vsys">Windows 10.0</span>
                    </div>
                    <div class="vmeta">
                        <span class="vtime">${timeAgo(ret.commentTime)}</span>
                        <span pid="${ret.rootCommentId}" cid="${ret.commentId}" at="@${ret.username}" class="vat">回复
                        </span>
                    </div>
                    <div class="vcontent">
                        ${ret.commentContent}
                    </div>
                    <div class="vquote"></div>
                </div>
            `);

            // is reply
            if (!mt) _vlist.appendChild(_vcard);
            else tar.appendChild(_vcard);

            let _vcontent = _vcard.querySelector('.vcontent');
            expandEvt(_vcontent);
            let _vat = _vcard.querySelector('.vat');
            bindAtEvt(_vat);
        };

        let inputs = {
            comment: _root.el.querySelector('.veditor'),
        };

        Event.on('input', inputs.comment, (e) => {
            defaultComment["comment"] = marked(inputs.comment.value, {sanitize: !0});
        });

        let atData = {
            at: ''
        };

        // at event  回复评论
        let bindAtEvt = (el) => {
            Event.on('click', el, (e) => {
                let at = el.getAttribute('at');
                let rid = el.getAttribute('cid');
                let pid = el.getAttribute('pid');
                atData['at'] = at;
                defaultComment['rid'] = rid;
                defaultComment['pid'] = pid;
                inputs['comment'].placeholder = `${at} ，`;
                inputs['comment'].focus();
            })
        };

        // submit
        let submitBtn = _root.el.querySelector('.vsubmit');
        let submitEvt = (e) => {
            if (submitBtn.getAttribute('disabled')) {
                return;
            }
            if (defaultComment.comment === '') {
                inputs['comment'].focus();
                return;
            }
            if (atData.at !== '') {
                let at = `<a class="at" href='#${defaultComment.rid}'>${atData.at}</a>`;
                defaultComment.comment = at + defaultComment.comment;
            }
            console.info(defaultComment.comment);
            commitEvt();
        };

        let commitEvt = () => {
            submitBtn.setAttribute('disabled', !0);
            _root.loading.show();
            console.info(defaultComment['comment'] + " uid: " + defaultComment['uid'] + " cid: " + defaultComment['cid']);
            console.info("pid: " + defaultComment['pid'] + " rid: " + defaultComment['rid']);
            let commentData = {
                commentContent: defaultComment['comment'],
                userId: defaultComment['uid'],
                courseId: defaultComment['cid'],
                rootCommentId: defaultComment['pid'],
                replyCommentId: defaultComment['rid']
            };
            axios({
                method: 'post',
                url: '/api/comment/add',
                data: Qs.stringify(commentData)
            }).then((response) => {
                commonQuery();
                // insertDom(ret);
                submitBtn.removeAttribute('disabled');
                _root.loading.hide();
                reset();
                // console.info(response);
                // console.info(response.data.data);
                // // let ret = response.data.data;
                // // 更新评论数量
                // let _count = _root.el.querySelector('.vnum');
                // let num = 1;
                // try {
                //     if (_count) {
                //         num = Number(_count.innerText) + 1;
                //         _count.innerText = num;
                //     } else {
                //         _root.el.querySelector('.vcount').innerHTML = '<span class="vnum">1</span> 评论'
                //     }
                // } catch (error) {
                //     console.log(error)
                // }
            }).catch(ex => {
                _root.loading.hide();
            })
        };

        Event.off('click', submitBtn, submitEvt);
        Event.on('click', submitBtn, submitEvt);

        // reset form
        let reset = () => {
            atData['at'] = '';
            defaultComment['comment'] = "";
            defaultComment['rid'] = '';
            defaultComment['pid'] = '';
        }
    }
}

const Event = {
    on(type, el, handler, capture) {
        if (el.addEventListener) el.addEventListener(type, handler, capture || false);
        else if (el.attachEvent) el.attachEvent(`on${type}`, handler);
        else el[`on${type}`] = handler;
    },
    off(type, el, handler, capture) {
        if (el.removeEventListener) el.removeEventListener(type, handler, capture || false);
        else if (el.detachEvent) el.detachEvent(`on${type}`, handler);
        else el[`on${type}`] = null;
    },
};

const timeAgo = (date) => {
    try {
        date = new Date(date);
        var oldTime = date.getTime();
        var currTime = new Date().getTime();
        var diffValue = currTime - oldTime;

        var days = Math.floor(diffValue / (24 * 3600 * 1000));
        if (days === 0) {
            //计算相差小时数
            var leave1 = diffValue % (24 * 3600 * 1000); //计算天数后剩余的毫秒数
            var hours = Math.floor(leave1 / (3600 * 1000));
            if (hours === 0) {
                //计算相差分钟数
                var leave2 = leave1 % (3600 * 1000); //计算小时数后剩余的毫秒数
                var minutes = Math.floor(leave2 / (60 * 1000));
                if (minutes === 0) {
                    //计算相差秒数
                    var leave3 = leave2 % (60 * 1000); //计算分钟数后剩余的毫秒数
                    var seconds = Math.round(leave3 / 1000);
                    return seconds + ' 秒前';
                }
                return minutes + ' 分钟前';
            }
            return hours + ' 小时前';
        }
        if (days < 0) return '刚刚';

        if (days < 8) {
            return days + ' 天前';
        } else {
            return dateFormat(date)
        }
    } catch (error) {
        console.log(error)
    }
};

const dateFormat = (date) => {
    var vDay = padWithZeros(date.getDate(), 2);
    var vMonth = padWithZeros(date.getMonth() + 1, 2);
    var vYear = padWithZeros(date.getFullYear(), 2);
    return `${vYear}-${vMonth}-${vDay}`;
};

const padWithZeros = (vNumber, width) => {
    var numAsString = vNumber.toString();
    while (numAsString.length < width) {
        numAsString = '0' + numAsString;
    }
    return numAsString;
};

export default Valine