<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common/taglib.jsp" %>


<head>
    <title>图书类别</title>
</head>

<body>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><i class="fa fa-home"></i> <a
                href="${dynamicServer}/index.htm">首页</a></li>
        <li>>图书类别</li>
        <li>>类别查看</li>
    </ul>
</div>
<div class="row" style="overflow: auto">
    <div class="col-md-12">
        <table class="table  table-striped table-bordered table-hover ">
            <thead>
                <th nowrap="nowrap" width="60">序号</th>
                <th nowrap="nowrap" width="60">类别号</th>
                <th nowrap="nowrap" width="60">类别名称</th>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="temp" varStatus="st">
                    <tr>
                        <td>${st.index+1}</td>
                        <td>${temp.bookCategoryId}</td>
                        <td>${temp.bookCategory}</td>
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
        //查询按钮事件
        var searchHandler = function () {
            window.location = "${dynamicService}/book/books/getByPage.htm?version=1.1" + url;

        }
        $(function () {
            $("#btnSearch").bind("click", searchHandler);
        });
    </script>
</bl-script>