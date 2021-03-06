<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isErrorPage="true" %>
<%@ include file="../common/taglib.jsp" %>
<%
    // 本页面是在客户查找的页面无法找到的情况下调用的
    response.setStatus(HttpServletResponse.SC_OK);
%>
<critc-css>
    <link href="${staticServer}/assets/metronic4.7/pages/css/error.min.css"
          rel="stylesheet" type="text/css" />
</critc-css>
<head>
    <title>500错误</title>
</head>

<body>
    <div class="page-bar">
        <ul class="page-breadcrumb">
            <li><i class="fa fa-home"></i> <a
                    href="${dynamicServer}/index.htm">首页</a></li>
            <li>>500错误</li>
        </ul>
    </div>

    <div class="alert alert-danger"
         style="text-align: center; margin-top: 10px;">
        <div class="row">
            <div class="col-md-12 page-500">
                <div class="number font-red">500</div>
                <div class="details">
                    <h3>哎呀!出问题了。</h3>
                    <p>${message}</p>
                    <p>
                        请联系我们修复！稍等片刻。<br />
                    </p>
                </div>
            </div>
        </div>
        <a href="javascript:void(0)" onclick='history.back(-1);'>返回</a>
    </div>
</body>
</html>
