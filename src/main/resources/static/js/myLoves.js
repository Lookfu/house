
new Vue({
    el:"#houseList",
    data:{
        houses:[],
    },
    methods:{
        test:function () {
        },
        send:function (id){
            window.localStorage.setItem('id',id);
            window.location.href="../html/houseDetail.html";
        },
    },
    mounted:function (){
        var _this=this;
        axios({
            method:'get',
            url:'http://localhost:8080/houseUser/myLikeHouses'
        }).then(function(res){
            var jsonObj = res.data;
            if(jsonObj.status=="fail"&&jsonObj.data.errorCode=="20002"){
                alert(jsonObj.data.errorMsg+" "+jsonObj.data.errorCode);
                window.location.href="../html/login_in.html";
                return ;
            }
            _this.houses=jsonObj.data;
        });
    }
})