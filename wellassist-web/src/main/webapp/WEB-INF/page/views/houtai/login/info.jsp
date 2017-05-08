<%@ include file="../../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
         
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 我的首页</a></li>
            <li class="active"><a href="${pageContext.request.contextPath}/main/0/0">我的信息</a></li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="callout callout-info" style="margin-top:15px;">
            <h4>我的信息</h4>
            <p>做什么？</p>
          </div>
          <div class="box box-info"> 
                <form role="form" class="form-horizontal">
                  <div class="box-body">
                     <div class="form-group">
                      <label for="inputEmail3" class="col-sm-2 control-label">* 管理者名 :</label>
                      <div class="col-sm-10">
                        <input id="name" type="text" class="form-control" value="***" placeholder="">
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-2 control-label">* 手机号 :</label>
                      <div class="col-sm-10">
                        <input id="phonenum" type="text"  class="form-control" value="" placeholder="">
                      </div>
                    </div>   
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-2 control-label">* 密码 :</label>
                      <div class="col-sm-10">
                        <input id="pass" type="password" class="form-control" value="" placeholder="">
                      </div>
                    </div> 
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-2 control-label">* 重密码 :</label>
                      <div class="col-sm-10">
                        <input id="pass-confirm" type="password" class="form-control" value="" placeholder="">
                      </div>
                    </div>
                  </div><!-- /.box-body -->

                  <div class="box-footer">
                    <button type="submit" class="btn btn-info pull-right">保存</button>
                  </div>
                </form>
          </div>
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
<%@ include file="../../footer.jsp"  %>