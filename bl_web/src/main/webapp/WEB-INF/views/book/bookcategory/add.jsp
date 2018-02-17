<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common/taglib.jsp" %>


<head>
    <title>添加图书类别</title>
    <bl-css>
        <link href="${staticServer }/assets/metronic4.7/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"
              rel="stylesheet" type="text/css"/>
    </bl-css>
</head>

<body>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><i class="fa fa-home"></i> <a
                href="${dynamicServer}/index.htm">首页</a></li>
        <li>>图书管理</li>
        <li>>图书类别维护</li>
        <li>>添加图书类别</li>
    </ul>
</div>

<div class="row  col-md-8 col-md-offset-1">
    <form role="form" class="form-horizontal">
        <div class="tabbable-custom  nav-justified">
            <ul class="nav nav-tabs ">
                <li class="active" id="pt">
                    <a href="#tab_1_1" data-toggle="tab"> 单个类别添加 </a>
                </li>
                <li id="pt1">
                    <a href="#tab_1_2" data-toggle="tab"> 文件添加 </a>
                </li>
                <li class="pull-right m-grid-col m-grid-col-bottom">
                    <a id="completion"> </a>
                </li>
            </ul>
            <input type="hidden" name="tab" value="1" id="tab2">
            <div class="tab-content ">

                <div class="tab-pane fade active in " id="tab_1_1">
                    <div class="row ">
                        <div class="col-md-8">
                            <input type="hidden" name="backUrl" value="${backUrl }">
                            <div class="form-group">
                                <label class="col-md-3 control-label">类别代码：</label>
                                <div class="col-md-9">
                                    <input id="bookCategoryId" name="bookCategoryId" type="text"
                                           class="form-control"
                                           placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">类别名称：</label>
                                <div class="col-md-9">
                                    <input id="bookCategory" name="bookCategory" type="text"
                                           class="form-control "
                                           placeholder="">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade in " id="tab_1_2">
                    <div class="row ">
                        <div class="col-md-8">
                            <input type="hidden" name="backUrl" value="${backUrl }">
                            <div class="form-group">
                                <label class="col-md-3 control-label">文件添加：</label>
                                <div class="col-md-3">
                                    <div class="fileinput fileinput-new" data-provides="fileinput">
                                        <div class="input-group input-large">
                                            <div class="form-control uneditable-input input-fixed input-medium"
                                                 data-trigger="fileinput">
                                                <i class="fa fa-file fileinput-exists"></i>&nbsp;
                                                <span class="fileinput-filename"> </span>
                                            </div>
                                            <span class="input-group-addon btn default btn-file">
                                            <span class="fileinput-new"> 选择文件 </span>
                                            <span class="fileinput-exists"> 修改 </span>
                                            <input type="file" name="bookCategorys" id="bookCategorys"> </span>
                                            <a href="javascript:;" class="input-group-addon btn red fileinput-exists"
                                               data-dismiss="fileinput"> 移除 </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <div class="row">
                <div class="col-md-offset-4 col-md-6">
                    <button id="btnSave" class="btn btn-primary"><i class="fa fa-save"/></i> 提交
                    </button>
                </div>
            </div>
        </div>
    </form>

</div>

</body>
<bl-script>
    <script src="${staticServer }/assets/metronic4.7/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
    <script type="text/javascript">
        $("#btnSave").bind("click", function () {
            console.log("1");
            var url = "";
            var formData = new FormData();
            if (document.getElementById("bookCategorys").files[0] == null) {
                url = "${dynamicServer}/book/books/category/addOne.htm";
                formData.append("bookCategory", document.getElementById("bookCategory").value);
                formData.append("bookCategoryId", document.getElementById("bookCategoryId").value);
            } else {
                url = "${dynamicServer}/book/books/category/addMore.htm";
                formData.append("bookCategorys", document.getElementById("bookCategorys").files[0]);
            }
            $.ajax({
                url: url,
                type: "POST",
                data: formData,
                dataType: 'json',
                //必须false才会自动加上正确的Content-Type
                contentType: false,
                //必须false才会避开jQuery对 formdata 的默认处理 XMLHttpRequest会对 formdata 进行正确的处理
                processData: false,
                success: function (data) {
                    console.log(data);
                    if (!data.success) {
                        bootbox.alert({
                            buttons: {
                                ok: {
                                    label: '确定'
                                }
                            },
                            message: "添加失败"
                        });
                        return;
                    } else {
                        bootbox.alert({
                            buttons: {
                                ok: {
                                    label: '确定'
                                }
                            },
                            message: "添加成功"
                        });
                        return;
                    }
                },
                error: function () {
                    bootbox.alert({
                        buttons: {
                            ok: {
                                label: '确定'
                            }
                        },
                        message: '上传失败'
                    });
                }
            });
        });
    </script>
</bl-script>