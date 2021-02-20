package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.qianyucc.qblog.dao.RoomMapper;
import pers.qianyucc.qblog.model.dto.RoomDTO;
import pers.qianyucc.qblog.model.entity.RoomPO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service


public class RoomService {
    @Autowired
    private RoomMapper formMapper;
    @Transactional(rollbackFor = Exception.class)

    public void insRoom(RoomDTO formDTO) {
        RoomPO formPO = formDTO.toRoomPO();
        formMapper.insert(formPO);
    }


}
