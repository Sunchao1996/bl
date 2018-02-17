<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common/taglib.jsp" %>


<head>
    <title>图书列表</title>
</head>

<body>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><i class="fa fa-home"></i> <a
                href="${dynamicServer}/index.htm">首页</a></li>
        <li>>图书管理</li>
        <li>>图书列表</li>
    </ul>
</div>
<div class="portlet box green">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-comments"></i>查询条件
        </div>
    </div>
    <div class="portlet-body">
        <table class="searchTable">
            <tr>
                <input id="backUrl" type="hidden" value="${backUrl}"/>
                <td nowrap="nowrap">标题：</td>
                <td><input type="text" id="bookTitle" class="form-control input-small"
                           placeholder="" value="${vo.bookTitle}"></td>
                <td nowrap="nowrap">作者：</td>
                <td><input type="text" id="bookAuthor" class="form-control input-small"
                           placeholder="" value="${vo.bookAuthor}"></td>
                <td nowrap="nowrap">出版社：</td>
                <td><input type="text" id="bookPress" class="form-control input-small"
                           placeholder="" value="${vo.bookPress}"></td>
                <td nowrap="nowrap">类别：</td>
                <td>
                    <select id="bookCategoryId" class="form-control">
                        <option value="">-----类别选择------</option>
                        <c:forEach items="${bookCategorys}" var="temp">
                            <option value="${temp.bookCategoryId}" ${temp.bookCategoryId == vo.bookCategoryId?'selected':'' } >${temp.bookCategory}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <button class="btn btn-primary" id="btnSearch">
                        <i class="ace-icon fa fa-search"></i> 查询
                    </button>
                </td>
                <td>
                    <button class="btn" id="clearSearchDate">
                        <i class="ace-icon fa"></i>清空日期
                    </button>
                </td>
            </tr>
            <tr>

                <td nowrap="nowrap">出版时间：</td>
                <td><input type="text" id="startTime" class="form-control input-small"
                           placeholder=""
                           value="${vo.startTimeStr}"></td>
                <td nowrap="nowrap">至</td>
                <td><input type="text" id="endTime" class="form-control input-small"
                           value="${vo.endTimeStr}" placeholder=""
                ></td>

            </tr>
        </table>
    </div>
</div>
<div class="row" style="overflow: auto">
    <div class="col-md-12">
        <table class="table  table-striped table-bordered table-hover ">
            <thead>
            <th nowrap="nowrap" width="60">序号</th>
            <th nowrap="nowrap">ISBN</th>
            <th nowrap="nowrap" width="200">标题</th>
            <th nowrap="nowrap" width="220">作者</th>
            <th nowrap="nowrap" width="220">出版社</th>
            <th nowrap="nowrap" width="120">出版时间</th>
            <th nowrap="nowrap">类别</th>
            <th nowrap="nowrap">可借数量</th>
            <th nowrap="nowrap">操作</th>
            </thead>
            <tbody id="booksTBody">
            <c:forEach items="${list}" var="n" varStatus="st">
                <tr>
                    <td nowrap="nowrap">${st.index +1}</td>
                    <td nowrap="nowrap">${n.bookISBN}</td>
                    <td nowrap="nowrap">${n.bookTitle}</td>
                    <td nowrap="nowrap">${n.bookAuthor}</td>
                    <td nowrap="nowrap">${n.bookPress}</td>
                    <td nowrap="nowrap">${n.bookPublish_text}</td>
                    <td nowrap="nowrap">${n.bookCategory_text}</td>
                    <td nowrap="nowrap">${n.bookCount}</td>
                    <td nowrap="nowrap"><a href="javascript:void(0);" onclick="delCode('${n.bookBarCode}');">下架</a>
                        <a href="javascript:void(0);" onclick="updateCode('${n.bookBarCode}');">修改</a><br>
                        <a value="${n.bookBarCode}" href="javascript:void(0);" class="bookDetail">查看明细</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div class="row">
    <div class="col-xs-12">${ pageNavigate.pageModel}</div>
