<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>TXXSXX</title>
  <link rel="stylesheet" href="../src/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">TXXSXX</div>
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="">发起事项</a></li>
      <li class="layui-nav-item"><a href="">待办事项</a></li>
			<li class="layui-nav-item"><a href="">已办事项</a></li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
          
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">修改密码</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="">退出</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <ul class="layui-nav layui-nav-tree" >
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">我的班级</a>
          <dl class="layui-nav-child">
            <dd><a href="">班委</a></dd>
            <dd><a href="">同学</a></dd>
            <dd><a href="">带课老师</a></dd>
            <dd><a href="">辅导员</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">我的信息</a>
          <dl class="layui-nav-child">
            <dd><a href="">学籍档案</a></dd>
            <dd><a href="">基本信息</a></dd>
            <dd><a href="">个人信息</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href=""></a></li>
        <li class="layui-nav-item"><a href=""></a></li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <div style="padding: 15px;">欢迎使用</div>
  </div>
  
  <div class="layui-footer">
		联系管理员 xxxx@xxxx.com
  </div>
</div>
<script src="../src/layui.js"></script>
<script>
layui.use('element', function(){
  var element = layui.element;
  
});
</script>
</body>
</html>