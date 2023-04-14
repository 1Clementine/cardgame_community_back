package com.syh1.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.syh1.common.api.ApiResult;
import com.syh1.model.entity.BmsBillboard;
import com.syh1.service.IBmsBillboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController//只需返回java对象，底层自动将java对象转成json字符串，客户端收到json字符串
@RequestMapping("/billboard")//指定路径mapper
public class BmsBillboardController extends BaseController {//继承共有BaseController

    @Resource//创建controller对象时自动找到实现类
    private IBmsBillboardService bmsBillboardService;

    @GetMapping("/show")//处理get请求
    public ApiResult<BmsBillboard> getNotices(){
        List<BmsBillboard> list = bmsBillboardService.list(new
                LambdaQueryWrapper<BmsBillboard>().eq(BmsBillboard::isShow,true));//LambdaQueryWrapper指定查询条件
        return ApiResult.success(list.get(list.size()- 1));
    }
}
