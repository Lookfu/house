
new Vue({
    el:"#root",
    data:{
        city:"",
        area:"",
        houses:[],
        page:1,
    },
    methods:{
        test:function () {
        },
        search:function (){
            var _this=this;
            axios({
                method:'get',
                url:'http://localhost:8080/house/search',
                params:{
                    city:this.city,
                    area:this.area,
                },
            }).then(function(res){
                var jsonObj = res.data;
                _this.houses=jsonObj.data;
            });
        },
        send:function (id){
            window.localStorage.setItem('id',id);
            window.location.href="../html/houseDetail.html";
        },
        nextPage:function (){
            var _this=this;
            this.page+=1;
            axios({
                method:'get',
                url:'http://localhost:8080/house/myHouse',
                params:{
                    pageNumber:_this.page,
                },
            }).then(function(res){
                var jsonObj = res.data;
                _this.houses=jsonObj.data;
            });
        },
        prePage:function (){
            var _this=this;
            this.page-=1;
            if(this.page==0){
                this.page=1;
            }
            axios({
                method:'get',
                url:'http://localhost:8080/house/myHouse',
                params:{
                    pageNumber:_this.page,
                },
            }).then(function(res){
                var jsonObj = res.data;
                _this.houses=jsonObj.data;
            });
        }
    },
    mounted:function (){
        var _this=this;
        axios({
            method:'get',
            url:'http://localhost:8080/house/myHouse',
            params:{
                pageNumber:_this.page,
            },
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

function bt() {
    var box = document.getElementById("test")
    if (box.offsetLeft == 0) {
    box.style['margin-left'] = -300 + "px"
    } else {
        box.style['margin-left'] = 0 + "px"
    }
}