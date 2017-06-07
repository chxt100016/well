/**
 * Created by Administrator on 2017/5/18.
 */
$(function () {
    $("#jqGrid").jqGrid({
        url: './withdrawList',
        datatype: "json",
        colModel: [
            // { label: 'id', name: 'withdrawId', index: "withdraw_id", width: 45, key: true },
            { label: '公司', name: 'userName', width: 75 },
            { label: '提现金额', name: 'withdrawMoney', width: 100 ,formatter:function (value,option,row) {
                return value+"元";
            }},
            { label: '开户行', name: 'withdrawBank', width: 100 ,formatter:function (value,option,row) {
                return value+"元<br>";
            }},
            { label: '户主', name: 'withdrawName', width: 100
            },
            { label: '提现时间', name: 'withdrawDate', width: 100
            },
            { label: '状态', name: 'withdrawState', width: 100, formatter:function (value,option,row) {
                if(value==0){
                    return "申请";
                }else if(value ==-1){
                    return "不通过";
                }else  if(value==1){
                    return "待付款";
                }else {
                    return "已付款";
                }
            }},
            { label: '操作', name: 'withdrawState', width: 100, formatter:function (value,option,row) {
                var withdrawId = row.withdrawId;
                if(value==0){
                    return '<a  class="btn btn-primary" onclick="vm.process('+withdrawId+',1)">审核</a>' +
                        '<a  class="btn btn-primary" onclick="vm.process('+withdrawId+',-1)">不通过</a>';
                }else if(value ==-1){
                    return '<a  class="btn btn-primary" onclick="vm.process('+withdrawId+',-2)">删除</a>' +
                        '<a  class="btn btn-primary" onclick="vm.process('+withdrawId+',0)">再审核</a>';
                }else  if(value==1){
                    return '<a  class="btn btn-primary" onclick="vm.process('+withdrawId+',2)">付款</a>';
                }else{
                    return "";
                }
            }},
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
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.roleList = {};
            vm.user = {status:1,roleIdList:[]};

            //获取角色信息
            this.getRoleList();
        },
        update: function () {
            var userId = getSelectedRow();
            if(userId == null){
                return ;
            }

            vm.showList = false;
            vm.title = "修改";

            vm.getUser(userId);
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
        process:function (withdrawId,withdrawState) {
            url = "./withdrawHandle?withdrawId="+withdrawId + "&withdrawState="+withdrawState;
            $.post(url,{},function(data){
                if(data.state==1 ){
                    vm.reload();
                }
            }, "json");
        }
    }
});