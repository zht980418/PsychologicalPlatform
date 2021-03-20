package pers.qianyucc.qblog.controller;

import cn.hutool.core.map.MapUtil;
import com.google.common.collect.Maps;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.model.comm.Results;
import pers.qianyucc.qblog.model.dto.UserDTO;
import pers.qianyucc.qblog.model.dto.UserinfoDTO;
import pers.qianyucc.qblog.model.vo.UserinfoVO;
import pers.qianyucc.qblog.service.UserinfoService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Api("用户相关接口")
@RestController
public class UserinfoController {
    @Autowired
    private UserinfoService userinfoService;

//    注册用户
    @ApiOperation("注册用户")
    @PostMapping("/register")
    public Results<String> postUserinfo(@ApiParam(name = "用户注册信息",required = true,value = "传入json格式")
                                        @RequestBody @Valid UserinfoDTO userinfoDTO){
        userinfoService.insUserinfo(userinfoDTO);
        return Results.ok("注册成功",null);
    }
//    删除用户
    @ApiOperation("注销用户")
    @DeleteMapping("/register/{userid}")
    @ApiImplicitParam(name = "userid", value = "用户id", required = true, dataType = "String", paramType = "path")
    public  Results<String> deleteUserinfo(@PathVariable String userid){
        userinfoService.deleteUserinfo(userid);
        return Results.ok("删除成功",null);
    }
//    修改用户
    @ApiOperation("修改用户信息")
    @PutMapping("/register/{userid}")
    public Results<String> updateUserinfo(@ApiParam(name = "修改用户信息",required = true, value = "传入json格式")
                                          @RequestBody @Valid UserinfoDTO userinfoDTO,
                                          @PathVariable String userid){
        userinfoService.updateUserinfo(userinfoDTO,userid);
        return Results.ok("修改成功",null);
    }
//登录验证
    @PostMapping("/logintest")
    @ApiOperation("用户登录")
    public Results<Map<String,Object>> login(@ApiParam(name = "用户登录信息", value = "传入json格式", required = true)
                                                  @RequestBody @Valid UserinfoDTO userinfoDTO){
        String Userid = userinfoDTO.getUserid();
        String Password = userinfoService.getUserinfo(Userid).getPassword();
        String token = userinfoService.checkUsernamePassword(userinfoDTO,Userid, Password);
        return Results.ok("登录成功", MapUtil.of("token", token));
    }
//    查询单独用户信息
    @GetMapping("/logintest/{userid}")
    @ApiOperation("获取用户信息")
    @ApiImplicitParam(name = "userid", value = "用户ID", required = true, dataType = "String", paramType = "path")
    public Results<UserinfoVO> getUserinfoByuserid(@PathVariable String userid) {
        UserinfoVO userinfoVO = userinfoService.getUserinfo(userid);
        return Results.ok(userinfoVO);
    }
//    查询用户列表
    @GetMapping("/logintest")
    @ApiOperation("获取用户列表")
    @ApiImplicitParam(name = "userid", value = "用户ID", required = true, dataType = "String", paramType = "path")
    public Results<List<UserinfoVO>> getAlluserinfo(){
        List<UserinfoVO> userinfoVOList = userinfoService.getAlluserinfo();
        return Results.ok(userinfoVOList);
    }
//    根据姓名查询用户列表
    @GetMapping("/user/{nickname}")
    @ApiOperation("根据姓名查询用户")
    @ApiImplicitParam(name = "nickname", value = "用户名",required = true,dataType = "String",paramType = "path")
    public Results<List<UserinfoVO>> getUserinfosBynickname(@PathVariable String nickname){
        return Results.ok(userinfoService.getUserinfoBynickname(nickname));
    }
//    查询咨询师列表
    @GetMapping("/order/doctorlist")
    @ApiOperation("获取咨询师列表")
    @ApiImplicitParam(name="doctorlsit",value = "用户列表",required = true,dataType = "String")
    public Results<List<UserinfoVO>> getAlldoctor(){
        List<UserinfoVO> userinfoVOList=userinfoService.getDoctorinfo();
        return Results.ok(userinfoVOList);
    }
}
