<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../../common/taglib.jsp" %>
<head>
    <title>资源管理</title>
    <bl-css>
        <link href="${staticServer}/assets/treetable/treeTable.min.css?version=${versionNo}" rel="stylesheet"
              type="text/css"/>
        <link href="${staticServer}/assets/metronic4.7/global/plugins/bootstrap-table/bootstrap-table.min.css"
              rel="stylesheet"
              type="text/css"/>

    </bl-css>
    <style>
        .container {
            border: 1px #ccc solid;
            width: 90%;
            margin: 100px auto;
        }

        .form-table {
            width: 100%;
            border-collapse: collapse;
            margin: 0 auto;
            text-align: center;
            table-layout: fixed;
        }

        .form-table th {
            border-left: none;
            border-top: none;
            border-right: 1px #ccc solid;
            border-bottom: 1px #ccc solid;
            background: #F3F3F3;
        }

        .form-table td {
            border-left: none;
            border-top: none;
            border-bottom: 1px #ccc solid;
            border-right: 1px #ccc solid;
        }

        .fixed-thead,
        .fixed-tfoot {
            display: block;
            /*width: 99%;*/
            width: -webkit-calc(100% - 17px);
            width: -moz-calc()(100% -17px);
            width: calc(100% - 17px);
            height: 50px;
        }

        .fixed-tfoot tr td {
            background: #F3F3F3;
            border-bottom: none;
        }

        .fixed-thead tr,
        .fixed-tfoot tr {
            width: 100%;
            height: 50px;
            line-height: 50px;
            display: block;
        }

        .fixed-thead tr th,
        .fixed-tfoot tr td {
            float: left;
            display: block;
            width: 25%;
            height: 50px;
            font-size: 16px;
            text-align: center;
        }

        .scroll-tbody {
            display: block;
            height: 306px;
            overflow: auto;
            width: 100%;
        }

        .scroll-tbody tr {
            width: 100%;
            display: block;
            height: 40px;
            line-height: 40px;
        }

        .scroll-tbody tr td {
            float: left;
            display: block;
            width: 25%;
        }

        .form-table .scroll-tbody tr:nth-child(odd) td {
            background-color: #ECE9D8;
        }
    </style>
</head>

<body>
<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i> <a href="${dynamicServer}/index.htm">首页</a>
        </li>
        <li>
            >系统管理
        </li>
        <li>
            >资源管理
        </li>
    </ul>
</div>
<div id="my_div" class="container" style="width: 1091px;height: 100px;overflow: auto">
    <table id="demoTable" class="form-table" style="width: 2900px">
        <tr class="fixed-thead">
            <td style="width: 100px" class="">test0</td>
            <td style="width: 100px">$0</td>
            <td style="width: 100px">c10</td>
            <td style="width: 100px">c20</td>
            <td style="width: 100px">c30</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c41</td>
        </tr>
        <tr>
            <td style="width: 100px" class="float_header">test0</td>
            <td style="width: 100px">$0</td>
            <td style="width: 100px">c10</td>
            <td style="width: 100px">c20</td>
            <td style="width: 100px">c30</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c41</td>
        </tr>
        <tr>
            <td style="width: 100px" class="float_header">test0</td>
            <td style="width: 100px">$0</td>
            <td style="width: 100px">c10</td>
            <td style="width: 100px">c20</td>
            <td style="width: 100px">c30</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c41</td>
        </tr>
        <tr>
            <td style="width: 100px" class="float_header">test0</td>
            <td style="width: 100px">$0</td>
            <td style="width: 100px">c10</td>
            <td style="width: 100px">c20</td>
            <td style="width: 100px">c30</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c41</td>
        </tr>
        <tr>
            <td style="width: 100px" class="float_header">test0</td>
            <td style="width: 100px">$0</td>
            <td style="width: 100px">c10</td>
            <td style="width: 100px">c20</td>
            <td style="width: 100px">c30</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c41</td>
        </tr>
        <tr>
            <td style="width: 100px" class="float_header">test0</td>
            <td style="width: 100px">$0</td>
            <td style="width: 100px">c10</td>
            <td style="width: 100px">c20</td>
            <td style="width: 100px">c30</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c40</td>
            <td style="width: 100px">c41</td>
        </tr>
    </table>
</div>
</body>
<bl-script>
    <script src="${staticServer}/assets/metronic4.7/global/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${staticServer}/assets/metronic4.7/pages/scripts/table-bootstrap.min.js"></script>
    <script src="${staticServer}/assets/supertable/js/superTables.js"></script>
    <script type="text/javascript">
    </script>
</bl-script>