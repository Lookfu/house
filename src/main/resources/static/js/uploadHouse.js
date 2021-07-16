

new Vue({
    el:"#root",
    data:{
        title:"",
        price:"",
        area:"",
        room:"",
        hall:"",
        remarks:"",
        properties:"",
        cityName:"",
        communityName:"",
        address:"",
        state:"",
    },
    methods:{
        file:function (event){
            var file = event.target.files;
            //alert(file.length+" "+file[0].name);
            var formData=new FormData();
            for(let i=0;i<file.length;i++){
                formData.append("files",file[i]);
            }
            axios({
                method:'post',
                url:'http://localhost:8080/house/addHouse',
                headers:{
                    'Content-Type':'multipart/form-data'
                },
                data:formData,
                params:{
                    title: this.title,
                    price: this.price,
                    area: this.area,
                    room: this.room,
                    hall: this.hall,
                    remarks: this.remarks,
                    properties: this.properties,
                    cityName: this.cityName,
                    communityName: this.communityName,
                    address: this.address,
                    state: this.state,
                }
            }).then(function(res){
                var jsonObj = res.data;
                if(jsonObj.status=="fail"&&jsonObj.data.errorCode=="20002"){
                    alert(jsonObj.data.errorMsg+" "+jsonObj.data.errorCode);
                    window.location.href="../html/login_in.html";
                    return ;
                }
                alert(jsonObj.data);
            });
        }
    },
})