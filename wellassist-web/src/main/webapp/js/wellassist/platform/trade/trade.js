/**
 * Created by Administrator on 2017/5/18.
 */
$(function () {
    $("#jqGrid").jqGrid({
        url: './orders',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'orderId', index: "order_id", width: 45, key: true },
            { label: '买家', name: 'customerName', width: 75 },
            { label: '订单号', name: 'orderNo', width: 100},
            { label: '产品', name: 'productName', width: 100},
            { label: '数量（吨）', name: 'number', index: "user_id", width: 45},
            { label: '交易金额', name: 'total', width: 100 },
            { label: '交易时间', name: 'orderDate', width: 100},
            { label: '交易状态', name: 'orderState', width: 100 ,formatter:function (value,option,row) {
                var isZordersQuestion=row.isZordersQuestion;
                if (isZordersQuestion==1){
                    return "买家收货存疑";
                }
                if(value==0){
                    return "待确认";
                }else if(value ==-1){
                    return "取消订单";
                }else  if(value==1){
                    return "待付款";
                }else if(value ==11){
                    return "产品订单已付款，物流订单未付款";
                }else  if(value==12){
                    return "物流订单已付款，产品订单未付款";
                }else if(value ==2){
                    return "未发货";
                }else if(value==3){
                    return "发送中";
                }else {
                    return "完成发货";
                }
            }}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});


var vm = new Vue({
    el:'#rrapp',
    data:{
        q:{
            username: null
        },
        showList: true,
        title:null,
        roleList:{},
        user:{
            status:1,
            roleIdList:[]
        }
    },
    methods: {
        query: function () {
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'queryStr': vm.q.username,'hasQuestion':null},
                page:1
            }).trigger("reloadGrid");
        },
        update:function(){
            var orderId = getSelectedRow();
            if(orderId == null){
                return ;
            }
            window.location.href=baseUrl+"platform/trade/orderDetail?orderId="+orderId;
        },
        hasQuestion: function () {
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'hasQuestion': 1},
                page:1
            }).trigger("reloadGrid");
        }
    }
});