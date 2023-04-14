package com.syh1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.syh1.model.entity.BmsTip;

public interface IBmsTipService extends IService<BmsTip> {
    BmsTip getRandomTip();
}
