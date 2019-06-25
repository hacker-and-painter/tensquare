package com.ghang.search.controller;

import com.ghang.search.pojo.Article;
import com.ghang.search.service.ArticleSearchService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/article")
@CrossOrigin
@EnableSwagger2
@Api(value = "API列表-查询功能", description = "关于文章的操作", tags = "article")
public class ArticleSearchController {

    @Autowired
    private ArticleSearchService articleSearchService;

    @ApiOperation(value = "增加文章")
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Article article){
        articleSearchService.add(article);
        return new Result(true, StatusCode.OK,"添加文章成功");
    }

    // 根据关键词搜索
    @GetMapping(value = "/{key}/{page}/{size}")
    public Result findByKey(@PathVariable String key, @PathVariable int page,@PathVariable int size){
        Page<Article> pageData = articleSearchService.findByKey(key, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Article>(pageData.getTotalElements(), pageData.getContent()));
    }
}
