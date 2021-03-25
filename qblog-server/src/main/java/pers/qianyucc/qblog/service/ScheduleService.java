package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.qianyucc.qblog.dao.ScheduleMapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.ScheduleDTO;
import pers.qianyucc.qblog.model.entity.SchedulePO;
import pers.qianyucc.qblog.model.vo.ScheduleVO;

import java.util.*;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

@Slf4j
@Service

public class ScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private RoomService roomService;

    @Transactional(rollbackFor = Exception.class)

    public void insSchedule(ScheduleDTO scheduleDTO) {
        SchedulePO schedulePO = scheduleDTO.toSchedulePO();
        scheduleMapper.insert(schedulePO);
    }

    public void deleteSchedule(String appid){
        Map<String,Object> map =new HashMap<>();
        map.put("appid",appid);
        scheduleMapper.deleteByMap(map);
    }

    public void updateSchedule(ScheduleDTO scheduleDTO, String appid){
        SchedulePO dbschedule = scheduleMapper.selectById(appid);
        if (Objects.isNull(dbschedule)) {
            throw new BlogException(INVALID_ID);
        }
        SchedulePO schedulePO = scheduleDTO.toSchedulePO();
        System.out.println(schedulePO.getAppid());
        schedulePO.setAppid(appid);
        scheduleMapper.updateById(schedulePO);
    }

    public List<ScheduleVO> getAllSchedules(){
        ArrayList res = new ArrayList<>();
        QueryWrapper<SchedulePO> wrapper = new QueryWrapper<>();
        wrapper.select("appid","start","end","daysofweek","doctorid","doctorname","roomid");
        List<Map<String, Object>> maps = scheduleMapper.selectMaps(wrapper);
        for(int i =0; i<maps.size(); i++){
            if(maps.get(i).get("roomid")!=null&&Integer.parseInt(maps.get(i).get("roomid").toString())>0){
                String roomid = maps.get(i).get("roomid").toString();
                String roomName = roomService.getRoomNameByRoomID(roomid);
                maps.get(i).put("roomName",roomName);
            }else maps.get(i).put("roomName",null);
            res.add(maps.get(i));
        }
        return res;
    }

    public List<ScheduleVO> getSchedulesByRoomID(String roomid){
        ArrayList res = new ArrayList<>();
        QueryWrapper<SchedulePO> wrapper = new QueryWrapper<>();
        wrapper.select("appid","start","end","daysofweek","doctorid","doctorname","roomid");
        List<Map<String, Object>> maps = scheduleMapper.selectMaps(wrapper);
        for(int i=0; i<maps.size();i++){
            if (maps.get(i).get("roomid")!=null&&maps.get(i).get("roomid").equals(roomid)){
                res.add(maps.get(i));
            }
        }
        return res;
    }
//    根据doctorid返回日程表
    public List<ScheduleVO> getSchedulesByDoctorID(String doctorid){
        ArrayList res = new ArrayList<>();
        QueryWrapper<SchedulePO> wrapper = new QueryWrapper<>();
        wrapper.select("start","end","daysofweek","doctorid","roomid");
        List<Map<String, Object>> maps = scheduleMapper.selectMaps(wrapper);
        for(int i=0; i<maps.size();i++){
            if (maps.get(i).get("roomid")!=null&&!maps.get(i).get("roomid").equals("-1")&&maps.get(i).get("doctorid")!=null&&maps.get(i).get("doctorid").equals(doctorid)){
                res.add(maps.get(i));
            }
        }
        return res;
    }

    public List<String> getStartByRoomID(String roomid) {
        ArrayList res = new ArrayList<>();
        QueryWrapper<SchedulePO> wrapper = new QueryWrapper<>();
        wrapper.select("start","roomid");
        List<Map<String, Object>> maps = scheduleMapper.selectMaps(wrapper);
        for(int i=0; i<maps.size();i++){
            if(maps.get(i).get("roomid")!=null&&maps.get(i).get("roomid").equals(roomid))
                res.add(maps.get(i).get("start"));
        }
        return res;
    }

    public List<String> getDaysofweekByRoomID(String roomid) {
        ArrayList res = new ArrayList<>();
        QueryWrapper<SchedulePO> wrapper = new QueryWrapper<>();
        wrapper.select("roomid","daysofweek");
        List<Map<String, Object>> maps = scheduleMapper.selectMaps(wrapper);
        for(int i=0; i<maps.size();i++){
            if(maps.get(i).get("roomid")!=null&&maps.get(i).get("roomid").equals(roomid))
                res.add(maps.get(i).get("daysofweek"));
        }
        return res;
    }
    public String getStartByappid(String appid){
        SchedulePO schedulePO = scheduleMapper.selectById(appid);
        String res = schedulePO.getStart();
        System.out.println("start:"+res);
        return res;
    }
    public String getDaysofweekByappid(String appid){
        SchedulePO schedulePO = scheduleMapper.selectById(appid);
        String res = schedulePO.getDaysofweek();
        System.out.println("daysofweek:"+res);
        return res;
    }
}
