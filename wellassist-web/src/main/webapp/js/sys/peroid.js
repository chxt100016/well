/**
 * Created by Administrator on 2017/5/2.
 */
// new Vue({
//     el:"#message",
//     data:{
//         message:'test',
//         message2:'message2'
//     }
// })

var vm = new Vue({
    el:"#message",
    data:{
        message:'test',
        message2:'message'
    }
});

new Vue({
    el: '#example-5',
    data: {
        selected: null
    }
});
function clicktest() {
    alert('test');
}
Vue.config.silent = true;
