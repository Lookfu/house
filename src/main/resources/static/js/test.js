
new Vue({
    //接管的内容，vue于dom绑定
    el:"#root",
    data:{
        msg:"你好",
        title:"this is title"
    },
    methods:{
        test:function () {
            alert(window.localStorage.getItem('user'));
            window.localStorage.removeItem('user');
            this.msg="hello";
        }
    }
})