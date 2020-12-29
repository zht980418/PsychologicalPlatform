package pers.qianyucc.qblog.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.model.comm.*;
import pers.qianyucc.qblog.model.dto.*;
import pers.qianyucc.qblog.model.vo.*;
import pers.qianyucc.qblog.service.*;

import javax.validation.*;

@Api("与留言相关的api接口")
@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @ApiOperation("分页获取留言")
    @GetMapping("/messages")
    public Results<PageVO<MessageVO>> getMessage(
            @ApiParam("页码")
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @ApiParam("每页存放的记录数")
            @RequestParam(required = false, defaultValue = "5") Integer limit) {
        return Results.ok(messageService.getMessages(page, limit));
    }

    @PostMapping("/messages")
    @ApiOperation("新增留言")
    public Results<String> postMessage(@ApiParam(name = "留言信息", value = "传入json格式", required = true)
                                       @RequestBody @Valid MessageDTO messageDTO) {
        messageService.insMessage(messageDTO);
        return Results.ok("留言新增成功", null);
    }

    @GetMapping("/message/{id}")
    @ApiOperation("根据id查询留言信息")
    @ApiImplicitParam(name = "id", value = "留言id", required = true, dataType = "Long", paramType = "path")
    public Results<MessageVO> getMessage(@PathVariable Long id) {
        MessageVO messageVO = messageService.findById(id);
        return Results.ok(messageVO);
    }

    @DeleteMapping("/auth/message/{id}")
    @ApiOperation("根据id删除留言")
    @ApiImplicitParam(name = "id", value = "留言id", required = true, dataType = "Long", paramType = "path")
    public Results<String> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return Results.ok("删除成功", null);
    }

    @PutMapping("/auth/message/{id}")
    @ApiOperation("修改留言")
    @ApiImplicitParam(name = "id", value = "留言id", required = true, dataType = "Long", paramType = "path")
    public Results<String> putMessage(@ApiParam(name = "要修改的留言信息", value = "传入json格式", required = true)
                                      @RequestBody ReplyMessageDTO replyMessageDTO,
                                      @PathVariable Long id) {
        messageService.updateById(id, replyMessageDTO);
        return Results.ok("更新成功", null);
    }
}
