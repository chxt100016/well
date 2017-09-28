<%@ include file="../header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div class="container1">
    <div class="container2">
        <div class="ui container segment " id='sb'>
                <h4 class="ui header">发票列表</h4>
                <div class="ui divider"></div>
                <table class="ui table celled">
                    <thead> 
                        <tr>
                            <th>#</th>
                            <th>公司名称</th>
                            <th>发票总额</th>
                            <th width='20%'>商品订单号：</th>
                            <th>发出时间</th>
                            <th>发票状态</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-if='list.length==0'>
                            <td>暂未数据...</td>
                        </tr>
                        <tr v-for='(item,index) in list' v-cloak>
                            <td>{{index+1}}</td>
                            <td>{{item.customerUserName}}</td>
                            <td>{{item.billMoney}}</td>
                            <td>{{item.orderNos}}</td>
                            <td>{{item.applyDate}}</td>
                            <td>
                                <span v-if='item.billState==-1'>收到否认</span>
                                <span v-if='item.billState==0'>申请</span>
                                <span v-if='item.billState==1'>发送中</span>
                                <span v-if='item.billState==2'>收到确认</span>
                            </td>
                        </tr>
                    </tbody>
                    <tfoot>
                            <tr>
                                <th colspan="8">
                                   
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
        
    </div>
</div>
<script>
    const url ='${pageContext.request.contextPath}/seller/handledBillsList';
    const vm = new Vue({
         el: '#sb',
         data: {
             list: [],
             checkedModel: [],
             checkedIndex: [],
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
                         console.log(that.list);

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
           

         }

     })
</script>

<%@ include file="../footer.jsp"%>