
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
            var _this=this;
            axios({
                method:'get',
                url:'http://localhost:8080/house/hotHouse'
            }).then(function(res){

            });
        },
    },
    mounted:function (){
        var _this=this;
        axios({
            method:'get',
            url:'http://localhost:8080/house/hotHouse'
        }).then(function(res){
            var jsonObj = res.data;
            //console.log(jsonObj.data);
            _this.houses=jsonObj.data;
        });
    }
})

function bt() {
    var box = document.getElementById("test")
    if (box.offsetLeft == 0) {
    box.style['margin-left'] = -300 + "px"
    } else {
        box.style['margin-left'] = 0 + "px"
    }
}