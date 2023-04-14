package com.syh1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syh1.mapper.BmsFollowMapper;
import com.syh1.model.entity.BmsFollow;
import com.syh1.service.IBmsFollowService;
import org.springframework.stereotype.Service;


@Service
public class IBmsFollowServiceImpl extends ServiceImpl<BmsFollowMapper, BmsFollow> implements IBmsFollowService {
}
