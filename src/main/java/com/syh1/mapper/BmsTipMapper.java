package com.syh1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syh1.model.entity.BmsTip;
import org.springframework.stereotype.Repository;

@Repository
public interface BmsTipMapper extends BaseMapper<BmsTip> {
    BmsTip getRandomTip();
}
