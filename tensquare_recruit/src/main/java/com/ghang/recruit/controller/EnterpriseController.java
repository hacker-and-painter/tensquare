package com.ghang.recruit.controller;

import com.ghang.recruit.pojo.Enterprise;
import com.ghang.recruit.service.EnterpriseService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/11 15:10
 */
@RestController
@CrossOrigin
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping(value = "/search/hotlist")
    public Result hotlist() {
        List<Enterprise> list = enterpriseService.hotList("1");
        return new Result(true, StatusCode.OK, "查询成功", list);
    }
}
