<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common/taglib.jsp" %>


<head>
    <title>添加图书</title>
    <bl-css>
    </bl-css>
    <style>
        .star {
            color: #FF0000

        }

    </style>
</head>

<body>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><i class="fa fa-home"></i> <a
                href="${dynamicServer}/index.htm">首页</a></li>
        <li>>图书管理</li>
        <li>>图书维护</li>
        <li>>添加图书</li>
    </ul>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="portlet light bordered">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-book"></i>
                    <span class="caption-subject font-dark sbold uppercase">
                        <c:if test="${addFlag == 1}">添加图书(带<span class="star">*</span>为必填项)</c:if>
                        <c:if test="${updateFlag == 1}">修改图书(带<span class="star">*</span>为必填项)</c:if>
                    </span>
                </div>
            </div>
            <div class="portlet-body">
                <form role="form" id="bookForm" name="bookForm"
                      class="form-horizontal" action="${dynamicServer}/book/books/add.htm" method="post">
                    <input type="hidden" name="backUrl" value="${backUrl }">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">图书类别：<span class="required"
                                                                             aria-required="true"> * </span></label>
                            <div class="col-md-3">
                                <select id="bookCategoryId" name="bookCategoryId" class="form-control input-circle">
                                    <c:forEach items="${bookCategoryList }" var="obj">
                                        <option value="${obj.bookCategoryId }" ${obj.bookCategoryId == book.bookCategoryId?'selected':'' }>${obj.bookCategory }</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label id="bookCategoryIdTip"></label>
                            </div>
                        </div>
                    </div>
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">图书的条形码：<span class="required"
                                                                               aria-required="true"> * </span></label>
                            <div class="col-md-3">
                                <input placeholder="请输入13个字符"
                                       type="text" id="bookBarCode" name="bookBarCode"
                                       class="form-control input-circle"
                                       value="${book.bookBarCode}" ${updateFlag == 1 ?'readonly':''}  />

                            </div>
                            <div class="col-md-6">
                                <label
                                        id="bookBarCodeTip"></label>
                            </div>
                        </div>
                    </div>
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">图书的ISBN码：<span class="required" aria-required="true"> * </span></label>
                            <div class="col-md-3">
                                <input class="form-control input-circle" placeholder="请输入13个字符"
                                       type="text" id="bookISBN" name="bookISBN"
                                       value="${book.bookISBN}" ${updateFlag== 1?'readonly':''} />
                            </div>
                            <div class="col-md-6"><label
                                    id="bookISBNTip"></label>
                            </div>
                        </div>
                    </div>
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">图书的标题：<span class="required"
                                                                              aria-required="true"> * </span></label>
                            <div class="col-md-3">
                                <input class="form-control input-circle"
                                       type="text" id="bookTitle" name="bookTitle" value="${book.bookTitle}"/>
                            </div>
                            <div class="col-md-6"><label
                                    id="bookTitleTip"></label></div>
                        </div>
                    </div>
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">图书的作者：<span class="required"
                                                                              aria-required="true"> * </span></label>
                            <div class="col-md-3">
                                <input class="form-control input-circle"
                                       type="text" id="bookAuthor" name="bookAuthor" value="${book.bookAuthor}"/>
                            </div>
                            <div class="col-md-6"><label
                                    id="bookAuthorTip"></label></div>
                        </div>
                    </div>
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">图书的出版社：<span class="required"
                                                                               aria-required="true"> * </span></label>
                            <div class="col-md-3">
                                <input class="form-control input-circle"
                                       type="text" id="bookPress" name="bookPress" value="${book.bookPress}"/>
                            </div>
                            <div class="col-md-6"><label
                                    id="bookPressTip"></label></div>
                        </div>
                    </div>
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">图书的出版时间：<span class="required"
                                                                                aria-required="true"> * </span></label>
                            <div class="col-md-3">
                                <input value="<fn:formatDate value="${book.bookPublish}" pattern="yyyy-MM-dd"/>" id="bookPublish" name="bookPublish" type="text"
                                       class="form-control input-circle" readonly/>
                            </div>
                            <div class="col-md-6"><label
                                    id="bookPublishTip"></label></div>
                        </div>
                    </div>
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">图书的印刷次数：<span class="required"
                                                                                aria-required="true"> * </span></label>
                            <div class="col-md-3">
                                <input class="form-control input-circle"
                                       type="text" id="bookPrintCount" name="bookPrintCount"
                                       value="${book.bookPrintCount}"/>
                            </div>
                            <div class="col-md-6"><label id="bookPrintCountTip"></label></div>
                        </div>
                    </div>
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">图书的价格：<span class="required"
                                                                              aria-required="true"> * </span></label>
                            <div class="col-md-3">
                                <input value="${book.bookPrice}" class="form-control input-circle"
                                       type="text" id="bookPrice" name="bookPrice"/>

                            </div>
                            <div class="col-md-6"><label
                                    id="bookPriceTip"></label></div>
                        </div>
                    </div>
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">图书的备注：</label>
                            <div class="col-md-3">
                        <textarea class="form-control input-circle"
                                  cols="10" rows="7" id="bookComment"
                                  name="bookComment">${book.bookComment}</textarea><label
                                    id="bookCommentTip"></label>
                            </div>
                        </div>
                    </div>
                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="submit" class="btn green">提交</button>
                                <c:if test="${updateFlag == 1}" >
                                    <button type="button" class="btn default" onclick="history.back(-1)"><i
                                            class="fa fa-undo"/></i>  取消
                                    </button>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="basic" tabindex="-1" role="basic"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true"></button>
                <h4 class="modal-title">选择上级节点</h4>
            </div>
            <div class="modal-body" style="max-height:400px; overflow: auto;">
                <ul id="tree" class="ztree"></ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary"
                        onclick="javascript:getSelected();">确认
                </button>
                <button type="button" class="btn " data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


