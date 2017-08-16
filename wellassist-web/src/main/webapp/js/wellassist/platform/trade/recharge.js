/**
 * Created by Administrator on 2017/5/18.
 */
$(function () {
    $("#jqGrid").jqGrid({
        url: './rechargeList',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'orderId', index: "order_id", width: 15, key: true },
            { label: '公司', name: 'userName', width: 75 },
            { label: '充值金额', name: 'zfMoney', width: 100 ,formatter:function (value,option,row) {
                return value+"元<br>";
            }},
            { label: '充值时间', name: 'zfDate', width: 100, key: true},
            { label: '支付类型', name: 'zfType', width: 40 ,formatter:function (value,option,row) {
                if(value==0){
                    return "线下";
                }else {
                    return "在线"
                }
            }},
            { label: '支付状态', name: 'zfState', width: 40 ,formatter:function (value,option,row) {
                if(value==0){
                    return "未确定";
                }else if(value==1) {
                    return "确定";
                }else {
                    return"";
                }
            }},
            { label: '操作', name: 'orderId', width: 40 ,formatter:function (value,option,row) {
                var zfState = row.zfState;
                if(zfState==0){
                    return '<a  class="btn btn-primary" onclick="vm.processRecharge( '+ value + ')">确定</a>';
                }else {
                    return '';
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
            vm.reload();
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.roleList = {};
            vm.user = {status:1,roleIdList:[]};

            //获取角色信息
            this.getRoleList();
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'username': vm.q.username},
                page:page
            }).trigger("reloadGrid");
        },
        processRecharge: function (orderId) {
            url = "./rechargeHandle?orderId=" + orderId;
            $.post(url,{},function(data){
                if(data.state==1 ){
                    vm.reload();
                }
            }, "json");
        }
    }
});
