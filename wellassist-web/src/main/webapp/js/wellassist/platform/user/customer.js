 $(function() {
        $("#jqGrid").jqGrid({
            url: '../user/customersList',
            datatype: "json",
            colModel: [{
                label: 'id',
                name: 'userId',
                index: "customer_id",
                width: 45,
                key: true
            }, {
                label: '公司',
                name: 'companyName',
                width: 75
            }, {
                label: '公司-详细',
                width: 90,
                formatter: function(value, options, row) {
                    var userId = row.userId;
                    return '<a  class="btn btn-primary" href="./customerInfo?id='+userId+'">公司详情</a>'
                    //return '<a  onclick= "vm.update( '+ userId + ')">公司详情</a>';
                    alert(row);
                }
            }, {
                label: '账户情况',
                name: 'userMoney',
                width: 100,
                formatter: function(value, option, row) {
                    return value + '元<br><a href="./accountDeatil.html?id="+id>详情</a>'
                }
            }, {
                label: '还款情况',
                name: 'status',
                width: 80,
                formatter: function(value, options, row) {
                    return value +
                    "<br><a href=''>详情</a>"
                }
            }, {
                label: '授信额度',
                name: 'customerMoney',
                width: 100,
                formatter: function(value, option, row) {
                    return value + "万元<br><a href=''>授信额度</a>";
                }

            },
            {
                label: '所属卖家',
                name: 'supplyName',
                width: 100,
                formatter: function(value, option, row) {
                    return value ;
                }

            },],
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
                vm.reload();
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
