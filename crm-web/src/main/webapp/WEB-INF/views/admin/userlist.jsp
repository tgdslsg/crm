<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>SUS员工管理</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/plugins/fontawesome/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="/static/plugins/datatables/css/dataTables.bootstrap.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../include/mainHeader.jsp" %>
    <%@include file="../include/leftSide.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                员工管理
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">员工列表</h3>
                    <div class="box-tools pull-right">
                        <button id="newBtn" type="button" class="btn btn-xs btn-success"><i class="fa fa-plus"></i> 新增
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="userTable">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>账号</th>
                            <th>员工姓名</th>
                            <th>微信号</th>
                            <th>角色</th>
                            <th>状态</th>
                            <th>创建时间</th>
                            <th>#</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->


<!-- Modal -->
<div class="modal fade" id="newModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增用户</h4>
            </div>
            <div class="modal-body">
                <form id="newForm">
                    <div class="form-group">
                        <label>账号(请用字母或数字组合,字数不得少于6字节且不得多余10字节)</label>
                        <input type="text" class="form-control" name="userName">
                    </div>
                    <div class="form-group">
                        <label>员工姓名(真实姓名)</label>
                        <input type="text" class="form-control" name="realName">
                    </div>
                    <div class="form-group">
                        <label>密码(默认 000000,请用字母或数字组合,字数不得少于6字节且不得多余18字节)</label>
                        <input type="password" class="form-control" id="password" name="password" value="000000">
                    </div>
                    <div class="form-group">
                        <label>请确认密码(默认 000000,请用字母或数字组合,字数不得少于6字节且不得多余18字节)</label>
                        <input type="password" class="form-control" name="repassword" value="000000">
                    </div>
                    <div class="form-group">
                        <label>微信号</label>
                        <input type="text" class="form-control" name="weixin">
                    </div>
                    <div class="form-group">
                        <label>角色</label>
                        <select class="form-control" name="roleid">
                            <c:forEach items="${roles}" var="role">
                                <h1>${role.id}</h1>
                                <option value="${role.id}">${role.roleName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="saveNew" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="editModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑用户</h4>
            </div>
            <div class="modal-body">
                <form id="editForm">
                    <input type="hidden" name="id" id="edit_user_id">
                    <div class="form-group">
                        <label>账号(请用字母或数字组合,字数不得少于6字节且不得多余10字节)</label>
                        <input type="text" class="form-control" name="userName" id="edit_user_username">
                    </div>
                    <div class="form-group">
                        <label>员工姓名(真实姓名)</label>
                        <input type="text" class="form-control" name="realName" id="edit_user_realname">
                    </div>
                    <div class="form-group">
                        <label>密码(默认 000000,请用字母或数字组合,字数不得少于6字节且不得多余18字节)</label>
                        <input type="password" class="form-control" id="editpassword" name="password" value="000000">
                    </div>
                    <div class="form-group">
                        <label>请确认密码(默认 000000,请用字母或数字组合,字数不得少于6字节且不得多余18字节)</label>
                        <input type="password" class="form-control" name="repassword" value="000000">
                    </div>
                    <div class="form-group">
                        <label>微信号</label>
                        <input type="text" class="form-control" name="weixin" id="edit_user_weixin">
                    </div>
                    <div class="form-group">
                        <label>角色</label>
                        <select class="form-control" name="roleid" id="edit_user_roleid">
                            <c:forEach items="${roles}" var="role">
                                <option value="${role.id}">${role.roleName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>状态</label>
                        <select class="form-control" name="enable" id="edit_user_enable">
                            <option value="true">正常</option>
                            <option value="false">禁用</option>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="editBtn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.2.0 -->
<script src="/static/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/plugins/datatables/js/jquery.dataTables.js"></script>
<script src="/static/plugins/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="/static/plugins/moment/moment.min.js"></script>
<script src="/static/plugins/validate/jquery.validate.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {

        var dataTable = $("#userTable").DataTable({
            serverSide:true,
            ajax:"/admin/users/load",
            ordering:false,
            "autoWidth": false,
            columns:[
                {"data":"id"},
                {"data":"userName"},
                {"data":"realName"},
                {"data":"weixin"},
                {"data":"role.roleName"},
                {"data":function(row){
                    if(row.enable) {
                        return "<span class='label label-success'>正常</span>";
                    } else {
                        return "<span class='label label-danger'>禁用</span>";
                    }
                }},
                {"data":function(row){
                    var timestamp = row.createTime;
                    var day = moment(timestamp);
                    return day.format("YYYY-MM-DD HH:mm");
                }},
                {"data":function(row){
                    if(row.role.roleName == '管理员') {
                        return "";
                    } else {
                        return "<a href='javascript:;' class='resetPwd' rel='"+row.id+"'>重置密码</a> &nbsp;&nbsp;" +
                            "<a href='javascript:;' class='edit' rel='"+row.id+"'>编辑</a>&nbsp;&nbsp;"+
                        "<a href='javascript:;' class='del' rel='"+row.id+"'>删除</a>";
                    }
                }}
            ],
            "columnDefs":[
                {targets:[0],visible: false}
            ],
            "language": { //定义中文
                "search": "请输入员工姓名或登录账号:",
                "zeroRecords": "没有匹配的数据",
                "lengthMenu": "显示 _MENU_ 条数据",
                "info": "显示a从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
                "infoFiltered": "(从 _MAX_ 条数据中过滤得来)",
                "loadingRecords": "加载中...",
                "processing": "处理中...",
                "paginate": {
                    "first": "首页",
                    "last": "末页",
                    "next": "下一页",
                    "previous": "上一页"
                }
            }
        });

        //模态框
        $("#newBtn").click(function () {
            $("#newForm")[0].reset();
            $("#newModal").modal({
                show: true,
                backdrop: 'static',
                keyboard: false
            });
        });
        //新用户验证
        $("#newForm").validate({
            errorClass:"text-danger",
            errorElement:"span",
            rules:{
                userName:{
                    required:true,
                    rangelength:[3,20],
                    remote:"/user/validate/newUser/username"
                },
                realName:{
                    required:true,
                    rangelength:[2,20]
                },
                editpassword:{
                    required:true,
                    rangelength:[6,18]
                },
                repassword : {
                    equalTo : "#password"
                },
                weixin:{
                    required:true
                }
            },
            messages:{
                userName:{
                    required:"请输入用户名",
                    rangelength:"用户名的长度3~20位",
                    remote:"该用户名已被占用"
                },
                realName:{
                    required:"请输入真实姓名",
                    rangelength:"真实姓名长度2~20位"
                },
                editpassword:{
                    required:"请输入密码",
                    rangelength:"密码长度6~18位"
                },
                repassword : {
                    equalTo : "两次输入密码不一致"
                },
                weixin:{
                    required:"请输入微信号码"
                }
            },
            submitHandler:function(form){
                $.post("/admin/users/new",$(form).serialize()).done(function(json){
                    if(json.state == "success") {
                        $("#newModal").modal('hide');
                        dataTable.ajax.reload();
                        layer.msg(json.message);
                    } else {
                        layer.msg(json.message);
                    }
                }).error(function(){
                   layer.msg("服务器异常");
                });
            }
        });

        //新增用户
        $("#saveNew").click(function () {
            $("#newForm").submit();
        });

        //删除用户
        $(document).delegate(".del","click",function () {
            var userId = $(this).attr("rel");
            layer.confirm('确定删除该用户？', {
                btn: ['狠心删除','心软拒绝'] //按钮
            }, function(){
                $.ajax({
                    url : "/admin/user/del/",
                    type : "post",
                    data : {"userId": userId},
                    success : function (json) {
                        if(json.state == 'success') {
                            layer.msg("删除成功！",function() {
                                window.location.reload();
                            });
                        } else if(json.state == 'error') {
                            layer.msg(json.message);
                        }
                    },
                    error : function() {
                        layer.msg("服务器错误，删除失败！");
                    }

                })
            })
        });

        //重置密码
        $(document).delegate(".resetPwd","click",function () {
            var userId = $(this).attr("rel");
            layer.confirm('确定重置密码？', {
                btn: ['一意孤行','后悔还来得及'] //按钮
            }, function(){
                $.ajax({
                    url : "/admin/user/resetPwd/",
                    type : "post",
                    data : {"userId": userId},
                    success : function (json) {
                        if(json.state == 'success') {
                            layer.msg("密码君重生了！",function() {
                                window.location.reload();
                            });
                        } else if(json.state == 'error') {
                            layer.msg(json.message);
                        }
                    },
                    error : function() {
                        layer.msg("服务器错误，密码重置失败！");
                    }

                })
            });
        });

        //编辑
        $(document).delegate(".edit","click",function () {
            var userId = $(this).attr("rel");
            $("#editForm")[0].reset();
            $("#editModal").modal({
                show:true,
                backdrop:"static",
                keyboard:false
            });

            $.get("/admin/user/" + userId + ".json").done(function (json) {
                if(json.state == "success") {
                    $("#edit_user_username").val(json.data.userName);
                    $("#edit_user_id").val(json.data.id);
                    $("#edit_user_realname").val(json.data.realName);
                    $("#edit_user_weixin").val(json.data.weixin);
                    $("#edit_user_roleid").val(json.data.role.id);
                    $("#edit_user_enable").val(json.data.enable);
                } else {
                    layer.msg(json.message);
                }
            }).error(function () {
                layer.msg("服务器错误,请重试！");
            });
        });

        //编辑验证
        $("#editForm").validate({
            errorClass:"text-danger",
            errorElement:"span",
            rules : {
                userName:{
                    rangelength:[3,20]
                },
                realName:{
                    required:true,
                    rangelength:[2,20]
                },
                password:{
                    required:true,
                    rangelength:[6,18]
                },
                repassword : {
                    equalTo : "#password"
                },
                weixin:{
                    required:true
                }
            },
            messages:{
                userName:{
                    required:"请输入用户名",
                    rangelength:"用户名的长度3~20位"
                },
                realName:{
                    required:"请输入真实姓名",
                    rangelength:"真实姓名长度2~20位"
                },
                password:{
                    required:"请输入密码",
                    rangelength:"密码长度6~18位"
                },
                repassword : {
                    equalTo : "两次输入密码不一致"
                },
                weixin:{
                    required:"请输入微信号码"
                }
            },
            submitHandler : function(form){
                $.post("/admin/user/edit",$(form).serialize()).done(function(json){
                    if(json.state == "success") {
                        $("#editModal").modal('hide');
                        dataTable.ajax.reload();
                        layer.msg(json.message);
                    } else if(json._state == 'error') {
                        layer.msg(json.message);
                    }
                }).error(function () {
                    layer.msg("服务器错误，编辑用户失败！");
                });
            }
        });

        //提交编辑
        $("#editBtn").click(function () {
            $("#editForm").submit();
        });
    });
</script>
</body>
</html>
