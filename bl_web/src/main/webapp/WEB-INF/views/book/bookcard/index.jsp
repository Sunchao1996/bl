<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common/taglib.jsp" %>


<head>
    <title>图书证信息</title>
</head>

<body>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><i class="fa fa-home"></i> <a
                href="${dynamicServer}/index.htm">首页</a></li>
        <li>>图书证信息</li>
        <li>>图书证管理</li>
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
                    <input type="text" id="bookCardId" class="form-control input-small" value="${vo.bookCardId}">
                </td>
                <td nowrap="nowrap">开通时间：</td>
                <td>
                    <input type="text" id="startTime" class="dateUnit form-control input-small"
                           value="${vo.startTime}">
                </td>
                <td nowrap="nowrap">至</td>
                <td>
                    <input type="text" id="endTime" class="dateUnit form-control input-small"
                           value="${vo.endTime}">
                </td>

            </tr>
            <tr>
                <td nowrap="nowrap">状态：</td>
                <td><input type="checkbox" class="" id="cardStatusId1" name="cardStatusId"
                           value="2" ${(vo.cardStatusId==-1 || vo.cardStatusId == 0)?'checked':''}>禁用
                    <input type="checkbox" class="" id="cardStatusId2" name="cardStatusId"
                           value="1"  ${(vo.cardStatusId==-1 || vo.cardStatusId == 1)?'checked':''}>启用
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
                <th width="12%">编号</th>
                <th width="11%">注册时间</th>
                <th width="12%">状态</th>
                <th width="11%">注销时间</th>
                <th width="11%">操作</th>
            </tr>
            </thead>
            <tbody id="booksTBody">
            <c:forEach items="${list}" var="n" varStatus="st">
                <tr>
                    <td>${st.index+1}</td>
                    <td>${n.bookCardId}</td>
                    <td>${n.regTime_text}</td>
                    <td>${n.status_text}</td>
                    <td>${n.endTime_text}</td>
                    <td>
                        <c:if test="${n.cardStatusId == 1}">
                            <a  onclick="deleteCard('${n.bookCardId}');" href="javascript:void(0);">禁用</a>
                        </c:if>
                        <c:if test="${n.cardStatusId == 0}">
                            无操作
                        </c:if>
                    </td>
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
        var deleteCard = function(id){
            window.location = "${dynamicServer}/book/bookcard/delete.htm?id="+id+"&backUrl="+$("#backUrl").val();
        }

        $('.dateUnit').datetimepicker({
            lang: 'ch',
            timepicker: false,
            format: 'Y-m-d'
        });
        //拼接查询条件；
        var searchHandler = function () {
            var bookCardId = $.trim($("#bookCardId").val());
            var startTime = $.trim($("#startTime").val());
            var endTime = $.trim($("#endTime").val());
            var cardStatusId1 = $("#cardStatusId1").prop("checked");
            var cardStatusId2 = $("#cardStatusId2").prop("checked");
            console.log(cardStatusId1)
            console.log(cardStatusId2)
            var url = "";
            if (bookCardId != "") {
                url += "&bookCardId=" + bookCardId;
            }
            if (startTime != '') {
                url += "&startTime=" + startTime;
            }
            if (endTime != '') {
                url += "&endTime=" + endTime;
            }

            if (cardStatusId1 && cardStatusId2) {
                url += "&cardStatusId=-1";
            } else if (cardStatusId1) {
                url += "&cardStatusId=0";
            } else if (cardStatusId2) {
                url += "&cardStatusId=1";
            }
            window.location = "${dynamicServer}/book/bookcard/index.htm?" + url;
        }
        $(function () {
            $("#btnSearch").bind("click", searchHandler);
            $("#clearSearchDate").bind("click", function () {
                $('.dateUnit').val("");
            });
        });
    </script>
</bl-script>