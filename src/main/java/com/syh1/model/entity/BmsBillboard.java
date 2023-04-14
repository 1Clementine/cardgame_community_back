package com.syh1.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


@Data//自动生成get set方法
@Builder//基于建造者模式支持链式操作
@Accessors(chain = true)//支持链式擦欧总，减少多余对象的创建，减少builder类元信息避免大材小用
@TableName("bms_billboard")//Mybatis注解，对应class BmsBillboard
@NoArgsConstructor//无参构造
@AllArgsConstructor//有参构造
public class BmsBillboard implements Serializable {//implements Serializable方便进行序列化

    private static final long serialVersionUID = 1L;
    //以下均和数据库billboard中实体一一对应
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)//自增长
    private Integer id;

    /**
     * 公告牌
     */
    @TableField("content")
    private String content;

    /**
     * 公告时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 1：展示中，0：过期
     */
    @Builder.Default
    @TableField("`show`")
    private boolean show = false;

}
