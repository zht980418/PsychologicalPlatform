package pers.qianyucc.qblog.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.qianyucc.qblog.dao.UserinfoMapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.UserinfoDTO;
import pers.qianyucc.qblog.model.entity.UserinfoPO;
import pers.qianyucc.qblog.model.enums.UserRoleEnum;
import pers.qianyucc.qblog.model.vo.ArticleVO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.UserinfoVO;
import pers.qianyucc.qblog.utils.JwtUtils;
import retrofit2.http.QueryMap;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.*;

@Slf4j
@Service
public class UserinfoService {
    @Autowired
    private UserinfoMapper userinfoMapper;
    @Transactional(rollbackFor = Exception.class)

//    增加用户
    public void insUserinfo(UserinfoDTO userinfoDTO){
        String userid = userinfoDTO.getUserid();
        String password = userinfoDTO.getPassword();
        String nickname = userinfoDTO.getPassword();
        String phonenumber = userinfoDTO.getPhonenumber();
        UserinfoPO dbUserinfo = userinfoMapper.selectById(userid);
        if(Objects.isNull(dbUserinfo)){
            UserinfoPO userinfoPO = userinfoDTO.toUserinfoPO();
            userinfoMapper.insert(userinfoPO);
        }
        else if (userid==null||password==null||nickname==null||phonenumber==null){
            throw  new BlogException(REGISTER_INFO_NOT_COMPLETE);
        }
        else {
            throw new BlogException(DUPLICATE_USERID);
        }
    }
//    删除用户
    public void deleteUserinfo(String userid){
        Map<String, Object> map = new HashMap<>();
        map.put("userid", userid);
        userinfoMapper.deleteByMap(map);
    }
//    修改用户
    public void updateUserinfo(UserinfoDTO userinfoDTO, String userid){
        UserinfoPO dbUserinfo = userinfoMapper.selectById(userid);
        if(Objects.isNull(dbUserinfo)){
            throw new BlogException(INVALID_ID);
        }
        UserinfoPO userinfoPO = userinfoDTO.toUserinfoPO();
        userinfoPO.setUserid(userid);
        userinfoMapper.updateById(userinfoPO);
    }
//    查询用户列表
    public PageVO<UserinfoVO> getAlluserinfo(int page, int limit, String search){
        QueryWrapper<UserinfoPO> qw = new QueryWrapper<>();
        qw.select(UserinfoPO.class, i-> !"content".equals(i.getColumn()));
        Page<UserinfoPO> res = userinfoMapper.selectPage(new Page<>(page, limit), qw);
        List<UserinfoVO> userinfoVOS = res.getRecords().stream()
                .map(UserinfoVO::fromUserinfoPO)
                .collect(Collectors.toList())
                ;
        ArrayList re = new ArrayList<>();
        for(int i=0;i<userinfoVOS.size();i++){
            if(search.equals("")|| Pattern.matches(".*"+search+".*",userinfoVOS.get(i).getUserid()))
                re.add(userinfoVOS.get(i));
        }
        PageVO<UserinfoVO> pageVO = PageVO.<UserinfoVO>builder()
                .records(re.isEmpty()? new ArrayList<>():re)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
        return pageVO;
//        ArrayList res = new ArrayList();
//        QueryWrapper<UserinfoPO> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select("userid", "password", "rolename","phonenumber","nickname");
//        List<Map<String, Object>> maps = userinfoMapper.selectMaps(queryWrapper);
//        for(int i = 0; i <maps.size(); i++){
//            res.add(maps.get(i));
//        }
//        return res;
    }
//    根据userid查询单个用户
    public UserinfoVO getUserinfo(String userid){
        UserinfoPO dbUserinfo = userinfoMapper.selectById(userid);
        UserinfoVO userinfoVO = UserinfoVO.fromUserinfoPO(dbUserinfo);
        if(userinfoVO.getUserid()==null){
            throw new BlogException(USERNAME_PASSWORD_ERROR);
        }
        else {
            return  userinfoVO;
        }
    }
//    根据nickname查询用户
    public List<UserinfoVO> getUserinfoBynickname(String nickname){
        ArrayList res = new ArrayList();
        QueryWrapper<UserinfoPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("userid", "rolename","phonenumber","nickname");
        List<Map<String, Object>> maps = userinfoMapper.selectMaps(queryWrapper);
        for(int i = 0; i <maps.size(); i++){
            if(maps.get(i).get("nickname").equals(nickname))
                res.add(maps.get(i));
        }
        return res;
    }
//    查询用户姓名
    public String getUserName(String userid){
        UserinfoPO dbUserinfo = userinfoMapper.selectById(userid);
        return dbUserinfo.getNickname();
    }
//    查询咨询师列表
    public List<UserinfoVO> getDoctorinfo(){
        ArrayList res = new ArrayList();
        QueryWrapper<UserinfoPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("userid","rolename","nickname");
        List<Map<String, Object>> maps =userinfoMapper.selectMaps(queryWrapper);
        for(int i=0;i<maps.size();i++){
            if(maps.get(i).get("rolename")!=null&&maps.get(i).get("rolename").equals("咨询师")){
                res.add(maps.get(i));
            }
        }
        return res;
    }
//    核对用户密码
    public String checkUsernamePassword(UserinfoDTO userinfoDTO, String Userid, String Password){
        if(Objects.equals(Userid, userinfoDTO.getUserid())&&Objects.equals(Password, userinfoDTO.getPassword())){
            return JwtUtils.createToken(Userid, CollUtil.newArrayList(UserRoleEnum.ADMIN.getValue()));
        }
        else {
            throw new BlogException(USERNAME_PASSWORD_ERROR);
        }
    }
}
