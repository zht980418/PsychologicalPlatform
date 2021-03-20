package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.qianyucc.qblog.dao.RecordformMapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.RecordformDTO;
import pers.qianyucc.qblog.model.entity.RecordformPO;
import pers.qianyucc.qblog.model.vo.RecordformVO;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class RecordformService {
    @Autowired
    private RecordformMapper recordformMapper;
    @Transactional(rollbackFor = Exception.class)
//    增加咨询记录
    public void insRecordform(RecordformDTO recordformDTO, int consultno){
        RecordformPO recordformPO =recordformDTO.toRecordformPO();
//        System.out.println(recordformDTO);
//        System.out.println(recordformPO);
        recordformPO.setConsultno(consultno);
        recordformMapper.insert(recordformPO);
    }
//    删除咨询记录
    public void deleteRecordform(int consultno){
        recordformMapper.deleteById(consultno);
    }
//    修改咨询记录
    public void updateRecordform(RecordformDTO recordformDTO, int consultno){
        RecordformPO dbrecordformPO = recordformMapper.selectById(consultno);
        if(Objects.isNull(dbrecordformPO))
            throw new BlogException(INVALID_ID);
        RecordformPO recordformPO = recordformDTO.toRecordformPO();
        recordformPO.setConsultno(consultno);
        recordformMapper.updateById(recordformPO);
    }
//    根据consultno查询咨询记录
    public RecordformVO getRecordform(int consultno){
        RecordformPO dbrecordform = recordformMapper.selectById(consultno);
        RecordformVO res = RecordformVO.fromRecordformPO(dbrecordform);
        return res;
    }

//    查询全部咨询记录
    public List<RecordformVO> getAllrecordform(){
        ArrayList res = new ArrayList();
        QueryWrapper<RecordformPO> wrapper = new QueryWrapper<>();
        List<Map<String,Object>> maps= recordformMapper.selectMaps(wrapper);
        for(int i=0;i<maps.size();i++){
            res.add(maps.get(i));
        }
        return res;
    }
//    查询咨询记录个数
    public int getNumofRecordform(){
        QueryWrapper<RecordformPO> wrapper = new QueryWrapper<>();
        List<Map<String,Object>> maps= recordformMapper.selectMaps(wrapper);
        return maps.size();
    }
//    查询最新主键
    public int getLatestID(int num){
        QueryWrapper<RecordformPO> wrapper = new QueryWrapper<>();
        List<Map<String, Object>> maps= recordformMapper.selectMaps(wrapper);
        long res = (long) maps.get(num-1).get("consultno");
        return (int) res;
    }
//    根据userid查询咨询记录条数
    public int getRecordtimesByuserid(String userid){
        int res=0;
        QueryWrapper<RecordformPO> wrapper = new QueryWrapper<>();
        List<Map<String,Object>> maps= recordformMapper.selectMaps(wrapper);
        for(int i=0;i<maps.size();i++){
            if(maps.get(i).get("userid").equals(userid)) {
                res++;
            }
        }
        return res;
    }
//    根据userid查询记录
    public List<RecordformVO> getRecordformsByuserid(String userid){
        ArrayList res = new ArrayList();
        QueryWrapper<RecordformPO> wrapper = new QueryWrapper<>();
        List<Map<String,Object>> maps= recordformMapper.selectMaps(wrapper);
        for(int i=0;i<maps.size();i++){
            if(maps.get(i).get("userid").equals(userid)){
//                System.out.println("*******");
                res.add(maps.get(i));
            }
        }
        return res;
    }
}
