package com.ghang.recruit.dao;

import com.ghang.recruit.pojo.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/11 14:36
 */
public interface EnterpriseDao extends JpaRepository<Enterprise, String>, JpaSpecificationExecutor<Enterprise> {
    public List<Enterprise> findByIshot(String ishot);// where ishot = >?
}
