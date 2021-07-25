package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.qianyucc.qblog.dao.FormMapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.FormDTO;
import pers.qianyucc.qblog.model.entity.FormPO;
import pers.qianyucc.qblog.model.entity.RoomPO;

import java.util.*;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

@Slf4j
@Service


public class FormService {
    @Autowired
    private FormMapper formMapper;
    @Transactional(rollbackFor = Exception.class)
    public List findByDoctorId(String doctorid) {

        ArrayList res = new ArrayList<>();
        QueryWrapper<FormPO> wrapper = new QueryWrapper<>();
        wrapper.eq("doctorid",doctorid)
        .select("orderid","name","start","end","uid","status");
        List<Map<String, Object>> maps = formMapper.selectMaps(wrapper);
        for(int i =0; i<maps.size(); i++){
            res.add(maps.get(i));
        }
        return res;
    }
    public List findByOrderId(String orderid) {

        ArrayList res = new ArrayList<>();
        QueryWrapper<FormPO> wrapper = new QueryWrapper<>();
        wrapper.eq("orderid",orderid);
        List<Map<String, Object>> maps = formMapper.selectMaps(wrapper);
        for(int i =0; i<maps.size(); i++){
            res.add(maps.get(i));
        }
        return res;
    }
    public List findByRoomId(String roomid) {

        ArrayList res = new ArrayList<>();
        QueryWrapper<FormPO> wrapper = new QueryWrapper<>();
        wrapper.eq("roomid",roomid)
                .select("orderid","name","start","end","status");;
        List<Map<String, Object>> maps = formMapper.selectMaps(wrapper);
        for(int i =0; i<maps.size(); i++){
            res.add(maps.get(i));
        }
        return res;
    }
//    public List findByUid(String uid) {
//
//        ArrayList res = new ArrayList<>();
//        QueryWrapper<FormPO> wrapper = new QueryWrapper<>();
//        wrapper.eq("uid",uid)
//                .select("orderid","doctorid","start","end","status","roomid");;
//        List<Map<String, Object>> maps = formMapper.selectMaps(wrapper);
////        for(int i =0; i<maps.size(); i++){
////            res.add(maps.get(i));
////        }
//        return maps;
//    }
    public List findDoctoridByUid(String uid){
        ArrayList res = new ArrayList<>();
        QueryWrapper<FormPO> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid)
                .select("doctorid");;
        List<Map<String, Object>> maps = formMapper.selectMaps(wrapper);
        for(int i =0; i<maps.size(); i++){
            res.add(maps.get(i).get("doctorid"));
        }
        return res;
    }
    public void insForm(FormDTO formDTO) {
        FormPO formPO = formDTO.toFormPO();
        formMapper.insert(formPO);
    }
    public void deleteForm(String orderid){
        Map<String,Object> map =new HashMap<>();
        map.put("orderid",orderid);
        formMapper.deleteByMap(map);
    }
    public void updateForm(FormDTO formDTO, String orderid){
        FormPO dbform = formMapper.selectById(orderid);
        if (Objects.isNull(dbform)) {
            throw new BlogException(INVALID_ID);
        }
        FormPO formPO = formDTO.toFormPO();
        formPO.setOrderid(orderid);
        formMapper.updateById(formPO);
    }


}
