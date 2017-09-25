<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
        <div class="container1">
            <div class="container2">
                <div class="ui segment container" >
                    <div class="column">
                        <h4>结算账单</h4>
                        <div class="ui divider"></div>
                    </div>
                    <div class="ui top attached tabular menu">
                        <a class="item active" data-tab="first">订单列表</a>
                        <a class="item" data-tab="second">物流订单列表</a>

                    </div>
                    <div class="ui bottom attached tab segment active" data-tab="first" id='sb'>
                        <table class="ui celled table">
                            <thead>
                                <tr>
                                    <th>x#</th>
                                    <th width='10%'>品类</th>
                                    <th width='20%'>出货方</th>
                                    <th>订单号</th>
                                    <th>详情</th>
                                    <th>交易时间（完成时间）</th>
                                    <th>金额</th>
                                    <th>状态</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for='(item,index) in list'>
                                    <td>
                                        <div class="ui fitted checkbox">
                                            <input type="checkbox" v-model='checkedModel' :value='item'>
                                            <label></label>
                                        </div>
                                    </td>
                                    <td>{{item.prodName}}</td>
                                    <td>{{item.sellerUserName}}</td>
                                    <td>{{item.orderNo}}</td>
                                    <td>
                                        <a href="#">查看</a>
                                    </td>
                                    <td>{{item.completeDate}}</td>
                                    <td>{{item.saleSjMoney}}元</td>
                                    <td>
                                        未开发票
                                    </td>
                                </tr>

                            </tbody>
                            <tfoot>
                                <tr>
                                    <th colspan="8">
                                        <div class="ui small primary button" @click='Approve'>
                                           申请
                                        </div>
                                        <div class="ui right floated pagination menu">
                                                <a class="icon item" @click="turnPage(-1)">
                                                     <i class="left chevron icon"></i>
                                                        </a>
                                                <a class="item" v-for="index in indexs" v-bind:class="{ 'active': currentPage == index-1}" v-on:click="btnClick(index)">{{index}}</a>
                                                <a class="icon item" @click="turnPage(1)">
                                                    <i class="right chevron icon"></i>
                                                    </a>
                                        </div>
                
                                    </th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                    <div class="ui bottom attached tab segment" data-tab="second" id='sb2'> 
                            <table class="ui celled table">
                                    <thead>
                                        <tr>
                                            <th>x#</th>
                                            <th width='10%'>品类</th>
                                            <th width='20%'>出货方</th>
                                            <th>订单号</th>
                                            <th>详情</th>
                                            <th>交易时间（完成时间）</th>
                                            <th>金额</th>
                                            <th>状态</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr v-for='(item,index) in list'>
                                            <td>
                                                <div class="ui fitted checkbox">
                                                    <input type="checkbox" v-model='checkedModel' :value='item'>
                                                    <label></label>
                                                </div>
                                            </td>
                                            <td>{{item.prodName}}</td>
                                            <td>{{item.senderUserName}}</td>
                                            <td>{{item.orderNo}}</td>
                                            <td>
                                                <a href="#">查看</a>
                                            </td>
                                            <td>{{item.completeDate}}</td>
                                            <td>{{item.prePayment}}元</td>
                                            <td>
                                                未开发票
                                            </td>
                                        </tr>
        
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th colspan="8">
                                                <div class="ui small primary button" @click='Approve'>
                                                    申请
                                                </div>
                                                <div class="ui right floated pagination menu">
                                                        <a class="icon item" @click="turnPage(-1)">
                                                             <i class="left chevron icon"></i>
                                                                </a>
                                                        <a class="item" v-for="index in indexs" v-bind:class="{ 'active': currentPage == index-1}" v-on:click="btnClick(index)">{{index}}</a>
                                                        <a class="icon item" @click="turnPage(1)">
                                                            <i class="right chevron icon"></i>
                                                            </a>
                                                </div>
                        
                                            </th>
                                        </tr>
                                    </tfoot>
                                </table>

                    </div>
                    <!-- <pre>{{ checkedModel | json }}</pre> -->

                </div>

            </div>

        </div>
        <script>
            $(function () {
                $('.menu .item').tab();
            })
        </script>
        <script>
            const url = '${pageContext.request.contextPath}/customer/billAvaliableOrderList';
            const vm = new Vue({
                el: '#sb',
                data: {
                    list: [],
                    checkedModel: [],
                    checkedIndex: [],
                    checkedsupId: [],
                    checkedOrderId:[],
                    bool:true,
                    checkedAll: false,
                    limit: '', // 每页显示行数
                    totalPage: '', // 总页数
                    currentPage: '', // 当前页
                },
                created: function () {
                    let that = this;
                    $.ajax({
                        type: 'get',
                        url: url,
                        data: { page: 1, limit: 10 },
                        dataType: 'json',
                        success: function (result) {
                            if (result.code == 0) {
                                console.log(result);
                                that.list = result.page.list;
                                that.totalPage = result.page.totalPage;
                                that.limit = 10;
                                that.currentPage = result.page.currPage - 1;
                                // console.log(result.page);

                            } else {
                                console.log(result.msg)
                            }
                        },

                    })
                },
                computed: {
                    indexs: function () {
                        var left = 1;
                        var right = this.totalPage;
                        var ar = [];
                        if (this.totalPage >= 5) {
                            if (this.currentPage > 3 && this.currentPage < this.totalPage - 2) {
                                left = this.currentPage - 2
                                right = this.currentPage + 2
                            } else {
                                if (this.currentPage <= 3) {
                                    left = 1
                                    right = 5
                                } else {
                                    right = this.totalPage
                                    left = this.totalPage - 4
                                }
                            }
                        }
                        while (left <= right) {
                            ar.push(left)
                            left++
                        }
                        return ar
                    }
                },

                watch: {
                    checkedModel: {
                        handler() {
                            if (this.checkedModel.length == this.list.length) {
                                this.checkedAll = true
                            } else {
                                this.checkedAll = false
                            }
                        },
                        deep: true
                    },
                    // currentPage:function (oldValue, newValue) {
                    //          console.log(newValue);

                    //     }
                },
                methods: {
                    changeState(item) {
                        this.checkedModel = []
                        if (this.checkedAll == true) {
                            this.list.forEach((value, index) => {
                                this.checkedModel.push(value);
                                
                            });
                        }
                    },
                    detailPage: function (id, isRead) {
                        window.location.href = '/wellassist/mes/messageDetailPage?id=' + id + '&isRead=' + isRead;
                    },

                    //分页

                    btnClick: function (data) {//页码点击事件
                        var that = this;
                        if (data != this.currentPage + 1) {
                            this.currentPage = data - 1;

                        }
                        // console.log(data);
                        that.currentPage = data;

                        this.getList();
                    },
                    turnPage(num) {
                        if (num === 1) {
                            if (this.currentPage === this.totalPage - 1) {
                                return
                            } else {
                                this.currentPage++;

                            }

                        } else {
                            if (this.currentPage === 0) {
                                return
                            } else {
                                this.currentPage--
                            }
                        }
                        let that = this, page = this.currentPage + 1, limit = this.limit;
                        $.ajax({
                            type: 'get',
                            url: url,
                            data: { page: page, limit: limit },
                            dataType: 'json',
                            success: function (result) {
                                if (result.code == 0) {
                                    that.list = result.page.list;
                                    that.totalPage = result.page.totalPage;
                                    that.currentPage = result.page.currPage - 1;
                                    // console.log(result.page);

                                } else {
                                    console.log(result.msg)
                                }
                            },

                        })

                    },
                    getList: function () {
                        let that = this, page = vm.currentPage, limit = vm.limit;
                        // console.log('当前页' + page)
                        $.ajax({
                            type: 'get',
                            url: url,
                            data: { page: page, limit: limit },
                            dataType: 'json',
                            success: function (result) {
                                if (result.code == 0) {
                                    that.list = result.page.list;
                                    that.totalPage = result.page.totalPage;
                                    that.currentPage = result.page.currPage - 1;
                                    // console.log(result.page);

                                } else {
                                    console.log(result.msg)
                                }
                            },

                        })
                    },
                    Approve:function(){
                        let checkmodel= this.checkedModel;
                        let checkOrder=[];
                        let supplierId=[];
                        // 遍历选中的数组的订单号和卖家名称
                    for (var j = 0; j < checkmodel.length; j++) {
                        supplierId.push(checkmodel[j].supplierId);
                        // supplierId.push(checkmodel[j].orderId);
                        checkOrder.push(checkmodel[j].orderId);
                    }
                    console.log(supplierId);
                    // 判断是否为同一卖家
                    for (var i = 0; i < supplierId.length; i++) {
                            if(supplierId[i]!==supplierId[0]){
                                vm.bool =false;
                            }
                        }
                    // 申请发票
                    if(this.bool){
                        console.log('correct!');
                        this.checkedOrderId=checkOrder;
                       
                        window.location.href='${pageContext.request.contextPath}/customer/goOrderBillRequest?ids='+ this.checkedOrderId;
                    }else{
                        alert('请选择相同出货方的订单！')
                    }
                       
                    },
                    // ApproveAjax:function(){
                    //     let  tourl='${pageContext.request.contextPath}/customer/goOrderBillRequest';
                    //     let ids= this.checkedOrderId;
                    //     $.ajax({
                    //         type:'post', 
                    //         url:tourl,
                    //         data:{},
                    //         dataType:'json',
                    //         success:
                    //                 function(result){
                    //                         if(result.code==0){

                    //                         }
                    //                         else{
                    //                                     console.log(result.msg)
                    //                             }
                    //                                 }


                    //         })
                    // }

                }

            })
        </script>

        <script>
             const url2 = '${pageContext.request.contextPath}/customer/billAvaliableLogisticsList';
            const vmm = new Vue({
                el: '#sb2',
                data: {
                    list: [],
                    checkedModel: [],
                    checkedIndex: [],
                    checkedsupId: [],
                    checkedOrderId:[],
                    bool:true,
                    checkedAll: false,
                    limit: '', // 每页显示行数
                    totalPage: '', // 总页数
                    currentPage: '', // 当前页
                },
                created: function () {
                    let that = this;
                    $.ajax({
                        type: 'get',
                        url: url2,
                        data: { page: 1, limit: 10 },
                        dataType: 'json',
                        success: function (result) {
                            if (result.code == 0) {
                                console.log(result);
                                that.list = result.page.list;
                                that.totalPage = result.page.totalPage;
                                that.limit = 10;
                                that.currentPage = result.page.currPage - 1;
                                // console.log(result.page);

                            } else {
                                console.log(result.msg)
                            }
                        },

                    })
                },
                computed: {
                    indexs: function () {
                        var left = 1;
                        var right = this.totalPage;
                        var ar = [];
                        if (this.totalPage >= 5) {
                            if (this.currentPage > 3 && this.currentPage < this.totalPage - 2) {
                                left = this.currentPage - 2
                                right = this.currentPage + 2
                            } else {
                                if (this.currentPage <= 3) {
                                    left = 1
                                    right = 5
                                } else {
                                    right = this.totalPage
                                    left = this.totalPage - 4
                                }
                            }
                        }
                        while (left <= right) {
                            ar.push(left)
                            left++
                        }
                        return ar
                    }
                },

                watch: {
                    checkedModel: {
                        handler() {
                            if (this.checkedModel.length == this.list.length) {
                                this.checkedAll = true
                            } else {
                                this.checkedAll = false
                            }
                        },
                        deep: true
                    },
                    // currentPage:function (oldValue, newValue) {
                    //          console.log(newValue);

                    //     }
                },
                methods: {
                    changeState(item) {
                        this.checkedModel = []
                        if (this.checkedAll == true) {
                            this.list.forEach((value, index) => {
                                this.checkedModel.push(value);
                                
                            });
                        }
                    },
                    detailPage: function (id, isRead) {
                        window.location.href = '/wellassist/mes/messageDetailPage?id=' + id + '&isRead=' + isRead;
                    },

                    //分页

                    btnClick: function (data) {//页码点击事件
                        var that = this;
                        if (data != this.currentPage + 1) {
                            this.currentPage = data - 1;

                        }
                        // console.log(data);
                        that.currentPage = data;

                        let  page = vmm.currentPage, limit = vmm.limit;
                        // console.log('当前页' + page)
                        $.ajax({
                            type: 'get',
                            url: url2,
                            data: { page: page, limit: limit },
                            dataType: 'json',
                            success: function (result) {
                                if (result.code == 0) {
                                    that.list = result.page.list;
                                    that.totalPage = result.page.totalPage;
                                    that.currentPage = result.page.currPage - 1;
                                    // console.log(result.page);

                                } else {
                                    console.log(result.msg)
                                }
                            },

                        })
                    },
                    turnPage(num) {
                        if (num === 1) {
                            if (this.currentPage === this.totalPage - 1) {
                                return
                            } else {
                                this.currentPage++;

                            }

                        } else {
                            if (this.currentPage === 0) {
                                return
                            } else {
                                this.currentPage--
                            }
                        }
                        let that = this, page = this.currentPage + 1, limit = this.limit;
                        $.ajax({
                            type: 'get',
                            url: url2,
                            data: { page: page, limit: limit },
                            dataType: 'json',
                            success: function (result) {
                                if (result.code == 0) {
                                    that.list = result.page.list;
                                    that.totalPage = result.page.totalPage;
                                    that.currentPage = result.page.currPage - 1;
                                    // console.log(result.page);

                                } else {
                                    console.log(result.msg)
                                }
                            },

                        })

                    },
                    getList: function () {
                        let that = this, page = vmm.currentPage, limit = vmm.limit;
                        // console.log('当前页' + page)
                        $.ajax({
                            type: 'get',
                            url: url2,
                            data: { page: page, limit: limit },
                            dataType: 'json',
                            success: function (result) {
                                if (result.code == 0) {
                                    that.list = result.page.list;
                                    that.totalPage = result.page.totalPage;
                                    that.currentPage = result.page.currPage - 1;
                                    // console.log(result.page);

                                } else {
                                    console.log(result.msg)
                                }
                            },

                        })
                    },
                    Approve:function(){
                        let checkmodel= this.checkedModel;
                        let checkOrder=[];
                        let senderUserId=[];
                        // 遍历选中的数组的订单号和卖家名称
                    for (var j = 0; j < checkmodel.length; j++) {
                        senderUserId.push(checkmodel[j].senderUserId);
                        // supplierId.push(checkmodel[j].orderId);
                        checkOrder.push(checkmodel[j].orderId);
                    }
                    console.log(senderUserId);
                    // 判断是否为同一卖家
                    for (var i = 0; i < senderUserId.length; i++) {
                            if(senderUserId[i]!==senderUserId[0]){
                                vm.bool =false;
                            }
                        }
                    // 申请发票
                    if(this.bool){
                        console.log('correct!');
                        this.checkedOrderId=checkOrder;
                       
                        window.location.href='${pageContext.request.contextPath}/customer/goLogisticsBillRequest?ids='+ this.checkedOrderId;
                    }else{
                        alert('请选择相同出货方的订单！')
                    }
                       
                    },
                    

                }

            })
        </script>

        <%@ include file="../footer.jsp"%>