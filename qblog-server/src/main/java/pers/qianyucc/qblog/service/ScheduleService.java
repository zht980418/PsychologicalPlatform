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
        wrapper.select("appid","start","end","daysofweek","doctorid");
        List<Map<String, Object>> maps = scheduleMapper.selectMaps(wrapper);
        for(int i =0; i<maps.size(); i++){
            res.add(maps.get(i));
        }
        return res;
    }
}
