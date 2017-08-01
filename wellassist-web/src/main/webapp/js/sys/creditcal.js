 $(function() {
        $("#jqGrid").jqGrid({
            url: '/mes/creditcalist/',
            datatype: "json",
            colModel: [
                { label: '用户ID', name: 'userId', index: "userId", width: 45, key: true },
                {label: '公司类型', name: 'companyType', width: 100,formatter: function (value, options, row) {
                    if(value == 1){
                        return '<span class="label label-success">央企</span>'
                    }
                    if(value == 2){
                        return '<span class="label label-success">国企</span>'
                    }
                    if(value == 3){
                        return '<span class="label label-success">民企</span>'
                    }
                    if(value == 4){
                        return '<span class="label label-success">民企</span>'
                    }
                    if(value == 5){
                        return '<span class="label label-success">上市企业</span>'
                    }
                }},
                {label: '公司名称',name:'companyName',width: 100},
                {label: '公司税号', name: 'companySh', width: 80},
                {label: '公司注册详细地址', name: 'zcXxAddress', width: 80},
                {label: '操作', name: '', width: 75 ,formatter: function (value, grid, rows, state) {
                    return '<button  class="btn btn-primary" onclick="vm.getcreditcal(' + rows.userId + ')">征信</button>'+'<button  class="btn btn-primary" onclick="vm.review(' + rows.userId + ')">查看</button>'
                    }
                }
            ],
            viewrecords: true,
            height: 385,
            rowNum: 10,
            rowList: [10, 30, 50],
            rownumbers: false,
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
                creditName: null
            },
            showList: true,
            title: null,
            roleList: {},
            userinfo: {
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
                vm.creditrecord = {
                    status: 1,
                    roleIdList: []
                };

                //获取角色信息
                this.getRoleList();
            },
            update: function() {
                var id = getSelectedRow();
                if (id == null) {
                    return;
                }

                vm.showList = false;
                vm.title = "征信计算";
                  console.log(vm.userinfo.userId);
                vm.getcreditcal(id);

            },
            getcreditcal: function(userId) {
                $.get("../mes/tocreditcal/"+userId, function(r) {
                    vm.title = "征信计算";
                    vm.userinfo =  r.userinfo;
                });
                vm.showList = false;
            },
            saveOrUpdate: function (event) {
                var url="../mes/creditcalsave/";
                $.ajax({
                    type: "POST",
                    url: url,
                    data: JSON.stringify(vm.userinfo),
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
