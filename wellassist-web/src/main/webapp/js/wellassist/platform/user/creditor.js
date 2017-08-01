/**
 * Created by Administrator on 2017/5/18.
 */
$(function () {
    $("#jqGrid").jqGrid({
        url: '../user/creditorList',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'userId', index: "user_id", width: 45, key: true },
            { label: '公司', name: 'userName', width: 75 },
            { label: '账户情况', name: 'userMoney', width: 100 ,formatter:function (value,option,row) {
                return value+"元<br>";
            }},
            { label: '资质认证', name: 'creditorState', width: 100 ,formatter:function (value,option,row) {
                if(value==0){return "未认证";}
                else if(value==1){return "待审核";}
                else if(value==2){return "已认证";}
            }},
            { label: '资质审核', name: 'creditorState', width: 100 ,formatter:function (value) {
                if(value==0){return "未提交认证申请";}
                else if(value==1){return '<a  class="btn btn-primary" href="#">审核</a>';}
                else if(value==2){return '<a  class="btn btn-primary" href="#">查看</a>';}
            }},
            { label: '公司详情', name: '', index: "user_id", width: 45, key: true,formatter:function (value,option,row) {
                var userId = row.userId;
                return '<a  class="btn btn-primary" href="./creditor/creditorInfo/'+userId+'">公司详情</a>';
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
                postData:{'queryStr': vm.q.username},
                page:1
            }).trigger("reloadGrid");
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.roleList = {};
            vm.user = {status:1,roleIdList:[]};

            //获取角色信息
            this.getRoleList();
        },
        getUser: function(userId){
            $.get("../sys/user/info/"+userId, function(r){
                vm.user = r.user;
            });
        },
        getRoleList: function(){
            $.get("../sys/role/select", function(r){
                vm.roleList = r.list;
            });
        },
        viewInfo:function (userId) {
            $.get("../user/reviewInfo/"+userId, function(r){
                vm.user = r.user;
            });
            vm.showList = false;
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

        }
    }
});