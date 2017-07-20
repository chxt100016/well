 $(function() {
        $("#jqGrid").jqGrid({
            url: './creditLimitList',
            datatype: "json",
            colModel: [{
                label: '公司名称',
                name: 'companyName',
                width: 100
            }, {
                label: '公司联系人',
                name: 'companyLxr',
                width: 100
            }, {
                label: '联系电话',
                name:'companyLxrPhone',
                width: 100
            },  {
                label: '申请额度',
                name:'creditMoney',
                width: 100,
                formatter: function(value, options, row) {
                    return value +" 元";
                }
            },  {
                label: '审批额度',
                name:'creditSjMoney',
                width: 100,
                formatter: function(value, options, row) {
                    if (typeof value === 'undefined'){
                        return '0 元';
                    }else {
                        return value +" 元";
                    }
                }
            }, {
                label: '状态',
                name: 'creditState',
                width: 100,
                formatter: function(value, options, row) {
                    if(value==-1){return "不通过";}
                    else if (value==0){return "待审核";}
                    else if (value==1){return "已通过";}
                    else if (value==2){return "已提额";}
                }
            }, {
                label: '操作',
                name: 'creditState',
                width: 80,
                formatter: function(value, options, row) {
                    var creditId=row.creditId;
                    if(value==0){return '<a  class="btn btn-primary" href="./creditLimitCheck?creditId='+creditId+'">审核</a>';}
                    else{return '<a  class="btn btn-success" href="./creditLimitDetail?creditId='+creditId+'">查看</a>';}
                }
            }],
            viewrecords: true,
            height: 385,
            rowNum: 10,
            rowList: [10, 30, 50],
            rownumbers: true,
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
