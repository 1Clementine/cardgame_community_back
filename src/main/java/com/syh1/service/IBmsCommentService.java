package com.syh1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.syh1.model.dto.CommentDTO;
import com.syh1.model.entity.BmsComment;
import com.syh1.model.entity.UmsUser;
import com.syh1.model.vo.CommentVO;

import java.util.List;


public interface IBmsCommentService extends IService<BmsComment> {
    /**
     *
     *
     * @param topicid
     * @return {@link BmsComment}
     */
    List<CommentVO> getCommentsByTopicID(String topicid);

    BmsComment create(CommentDTO dto, UmsUser principal);
}
