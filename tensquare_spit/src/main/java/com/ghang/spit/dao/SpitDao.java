package com.ghang.spit.dao;

import com.ghang.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/15 13:19
 */
public interface SpitDao extends MongoRepository<Spit,String> {
    public Page<Spit> findByParentid(String parentid, Pageable pageable);
}
