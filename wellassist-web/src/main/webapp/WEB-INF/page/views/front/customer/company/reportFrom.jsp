<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<head>

<!-- custom css -->
<link rel="stylesheet"
	href="<c:url value="/resources/wella/front/css/pagetempl.css"/>">

<script src="<c:url value="/resources/library/js/chosen.jquery.js"/>"></script>
<script src="<c:url value="/resources/library/js/bootstrap-fileupload.js"/>"></script>
<script src="${pageContext.request.contextPath}/statics/libs/ajaxupload.js"></script>
<script src="<c:url value="/resources/library/js/echarts.common.min.js"/>"></script>
<style>
    .ui .breadcrumb a,.ui .breadcrumb i{
        color: #777;
        vertical-align: middle;
    }
    .ui.text.menu,.ui.form {padding: 0 45px;}
</style>

</head>
<div class="container1">
	<div style="margin:40px 0 0 210px;">
		<div id = "content-rect" style="width:90%;">
			<div style="border-bottom:solid 1px #d0d0d0;padding-bottom:10px;font-size:15px;font-weight: 600;">财务报表</div>
			<div class="ui text menu">
	            <div class="header item">年份</div>
	            <a class="active item" onclick="javascript: changeYear(2017);">2017</a>
	            <a class="item" onclick="javascript: changeYear(2016);">2016</a>
	            <a class="item" onclick="javascript: changeYear(2015);">2015</a>
	            <a class="item">
	                <div class="ui selection dropdown">
	                    <input name="gender" type="hidden">
	                    <i class="dropdown icon"></i>
	                    <div class="default text">年份</div>
	                    <div class="menu">
	                        <div class="item" onclick="javascript: changeYear(2017);">2017</div>
	                        <div class="item" onclick="javascript: changeYear(2016);">2016</div>
	                        <div class="item" onclick="javascript: changeYear(2015);">2015</div>
	                        <div class="item" onclick="javascript: changeYear(2014);">2014</div>
	                        <div class="item" onclick="javascript: changeYear(2013);">2013</div>
	                        <div class="item" onclick="javascript: changeYear(2012);">2012</div>
	                        <div class="item" onclick="javascript: changeYear(2011);">2011</div>
	                        <div class="item" onclick="javascript: changeYear(2010);">2010</div>
	                    </div>
	                </div>
	            </a>
	            <input type="hidden" id="year" value="2017" />
	        </div>
	        <div class="ui form">
	            <div class="inline fields">
	                <div class="field" onclick="javascript: changeType('交易量', 'tradingVolume');">
	                    <div class="ui radio checkbox">
	                        <input name="type" checked="" tabindex="0" class="hidden" type="radio" />
	                        <label>交易量</label>
	                    </div>
	                </div>
	                <div class="field" onclick="javascript: changeType('贷款量', 'loanAmount');">
	                    <div class="ui radio checkbox">
	                        <input name="type" tabindex="0" class="hidden" type="radio">
	                        <label>贷款量</label>
	                    </div>
	                </div>
	                <div class="field" onclick="javascript: changeType('成交额', 'turnover');">
	                    <div class="ui radio checkbox">
	                        <input name="type" tabindex="0" class="hidden" type="radio">
	                        <label>成交额</label>
	                    </div>
	                </div>
	                <div class="field" onclick="javascript: changeType('利息', 'interest');">
	                    <div class="ui radio checkbox">
	                        <input name="type" tabindex="0" class="hidden" type="radio">
	                        <label>利息</label>
	                    </div>
	                </div>
	                <div class="field" onclick="javascript: changeType('资金流量', 'fundFlow');">
	                    <div class="ui radio checkbox">
	                        <input name="type" tabindex="0" class="hidden" type="radio">
	                        <label>资金流量</label>
	                    </div>
	                </div>
	                <input type="hidden" id="dataType" value="交易量" />
	                <input type="hidden" id="url" value="tradingVolume" />
	            </div>
	        </div>
	        <div style="width:60%;min-height: 500px;margin-left:0;">
	            <div id="main" style="width: 100%;min-height:500px;"></div>
	        </div>
		</div>
	</div>
</div>
<script type="text/javascript">

    $('.ui.dropdown').dropdown();
    $('.ui.radio.checkbox').checkbox();
    $('.ui.text.menu a').click(function(){
        $('.ui.text.menu a').removeClass("active");
        $(this).addClass("active");
    })
    $(document).ready(function() {
        getData();
    });

    function changeYear(year) {

        $("#year").val(year);
        getData();
    }

    function changeType(type, url) {

        $("#dataType").val(type);
        $("#url").val(url);
        getData();
    }

    function getData() {

        var dataType = $("#dataType").val();
        var dataType1="";
        if(dataType=="资金流量"){
             dataType="收入";
             dataType1="支出";
        }
        var year = $("#year").val();
        var url = $("#url").val();
       /* var userId=$("#userId").val();*/



        // $ajax 获取类型
        var resourceData = [];
        var resourceData1 = [];
        var unit="";
        $.ajax({
            type: 'post',
            url:'${pageContext.request.contextPath}/platform/' + url,
            contentType:'application/json',
            data: JSON.stringify({
                year: year,
                userId: "",
            }),
            dataType: 'json',
            "success": function (result) {

                if (result.code == 0) {
                    resourceData = result.data;
                    resourceData1 = result.data1;

//                    console.log(resourceData);
//                    console.log(resourceData1);

                    if(result.unit==1){
                        unit="吨";
                    }else{
                        unit="元";
                    }

                    var myChart = echarts.init(document.getElementById('main'));//基于准备好的demo，初始化echarts实例
                    // var resourceData = [10, 52, 200, 334, 390, 330, 220, 170, 270, 330, 550, -300]; // 通过ajax获取

                    var option = {
                        tooltip : {
                            trigger: 'axis',
                            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                            }
                        },
                        toolbox: {
                            show : true,
                            feature : {
                                dataView : {show: true, readOnly: false},
                                magicType : {show: true, type: ['line', 'bar']},
                                restore : {show: true},
                                saveAsImage : {show: true}
                            }
                        },
                        legend: {
                            data: [dataType, dataType1],
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        xAxis : [
                            {
                                type : 'category',

                                axisTick: {
                                    alignWithLabel: true
                                },
                                data : ['一月', '二月', '三月', '四月', '五月', '六月', '七月','八月', '九月', '十月', '十一月', '十二月']
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value',
                                name: unit
                            }
                        ],
                        series : [
                            {
                                name:dataType,
                                type:'bar',
                                stack: '总量',
                                label: {
                                    normal: {
                                        show: true
                                    }
                                },
                                data:resourceData
                            },
                            {
                                name:dataType1,
                                type:'bar',
                                stack: '总量',
                                label: {
                                    normal: {
                                        show: true,
                                    }
                                },
                                data:resourceData1
                            }
                        ]
                    };

                    myChart.setOption(option);
                }
            },
            "error": function (data) {

                console.log("系统异常");
            }
        });
    }

</script>
<%@ include file="../footer.jsp"%>