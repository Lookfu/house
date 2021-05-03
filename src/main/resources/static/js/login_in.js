
new Vue({
    el:"#loginIn",
    data:{
        email:"",
        password:"",
    },
    methods:{
        test:function (){
          alert("test");
        },
        submit:function (email,password){
            axios({
                method:'post',
                url:'http://localhost:8080/user/loginIn/email',
                params:{
                    email:email,
                    password:password,
                },
            }).then(function(res){
                var jsonObj = res.data;
                if(jsonObj.status=='success'){
                    window.location.href="../html/index.html";
                }else{
                    alert('登录失败'+jsonObj.data.errorMsg+" "+jsonObj.data.errorCode);
                }
            });
        },
    },
})