 $(function() {
        $("#jqGrid").jqGrid({
            url: baseUrl+"platform/risk/companyList",
            datatype: "json",
            colModel: [ {
                label: '企业名称',
                name: 'name',
                width: 75
            }, {
                label: '注册资金(万元)',
                name:'registeredCaptital',
                width: 90
            }, {
                label: '社会信用代码',
                name: 'creditCode',
                width: 100
            }, {
                label: '经营范围',
                name: 'businessScope',
                width: 80
            }, {
                label: '公司地址',
                name: 'location',
                width: 100
            }],
            viewrecords: true,
            height: 385,
            rowNum: 10,
            rowList: [10, 30, 50],
            rownumbers: true,
            rownumWidth: 25,
            autowidth: true,
            multiselect: true,
            pager: "#jqGridPager",
            jsonReader: {
                root: "page.list",
                page: "page.currPage",
                total: "page.totalPage",
                records: "page.totalCount"
            },
            prmNames: {
                page: "page",
                rows: "limit",
                order: "order"
            },
            gridComplete: function() {
                //隐藏grid底部滚动条
                $("#jqGrid").closest(".ui-jqgrid-bdiv").css({
                    "overflow-x": "hidden"
                });
            }
        });
    });

    var vm = new Vue({
        el: '#rrapp',
        data: {
            q: {
                name: null
            },
            showList: true,
            title: null,
            roleList: {},
            user: {
                status: 1,
                roleIdList: []
            }
        },
        methods: {
            query: function() {
                $("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'queryStr': vm.q.name},
                page:1 
            }).trigger("reloadGrid");
            },
            add: function() {
               window.location.href="./customerInsert.html";
            },
            update: function(userId) {

                if (userId == null) {
                    return;
                }

                vm.showList = false;
                vm.title = "修改";
                console.log(vm.user.userId);
                vm.getUser(userId);

            },
            del: function() {
                var userIds = getSelectedRows();
                if (userIds == null) {
                    return;
                }

                confirm('确定要删除选中的记录？', function() {
                    $.ajax({
                        type: "POST",
                        url: "../sys/user/delete",
                        data: JSON.stringify(userIds),
                        success: function(r) {
                            if (r.code == 0) {
                                alert('操作成功', function(index) {
                                    vm.reload();
                                });
                            } else {
                                alert(r.msg);
                            }
                        }
                    });
                });
            },
            saveOrUpdate: function(event) {
                var url = vm.user.userId == null ? "../sys/user/save" : "../sys/user/update";
              
                $.ajax({
                    type: "POST",
                    url: url,
                    data: JSON.stringify(vm.user),
                    success: function(r) {
                        if (r.code === 0) {
                            alert('操作成功', function(index) {
                                vm.reload();
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            },
            getUser: function(userId) {
                $.get("../user/userinfo/" + userId, function(r) {
                    vm.user = r.user;
                });
            },
            reload: function(event) {
                vm.showList = true;
                var page = $("#jqGrid").jqGrid('getGridParam', 'page');
                $("#jqGrid").jqGrid('setGridParam', {
                    postData: {
                        'queryStr': vm.q.name
                    },
                    page: page
                }).trigger("reloadGrid");
            }
        }
    });



function  insert() {
    console.log("dshbfh");
    console.log("dshbfh");
    window.location.herf="customer/customerInsert.html";
}
