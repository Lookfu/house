package com.example.house.controller;

import com.example.house.constants.SessionConstants;
import com.example.house.controller.viewobject.HouseVO;
import com.example.house.controller.viewobject.SimpleHotHouseVO;
import com.example.house.controller.viewobject.SimpleHouseVO;
import com.example.house.entity.Orders;
import com.example.house.error.BusinessException;
import com.example.house.error.EmBusinessError;
import com.example.house.response.CommonReturnType;
import com.example.house.service.HouseService;
import com.example.house.service.OrderService;
import com.example.house.service.RedisService;
import com.example.house.service.model.HouseModel;
import com.example.house.service.model.UserModel;
import com.example.house.utils.CommonUtil;
import com.example.house.utils.PageParam;
import com.example.house.utils.covert.HouseConvert;
import com.example.house.validate.entity.HouseVD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/house")
public class HouseController {
    @Autowired
    private HouseService houseService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private OrderService orderService;
    /**
     *
     * @return
     */
    @GetMapping("/list/all")
    public CommonReturnType houseListOfAll(int pageNumber){
        PageParam pageParam=new PageParam(pageNumber);
        List<HouseModel> list= houseService.houseListOfAll(pageParam.getLimit(),pageParam.getOffset());
        List<SimpleHouseVO> res=new ArrayList<>(list.size());
        for(HouseModel e:list){
            SimpleHouseVO simpleHouseVO=HouseConvert.simpleHouseVOCFhouseModel(e);
            res.add(simpleHouseVO);
        }
        return CommonReturnType.create(res);
    }

    @GetMapping("/search")
    public CommonReturnType houseListOfRenting(String city,String area){
        List<HouseModel> list=houseService.searchHouse(city,area);
        List<SimpleHouseVO> res=new ArrayList<>(list.size());
        for(HouseModel e:list){
            SimpleHouseVO simpleHouseVO= HouseConvert.simpleHouseVOCFhouseModel(e);
            res.add(simpleHouseVO);
        }
        return CommonReturnType.create(res);
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
    public CommonReturnType hotHouse(int pageNumber){
        /*List<HotHouseModel> hotHouseModels=houseService.hotHouse(RedisConstants.HOT_HOUSE_SHOW);
        List<SimpleHotHouseVO> simpleHotHouseVOS=new LinkedList<>();
        for(HotHouseModel e:hotHouseModels){
            SimpleHotHouseVO simple=simpleHotHouseVOCFhotHouseModel(e);
            simpleHotHouseVOS.add(simple);
        }
        return CommonReturnType.create(simpleHotHouseVOS);*/

        return houseListOfAll(pageNumber);
    }
    @PostMapping("/rentHouse")
    public CommonReturnType rentHouse(HttpServletRequest request,long houseId,String note,int months) throws BusinessException {
        UserModel userModel=(UserModel)request.getSession().getAttribute(SessionConstants.USER);
        HouseModel houseModel=houseService.houseDetail(houseId);
        if(houseModel==null){
            throw new BusinessException(EmBusinessError.NORMAL_ERROR.setErrorMsg("房产不存在"));
        }
        if(houseModel.getState()==2){
            throw new BusinessException(EmBusinessError.NORMAL_ERROR.setErrorMsg("房产已下架"));
        }
        Orders orders =new Orders();
        orders.setUserId(userModel.getId());
        orders.setId(CommonUtil.getOderId());
        orders.setConfirmed(0);
        orders.setHouseId(houseId);
        orders.setPrice(new BigDecimal(houseModel.getPrice()));
        orders.setNote(note);
        orders.setMonths(months);
        int res=orderService.insertItem(orders);
        if(res==0){
            throw new BusinessException(EmBusinessError.NORMAL_ERROR.setErrorMsg("产生订单失败"));
        }
        res=houseService.unSellHouse(houseId);
        if(res>0){
            return CommonReturnType.create("租入成功！");
        }else{
            throw new BusinessException(EmBusinessError.NORMAL_ERROR.setErrorMsg("设置房产状态失败"));
        }
    }

    @PostMapping("/addHouse")
    public CommonReturnType addHouse(HouseVD houseVD,HttpServletRequest request) throws BusinessException {
        UserModel userModel=(UserModel)request.getSession().getAttribute(SessionConstants.USER);
        long houseId=houseService.addHouse(HouseConvert.houseModelCFHouseVD(houseVD,userModel.getId()));
        for(int i=1;i<=houseVD.getFiles().length;i++){
            boolean isSuccess=saveFile(houseVD.getFiles()[i-1],"E:\\Workspace\\ideaWorkspace\\house\\src\\main\\resources\\static\\image\\house\\"+houseId+"\\"+"pic"+i+".png");
            if(!isSuccess){
                throw new BusinessException(EmBusinessError.NORMAL_ERROR.setErrorMsg("图片保存失败！"));
            }
        }
        return CommonReturnType.create("上传完成！");
    }

    @GetMapping("/myHouse")
    public CommonReturnType myHouse(int pageNumber,HttpServletRequest request){
        UserModel userModel=(UserModel)request.getSession().getAttribute(SessionConstants.USER);
        PageParam pageParam=new PageParam(pageNumber);
        List<HouseModel> list=houseService.myHouse(userModel.getId(),pageParam.getLimit(),pageParam.getOffset());
        List<SimpleHouseVO> res=new ArrayList<>(list.size());
        for(HouseModel e:list){
            res.add(HouseConvert.simpleHouseVOCFhouseModel(e));
        }
        return CommonReturnType.create(res);
    }

    private boolean saveFile(MultipartFile multipartFile,String filePath){
        File desFile=new File(filePath);
        if(!desFile.getParentFile().exists()){
            desFile.mkdirs();
        }
        try {
            multipartFile.transferTo(desFile);
        }catch (IllegalStateException | IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
