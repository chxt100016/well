var socket = io.connect( nodeServer );
socket.on('connect', function(){
    socket.emit('login',typeBoxID);
});

socket.on('updateContent',function(ret){
	//成交
	var transaction = ret.transaction;
    $("#transaction").empty();
    var html = "";
    var transactionLen = transaction.length;
    if(myPage==false){
        transactionLen = (transactionLen>10?10:transactionLen);
    }
    for(var i=0;i<transactionLen;i++){
        html += "<tr>";
        html += "<td>"+transaction[i].inserttime+"</td>";
        if(transaction[i].flag==1){
        	html += "<td class='buy'>买</td>";
        	html += "<td class='buy'>"+toDecimal2(transaction[i].price)+"</td>"; 
        }else{
        	html += "<td class='sell'>卖</td>";
        	html += "<td class='sell'>"+toDecimal2(transaction[i].price)+"</td>"; 
        }
        
        html += "<td>"+toDecimal2(transaction[i].num)+"</td>"; 
        html += "</tr>";
    }
    if(transactionLen==0) html = "没有找到数据";
    $("#transaction").html(html);
    
    //卖出委托
    var sell = ret.sell;
    var sellObj = $("#myWeituoSell");
    var weituoSellLen = sell.length;
    if(myPage==false){
        sellObj = $("#selllist");
        weituoSellLen = (weituoSellLen>5?5:weituoSellLen);
    }
    $(sellObj).empty();
    html = "";
    for(var i=0;i<weituoSellLen;i++){
        html += "<tr style='text-align:center;'>";
        html += "<td class='sell'>卖 "+(weituoSellLen-i)+"</td>";
        html += "<td><span class='sub_price'>"+toDecimal2(sell[sell.length-weituoSellLen+i].price)+"</span></td>"
        html += "<td><span class='sub_amount'>"+toDecimal2(sell[sell.length-weituoSellLen+i].nums)+"</span></td>";
        html += "<td><span style='width:0.065px' class='buySpan'></span></td>"; 
        html += "</tr>";
    }
    if(weituoSellLen==0 && myPage==true) html = "没有找到数据";
    $(sellObj).html(html);
    
    //卖入委托
    var buy = ret.buy;
    var buyObj = $("#myWeituoBuy");
    var weituoBuyLen = buy.length;
    if(myPage==false){
        buyObj = $("#buylist");
        weituoLen = (weituoBuyLen>5?5:weituoBuyLen);
    }
    buyObj.empty();
    html = "";
    for(var i=0;i<weituoBuyLen;i++){
        html += "<tr style='text-align:center;'>";
        html += "<td class='buy'>买 "+(i+1)+"</td>";
        html += "<td><span class='sub_price'>"+toDecimal2(buy[i].price)+"</span></td>"
        html += "<td><span class='sub_amount'>"+toDecimal2(buy[i].nums)+"</span></td>";
        html += "<td><span style='width:0.596px' class='sellSpan'></span></td>"; 
    }
    if(weituoBuyLen==0 && myPage==true) html = "没有找到数据";
//    if(weituoBuyLen==0 && weituoSellLen==0 && myPage==false) html = "没有找到数据";
    $(buyObj).html(html);
    
    //行情
    var market = ret.market;
    $("#BTC_RMB_rate").html(toDecimal2(market.now));
    if(market.flag=='0'){
        $("#price_arrow").addClass("arrow-fall");
    }else{
        $("#price_arrow").addClass("arrow-up");
    }
    $("#time").html(market.addtime);
    
    $("#low_price").html(toDecimal2(market.low));
    
    $("#high_price").html(toDecimal2(market.high));
    
    $("#volume").html(toDecimal2(market.volume));
    
    //chart
    if(fenshi==true){
        changeChart(ret.chart[0]);
    }
});

function toDecimal2(x) { 
    var f = parseFloat(x); 
    if (isNaN(f)) { 
        return false; 
    } 
    var f = Math.round(x*100)/100; 
    var s = f.toString(); 
    var rs = s.indexOf('.'); 
    if (rs < 0) { 
        rs = s.length; 
        s += '.'; 
    } 
    while (s.length <= rs + 2) { 
        s += '0'; 
        } 
  return s; 
} 
 
