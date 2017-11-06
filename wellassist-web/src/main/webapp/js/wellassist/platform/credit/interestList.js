 $(function() {
        $("#jqGrid").jqGrid({
            url: './loanList',
            datatype: "json",
            colModel: [{
                label: 'id',
                name: 'loanId',
                width: 20
            },{
                label: '还款方',
                name: 'customerUserName',
                width: 100
            }, {
                label: '放款方',
                name: 'creditorUserName',
                width: 50
            }, {
                label: '本金',
                name:'loanMoney',
                width: 50
            },  {
                label: '总利息',
                name:'lixiMoney',
                width: 50,
                formatter: function(value) {
                    return value +" 元";
                }
            },  {
                label: '总滞纳金',
                name:'overdueFine',
                width: 50,
                formatter: function(value) {
                    return value +" 元";
                }
            }, {
                label: '结算总额',
                name: 'settleMoney',
                width: 50,
                formatter: function(value) {
                    return value +" 元";
                }
            }, {
                label: '盈利',
                name: 'profit',
                width: 50,
                formatter: function(value) {
                    return value +" 元";
                }
            },{
                label: '状态',
                name: 'loanState',
                width: 100,
                formatter: function(value) {
                    if(value==2||value==21){return "待还款";}
                    if(value==3){return "已还清";}
                    if(value==4){return "已结算";}
                }
            }],
            viewrecords: true,
            height: 385,
            rowNum: 10,
            rowList: [10, 30, 50],
            rownumbers: false,
            rownumWidth: 25,
            autowidth: true,
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
                postData:{'companyName': vm.q.username},
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
