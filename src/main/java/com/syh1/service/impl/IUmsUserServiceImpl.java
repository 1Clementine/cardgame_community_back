package com.syh1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syh1.common.exception.ApiAsserts;
import com.syh1.jwt.JwtUtil;
import com.syh1.mapper.BmsFollowMapper;
import com.syh1.mapper.BmsTopicMapper;
import com.syh1.mapper.UmsUserMapper;
import com.syh1.model.dto.LoginDTO;
import com.syh1.model.dto.RegisterDTO;
import com.syh1.model.entity.BmsFollow;
import com.syh1.model.entity.BmsPost;
import com.syh1.model.entity.UmsUser;
import com.syh1.model.vo.ProfileVO;
import com.syh1.service.IUmsUserService;
import com.syh1.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import java.util.Date;



@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class IUmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements IUmsUserService {

    @Autowired
    private BmsTopicMapper bmsTopicMapper;
    @Autowired
    private BmsFollowMapper bmsFollowMapper;

    @Override
    public UmsUser executeRegister(RegisterDTO dto) {
        //查询是否有相同用户名的用户
        LambdaQueryWrapper<UmsUser> wrapper = new LambdaQueryWrapper<>();//LambdaQueryWrapper是mybatis中一个对象，对应查找条件
        wrapper.eq(UmsUser::getUsername, dto.getName()).or().eq(UmsUser::getEmail, dto.getEmail());//（数据库中）用户名和客户端传过来（dto）的用户名或邮箱是否有重复
        UmsUser umsUser = baseMapper.selectOne(wrapper);//mybatis-plus提供的selectOne查找一条记录，将其赋给umsUser
        if (!ObjectUtils.isEmpty(umsUser)) {//如果存在此对象（对象不为空）
            ApiAsserts.fail("账号或邮箱已存在！");//有重复则不能使用
        }
        UmsUser addUser = UmsUser.builder()//若不存在此对象，则创建一个实体层的builder对象
                .username(dto.getName())
                .alias(dto.getName())
                .password(MD5Utils.getPwd(dto.getPass()))//getPass为MD5Utils提供的方法
                .email(dto.getEmail())
                .createTime(new Date())
                .status(true)
                .build();
        baseMapper.insert(addUser);

        return addUser;
    }
    @Override
    public UmsUser getUserByUsername(String username) {//查找数据库中用户信息（看是否存在此人）
        return baseMapper.selectOne(new LambdaQueryWrapper<UmsUser>().eq(UmsUser::getUsername, username));
    }
    @Override
    public String executeLogin(LoginDTO dto) {
        String token = null;
        try {
            UmsUser user = getUserByUsername(dto.getUsername());
            String encodePwd = MD5Utils.getPwd(dto.getPassword());//将dto前台传过来的密码进行加密
            if(!encodePwd.equals(user.getPassword()))//加密后和数据库中密码对比
            {
                throw new Exception("密码错误");
            }
            token = JwtUtil.generateToken(String.valueOf(user.getUsername()));//用户名作为生成token的种子
        } catch (Exception e) {
            log.warn("用户不存在or密码验证失败=======>{}", dto.getUsername());
        }
        return token;//返回给controller层
    }
    @Override
    public ProfileVO getUserProfile(String id) {
        ProfileVO profile = new ProfileVO();
        UmsUser user = baseMapper.selectById(id);
        BeanUtils.copyProperties(user, profile);
        // 用户文章数
        int count = bmsTopicMapper.selectCount(new LambdaQueryWrapper<BmsPost>().eq(BmsPost::getUserId, id));
        profile.setTopicCount(count);

        // 粉丝数
        int followers = bmsFollowMapper.selectCount((new LambdaQueryWrapper<BmsFollow>().eq(BmsFollow::getParentId, id)));
        profile.setFollowerCount(followers);

        return profile;
    }
}
