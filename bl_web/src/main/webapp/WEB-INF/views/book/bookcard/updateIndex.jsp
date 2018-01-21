<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="../../common/taglib.jsp" %>

<head>
    <title>图书卡维护</title>
</head>
<bl-css>
    <link rel="stylesheet" href="${staticServer}/assets/cropper3.0/cropper.min.css"/>
    <link rel="stylesheet" href="${staticServer}/assets/cropper3.0/main.css"/>
    <link href="${staticServer }/assets/zTree3.5/css/zTreeStyle/metro.css" rel="stylesheet" type="text/css"/>

</bl-css>
<body class="no-skin">
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><i class="fa fa-home"></i> <a
                href="${dynamicServer}/index.htm">首页</a></li>
        <li>>图书卡维护</li>
        <li>>图书卡挂失</li>
    </ul>
</div>
<h1 class="page-title">
    图书卡维护
    <small>>>图书卡挂失</small>
</h1>


<div class="row  col-md-10 col-md-offset-1">
    <form id="bookCardForm" name="bookCardForm" class="form-horizontal "
          action="${dynamicServer}/book/bookcard/update.htm" method="post">

        <div class="tabbable-custom  nav-justified">
            <ul class="nav nav-tabs ">
                <li class="active" id="pt">
                    <a href="#tab_1_1" data-toggle="tab"> 在线挂失图书卡 </a>
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
                                <label class="col-md-3 control-label">身份证号：</label>
                                <div class="col-md-9">
                                    <input id="userCard" name="userCard" type="text"
                                           class="form-control input-inline input-medium"
                                           placeholder=""> <label
                                        id="userCardTip"></label>
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
                    <button type="submit" class="btn btn-primary"><i class="fa fa-save"/></i> 挂失
                    </button>
                </div>
            </div>
        </div>


    </form>
</div>
</body>

<bl-script>

    <script type="text/javascript">
        //身份证号验证
        jQuery.validator.addMethod("isIdCardNo", function (value, element) {
            var regex = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
            return this.optional(element) || regex.test(value);
        }, "请正确输入您的身份证号码");
        $(document).ready(function () {
            $("#bookCardForm").validate({
                errorElement: "label",
                errorClass: "valiError",
                errorPlacement: function (error, element) {
                    error.appendTo($("#" + element.attr('id') + "Tip"));
                },
                rules: {
                    userCard: {
                        isIdCardNo: true
                    },
                },
                messages: {},
                submitHandler: function (form) {
                    form.submit();
                }
            });

        });
    </script>
</bl-script>