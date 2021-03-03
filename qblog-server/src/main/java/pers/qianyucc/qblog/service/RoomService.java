package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.qianyucc.qblog.dao.RoomMapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.RoomDTO;
import pers.qianyucc.qblog.model.entity.DoctorPO;
import pers.qianyucc.qblog.model.entity.RoomPO;
import pers.qianyucc.qblog.model.vo.RoomVO;

import java.util.*;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

@Slf4j
@Service


public class RoomService {
    @Autowired
    private RoomMapper roomMapper;
    @Transactional(rollbackFor = Exception.class)

    public void insRoom(RoomDTO roomDTO) {
        RoomPO roomPO = roomDTO.toRoomPO();
        roomMapper.insert(roomPO);
    }

    public void deleteRoom(String roomid){
        Map<String,Object> map =new HashMap<>();
        map.put("roomid",roomid);
        roomMapper.deleteByMap(map);
    }

    public void updateRoom(RoomDTO roomDTO, String roomid){
        RoomPO dbroom = roomMapper.selectById(roomid);
        if (Objects.isNull(dbroom)) {
            throw new BlogException(INVALID_ID);
        }
        RoomPO roomPO = roomDTO.toRoomPO();
//        System.out.println(roomPO.getRoomid());
        roomPO.setRoomid(roomid);
        roomMapper.updateById(roomPO);
    }

    public List<RoomVO> getAllRooms(){
        ArrayList res = new ArrayList<>();
        QueryWrapper<RoomPO> wrapper = new QueryWrapper<>();
        wrapper.select("name","address","roomid");
        List<Map<String, Object>> maps = roomMapper.selectMaps(wrapper);
        for(int i =0; i<maps.size(); i++){
            res.add(maps.get(i));
        }
        return res;
    }


}
