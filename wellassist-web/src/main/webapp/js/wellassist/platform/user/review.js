/**
 * Created by Administrator on 2017/5/18.
 */
$(function () {
    $("#jqGrid").jqGrid({
        url: '../user/reviewlist',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'userId', index: "user_id", width: 45, key: true },
            { label: '公司', name: 'userName', width: 75 },
            { label: '申请日期', name: 'createDate', width: 75 },
            { label: '审核状态', name: 'userState', width: 75 ,formatter:function (value,option,row) {
                    if(value == "0"){return "待审核"}
                    else if(value == "-1"){return "不通过"}
                    else if(value == "1"){return "通过"}
                }
            },
            {label: '审核', name: '', width: 75 ,formatter:function (value,option,row) {
                    var userId = row.userId;
                    var state = row.userState;
                    // if(state==1){
                    //     return '<button  class="btn btn-primary" onclick="vm.review('+ userId + ')">查看</button>'
                    // }else {
                        return '<button  class="btn btn-primary" onclick="vm.review(' + userId + ')">审核</button>'
                    // }
                }
            }
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
        //审核通过时挑用的业务逻辑
        approve: function (event) {
            var url = "../user/approve";
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
        notApprove:function(event){
            var url = "../user/notApprove";
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
        review: function(userId){
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
        }
    }
});