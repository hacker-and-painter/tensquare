package com.ghang.search.article.dao;

import com.ghang.search.article.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{
    //审核
    @Query(value = "update `tb_article` set state=1 where id=?1",nativeQuery = true)
    @Modifying
    public void examine(String articleId);
    //点赞
    @Query(value = "update `tb_article` set thumbup=thumbup+1 where id=?",nativeQuery = true)
    @Modifying
    public void dianzan(String articleId);
}
