let jobId = $("#jobId").val();
layui.use(['table', 'layer', 'element'], function () {
    let table = layui.table, layer = layui.layer,element = layui.element;
    let tableIns = table.render({
        elem: '#demo'
        , height: 570
        , url: '/jobSubmitRecord' //数据接口
        , page: true //开启分页
        , method: "post"
        ,toolbar: '#toolbarDemo'
        , where: {
            jobId: jobId, keyword: function () {
                return $("#search").val()
            }
        }
        , cols: [[ //表头
            {field: 'userId', title: '学号', width: '19%', sort: true}
            , {field: 'userName', title: '学生姓名', width: '17%'}
            , {field: 'status', title: '作业是否提交', width: '16%', sort: true,templet: function (d) {
                    if (d.status === true)
                        return '是';
                    return '否';
                }}
            , {field: 'fileCount', title: '作业提交文件数', width: '15%'}
            , {
                field: 'lastSubmitTime', title: '提交作业时间', width: '21%', sort: true, templet: function (d) {
                    if (d.lastSubmitTime === null)
                        return '无';
                    return new Date(d.lastSubmitTime).toLocaleString();
                }
            }, {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 150}
        ]]
    });
    console.info('table.on');
    table.on('tool(record)', function (obj) {
        let data = obj.data; //获得当前行数据
        let layEvent = obj.event;
        if (layEvent === 'detail') { //查看
            let param = "?jobId=" + jobId+'&studentId='+data.userId;
            console.info("fileList click");
            layer.open({
                title: false,
                area: ['700px', '500px'],
                type: 2,
                scrollbar: true,
                content: ['/jobFileList' + param, 'no']
            });
        }
    });
    table.on('toolbar(record)', function (obj) {
        console.info('table.on');
        let layEvent = obj.event;
        if (layEvent === 'downloadAll') { //查看
            layer.open({
                title: '任务下载中',
                area: ['600px', '200px'],
                type: 2,
                scrollbar: true,
                content: ['/downloadAll?jobId='+jobId, 'no']
            });
        }
    });
    let lastTime;
    $("#search").keyup(function (event) {
        lastTime = event.timeStamp;
        setTimeout(function () {
            if (lastTime - event.timeStamp === 0) {
                tableIns.reload({
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            }
        }, 300);
    });
});