 $(function() {
        $("#jqGrid").jqGrid({
            url: '/mes/creditcalist/',
            datatype: "json",
            colModel: [
                { label: 'ID', name: 'id', index: "id", width: 45, key: true },
                {label: '用户ID', name: 'userId', width: 100},
                {label: '企业ID',name: 'enterpriseId',width: 100},
                {label: '信誉等级',name:'creditLevel',width: 100},
                {label: '信誉类型',name:'creditType',width: 100},
                {label: '等级发生时间',name:'creditDate',width: 100},
                {label: '评定机构',name: 'evaluationInstitution', width: 100},
                {label: '备注', name: 'memo', width: 80},
                {name: '操作',index:'ope',formatter:function(value,grid,rows,state){
                    return "<a href=\"/mes/tocreditcal?id="+rows.id+"\" style=\"color:#f60\">征信</a>&nbsp;&nbsp;"
                        +"<a href=\"#\" style=\"color:#f60\" onclick=\"tovalue("+value+")\">查看</a>"
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
                creditName: null
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
                alert("ssssssss");
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
            toModify: function(id) {

                if (id == null) {
                    return;
                }
                alert(id);
                vm.showList = false;
                vm.title = "修改";
                console.log(vm.creditName);
                vm.getUser(userId);

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
