

new Vue({
    el:"#inf",
    data:{
        img:"../image/avatar.png",
        id:"",
        name:"",
        phone:"",
        email:"",
        aboutMe:"",
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
            url:'http://localhost:8080/user/inf',
        }).then(function(res){
            var jsonObj = res.data;
            if(jsonObj.status=="fail"&&jsonObj.data.errorCode=="20002"){
                alert(jsonObj.data.errorMsg+" "+jsonObj.data.errorCode);
                window.location.href="../html/login_in.html";
                return ;
            }
            _this.id=jsonObj.data.id;
            _this.name=jsonObj.data.name;
            _this.phone=jsonObj.data.phone;
            _this.email=jsonObj.data.email;
            _this.aboutMe=jsonObj.data.aboutMe;
            _this.img=jsonObj.data.avatar;
        });
    }
})