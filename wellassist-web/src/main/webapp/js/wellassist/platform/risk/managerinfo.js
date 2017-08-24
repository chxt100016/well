 $(function() {
        $("#jqGrid").jqGrid({
            url: baseUrl+"platform/risk/managerList",
            datatype: "json",
            colModel: [{
                label: '姓名',
                name: 'name',
                width: 75
            },{
                label: '职位',
                width: 90,
                name:'position'
            },{
                label: '身份证',
                width: 90,
                name:'identityCard'
            },{
                label: '企业工信号',
                width: 90,
                name:'creditCode'
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
                queryStr: null
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
                postData:{'queryStr': vm.q.queryStr},
                page:1 
            }).trigger("reloadGrid");
            },
            update: function(userId) {


            },
            del: function() {

            },
            saveOrUpdate: function(event) {

            },
            reload: function(event) {
                vm.showList = true;
                var page = $("#jqGrid").jqGrid('getGridParam', 'page');
                $("#jqGrid").jqGrid('setGridParam', {
                    postData: {
                        'queryStr': vm.q.queryStr
                    },
                    page: page
                }).trigger("reloadGrid");
            }
        }
    });