</div>
<div class="modal fade" id="detail" tabindex="-1" role="basic"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
            </div>
            <div id="modalBody" class="modal-body" style="max-height:400px; overflow: auto;">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn " data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


</body>
<bl-script>
    <script type="text/javascript">
        var delCode = function (code) {
            var backUrl = $("#backUrl").val();
            window.location = "${dynamicServer}/book/books/delete.htm?code=" + code + "&backUrl=" + backUrl;
        }
        var updateCode = function(code){
            var backUrl = $("#backUrl").val();
            window.location = "${dynamicServer}/book/books/toUpdate.htm?id="+code+"&backUrl="+backUrl;
        }
        $('#startTime').datetimepicker({
            lang: 'ch',
            timepicker: false,
            format: 'Y-m-d'
        });
        $('#endTime').datetimepicker({
            lang: 'ch',
            timepicker: false,
            format: 'Y-m-d'
        });
        //拼接查询条件；
        var searchHandler = function () {
            var bookTitle = $.trim($("#bookTitle").val());
            var bookAuthor = $.trim($("#bookAuthor").val());
            var bookPress = $.trim($("#bookPress").val());
            var bookCategoryId = $.trim($("#bookCategoryId").val());
            var startTime = $.trim($("#startTime").val());
            var endTime = $.trim($("#endTime").val());
            var url = "";
            if (bookTitle != "") {
                url += "&bookTitle=" + bookTitle;
            }
            if (bookAuthor != '') {
                url += "&bookAuthor=" + bookAuthor;
            }
            if (bookPress != '') {
                url += "&bookPress=" + bookPress;
            }
            if (bookCategoryId != '') {
                url += "&bookCategoryId=" + bookCategoryId;
            }
            if (startTime != '') {
                url += "&startTime=" + startTime;
            }
            if (endTime != '') {
                url += "&endTime=" + endTime;
            }
            window.location = "${dynamicService}/book/books/getByPage.htm?version=1.1" + url;

        }
        $(function () {
            $("#btnSearch").bind("click", searchHandler);
            $("#clearSearchDate").bind("click", function () {
                $('#startTime').val("");
                $('#endTime').val("");
            });


            //显示详情
            $(".bookDetail").bind("click", function () {
                var _this = this;
                var code = $(_this).attr("value");
                $.ajax({
                    url: "${dynamicServer}/book/books/detail.htm",
                    dataType: 'json',
                    data: {
                        id: code
                    },
                    success: function (data) {
                        $('#detail').modal('show');
                        var htmlText = "";
                        htmlText += '<div style="background: #FFFFFF">';
                        htmlText += 'ISBN：' + data.bookISBN;
                        htmlText += '<br />';
                        htmlText += '<br />';
                        htmlText += ' 标题：' + data.bookTitle;
                        htmlText += ' <br />';
                        htmlText += ' <br />';
                        htmlText += ' 作者：' + data.bookAuthor;
                        htmlText += ' <br />';
                        htmlText += ' <br />';
                        htmlText += ' 出版社：' + data.bookPress;
                        htmlText += '  <br />';
                        htmlText += '<br />';
                        htmlText += '出版时间：' + data.bookPublish_text;
                        htmlText += '<br />';
                        htmlText += '<br />';
                        htmlText += '所在的位置：<br>';
                        if (data.bookSeatList == null || $(data.bookSeatList).length == 0) {
                            htmlText += '&nbsp;&nbsp;&nbsp;&nbsp;已下架';
                        }
                        $.each(data.bookSeatList, function (i, v) {
                            htmlText += v.bookSeat.substring(0, 1) + "区，";
                            htmlText += v.bookSeat.substring(1, 2) + "号书架，";
                            htmlText += v.bookSeat.substring(2, 3) + "行，";
                            htmlText += "第" + v.bookSeat.substring(3, -1) + "本<br>";

                        })

                        htmlText += '  <br />';
                        htmlText += '   <br /> ';
                        htmlText += '</div>';
                        $("#modalBody").html(htmlText);
                    }
                })
                ;
            });
        });
    </script>
</bl-script>