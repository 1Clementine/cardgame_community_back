package com.syh1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syh1.mapper.BmsBillboardMapper;
import com.syh1.model.entity.BmsBillboard;
import com.syh1.service.IBmsBillboardService;
import org.springframework.stereotype.Service;

@Service
public class IBmsBillboardServiceImpl extends ServiceImpl<BmsBillboardMapper
        , BmsBillboard> implements IBmsBillboardService {//继承mapper类和实体类billboard,并且实现接口IBmsBillboardService

}
