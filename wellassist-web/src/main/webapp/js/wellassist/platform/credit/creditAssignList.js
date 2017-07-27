 $(function() {
        $("#jqGrid").jqGrid({
            url: './assignList',
            datatype: "json",
            colModel: [{
                label: '公司名称',
                name: 'customerUserName',
                width: 100
            }, {
                label: '订单编号',
                name: 'orderNo',
                width: 100
            }, {
                label: '贷款金额(元)',
                name:'loanMoney',
                width: 100
            },  {
                label: '利率（%%/日）',
                name:'lixiRate',
                width: 100,
                formatter: function(value) {
                    return value;
                }
            },  {
                label: '放款方',
                name:'creditorUserName',
                width: 100,
                formatter: function(value) {
                    if (typeof value === 'undefined'){
                        return '未指定';
                    }else {
                        return value;
                    }
                }
            }, {
                label: '申请时间',
                name:'applyDate',
                width: 100,
                formatter: function(value) {
                    return value;
                }
            },{
                label: '状态',
                name: 'loanState',
                width: 100,
                formatter: function(value) {
                    if(value==-2){return "拒绝放款";}
                    else if (value==0){return "待指派";}
                    else if (value==1){return "已指派";}
                    else if (value==2){return "已放款";}
                }
            }, {
                label: '操作',
                name: 'loanState',
                width: 80,
                formatter: function(value, options, row) {
                    var loanId=row.loanId;
                    if(value==-2){return '<a  class="btn btn-danger" href="./creditAssign?loanId='+loanId+'">重新指派</a>';}
                    else if(value==0){return '<a  class="btn btn-primary" href="./creditAssign?loanId='+loanId+'">指派</a>';}
                    else if(value==1){return '<a  class="btn btn-warning" onclick="recall('+loanId+')">撤回</a>';}
                    else if(value==2){return '<a  class="btn btn-success" href="./creditLimitDetail?creditId='+loanId+'">查看</a>';}
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
    
    var url1='./assignRollback';

    function recall(x){
        console.log(x);
     $.ajax({  
                    type : "GET",  //提交方式  
                    url : url1,//路径
                    dataType:'json',
                    contentType: "application/json; charset=utf-8",
                    data : {  
                        "loanId" : x 
                    },
                     success : function(result) {//
                        if( result.code ==0 ){
                            console.log("成功")
                            alert('撤销成功啦');
                            window.location.reload();
                        }
                        else{
                             console.log('失败');
                        }
                     }
     })
    // $.post(url1,{"loanId" : loanId },function(result){
    //     if( result.code ==0 ){
    //                         console.log("成功")
    //                     }
    //                     else{
    //                          console.log('失败');
    //                     }
    // })
    }