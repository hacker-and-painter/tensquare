package com.ghang.spit.controller;

import com.ghang.spit.pojo.Spit;
import com.ghang.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import util.IdWorker;

import java.util.Date;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/15 13:26
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
@EnableSwagger2 // 启动swagger注解
@Api(value = "API列表-吐槽", description = "关于吐槽的操作", tags = "spit")
public class SpitController {

    @Autowired // 按类型注入，类型找不到按名称
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IdWorker idWorker;

    @ApiOperation(value = "spit全部列表")
    @GetMapping
    public Result finAll() {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findAll());
    }

    @ApiOperation(value = "根据ID查询吐槽")
    @GetMapping(value = "/{spitId}")
    public Result findById(@PathVariable String spitId) {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findById(spitId));
    }

    @ApiOperation(value = "增加吐槽")
    @PostMapping
    public Result save(@RequestBody Spit spit) {
        spitService.save(spit);
        return new Result(true, StatusCode.OK, "保存成功");
    }

    @ApiOperation(value = "修改吐槽")
    @PutMapping(value = "/{spitId}")
    public Result update(@PathVariable String spitId, @RequestBody Spit spit) {
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    @ApiOperation(value = "删除吐槽")
    @DeleteMapping(value = "/{spitId}")
    public Result delete(@PathVariable String spitId) {
        spitService.deleteById(spitId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @ApiOperation(value = "吐槽分页")
    @GetMapping(value = "/{page}/{size}")
    public Result findPage(@PathVariable int page, @PathVariable int size) {
        Page<Spit> pageData = spitService.findPage(page, size);
        return new Result(true, StatusCode.OK, "分页查询成功", new PageResult<Spit>(pageData.getTotalElements(), pageData.getContent()));
    }

    @ApiOperation(value = "根据上级ID查询吐槽数据（分页）")
    @GetMapping(value = "/comment/{parentid}/{page}/{size}")
    public Result findByParentid(@PathVariable String parentid, @PathVariable int page, @PathVariable int size) {
        Page<Spit> pageData = spitService.findByParentid(parentid, page, size);
        return new Result(true, StatusCode.OK, "根据上级ID查询吐槽数据（分页）成功", new PageResult<Spit>(pageData.getTotalElements(), pageData.getContent()));
    }

    @ApiOperation(value = "点赞吐槽")
    @PutMapping(value = "/thumbup/{spitId}")
    public Result thumbup(@PathVariable String spitId) {
        // 判断当前用户是否已经点赞，但是现在我们没有做认证，暂时把userid写死
        String userId = "111";
        // 判断当前用户是否已经点赞
        if (redisTemplate.opsForValue().get("thumbup_" + userId + "_spitId_" + spitId) != null) {
            return new Result(false, StatusCode.REPERROR, "重复点赞");
        }
        spitService.thumbup(spitId);
        redisTemplate.opsForValue().set("thumbup_" + userId + "_spitId_" + spitId, 1);
        return new Result(true, StatusCode.OK, "点赞成功");
    }
}
