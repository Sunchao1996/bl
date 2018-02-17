<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common/taglib.jsp" %>


<head>
    <title>登记管理</title>
</head>

<body>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><i class="fa fa-home"></i> <a
                href="${dynamicServer}/index.htm">首页</a></li>
        <li>>登记管理</li>
        <li>>还书登记</li>
    </ul>
</div>
<div class="portlet box green">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-comments"></i>信息填写
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
                <td>
                    <button class="btn btn-primary" id="btnSearch">
                        <i class="ace-icon fa fa-search"></i> 查询
                    </button>
                </td>
                <td>
                    <button class="btn" onclick="back();">
                        <i class="ace-icon fa "></i> 归还
                    </button>
                </td>
                <td>
                    <button class="btn " onclick="lose();">
                        <i class="ace-icon fa "></i> 丢失
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
                <th width="8%"><input type="checkbox"
                                      id="checkOrCancelAll"/></th>
                <th width="7%">序号</th>
                <th width="12%">位置</th>
                <th width="11%">ISBN</th>
                <th width="11%">标题</th>
                <th width="11%">借书时间</th>
                <th width="11%">应还时间</th>
                <th width="11%">今天时间</th>
                <th width="11%">逾期罚款</th>
                <th width="11%">丢失罚款</th>
            </tr>
            </thead>
            <tbody id="booksTBody">
            <c:forEach items="${list}" var="n" varStatus="st">
                <tr>
                    <td><input type="checkbox" name="id" value="${n.borrowId}" onclick="controlFirstCheckbox();"/>
                    </td>
                    <td>${st.index+1}</td>
                    <td>${n.bookSeat}</td>
                    <td>${n.bookISBN}</td>
                    <td>${n.bookTitle}</td>
                    <td>${n.borrowTime_text}</td>
                    <td>${n.backTime_text}</td>
                    <td>${n.dateNow}</td>
                    <td>${n.priceMore}</td>
                    <td>${n.bookPrice *2}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
<bl-script>
    <script type="text/javascript">
        $("#checkOrCancelAll").click(function () {
            $(":checkbox[name='id']").prop("checked", this.checked);
        });
        function controlFirstCheckbox() {
            $("#checkOrCancelAll").prop("checked", $(":checkbox[name='id']").length == $(":checkbox[name='id']:checked").length);
        }
        //拼接查询条件；
        var searchHandler = function () {
            var readerId = $.trim($("#readerId").val());
            var url = "";
            if (readerId != "") {
                url += "&readerId=" + readerId;
            }
            if(readerId == ""){
                bootbox.alert("请填写图书卡卡号！");
                return ;
            }
            window.location = "${dynamicServer}/book/back/queryByPage.htm?flag=1&borrowStatus=0&" + url;
        }
        $(function () {
            $("#btnSearch").bind("click", searchHandler);
        });


        //归还图书
        function back() {
            var checkedElts = $(":checkbox[name='id']:checked");
            if (checkedElts.length == 0) {
                bootbox.alert("请选择要归还的记录");
            } else {
                if (confirm("您确定要对选中的图书归还吗?")) {
                    var sendData = "ids=";
                    var idArray = [];
                    $.each(checkedElts, function (i, n) {
                        idArray.push(n.value);
                    });
                    sendData += idArray.join("&ids=");
                    $.ajax({
                        url: "${pageContext.request.contextPath}/book/back/back.htm",
                        type: "post",
                        data: sendData,
                        dataType:'json',
                        success: function (jsonObject) {
                            if (jsonObject.success) {
                                bootbox.alert("归还成功");
                                //重新加载页面 displayData(0);
                                window.location.reload();
                            } else {
                                bootbox.alert("归还失败");
                            }
                        }
                    });
                }
            }
        }
        //丢失图书
        function lose() {
            var checkedElts = $(":checkbox[name='id']:checked");
            if (checkedElts.length == 0) {
                bootbox.alert("请选择要登记丢失的记录");
            } else {
                if (confirm("是否已经缴清罚款?")) {
                    //拼接要删除的角色id 方式2
                    var sendData = "ids=";
                    var idArray = [];
                    $.each(checkedElts, function (i, n) {
                        idArray.push(n.value);
                    });
                    sendData += idArray.join("&ids=");
                    $.ajax({
                        url: "${pageContext.request.contextPath}/book/back/lose.htm",
                        type: "post",
                        data: sendData,
                        dataType:'json',
                        success: function (jsonObject) {
                            if (jsonObject.success) {
                                bootbox.alert("登记成功");
                                //重新加载页面 displayData(0);
                                window.location.reload();
                            } else {
                                bootbox.alert("登记失败");
                            }
                        }
                    });
                }
            }
        }
    </script>
</bl-script>