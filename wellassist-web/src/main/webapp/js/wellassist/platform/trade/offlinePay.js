 $(function() {
        $("#jqGrid").jqGrid({
            url: './offlinePayList',
            datatype: "json",
            colModel: [{
                label: '订单编号',
                name: 'orderNo',
                width: 100
            }, {
                label: '买家公司',
                name: 'customerUserName',
                width: 100
            }, {
                label: '申请时间',
                name:'tjDate',
                width: 100
            }, {
                label: '审核状态',
                name: 'transState',
                width: 100,
                formatter: function(value, options, row) {
                    if(value==-1){return "待审核";}
                    else if (value==-2){return "审核不通过";}
                    else if (value>-1){return "已通过";}
                    return value;
                }
            }, {
                label: '操作',
                name: 'transState',
                width: 80,
                formatter: function(value, options, row) {
                    var orderTransId=row.transId;
                    if(value==-1){return '<a  class="btn btn-primary" href="./offlinePayCheck?orderTransId='+orderTransId+'">审核</a>';}
                    else if (value==-2){return '<a  class="btn btn-primary" href="./offlinePayDetail?orderTransId='+orderTransId+'">查看</a>';}
                    else if (value>-1){return '<a  class="btn btn-primary" href="./offlinePayDetail?orderTransId='+orderTransId+'">查看</a>';}
                }
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
                username: null
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
                // vm.reload();
                $("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'queryStr': vm.q.username},
                page:1 
            }).trigger("reloadGrid");
            },
            add: function() {
                vm.showList = false;
                vm.title = "新增";
                vm.roleList = {};
                vm.user = {
                    status: 1,
                    roleIdList: []
                };

                //获取角色信息
                this.getRoleList();
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
                        'username': vm.q.username
                    },
                    page: page
                }).trigger("reloadGrid");
            }
        }
    });
