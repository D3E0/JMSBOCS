const setting = {
    uid: '2',
};

const shorten = (str) => str.trim().replace(/>\s+</g, '><');

export default class Proline {

    constructor() {
    }

    init(option) {
        console.info(option);
        let _root = this;
        _root.el = document.querySelectorAll(option.el)[0];
        // setting.uid = option.uid;

        _root.el.innerHTML = shorten(`
            <div class="vloading"></div>
            <div class="vempty" style="display:none;"></div>
            <div class="vlist"></div>
        `);

        // Empty Data
        let vempty = _root.el.querySelector('.vempty');
        _root.nodata = {
            show(txt) {
                vempty.innerHTML = txt || `还没有消息哦，去别的地方转转吧`;
                vempty.setAttribute('style', 'display:block;');
            },
            hide() {
                vempty.setAttribute('style', 'display:none;');
            }
        };

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
        this.bind();
    }

    bind() {
        let _root = this;

        let commonQuery = () => {
            axios.get('/api/notify', {
                params: {
                    userId: setting.uid
                }
            }).then(function (response) {
                console.info(response.data.data);
                query(response.data.data);
            }).catch(function (error) {
                console.log(error);
            });
        };
        commonQuery();

        let query = (rets) => {
            _root.loading.show();
            let len = rets.length;
            if (len) {
                _root.el.querySelector('.vlist').innerHTML = '';
                for (let i = 0; i < len; i++) {
                    insertDom(rets[i])
                }
            }
            _root.loading.hide();
        };

        let insertDom = (ret) => {
            let _vlist = _root.el.querySelector('.vlist');
            let _vcard = document.createElement('div');
            _vcard.setAttribute('class', 'vcard layui-card');
            _vcard.setAttribute('id', ret.id);
            _vcard.innerHTML = shorten(`
                <div class="layui-card-header" style="height: 50px">
                    <span class="title">${ret.title}</span>
                </div>
                <div class="layui-card-body">
                    <div class="vmeta"><span class="vtime">${timeAgo(ret.time)}</span></div>
                    <div class="vcontent">${ret.content}</div>
                    <div class="vquote">
                        <button class="layui-btn layui-btn-primary layui-btn-sm vdelete" nid="${ret.id}">删除</button>
                    </div>
                </div>`);
            _vlist.appendChild(_vcard);
            let _vdelete = _vcard.querySelector('.vdelete');
            bindAtEvt(_vdelete);
        };

        let bindAtEvt = (el) => {
            Event.on('click', el, (e) => {
                let nid = el.getAttribute('nid');
                console.info("delete " + nid);
                axios.delete('/api/notify', {
                    params: {
                        id: nid
                    }
                }).then((response) => {
                    if (response.data.message === 'success') {
                        let _vlist = _root.el.querySelector('.vlist');
                        let _vcard = document.getElementById(nid);
                        _vlist.removeChild(_vcard);
                    }
                }).catch(ex => {
                    console.info(ex);
                })
            })
        };
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
            return date.toLocaleString('chinese', {hour12: false});
        }
    } catch (error) {
        console.log(error)
    }
};
