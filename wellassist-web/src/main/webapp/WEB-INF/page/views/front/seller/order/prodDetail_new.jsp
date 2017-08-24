<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${user.userType==0}">
	<%@ include file="/WEB-INF/page/views/front/seller/header.jsp"%>
</c:if>
<c:if test="${user.userType==1}">
	<%@ include file="/WEB-INF/page/views/front/customer/header.jsp"%>
</c:if>
<c:if test="${user.userType==3}">
	<%@ include file="/WEB-INF/page/views/front/sender/header.jsp"%>
</c:if>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
		.taj{text-align: justify;}
		.cf{color: #fff;}
		.c0{color: #0068b7;}
		.cff{color: #FF0000!important;}
		.fs15{font-size: 15px;}
		.fw6{font-weight: 600;}
		.mgl10{margin-left: 10%!important;}
		.mgl20{margin-left: 20%!important;}
		.pdl20{padding-left: 20px;}
		.lh40{line-height: 40px;}
		.lh75{line-height: 75px;}
		.lh95{line-height: 95px;}
		.tar{text-align: right;}
		.bdrb{border-bottom: 1px dashed #bcbcbc;}
		.bdrl{border-left: 1px solid #e2e2e2;}
		.bgcf2{
			background-color: #f2f2f2;
			padding: 35px 30px 20px;
		}
	</style>

<div class="container1" id="app">

    <div style="margin:40px 0 0 210px;">
    	<div class="ui container" id="app1" style="width:90%;">
    		<h4 class="ui header">产品详情</h4>
            <div class="ui divider"></div>

            <div class="column">
				<div class="ui form">
					<div class="two fields">
						<div class="field" style="width:50%;">
							<img v-bind:alt="prod.prodName" v-bind:src="prod.prodImg" width="90%" height="440">
						</div>
						<div class="field" style="width:50%;">
							<div class="bdrb lh95">
								<span class="ui header">{{prod.prodName}}</span>
								<a href="${pageContext.request.contextPath}/customer/makeOrder?prodId=${param.prodId}">
								<div class="ui button orange mgl20" style="width:15%;">
									<span href="#" class="cf">下单</span>
								</div>
								</a>
							</div>
							
							<div class="bgcf2 fs15 fw6" style="margin-top:40px;">
								<div class="column">
									<div class="ui form">
										<div class="two fields">
											<div class="field" style="width:70%;">
												<div class="lh40">
													<span>单价</span><span class="cff ui header mgl10">{{prod.prodMoney}}元/吨</span><br>
													<span>品类</span>
													<span class="mgl10" v-if="prod.prodType==0">天然气</span>
													<span class="mgl10" v-if="prod.prodType==1">原油</span>
													<span class="mgl10" v-if="prod.prodType==2">管道气</span>
												</div>
											</div>
											<div class="field" style="width:70%;">
												<div class="pdl20 bdrl tar">
													<span>累计评价</span><br><span class="c0">10万+</span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="fs15 fw6 bdrb lh75">
								<span>联系人：{{prod.prodLxr}}</span><span class="mgl20">联系电话：{{prod.prodLxrPhone}}</span>
							</div>
							<div class="fs15 fw6 bdrb lh75">
								<span>所在地：{{prod.prodRegionAddr}}</span>
							</div>
						</div>
					</div>
				</div>
            </div>

            <h4 class="ui header">产品介绍</h4>
            <div class="ui divider"></div>
            <p class="taj">
				&emsp;&emsp;{{prod.prodIntro}}
            </p>
    	</div>
    </div>
</div>
<script type="text/javascript">
var url = '${pageContext.request.contextPath}/userinfo/selectProduct';
var prodId = ${param.prodId};
var vm = new Vue({
	el: '#app',
	data:{
		prod:{}
	},
	created:function(){
		let that = this;
		$.ajax({
			type:'get',
			url:url,
			data:{'prodId':prodId},
			dataType:'json',
			success:function(result){
				if (result.code == 0) {
					that.prod = result.prod;
					console.log(result);
				}
				else{
					alert(result.msg)
				}
			}
		});
	}
})

</script>