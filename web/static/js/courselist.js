layui.use(['laypage', 'element', 'layer'], function () {
    var laypage = layui.laypage, $ = layui.$;

    function laypageReload() {
        var count = 0;
        $.post("/courseCount",
            {
                studentId: 2,
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
                        getCourseList(obj.curr, $("#search").val());
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
    function getCourseList(i, keyword) {
        console.info("page   "+i);
        $.post("/courseList",
            {
                studentId: 2,
                page: i,
                keyword: keyword
            },
            function (data, status) {
                $("#list").empty();
                for (var i = 0; i < data.length; i++) {
                    var obj = data[i];
                    var item ='<img class="icon" src="/static/img/class.png" height="60px">\n' +
                        '            <div class="item">\n' +
                        '                <div class="title">\n' +
                        '                    <a href="/course?courseId='+obj.courseId+'&courseName='+obj.courseName+'">'+obj.courseName+'</a>\n' +
                        '                </div>\n' +
                        '                <div class="time">\n' +
                        '                    <a class="fa fa-calendar-o fa-fw" style="color: #2d8cf0"></a>'+obj.academicYear+'&nbsp;&nbsp;\n' +
                        '                    <a class="fa fa-clock-o fa-fw" style="color: #2d8cf0"></a>第'+obj.semester+'学期&nbsp;&nbsp;\n' +
                        '                    <a class="fa fa-user-o fa-fw" style="color: #2d8cf0"></a>'+obj.teacherName+'\n' +
                        '                </div>\n' +
                        '                <button></button>\n' +
                        '            </div>\n' +
                        '            <div class="state">\n' +
                        '                <span class="layui-badge-dot greendot"></span>&nbsp;&nbsp;Underway\n' +
                        '            </div>';
                    var li = document.createElement("li");
                    li.innerHTML = item;
                    $("#list").append(li);
                }
                window.parent.setIframeHeight();
            }
        );
    }
});