<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>抢单大厅</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.3/vue.js"></script>
    <style>
        .oliver-color {
            color: #B5CC18;
        }
        
        .teal-color {
            color: #00B5AD;
        }
        .ft-sz-17{
 font-size: 17px;
 font-weight:bold;
 }
 .pd-lf-20{
     padding-left: 20px
 }
 .bluefont{
     color: #617B90
 }
    </style>
</head>

<body>
    <div class="container1">
  <div class="container2">

 
    <div class="ui container segment" id="app1" >

        <form id="searchFrm" method="post" action="${pageContext.request.contextPath}/sender/vehicleGrabHall">
            <input type="hidden" id="page" name="page" value="${param.page}">
        </form>

        <h4 class="ui header">抢单大厅</h4>
        <div class="ui divider"></div>

         <div class="ui three column doubling grid stackable">
              <c:forEach items="${info}" var="ii">
            <div class="column">
                <div class="ui segment items">
                    <img src="${ii.prodImg}" alt="${ii.prodName}" width="100%" height="210px">
                    <div class="item pd-lf-20"> 
                        <div class="extra">
                            <div class="ui left floated ft-sz-17 pd-lf-20">${ii.prodName}</div>
                            <div class="ui right floated" > ${ii.num}吨</div>
                         </div>
                    </div>
                        <div class="item "> 
                        <div class="extra">
                            <div class="ui left floated pd-lf-20">
                                <div>来自 <span class="bluefont">${ii.fromAddress}</span></div>
                                 <div>去往 <span class="bluefont">${ii.toAddress}</span></div>
                            </div>
                            <div class="ui right floated primary button" onclick="grab(${ii.logisticsId})" >抢单</div>
                         </div>
                    </div>
                </div>
            </div>
           </c:forEach>
        </div>
       
        <%--<div class="ui right floated pagination menu">
            <a class="icon item">
                <i class="left chevron icon"></i>
            </a>
            <a class="item">1</a>
            <a class="item">2</a>
            <a class="item">3</a>
            <a class="item">4</a>
            <a class="icon item">
                <i class="right chevron icon"></i>
            </a>
        </div>--%>
        <br><br>
        <div class="right-pagination">
            <%@ include file="../../pagination.jsp"%>
        </div>
        <br><br>
    </div>
 </div>
    </div>
</body>
<script>
    function grab(logisticsId){
        window.location.href="${pageContext.request.contextPath}/sender/grabLogistics?logisticsId="+logisticsId;
    }
</script>
<%@ include file="../footer.jsp"%>