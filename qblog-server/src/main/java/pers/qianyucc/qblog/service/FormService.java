package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.qianyucc.qblog.dao.FormMapper;
import pers.qianyucc.qblog.model.dto.FormDTO;
import pers.qianyucc.qblog.model.dto.MessageDTO;
import pers.qianyucc.qblog.model.entity.FormPO;
import pers.qianyucc.qblog.model.entity.MessagePO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        .select("orderid","name","start","end");
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
    public void insForm(FormDTO formDTO) {
        FormPO formPO = formDTO.toFormPO();
        formMapper.insert(formPO);
    }


}
