package com.ghang.base.dao;

import com.ghang.base.pojo.Label;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/09 21:19
 */
public interface LabelDao extends JpaRepository<Label, String>, JpaSpecificationExecutor<Label> {
}