</body>
<bl-script>
    <script type="text/javascript">
        if ("${updateFlag}" == 1) {
            $("#bookForm").attr("action", "${dynamicServer}/book/books/update.htm");
        }
        $('#bookPublish').datetimepicker({
            lang: 'ch',
            timepicker: false,
            format: 'Y-m-d'
        });
        //全数字验证
        jQuery.validator.addMethod("isNumber", function (value, element) {
            var regex = /^\d+$/;
            return this.optional(element) || regex.test(value);
        }, "请正确格式的全数字字符串");
        $(function () {
            $("#bookForm").validate({
                errorElement: "label",
                errorClass: "valiError",
                errorPlacement: function (error, element) {
                    error.appendTo($("#" + element.attr('id') + "Tip"));
                },
                rules: {
                    bookBarCode: {
                        required: true,
                        minlength: 13,
                        maxlength: 13,
                        isNumber: true
                    },
                    bookISBN: {
                        required: true,
                        minlength: 13,
                        maxlength: 13,
                        isNumber: true
                    },
                    bookPrintCount: {
                        required: true,
                        isNumber: true
                    },
                    bookTitle: {
                        required: true
                    },
                    bookAuthor: {
                        required: true
                    },
                    bookPress: {
                        required: true
                    },
                    bookPublish: {
                        required: true
                    },
                    bookPublish: {
                        required: true
                    }
                },
                messages: {
                    bookBarCode: {
                        required: "这是必填字段",
                        maxlength: $.validator.format("最多 {0} 字符."),
                        minlength: $.validator.format("最少 {0} 字符."),
                        isNumber: "必须全为数字"
                    },
                    bookISBN: {
                        required: "这是必填字段",
                        maxlength: $.validator.format("最多 {0} 字符."),
                        minlength: $.validator.format("最少 {0} 字符."),
                        isNumber: true
                    },
                    bookPrintCount: {
                        required: "这是必填字段",
                        isNumber: "必须全为数字"
                    },
                    bookTitle: {
                        required: "这是必填字段"
                    },
                    bookAuthor: {
                        required: "这是必填字段"
                    },
                    bookPress: {
                        required: "这是必填字段"
                    },
                    bookPublish: {
                        required: "这是必填字段"
                    },
                    bookPublish: {
                        required: "这是必填字段"
                    }
                },
                submitHandler: function (form) {
                    form.submit();
                }
            });
        });
    </script>
</bl-script>