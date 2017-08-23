<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${user.userType==0}">
    <%@ include file="/WEB-INF/page/views/front/seller/header.jsp"%>
</c:if>
<c:if test="${user.userType==1}">
    <%@ include file="/WEB-INF/page/views/front/customer/header.jsp"%>
</c:if>
<c:if test="${user.userType==3}">
    <%@ include file="/WEB-INF/page/views/front/sender/header.jsp"%>
</c:if>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
        .pd-30 {
            padding-left: 30px;
            padding-right: 30px;
        }

        .tx-al-rg {
            text-align: right
        }

        .ellip {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis
        }

        .max-wid-600 {
            max-width: 600px;
        }

        .min-wid-500 {
            min-width: 500px;
        }

        .ms-table tr td {
            height: 35px;
            line-height: 35px;
        }

        .unread {
            font-weight: bolder;
        }

        .grey7 {
            color: #adadad;
        }
        .isreadgold{
             color: #e3ba68
        }
        .isreadgrey{
           color:  #b7b7b7
        }
    </style>
</head>

<body>
    <div class="container1">
        <div class="container2">

       
    <div class="ui container segment" id='app'>
        <h4>财务消息 <span style="float:right" class="grey7 pointer" @click='deleteItem'>删除</span></h4>
        <div class="ui divider"></div>
        <div class="pd-30">
            <table class="ms-table">
                <tbody>
                    <tr>
                        <td>
                            <div class="ui checkbox">
                                <input type="checkbox" name="example" v-model='checkedAll' @change='changeState'>
                                <label>全选</label>
                            </div>
                        </td>
                    </tr>
                    <tr width='100% ' v-for='item in list' v-bind:class="{unread:item.isRead==0}">
                        <td width='25%'>
                            <div class="ui checkbox">
                                <input type="checkbox" name="example" v-model='checkedModel' :value='item.id'>
                                <label>
                                        <i  v-if='item.isRead==0'class="mail icon isreadgold"></i> 
                                        <i  v-if='item.isRead==1'class="mail outline icon isreadgrey"></i> 
                                        {{item.title}}
                                </label>
                            </div>
                        </td>
                        <td width='50%' class="ellip max-wid-600 min-wid-500 pointer"  @click='detailPage(item.id,item.isRead)'>{{item.content}}</td>
                        <td width='25%' class=" tx-al-rg">{{item.date}}</td>
                    </tr>
                </tbody>
                <tfoot  >
                    <tr>
                        <td colspan="6"></td>
                    </tr>
                    <tr>
                        <th colspan="6">
                            <div class="ui right floated pagination menu">
                                <a class="icon item" @click="turnPage(-1)">
                                     <i class="left chevron icon"></i>
                                        </a>
                                <a class="item" v-for="index in indexs" v-bind:class="{ 'active': currentPage == index-1}" v-on:click="btnClick(index)">{{index}}</a>
                                <a class="icon item" @click="turnPage(1)">
                                    <i class="right chevron icon"></i>
                                    </a>
                            </div>
                </tfoot>


            </table>
            <div class="fl-lf" v-if='list.length==0'>
                    暂无消息...
           </div>
        </div>
    </div>
</div>
</div>
</body>
<script>
    const url = '${pageContext.request.contextPath}/mes/financeMesList';
    const url1 = '${pageContext.request.contextPath}/mes/deleteMsgBatch';
    const vm = new Vue({
        el: '#app',
        data: {
            list: [],
            checkedModel: [],
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
                        this.checkedModel.push(value.id)
                    });
                }
            },
            detailPage:function(id,isRead){
               window.location.href='${pageContext.request.contextPath}/mes/messageDetailPage?id='+id+'&isRead='+isRead;
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
                let that = this, page = that.currentPage, limit = that.limit;
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
            deleteItem: function () {
                let ids = this.checkedModel.join(',');
                console.log(ids)
                let r = confirm('确定要删除？');
                if (r == true) {
                    $.ajax({
                        type: 'get',
                        url: url1,
                        data: { ids: ids },
                        dataType: 'json',
                        success: function (result) {
                            if (result.code == 0) {
                                alert('删除成功');
                                // console.log(result.msg);
                                window.location.reload();

                            } else {
                                console.log(result.msg)
                            }
                        },

                    })

                }else{
                    alert('好的不删')
                }


            }

        }



    })

</script>