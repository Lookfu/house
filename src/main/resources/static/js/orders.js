

new Vue({
    el:"#table",
    data:{
        items:[{}],
    },
    methods:{
        clickImg:function (img){
            this.img=img;
        }
    },
    mounted:function (){
        var _this=this;
        axios({
            method:'get',
            url:'http://localhost:8080/order/list',
        }).then(function(res){
            var jsonObj = res.data;
            if(jsonObj.status=="fail"&&jsonObj.data.errorCode=="20002"){
                alert(jsonObj.data.errorMsg+" "+jsonObj.data.errorCode);
                window.location.href="../html/login_in.html";
                return ;
            }
            _this.items=jsonObj.data;
        });
    }
})