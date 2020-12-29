package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import pers.qianyucc.qblog.dao.*;
import pers.qianyucc.qblog.exception.*;
import pers.qianyucc.qblog.model.dto.*;
import pers.qianyucc.qblog.model.entity.*;
import pers.qianyucc.qblog.model.vo.*;

import java.util.*;
import java.util.stream.*;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.*;

@Slf4j
@Service
public class MessageService {
    @Autowired
    private MessageMapper messageMapper;

    public PageVO<MessageVO> getMessages(Integer page, Integer limit) {
        Page<MessagePO> poPage = messageMapper.selectPage(new Page<>(page, limit), null);
        List<MessageVO> messageVOS = poPage.getRecords().stream()
                .map(MessageVO::fromMessagePO)
                .collect(Collectors.toList());
        PageVO<MessageVO> pageVO = PageVO.<MessageVO>builder()
                .records(messageVOS.isEmpty() ? new ArrayList<>() : messageVOS)
                .total(poPage.getTotal())
                .current(poPage.getCurrent())
                .size(poPage.getSize())
                .build();
        return pageVO;
    }

    public void insMessage(MessageDTO messageDTO) {
        MessagePO messagePO = messageDTO.toMessagePO();
        messageMapper.insert(messagePO);
    }

    public MessageVO findById(Long id) {
        MessagePO messagePO = messageMapper.selectById(id);
        MessageVO vo = MessageVO.fromMessagePO(messagePO);
        return vo;
    }

    public void deleteMessage(Long id) {
        int i = messageMapper.deleteById(id);
        if (i <= 0) {
            throw new BlogException(INVALID_ID);
        }
    }

    public void updateById(Long id, ReplyMessageDTO replyMessageDTO) {
        MessagePO messagePO = messageMapper.selectById(id);
        if (Objects.isNull(messagePO)) {
            throw new BlogException(INVALID_ID);
        }
        MessagePO messageForUpdate = new MessagePO();
        messageForUpdate.setId(messagePO.getId());
        Integer status = replyMessageDTO.getStatus();
        if (Objects.nonNull(status)) {
            messageForUpdate.setStatus(status);
        }
        String content = replyMessageDTO.getReplyContent();
        if (Objects.nonNull(content)) {
            messageForUpdate.setReplyContent(content);
            messageForUpdate.setGmtReply(System.currentTimeMillis());
        }
        messageMapper.updateById(messageForUpdate);
    }
}
