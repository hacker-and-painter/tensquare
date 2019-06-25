package com.ghang.recruit.dao;

import com.ghang.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/11 14:37
 */
public interface RecruitDao extends JpaRepository<Recruit,String>, JpaSpecificationExecutor<Recruit> {
    public List<Recruit> findTop6ByStateOrderByCreatetime(String state);//where state = ? order by createtime
    public List<Recruit> findTOP6ByStateNotOrderByCreatetimeDesc(String state);//where state != ? order by createtime desc
}

