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
                <td>标题：</td>
                <td><input type="text" id="bookTitle" class="form-control input-small"
                           placeholder=""
                           value="${sysUserSearchVO.username }"></td>
                <td>作者：</td>
                <td><input type="text" id="bookAuthor" class="form-control input-small"
                           placeholder=""
                           value="${sysUserSearchVO.username }"></td>
                <td>出版社：</td>
                <td><input type="text" id="bookPress" class="form-control input-small"
                           placeholder=""
                           value="${sysUserSearchVO.username }"></td>
                <td>类别：</td>
                <td><input type="text" id="bookCategoryId" class="form-control input-small"
                           placeholder=""
                           value="${sysUserSearchVO.username }"></td>
                <td>
                    <button class="btn btn-primary" id="btnSearch">
                        <i class="ace-icon fa fa-search"></i> 查询
                    </button>
                </td>
            </tr>
            <tr>

                <td>出版时间：</td>
                <td><input type="text" id="startTime" class="form-control input-small"
                           placeholder=""
                           value="${sysUserSearchVO.username }"></td>
                <td>至</td>
                <td><input type="text" id="endTime" class="form-control input-small"
                           placeholder=""
                           value="${sysUserSearchVO.username }"></td>

            </tr>
        </table>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td align="center">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <th>序号</th>
                        <th>ISBN</th>
                        <th>标题</th>
                        <th>作者</th>
                        <th>出版社</th>
                        <th>出版时间</th>
                        <th>类别</th>
                        <th>可借数量</th>
                        <th>操作</th>
                        </thead>
                        <tbody id="booksTBody">
                        </tbody>
                    </table>
                </td>
            </tr>
        </table>
    </div>
</div>


<div class="modal fade" id="basic" tabindex="-1" role="basic"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
            </div>
            <div class="modal-body" style="max-height:400px; overflow: auto;">
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
    </script>
</bl-script>