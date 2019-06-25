package com.ghang.search.service;

import com.ghang.search.dao.ArticleSearchDao;
import com.ghang.search.pojo.Article;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleSearchService {

    @Autowired
    private ArticleSearchDao articleSearchDao;

    // 添加文章
    public void add(Article article){
        articleSearchDao.save(article);
    }

    // 复制域分页搜索
    public Page<Article> findByKey(String key, int page, int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        return articleSearchDao.findByTitleOrContentLike(key, key, pageable);
    }
}
