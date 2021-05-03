package com.example.house.controller;

import com.example.house.constants.RedisConstants;
import com.example.house.controller.viewobject.SimpleHotHouseVO;
import com.example.house.error.BusinessException;
import com.example.house.error.EmBusinessError;
import com.example.house.response.CommonReturnType;
import com.example.house.service.HouseService;
import com.example.house.service.RedisService;
import com.example.house.service.model.HotHouseModel;
import com.example.house.service.model.HouseModel;
import com.example.house.utils.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

import static com.example.house.utils.covert.HouseConvert.simpleHotHouseVOCFhotHouseModel;

@RestController
@RequestMapping("/house")
public class HouseController {
    @Autowired
    private HouseService houseService;
    @Autowired
    private RedisService redisService;
    /**
     *
     * @return
     */
    @GetMapping("/list/all")
    public CommonReturnType houseListOfAll(int pageSize,int pageNumber){
        PageParam pageParam=new PageParam(pageSize,pageNumber);
        //暂时省略从HouseModel到HouseVO的转换
        return CommonReturnType.create(
                houseService.houseListOfAll(pageParam.getLimit(),pageParam.getOffset()));
    }

    @GetMapping("/list/renting")
    public CommonReturnType houseListOfRenting(int pageSize,int pageNumber){

        return null;
    }

    @GetMapping("/list/selling")
    public CommonReturnType houseListOfSelling(int pageSize,int pageNumber){

        return null;
    }

    @GetMapping("/list/selves")
    public CommonReturnType houseListOfSelves(int pageSize,int pageNumber){

        return null;
    }

    /************************************获取房屋详情****************************************/

    @GetMapping("/detail")
    public CommonReturnType houseDetail(int id) throws BusinessException{
        HouseModel houseModel=houseService.houseDetail(id);
        if(houseModel==null){
            throw new BusinessException(EmBusinessError.HOUSE_NOT_EXIST);
        }
        //redisService.setAndIncreaseZset(RedisConstants.HOT_HOUSE,id,1,RedisConstants.HOT_HOUSE_LENGTH);
        return CommonReturnType.create(houseModel);
    }

    /************************************获取热点房屋************************************/
    @GetMapping("/hotHouse")
    public CommonReturnType hotHouse(){
        /*List<HotHouseModel> hotHouseModels=houseService.hotHouse(RedisConstants.HOT_HOUSE_SHOW);
        List<SimpleHotHouseVO> simpleHotHouseVOS=new LinkedList<>();
        for(HotHouseModel e:hotHouseModels){
            SimpleHotHouseVO simple=simpleHotHouseVOCFhotHouseModel(e);
            simpleHotHouseVOS.add(simple);
        }
        return CommonReturnType.create(simpleHotHouseVOS);*/

        List<SimpleHotHouseVO> simpleHotHouseVOS=new LinkedList<>();
        for(int i=0;i<10;i++){
            SimpleHotHouseVO simpleHotHouseVO=new SimpleHotHouseVO();
            simpleHotHouseVO.setId(i+22);
            simpleHotHouseVO.setArea(200);
            simpleHotHouseVO.setRoom(3);
            simpleHotHouseVO.setHall(1);
            simpleHotHouseVO.setHot(1000000);
            simpleHotHouseVO.setPrice(150);
            simpleHotHouseVO.setTitle("万科嘉园 小三房 满两年 小区中间位置 诚心出售");
            simpleHotHouseVO.setImage("/image/pic1.jpg");
            simpleHotHouseVOS.add(simpleHotHouseVO);
        }
        return CommonReturnType.create(simpleHotHouseVOS);
    }
}
