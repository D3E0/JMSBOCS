layui.use(['laypage', 'element', 'layer'], function () {
    let laypage = layui.laypage, $ = layui.$;
    function getLocalTime(nS) {
        return new Date(parseInt(nS) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
    }
    function laypageReload() {
        let count = 0;
        $.post("/publicFilesCount",
            {
                courseId: 1,
                keyword: $("#search").val()
            }, function (data) {
                count = data;
                laypage.render({
                    limit: 10
                    , elem: 'mypage' //注意，这里的 test1 是 ID，不用加 # 号
                    , count: count //数据总数，从服务端得到
                    , jump: function (obj, first) {
                        console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                        console.log(obj.limit); //得到每页显示的条数
                        getFileList(obj.curr);
                    }
                });
            });
    }
    laypageReload();
    function getFileList(i) {
        $.post("/publicFiles",
            {
                courseId: 1,
                page: i,
            },
            function (data, status) {
                $("#list").empty();
                for (var i = 0; i < data.length; i++) {
                    var arr = data[i].fileName.split("/");
                    var filename = arr[arr.length - 1];
                    console.info(filename);
                    var item = '<img class="icon" src="/static/img/file.png" height="60px">\n' +
                        '                <div class="item">\n' +
                        '                    <div class="title">\n' +
                        '                        '+filename+'\n' +
                        '                    </div>\n' +
                        '                    <div class="time">\n' +
                        '                        <a class="fa fa-calendar-o fa-fw" style="color: #2d8cf0"></a>'+ getLocalTime(data[i].uploadTime / 10000000) + '&nbsp;&nbsp;\n' +
                        '                        <a class="fa fa-clock-o fa-fw" style="color: #2d8cf0"></a>'+ data[i].fileSize +'&nbsp;&nbsp;\n' +
                        '                    </div>\n' +
                        '                </div>\n' +
                        '                <a href="' + data[i].downloadUrl + '" target="_blank" download="'+filename+'" ><button type="button" class="layui-btn downloadbtn">下载</button></a>\n';
                    var li = document.createElement("li");
                    li.innerHTML = item;
                    $("#list").append(li);
                }
                window.parent.setIframeHeight();
            }
        );
    }
});