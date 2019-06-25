package com.ghang.base.controller;

import com.ghang.base.pojo.Label;
import com.ghang.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/09 21:03
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
@EnableSwagger2 // 启动swagger注解
@Api(value = "API列表-标签", description = "关于标签的操作", tags = "label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    @ApiOperation(value = "标签所有列表")
    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    // 方法的说明
    @ApiOperation(value = "根据id查询")
    // 定义请求参数
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "path", dataType = "String", name = "labelId", value = "主键", required = true) })
    @GetMapping(value = "/{labelId}")
    public Result findById(@PathVariable() String labelId) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(labelId));
    }

    @ApiOperation(value = "标签所有列表")
    @PostMapping
    public Result save(@RequestBody Label label) {
        labelService.save(label);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @ApiOperation(value = "根据id更新标签")
    @PutMapping("/{labelId}")
    public Result update(@PathVariable String labelId, @RequestBody Label label) {
        label.setId(labelId);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("/{labelId}")
    public Result deleteById(@PathVariable String labelId) {
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @ApiOperation(value = "根据条件查询")
    @PostMapping(value = "/search")
    public Result findSearch(@RequestBody Label label) {
        List<Label> list = labelService.findSearch(label);
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    @ApiOperation(value = "条件+分页查询")
    @PostMapping(value = "/search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label, @PathVariable int page, @PathVariable int size) {
        Page<Label> pageData = labelService.pageQuery(label, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageData.getTotalElements(), pageData.getContent()));
    }
}
