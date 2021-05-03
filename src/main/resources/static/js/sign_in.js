
new Vue({
    el:"#signIn",
    data:{
        email:"",
        password:"",
        confirmPasswd:"",
    },
    methods:{
        submit:function (email,password,confirmPasswd){
            axios({
                method:'post',
                url:'http://localhost:8080/user/signIn',
                params:{
                    email:email,
                    password:password,
                    confirmPasswd:confirmPasswd,
                },
            }).then(function(res){
                var jsonObj = res.data;
                if(jsonObj.status=='success'){
                    alert('已发送激活链接');
                }else{
                    alert('激活链接发送失败'+jsonObj.data.errorMsg+" "+jsonObj.data.errorCode);
                }
            });
        },
    },
})