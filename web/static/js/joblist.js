function close(){
    layui.use('layer', function () {
        layui.layer.close(index)
    });
}
layui.use(['laypage', 'element', 'layer'], function () {
    var laypage = layui.laypage, $ = layui.$,layer=layui.layer;
    $(".additem").click(function () {
        window.location.href="/addJob"
    });
    function laypageReload() {
        var count = 0;
        $.post("/countJob",
            {
                studentId: 1160299001,
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
                        getjoblist(obj.curr, $("#search").val());
                    }
                });
            });
    }
    laypageReload();
    var lastTime;
    $("#search").keyup(function (event) {
        lastTime = event.timeStamp;
        setTimeout(function () {
            if (lastTime - event.timeStamp === 0) {
                var txt = $("#search").val();
                console.info(txt);
                laypageReload();
            }
        }, 300);
    });
    function getjoblist(i, keyword) {
        $.post("/jobList",
            {
                studentId: 1160299001,
                page: i,
                keyword: keyword
            },
            function (data, status) {
                $("#list").empty();
                for (var i = 0; i < data.length; i++) {
                    var obj = data[i];
                    let job='javascript:;';
                    let status='organgedot';
                    let text='Not Started';
                    if (obj.ended === true) {
                        status='reddot';
                        text='Ended';
                        job='/job?jobId=' + obj.jobId;
                    }
                    else if(obj.started === true){
                        status='greendot';
                        text='Underway';
                        job='/job?jobId=' + obj.jobId;
                    }
                    var item = '<img class="icon" src="/static/img/job.png" height="60px">' +
                        '<div class="item">\n' +
                        '    <div class="title">\n' +
                        '        <a href="' + job+' ">' + obj.jobTitle + '</a>\n' +
                        '    </div>\n' +
                        '    <div class="time">\n' +
                        '        <a class="fa fa-calendar-o fa-fw" style="color: #2d8cf0"></a>' + obj.jobBeginTime + '&nbsp;&nbsp;\n' +
                        '        <a class="fa fa-clock-o fa-fw" style="color: #2d8cf0"></a>\n' +
                        '        &nbsp;' + obj.time + 'days&nbsp;&nbsp;\n' +
                        '        <a class="fa fa-book fa-fw" style="color: #2d8cf0"></a>' + obj.courseName + '&nbsp;&nbsp;\n' +
                        '    </div>\n' +
                        '</div>'+
                        '<div class="state">\n' +
                        '<span class="layui-badge-dot '+status+'"></span>&nbsp;&nbsp;'+text+'\n' +
                        '</div>';
                    var li = document.createElement("li");
                    li.innerHTML = item;
                    $("#list").append(li);
                }
            }
        );
    }
});