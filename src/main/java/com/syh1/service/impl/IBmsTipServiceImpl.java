package com.syh1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syh1.mapper.BmsTipMapper;
import com.syh1.model.entity.BmsTip;
import com.syh1.service.IBmsTipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j//自动帮忙定义log对象
@Service
public class IBmsTipServiceImpl extends ServiceImpl<BmsTipMapper
        , BmsTip> implements IBmsTipService {

    @Override
    public BmsTip getRandomTip() {
        BmsTip todayTip = null;
        try {
            todayTip = this.baseMapper.getRandomTip();
        } catch (Exception e) {
            log.info("tip转化失败");
        }
        return todayTip;
    }
}
