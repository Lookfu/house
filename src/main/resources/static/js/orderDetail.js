
new Vue({
    el:"#inf",
    data:{
        img:"../image/error.png",
        price:"",
        allPrice:"",
        note:"",
        months:"1",
        houseId:"",
        productList:[{id:1,title:"1个月"},{id:2,title:"2个月"},{id:3,title:"3个月"},{id:4,title:"4个月"},
            {id:5,title:"5个月"},{id:6,title:"6个月"},{id:7,title:"7个月"},{id:8,title:"8个月"},
            {id:9,title:"9个月"},{id:10,title:"10个月"},{id:11,title:"11个月"},{id:12,title:"12个月"}],
        ProductActive:"1",//获取被选中的value值 默认选中的是1
    },
    methods:{
        changeProduct(event) {
            this.ProductActive = event.target.value; //获取option对应的value值
            var value=this.ProductActive;
            this.months=value;
            this.allPrice=this.price*value;
        },
        confirm:function (){
            axios({
                method:'post',
                url:'http://localhost:8080/house/rentHouse',
                params:{
                    houseId:this.houseId,
                    note:this.note,
                    months:this.months,
                },
            }).then(function(res){
                var jsonObj = res.data;
                if(jsonObj.status=="fail"&&jsonObj.data.errorCode=="20002"){
                    alert(jsonObj.data.errorMsg+" "+jsonObj.data.errorCode);
                    window.location.href="../html/login_in.html";
                }else if(jsonObj.status=="fail"){
                    alert(jsonObj.data.errorMsg+" "+jsonObj.data.errorCode);
                }else{
                    alert("租入成功！")
                }
            });
        }
    },
    mounted:function (){
        var img=window.localStorage.getItem('img');
        var houseId=window.localStorage.getItem('houseId');
        var price=window.localStorage.getItem('price');
        //alert(img+" "+houseId+" "+price);
        this.img=img;
        this.price=price;
        this.allPrice=price;
        this.houseId=houseId;
    }
})