layui.use(['laypage', 'layer'], function () {
    var index = 1;
    var layer = layui.layer;
    var $ = layui.$;

    function getLocalTime(nS) {
        return new Date(parseInt(nS) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
    }
    var length=0;
    let courseId = $("#courseId").val();
    let jobId = $("#jobId").val();
    let studentId= $("#studentId").val();
    $.post('/uploadFiles', {
        jobId: jobId,
        courseId: courseId,
        studentId: studentId
    }, function (data) {
        if (data.length === 0) {
            $('.nothing').show();
        }
        length=data.length;
        for (var i = 0; i < data.length; i++) {
            var arr = data[i].fileName.split("/");
            var filename = arr[arr.length - 1];
            console.info(filename);
            var item =
                '                <img class="icon" src="/static/img/file.png" height="60px">\n' +
                '                <div class="item">\n' +
                '                    <div class="title">\n' +
                '                        ' + filename + '\n' +
                '                    </div>\n' +
                '                    <div class="time">\n' +
                '                        <a class="fa fa-calendar-o fa-fw" style="color: #2d8cf0"></a>' + getLocalTime(data[i].uploadTime / 10000000) + '&nbsp;&nbsp;\n' +
                '                        <a class="fa fa-clock-o fa-fw" style="color: #2d8cf0"></a>' + data[i].fileSize + '&nbsp;&nbsp;\n' +
                '                    </div>\n' +
                '                </div>\n' +
                '                <a href="' + data[i].downloadUrl + '" target="_blank" ><button type="button" class="layui-btn downloadbtn">下载</button></a>\n' +
                '<button class="layui-btn deletebtn" data-key="' + data[i].fileName + '">删除</button> ';
            var li = document.createElement("li");
            li.innerHTML = item;
            $("#list").append(li);
        }
        $(".deletebtn").click(function (e) {
            let li = $(this);
            let key = li.data("key");
            $.post("/deleteFile", {
                courseId: courseId,
                key: key,
                jobId: jobId
            }, function (data) {
                console.info(data);
                if (data === 1) {
                    layer.msg("删除成功");
                    li.parent("li").remove();
                    length--;
                    if(length===0){
                        $('.nothing').show();
                    }
                } else {
                    layer.msg("删除失败");
                }
            });
        });
    });
});