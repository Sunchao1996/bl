<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common/taglib.jsp" %>


<head>
    <title>借阅信息</title>
</head>

<body>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><i class="fa fa-home"></i> <a
                href="${dynamicServer}/index.htm">首页</a></li>
        <li>>图书借阅</li>
        <li>>综合查询</li>
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
                <td nowrap="nowrap">卡号：</td>
                <td>
                    <input type="text" id="readerId" class="form-control input-small" value="${vo.readerId}">
                </td>
                <td nowrap="nowrap">借书时间：</td>
                <td>
                    <input type="text" id="startBorrowTime" class="dateUnit form-control input-small"
                           value="${vo.startBorrowTimeStr}">
                </td>
                <td nowrap="nowrap">至</td>
                <td>
                    <input type="text" id="endBorrowTime" class="dateUnit form-control input-small"
                           value="${vo.endBorrowTimeStr}">
                </td>
                <td nowrap="nowrap">归还时间：</td>
                <td>
                    <input type="text" id="startReturnTime" class="dateUnit form-control input-small"
                           value="${vo.startReturnTimeStr}">
                </td>
                <td nowrap="nowrap">至</td>
                <td>
                    <input type="text" id="endReturnTime" class="dateUnit form-control input-small"
                           value="${vo.endReturnTimeStr}">
                </td>

            </tr>
            <tr>
                <td nowrap="nowrap">类别：</td>
                <td>
                    <select id="bookCategoryId" class="form-control">
                        <option value="">-----类别选择------</option>
                        <c:forEach items="${bookCategoryList}" var="temp">
                            <option value="${temp.bookCategoryId}" ${temp.bookCategoryId == vo.bookCategoryId?'selected':'' } >${temp.bookCategory}</option>
                        </c:forEach>
                    </select>
                </td>

                <td nowrap="nowrap">应还时间：</td>
                <td>
                    <input type="text" id="startBackTime" class="dateUnit form-control input-small"
                           value="${vo.startBackTimeStr}">
                </td>
                <td nowrap="nowrap">至</td>
                <td>
                    <input type="text" id="endBackTime" class="dateUnit form-control input-small"
                           value="${vo.endBackTimeStr}">
                </td>
                <td nowrap="nowrap">状态：</td>
                <td><input type="checkbox" class="" id="status1" name="status" value="1" ${vo.borrowStatus==-1 || vo.borrowStatus == 1?'checked':''}>已还
                    <input type="checkbox" class="" id="status2" name="status" value="0"  ${vo.borrowStatus==-1 || vo.borrowStatus == 0?'checked':''}>未还
                </td>
                <td></td>
                <td>
                    <button class="btn btn-primary" id="btnSearch">
                        <i class="ace-icon fa fa-search"></i> 查询
                    </button>
                    <button class="btn" id="clearSearchDate">
                        <i class="ace-icon fa"></i>清空
                    </button>
                </td>
            </tr>
        </table>
    </div>
</div>
<div class="row" style="overflow: auto">
    <div class="col-md-12">
        <table class="table  table-striped table-bordered table-hover ">
            <thead>
                <tr>
                    <th width="7%">序号</th>
                    <th width="12%">位置</th>
                    <th width="11%">ISBN</th>
                    <th width="11%">标题</th>
                    <th width="11%">借书时间</th>
                    <th width="11%">应还时间</th>
                    <th width="11%">今天时间</th>
                </tr>
            </thead>
            <tbody id="booksTBody">
            <c:forEach items="${list}" var="n" varStatus="st">
                <tr>
                    <td>${st.index+1}</td>
                    <td>${n.bookSeat}</td>
                    <td>${n.bookISBN}</td>
                    <td>${n.bookTitle}</td>
                    <td>${n.borrowTime_text}</td>
                    <td>${n.backTime_text}</td>
                    <td>${n.dateNow}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div class="row">
    <div class="col-xs-12">${ pageNavigate.pageModel}</div>
</div>
</body>
<bl-script>
    <script type="text/javascript">
        $('.dateUnit').datetimepicker({
            lang: 'ch',
            timepicker: false,
            format: 'Y-m-d'
        });
        //拼接查询条件；
        var searchHandler = function () {
            var readerId = $.trim($("#readerId").val());
            var startBorrowTime = $.trim($("#startBorrowTime").val());
            var endBorrowTime = $.trim($("#endBorrowTime").val());
            var startBackTime = $.trim($("#startBackTime").val());
            var endBackTime = $.trim($("#endBackTime").val());
            var startReturnTime = $.trim($("#startReturnTime").val());
            var endReturnTime = $.trim($("#endReturnTime").val());
            var bookCategoryId = $.trim($("#bookCategoryId").val());
            var status1 = $("#status1").prop("checked");
            var status2 = $("#status2").prop("checked");
            var url = "";
            if (readerId != "") {
                url += "&readerId=" + readerId;
            }
            if (startBorrowTime != '') {
                url += "&startBorrowTime=" + startBorrowTime;
            }
            if (bookCategoryId != '') {
                url += "&bookCategoryId=" + bookCategoryId;
            }
            if (endBorrowTime != '') {
                url += "&endBorrowTime=" + endBorrowTime;
            }
            if (startBackTime != '') {
                url += "&startBackTime=" + startBackTime;
            }
            if (endBackTime != '') {
                url += "&endBackTime=" + endBackTime;
            }
            if (startReturnTime != '') {
                url += "&startReturnTime=" + startReturnTime;
            }
            if (endReturnTime != '') {
                url += "&endReturnTime=" + endReturnTime;
            }
            if (status1) {
                url += "&borrowStatus=1";
            }
            if(status2){
                url += "&borrowStatus=0";
            }
            if(status1 && status2){
                url += "&borrowStatus=-1";
            }
            window.location="${dynamicServer}/book/borrowinfo/queryByPage.htm?"+url;
        }
        $(function () {
            $("#btnSearch").bind("click", searchHandler);
            $("#clearSearchDate").bind("click", function () {
                $('.dateUnit').val("");
            });
        });
    </script>
</bl-script>