package com.ghang.search.dao;

import com.ghang.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleSearchDao extends ElasticsearchRepository<Article,String> {
    // 根据标题和内容来搜索文章
    public Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
