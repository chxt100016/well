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
            riskDetail:function(){
                var row=getSelectedRowData();
                if (typeof row == undefined ){
                    return ;
                }
                var creditCode=row.creditCode;
                window.location.href=baseUrl+"platform/risk/risk?creditCode="+creditCode;
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

 //选择一条记录
 function getSelectedRowData() {
     var grid = $("#jqGrid");
     var rowKey = grid.getGridParam("selrow");
     if(!rowKey){
         alert("请选择一条记录");
         return ;
     }

     var selectedIDs = grid.getGridParam("selarrrow");
     if(selectedIDs.length > 1){
         alert("只能选择一条记录");
         return ;
     }
     return grid.jqGrid('getRowData',selectedIDs[0]);
 }