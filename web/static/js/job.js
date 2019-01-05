$.post('/getJobContent',{jobId},function (data) {
    testEditor = editormd.markdownToHTML("content", {
        markdown:data,
        htmlDecode      : "style,script,iframe",  // you can filter tags decode
        emoji           : true,
        taskList        : true,
    });
});
layui.use(['layer'], function () {
    let courseId=$("#courseId").val();
    let userId=$("#userId").val();
    let jobId=$("#jobId").val();
    let layer=layui.layer;
    $("#delbtn").click(function () {
        var jobId = $("#jobId").val();
        $.post('/deleteJob', {
            jobId: jobId
        }, function (data) {
            window.location.href = '/jobList';
        })
    });
    $('#fileList').click(function () {
        let param="?jobId="+jobId+"&studentId="+userId;
        console.info("fileList click");
        layer.open({
            offset: '100px',
            title: false,
            area: ['700px', '500px'],
            type: 2,
            scrollbar: true,
            content: ['/jobFileList'+param]
        });
    });
});