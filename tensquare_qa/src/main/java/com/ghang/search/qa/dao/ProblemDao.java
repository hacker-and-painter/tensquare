package com.ghang.search.qa.dao;

import com.ghang.search.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    // 最新问答列表
	@Query(value = "SELECT * FROM tb_problem,tb_pl WHERE id = problemid AND labelid =? ORDER BY replytime desc", nativeQuery = true)
    public Page<Problem> newlist(String labelid, Pageable pageable);

	// 热门问答列表
    @Query(value = "SELECT * FROM tb_problem,tb_pl WHERE id = problemid AND labelid =? ORDER BY reply desc", nativeQuery = true)
    public Page<Problem> hotlist(String labelid, Pageable pageable);

    // 等待回答列表
    @Query(value = "SELECT * FROM tb_problem,tb_pl WHERE id = problemid AND labelid =? AND reply = 0 ORDER BY createtime desc", nativeQuery = true)
    public Page<Problem> waitlist(String labelid, Pageable pageable);
}
