/**
 * Created by Administrator on 2017/5/18.
 */
$(function () {
    $("#jqGrid").jqGrid({
        url: './productlist',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'prodId', index: "user_id", width: 45, key: true,hidden:true},
            { label: '产品', name: 'prodName', width: 75 },
            { label: '地址', name: 'address', width: 100 ,formatter:function (value,option,row) {
                return value+"元<br>";
            }},
            { label: '品类', name:'prodType', width: 80,formatter:function (value,options,row) {
                if(value==1){
                    return "燃油";
                }else if(value ==0){
                    return "天然气";
                }else {
                    return '管道气';
                }
            }},
            { label: '单价', name: 'prodPrice',  width: 45, key: true,formatter:function (value,option,row) {
                return value+"元/吨";
            } },
            { label: '操作', name: 'prodPrice',  width: 45, key: true,formatter:function (value,option,row) {
                var prodId = row.prodId;
                return  '<a  class="btn btn-primary" href="./edit?prodId='+ prodId+'">编辑</a>' +
                    '<button  class="btn btn-primary" onclick="vm.delete(' + prodId + ')">删除</button>'
            } }
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
        delete:function(prodId){
            var url = "./delete/"+prodId;
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