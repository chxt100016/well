/**
 * Created by Administrator on 2017/5/18.
 */
$(function () {
    $("#jqGrid").jqGrid({
        url: '../trade/logisticsList',
        datatype: "json",
        colModel: [
            { label: '订单编号', name: 'orderNo', index: "order_no", width: 70, key: true },
            { label: '物流公司', name: 'senderName', width: 70 },
            { label: '货物名称', name: 'prodName', width: 70},
            { label: '货物金额', name: 'total', width: 50},
            { label: '联系电话', name: 'phone', index: "user_id", width: 60 },
            { label: '发货日期', name: 'sendDate', width: 100},
            { label: '货物状态', name: 'state', width: 100 ,formatter:function (value,option,row) {
                if(value ==2){
                    return "确定";
                }else {
                    return "已完成";
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
        del: function () {
            var userIds = getSelectedRows();
            if(userIds == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: "../sys/user/delete",
                    data: JSON.stringify(userIds),
                    success: function(r){
                        if(r.code == 0){
                            alert('操作成功', function(index){
                                vm.reload();
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.user.userId == null ? "../sys/user/save" : "../sys/user/update";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.user),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(index){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
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