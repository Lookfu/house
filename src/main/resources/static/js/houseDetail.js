
new Vue({
    el:"#house",
    data:{
        title:"",
        imgs:["../image/error.png"],
        img:"../image/error.png",
        price:"",
        room:"",
        hall:"",
        area:"",
        rating:"",
        state:"",
        address:"",
        properties:"",
        remarks:"",
        enable:false,
        houseId:"",
    },
    methods:{
        clickImg:function (img){
            this.img=img;
        },
        like:function (id){
            axios({
                method:'post',
                url:'http://localhost:8080/houseUser/likeHouse',
                params:{
                    houseId:id,
                },
            }).then(function(res){
                var jsonObj = res.data;
                if(jsonObj.status=="fail"&&jsonObj.data.errorCode=="20002"){
                    alert(jsonObj.data.errorMsg+" "+jsonObj.data.errorCode);
                    window.location.href="../html/login_in.html";
                }else if(jsonObj.status=="fail"){
                    alert(jsonObj.data.errorMsg+" "+jsonObj.data.errorCode);
                }else{
                    alert("收藏成功！")
                }
            });
        },
        rent:function (img,houseId,price){
            window.localStorage.setItem('img',img);
            window.localStorage.setItem('houseId',houseId);
            window.localStorage.setItem('price',price);
            window.location.href="../html/orderDetail.html";
        },
    },
    mounted:function (){
        var _this=this;
        var id=window.localStorage.getItem('id');
        _this.houseId=id;
        axios({
            method:'get',
            url:'http://localhost:8080/house/detail',
            params:{
                id:id,
            },
        }).then(function(res){
            var jsonObj = res.data;
            _this.title=jsonObj.data.title;
            _this.price=jsonObj.data.price;
            _this.room=jsonObj.data.room;
            _this.hall=jsonObj.data.hall;
            _this.area=jsonObj.data.area;
            _this.rating=jsonObj.data.rating;
            _this.address=jsonObj.data.cityName+"/"+jsonObj.data.communityName+"/"+jsonObj.data.address;
            _this.properties=jsonObj.data.properties;
            _this.remarks=jsonObj.data.remarks;
            _this.imgs=jsonObj.data.images;
            _this.img=jsonObj.data.images[0];
            if(jsonObj.data.state==1){
                _this.state="上架";
            }else{
                _this.state="下架";
                _this.enable=true;
            }
        });
    }
})