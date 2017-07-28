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
                {label: '备注', name: 'memo', width: 80}
            ],
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
                creditName: null
            },
            showList: true,
            title: null,
            roleList: {},
            cds: {
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
                  console.log(vm.cds.id);
                vm.getcreditcal(id);

            },
            getcreditcal: function(id) {
                $.get("../mes/tocreditcal/"+id, function(r) {
                    vm.cds =  r.creditrecord;
                });
            },
            saveOrUpdate: function () {
                alert("ha");
                //var url = vm.spartEntity.id == null ? "spar/req/save":"spar/req/update";
                //vm.spartEntity.directiveSendDate=$("#directiveSendDate").val();
                //vm.spartEntity.partStorageDate=$("#partStorageDate").val();
                //vm.spartEntity.directiveReceiveDate=$("#directiveReceiveDate").val();
                //$.ajax({
                //    type: "POST",
                //    url: baseURL + url,
                //    contentType: "application/json",
                //    data: JSON.stringify(vm.spartEntity),
                //    success: function(r){
                //        if(r.code === 0){
                //            alert('操作成功', function(){
                //                vm.reload();
                //            });
                //        }else{
                //            alert(r.msg);
                //        }
                //    }
                //});
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
