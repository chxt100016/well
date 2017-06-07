/**
 * Created by Administrator on 2017/5/18.
 */
$(function () {
    $("#jqGrid").jqGrid({
        url: './tradeList',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'moneyId', index: "money_id", width: 45, key: true },
            { label: '公司', name: 'userName', width: 75 },
            { label: '交易类型', name: 'jyType', width: 100,formatter:function (value,option,row){
                if(value==0){
                    return "充值";
                }else if(value ==1){
                    return "订单";
                }else  if(value==2){
                    return "提现";
                }else {
                    return "物流";
                }
            }},
            { label: '交易名称', name: 'jyMc', width: 100},
            { label: '交易金额', name: 'jyMoney', index: "user_id", width: 45, key: true },
            { label: '交易时间', name: 'jyDate', width: 100 },
            { label: '交易状态', name: 'jyState', width: 100, formatter:function (value,option,row){
                if(value==0){
                    return "未确定";
                }else if(value ==-1){
                    return "取消";
                }else  if(value==1){
                    return "进行中";
                }else {
                    return "完成";
                }
            }},
            { label: '操作', name: 'moneyId', width: 100 ,formatter:function (value,option,row) {
                return '<a  class="btn btn-primary" onclick="vm.process('+value+',2)">付款</a>';
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
        resetPassword:function(){
            var userId = vm.user.userId;
            var url = "../user/resetPassword/"+userId;
            alert(url);
            // console.log(vm.user);
            $.post(
                url,
                JSON.stringify(vm.user),
                function(r){
                    if(r.code === 0){
                        alert('操作成功', function(index){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                },
                "json"
            );

        },
        process:function(){

        }
    }
});