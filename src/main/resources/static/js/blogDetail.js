
new Vue({
    el:"#root",
    data:{
        title:"",
        tags:"",
        time:"",
        detail:"",
    },
    methods:{
        clickImg:function (){
        }
    },
    mounted:function (){
        var id=window.localStorage.getItem('id');
        var _this=this;
        axios({
            method:'get',
            url:'http://localhost:8080/blog/detail',
            params:{
                id:id,
            },
        }).then(function(res){
            var jsonObj = res.data;
            _this.title=jsonObj.data.title;
            _this.tags=jsonObj.data.tags;
            _this.time=jsonObj.data.createTime;
            _this.detail=jsonObj.data.content;
        });
    }
})