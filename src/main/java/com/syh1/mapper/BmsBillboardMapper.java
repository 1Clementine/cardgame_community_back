package com.syh1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syh1.model.entity.BmsBillboard;
import org.springframework.stereotype.Repository;

@Repository//依赖注入
public interface BmsBillboardMapper extends BaseMapper<BmsBillboard> {//增删改查（Mybatis-plus）
    //BmsBillboard是实体类
}
