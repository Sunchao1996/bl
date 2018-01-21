<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../common/taglib.jsp" %>

<head>
    <title>书架维护</title>
</head>
<bl-css>
    <link rel="stylesheet" href="${staticServer}/assets/cropper3.0/cropper.min.css"/>
    <link rel="stylesheet" href="${staticServer}/assets/cropper3.0/main.css"/>
    <link href="${staticServer }/assets/zTree3.5/css/zTreeStyle/metro.css" rel="stylesheet" type="text/css"/>
    <link href="${staticServer }/assets/metronic4.7/global/css/components.min.css" rel="stylesheet" type="text/css"/>
    <link href="${staticServer }/assets/metronic4.7/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet" type="text/css"/>
</bl-css>
<body class="no-skin">
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><i class="fa fa-home"></i> <a
                href="${dynamicServer}/index.htm">首页</a></li>
        <li>>书架维护</li>
        <li>>初始化书架</li>
    </ul>
</div>
<h1 class="page-title">
    书架维护
    <small>>>初始化书架</small>
</h1>


<div class="row  col-md-10 col-md-offset-1">
    <form id="bookCardForm" name="bookCardForm" class="form-horizontal "
          action="${dynamicServer}/book/bookshelves/add.htm" method="post" enctype="multipart/form-data">

        <div class="tabbable-custom  nav-justified">
            <ul class="nav nav-tabs ">
                <li class="active" id="pt">
                    <a href="#tab_1_1" data-toggle="tab"> 初始化书架 </a>
                </li>
                <li class="pull-right m-grid-col m-grid-col-bottom">
                    <a id="completion"> </a>
                </li>
            </ul>
            <input type="hidden" name="tab" value="1" id="tab2">
            <div class="tab-content ">

                <div class="tab-pane fade active in " id="tab_1_1">
                    <div class="row ">
                        <div class="col-md-7">
                            <input type="hidden" name="backUrl" value="${backUrl }">
                            <div class="form-group">
                                <label class="col-md-3 control-label">请选择文件：</label>
                                <div class="col-md-3">
                                    <div class="fileinput fileinput-new" data-provides="fileinput">
                                        <div class="input-group input-large">
                                            <div class="form-control uneditable-input input-fixed input-medium" data-trigger="fileinput">
                                                <i class="fa fa-file fileinput-exists"></i>&nbsp;
                                                <span class="fileinput-filename"> </span>
                                            </div>
                                            <span class="input-group-addon btn default btn-file">
                                                                    <span class="fileinput-new"> 选择文件 </span>
                                                                    <span class="fileinput-exists"> 修改 </span>
                                                                    <input type="file" name="bookshelves" id="bookshelves"> </span>
                                            <a href="javascript:;" class="input-group-addon btn red fileinput-exists" data-dismiss="fileinput"> 移除 </a>
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
                    <button type="submit" class="btn btn-primary"><i class="fa fa-save"/></i> 开始
                    </button>
                </div>
            </div>
        </div>


    </form>
</div>
</body>
<bl-script>
    <script src="${staticServer }/assets/metronic4.7/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
</bl-script>