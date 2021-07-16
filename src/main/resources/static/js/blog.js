
new Vue({
    el:"#root",
    data:{
        blogs:[],
    },
    methods:{
        send:function (id){
            window.localStorage.setItem('id',id);
            window.location.href="../html/blogDetail.html";
        }
    },
    mounted:function (){
        var _this=this;
        axios({
            method:'get',
            url:'http://localhost:8080/blog/listAll',
        }).then(function(res){
            var jsonObj = res.data;
            _this.blogs=jsonObj.data;
        });
    }
})