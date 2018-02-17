<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common/taglib.jsp" %>


<head>
    <title>登记</title>
</head>

<body>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><i class="fa fa-home"></i> <a
                href="${dynamicServer}/index.htm">首页</a></li>
        <li>>图书登记</li>
        <li>>借书登记</li>
    </ul>
</div>
<div class="portlet box green">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-comments"></i>填写信息
        </div>
    </div>
    <div class="portlet-body">
        <table class="searchTable">
            <tr>
                <input id="backUrl" type="hidden" value="${backUrl}"/>
                <td nowrap="nowrap">图书卡卡号：</td>
                <td>
                    <input type="text" id="bookCardId" class="form-control input-small" >
                </td>
                <td nowrap="nowrap">区号：</td>
                <td>
                    <select id="bookCategoryId" name="bookCategoryId" class="form-control">
                    </select>
                </td>
                <td>位置</td>
                <td>
                    <select id="bookSeat" name="bookSeat" class="form-control">
                    </select>
                </td>
                <td>
                    <button class="btn btn-primary" onclick="add();">
                        <i class="ace-icon fa"></i> 增加记录
                    </button>
                </td>
                <td>
                    <button class="btn"  onclick="register();">
                        <i class="ace-icon fa"></i>登记
                    </button>
                </td>
                <td>
                    <button class="btn"  onclick="remove();">
                        <i class="ace-icon fa"></i>移除
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
            <th width="8%"><input type="checkbox" id="checkOrCancelAll"/></th>
            <th width="7%">序号</th>
            <th width="12%">图书卡卡号</th>
            <th width="11%">图书位置</th>
            <th width="12%">图书条形码</th>
            <th width="11%">图书名称</th>
            </thead>
            <tbody id="userInfoTBody">
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
        //页面加载完成之后
        $(function(){
            //二级联动
            $.ajax({
                url:"${dynamicServer}/book/books/showCategory.htm",
                type:"get",
                dataType:'json',
                success:function(jsonObject){
                    var htmlString = "";
                    $.each(jsonObject,function(i,n){

                        htmlString += '<option value="'+n.bookCategoryId+'">'+n.bookCategoryId+'</option>';
                    });
                    $("#bookCategoryId").append(htmlString);
                    $.ajax({
                        url:"${dynamicServer}/book/books/getSeatById.htm?categoryId="+$("#bookCategoryId").val(),
                        type:"get",
                        dataType:'json',
                        success:function(jsonObject){
                            var htmlString = "";
                            $.each(jsonObject,function(i,n){
                                htmlString += '<option value="'+n.bookSeat+'">'+n.bookSeat+'</option>';
                            });
                            $("#bookSeat").append(htmlString);
                        }
                    });
                }
            });
            $("#bookCategoryId").bind("change",function(){
                $("#bookSeat").html("");
                $.ajax({
                    url:"${dynamicServer}/book/books/getSeatById.htm?categoryId="+$(this).val(),
                    type:"get",
                    dataType:'json',
                    success:function(jsonObject){
                        var htmlString = "";
                        $.each(jsonObject,function(i,n){
                            htmlString += '<option value="'+n.bookSeat+'">'+n.bookSeat+'</option>';
                        });
                        $("#bookSeat").append(htmlString);
                    }
                });
            });


            $("#checkOrCancelAll").click(function(){
                $(":checkbox[name='id']").prop("checked",this.checked);
            });
        });
        var arr = [];
        var number = 1;
        //向表格中添加记录
        function add(){
            $.ajaxSetup({
                async:false
            });
            if(arr.length >= 5){
               bootbox.alert("每人每次最多借阅5本");
                return false;
            }
            var falg = false;//是否有为归还图书标记

            //查询是否有未还图书
            $.ajax({
                url:"${dynamicServer}/book/back/checkBorrowInfo.htm",
                type:"get",
                cache:false,
                dataType:'json',
                data:{
                    "pageNo":1,
                    "pageSize":2,
                    "readerId":$("#bookCardId").val(),
                    "borrowStatus":0
                },
                success:function(jsonObject){
                    if(jsonObject*1>0){
                        bootbox.alert("此读者有未还图书，不可借阅");
                        falg = true;
                    }
                }
            });
            if(falg){
                return false;
            }
            if($.trim($("#bookCardId").val()) == ""){
                bootbox.alert("填写卡号...");
            }else{
                var id=$.trim($("#bookCardId").val())+"_"+$("#bookSeat").val();

                $.ajax({
                    url:"${pageContext.request.contextPath}/book/books/queryBookBySeat.htm?seat="+$("#bookSeat").val(),
                    type:"get",
                    dataType:'json',
                    success:function(bookInfo){
                        var htmlString = "";
                        id += "_"+bookInfo.bookBarCode;
                        htmlString +='<tr class="odd">';
                        htmlString +='<td><input type="checkbox" name="id" value="'+id+'" onclick="controlFirstCheckbox();"/></td>';
                        htmlString +='<td>'+(number)+'</td>';
                        htmlString +='<td>'+$.trim($("#bookCardId").val())+'</td>';
                        htmlString +='<td>'+$("#bookSeat").val()+'</td>';
                        htmlString +='<td>'+bookInfo.bookBarCode+'</td>';
                        htmlString +='<td>'+bookInfo.bookTitle+'</td>';
                        htmlString +='</tr>';
                        var falg = true;
                        for(var i=0;i<arr.length;i++){
                            if(id == arr[i]){
                                falg = false;
                            }
                        }
                        if(falg){
                            $("#userInfoTBody").append(htmlString);
                            bootbox.alert("添加成功");
                            arr.push(id);
                        }else{
                            bootbox.alert("添加失败，同一人不能借阅相同书籍两本或两本以上");
                        }
                        $.ajaxSetup({
                            async:true
                        });
                    }
                });

            }
        }

        function controlFirstCheckbox(){
            $("#checkOrCancelAll").prop("checked",$(":checkbox[name='id']").length==$(":checkbox[name='id']:checked").length);
        }
        //登记
        function register(){
            var checkedElts = $(":checkbox[name='id']:checked");
            if(checkedElts.length==0){
                bootbox.alert("请选择至少一条登记记录");
            }else{
                if(confirm("您确定要对选中的记录进行添加吗?")){
                    var sendData = "ids=";
                    sendData += arr.join("&ids=");
                    $.ajax({
                        url:"${dynamicServer}/book/bookloan/register.htm",
                        type:"post",
                        dataType:'json',
                        data:sendData,
                        success:function(jsonObject){
                            if(jsonObject.success){
                                bootbox.alert("登记成功");

                                //重新加载页面 displayData(0);
                                //window.location.reload();
                                $("#userInfoTBody").html("");
                                bootbox.alert("还书时间:"+jsonObject.afterTime);
                            }else{
                                if(jsonObject.error){
                                    bootbox.alert("读者信息错误");
                                }else{
                                    bootbox.alert("登记失败");
                                }
                            }
                            $("#checkOrCancelAll").click();
                        }
                    });
                }
            }
        }
        //移除添加的记录
        function remove(){
            var checkedElts = $(":checkbox[name='id']:checked");
            if(checkedElts.length!=1){
               bootbox.alert("请选择至少一条记录");
            }else{
                var falg = false;
                for(var i=0;i<arr.length;i++){
                    if(checkedElts.val() == arr[i]){
                        falg = true;
                        arr.splice(i,1);
                    }
                }
                if(falg){
                    checkedElts.parent().parent().remove();
                    bootbox.alert("移除成功");
                    $("#checkOrCancelAll").prop("checked",false);
                }else{
                    bootbox.alert(arr);
                }
            }
        }
    </script>
</bl-script>