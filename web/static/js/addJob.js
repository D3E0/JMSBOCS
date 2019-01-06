let testEditor;
testEditor = editormd("test-editormd", {
    placeholder: '本编辑器支持Markdown编辑，左边编写，右边预览',  //默认显示的文字，这里就不解释了
    width: "90%",
    height: 400,
    syncScrolling: "single",
    path: '../editor.md-master/lib/',   //你的path路径（原资源文件中lib包在我们项目中所放的位置）
    emoji: true,
    taskList: true,
    tocm: true,         // Using [TOCM]
    tex: true,                   // 开启科学公式TeX语言支持，默认关闭
    flowChart: true,             // 开启流程图支持，默认关闭
    sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,
    toolbarIcons: function () {  //自定义工具栏，后面有详细介绍
        return editormd.toolbarModes['full']; // full, simple, mini
    },
    imageUpload : true,
    imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
    imageUploadURL : "/uploadMdimage",
});
layui.use(['form', 'laydate','layer'], function () {
    let form = layui.form, laydate = layui.laydate,layer=layui.layer;
    let different = 1;
    $("#jobTitle").blur(function (event) {
        let jobTitle = $('#jobTitle').val();
        let courseId = $('#courseId').val();
        $.post('/isSameJobTitle', {jobTitle: jobTitle, courseId: courseId}, function (data) {
            let tag = $("#tag");
            tag.removeClass("redColor");
            tag.removeClass("greenColor");
            tag.text('');
            console.info("jobTitle"+jobTitle);
            if (data > 0||jobTitle==="") {
                different=1;
                tag.addClass("redColor");
                tag.text('重复或不可用');
            } else {
                different = 0;
                tag.addClass("greenColor");
                tag.text('可以使用');
            }
        });
    });
    form.on('submit(formDemo)', function (data) {
        // layer.msg(JSON.stringify(data.field));
        console.info(JSON.stringify(data.field));
        if (different === 0){
            $.post('/addJob', data.field, function (data) {
                window.location.href = '/jobList';
            });
        }else{
            layer.msg('请填写合法的作业标题');
        }
        return false;
    });
    laydate.render({
        elem: document.getElementById('jobBeginTime')
        , type: 'datetime', min: 0
    });
    laydate.render({
        elem: document.getElementById('jobEndTime')
        , type: 'datetime'
    });
});