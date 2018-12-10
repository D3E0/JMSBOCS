var upload_token;
layui.use('upload', function () {
    var $ = layui.jquery
        , upload = layui.upload;
    var courseId = $("#courseId").val();
    test = "test";
    $.post('/qiniu', {
            courseId: courseId
        }, function (data) {
            upload_token = data;
            var demoListView = $('#demoList')
                , uploadListIns = upload.render({
                elem: '#testList'
                , url: 'http://upload.qiniup.com'
                , accept: 'file'
                , data: {
                    token: upload_token, key: function () {
                        return test;
                    }
                }
                , multiple: true
                , auto: false
                , bindAction: '#testListAction'
                , choose: function (obj) {
                    var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                    //读取本地文件
                    obj.preview(function (index, file, result) {
                        var tr = $(['<tr id="upload-' + index + '">'
                            , '<td>' + file.name + '</td>'
                            , '<td>' + (file.size / 1014).toFixed(1) + 'kb</td>'
                            , '<td>正在上传</td>'
                            , '<td>'
                            , '<button class="layui-btn layui-btn-sm uploadbtn demo-reload layui-hide">重传</button>'
                            , '<button class="layui-btn layui-btn-sm delbtn demo-delete">删除</button>'
                            , '</td>'
                            , '</tr>'].join(''));
                        obj.resetFile(index, file, '1/1/1/' + file.name);
                        test = courseId+'/'+$('#jobId').val()+'/2/' + file.name;
                        layui.$.post('/jobItemSubmit', {jobId: $('#jobId').val(), fileName: file.name, userId: 2},function (data) {});
                        //单个重传
                        tr.find('.demo-reload').on('click', function () {
                            obj.upload(index, file);
                        });
                        //删除
                        tr.find('.demo-delete').on('click', function () {
                            delete files[index]; //删除对应的文件
                            tr.remove();
                            uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                        });
                        demoListView.append(tr);
                        $('#testListAction').click();
                    });
                }
                , done: function (res, index, upload) {
                    if (res.error === undefined) { //上传成功
                        var tr = demoListView.find('tr#upload-' + index)
                            , tds = tr.children();
                        tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                        tds.eq(3).html(''); //清空操作
                        $.post('/uploadFileName',{courseId:1,  jobId:1,  studentId:1, filename:this.files[index].name});
                        return delete this.files[index]; //删除文件队列已经上传成功的文件
                    }
                    this.error(index, upload);
                }
                , error: function (index, upload) {
                    var tr = demoListView.find('tr#upload-' + index)
                        , tds = tr.children();
                    tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                    tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
                }
            });
        }
    );
});