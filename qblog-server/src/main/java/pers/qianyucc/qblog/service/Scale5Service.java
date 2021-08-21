package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.qianyucc.qblog.dao.Scale5Mapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.Scale5DTO;
import pers.qianyucc.qblog.model.entity.Scale5PO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.Scale5VO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

@Slf4j
@Service
public class Scale5Service {
    @Autowired
    private Scale5Mapper scale5Mapper;

    public String insScale5(Scale5DTO scale5DTO) {
        Scale5PO scale5PO =  scale5DTO.toScale5PO(false);
        scale5PO.setId(UUID.randomUUID().toString().replace("-",""));
        scale5Mapper.insert(scale5PO);
        return scale5PO.getId();
    }

    public void deleteScale5(String id){
        Map<String,Object> map =new HashMap<>();
        map.put("id",id);
        scale5Mapper.deleteByMap(map);
    }

    public void updateScale5(Scale5DTO scale5DTO, String id){
        Scale5PO dbscale5 = scale5Mapper.selectById(id);
        if (Objects.isNull(dbscale5)) {
            throw new BlogException(INVALID_ID);
        }
        Scale5PO scale5PO = scale5DTO.toScale5PO(true);
//        System.out.println(scale5PO.getScale5id());
        scale5PO.setId(id);
        scale5Mapper.updateById(scale5PO);
    }

    public PageVO<Scale5VO> getAllScale5s(int page, int limit, String search,String field,String start,String end) throws ParseException {
        QueryWrapper<Scale5PO> qw = new QueryWrapper<>();
        qw.orderByDesc("gmt_create");
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        qw.between("gmt_create",sdf.parse(start),sdf.parse(end));
//        if(!start.isEmpty()){
//            qw.apply("date_format(gmt_create,'%Y-%m-%d %H:%i') >= date_format('" + start + "','%Y-%m-%d %H:%i')")
//                    .apply("date_format(gmt_create,'%Y-%m-%d %H:%i') <= date_format('" + end + "','%Y-%m-%d %H:%i')");
//        }
//        qw.apply("UNIX_TIMESTAMP(gmt_create) >= UNIX_TIMESTAMP('" +start  + "')")
//        .apply("UNIX_TIMESTAMP(gmt_create) <= UNIX_TIMESTAMP('" +end  + "')");

        if(!start.isEmpty()){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm");
            Date st = dateFormat.parse(start.substring(0,10)+start.substring(11,16));
            Date et = dateFormat.parse(end.substring(0,10)+end.substring(11,16));
            qw.between("gmt_create",st.getTime(),et.getTime());
        }

        if(search.equals("")) qw.select(Scale5PO.class, i-> !"content".equals(i.getColumn()));
        else {
            if(field.equals("orderId"))
                qw.like("id",search).select(Scale5PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("name"))
                qw.like("name",search).select(Scale5PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("uid"))
                qw.like("uid",search).select(Scale5PO.class, i-> !"content".equals(i.getColumn()));
            else if(field.equals("remark"))
                qw.like("remark",search).select(Scale5PO.class, i-> !"content".equals(i.getColumn()));
        }




        qw.select(Scale5PO.class, i-> !"content".equals(i.getColumn()));

        Page<Scale5PO> page1 = new Page<>(page,limit);
        page1.setSize(limit);
        Page<Scale5PO> res = scale5Mapper.selectPage(page1, qw);

        List<Scale5VO> scale5VOS = res.getRecords().stream()
                .map(Scale5VO::fromScale5PO)
                .collect(Collectors.toList())
                ;
        ArrayList re = new ArrayList<>();
        for(int i=0;i<scale5VOS.size();i++){
            re.add(scale5VOS.get(i));
            System.out.println(scale5VOS.get(i).getGmtCreate());
        }
        PageVO<Scale5VO> pageVO = PageVO.<Scale5VO>builder()
                .records(re.isEmpty()? new ArrayList<>():re)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
        return pageVO;

//        ArrayList res = new ArrayList<>();
//        QueryWrapper<Scale5PO> wrapper = new QueryWrapper<>();
//        List<Map<String, Object>> maps = scale5Mapper.selectMaps(wrapper);
//        for(int i =0; i<maps.size(); i++){
//            res.add(maps.get(i));
//        }
//        return res;
    }

    public List<Scale5VO> getAnsbyId(String id) {
        ArrayList res = new ArrayList<>();
        QueryWrapper<Scale5PO> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id)
                .select("name","remark","sex",
                        "question1","question2","question3","question4","question5",
                        "question6","question7","question8","question9","question10",
                        "question11","question12","question13","question14","question15",
                        "question16","question17","question18","question19","question20");
        List<Map<String,Object>> maps = scale5Mapper.selectMaps(wrapper);
        for(int i=0;i<maps.size();i++) {
//            System.out.println(maps.get(i));
            res.add(maps.get(i));
        }
        return  res;
    }

    public List<String> getResult(String id) {
        ArrayList res = new ArrayList<>();
        Scale5PO dbscale5PO = scale5Mapper.selectById(id);
        int result = dbscale5PO.getResult();
        if(result<50) res.add(
                "您的得分为"+dbscale5PO.getResult()+"。"+
                "您的状态为正常状态"
                );
        else  if(result>=50&&result<80) res.add(
                "您的得分为"+dbscale5PO.getResult()+"。"+
                "你正面临来自网络的问题."
        );
        else if(result>=80&&result<=100) res.add(
                "您的得分为"+dbscale5PO.getResult()+"。"+
                "为你的网络生涯已经到了引起严重生活问题的程度了，你恐怕需要很强的意志力，甚至需要求助于心理医生才能恢复正常了。"
        );
        return  res;
    }
}
