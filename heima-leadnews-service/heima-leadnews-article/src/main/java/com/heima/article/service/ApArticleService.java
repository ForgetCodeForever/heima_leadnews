package com.heima.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.article.dtos.ArticleDto;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;

public interface ApArticleService extends IService<ApArticle> {

    /**
     * 根据参数加载文章列表
     * @param loadtype  1  加载更多  2  加载最新
     * @param dto
     * @return
     */
    ResponseResult load(ArticleHomeDto dto, Short loadtype);

    /**
     *
     * @param dto
     * @param loadtype  1  加载更多  2  加载最新
     * @param firstPage  true 首页  false 非首页
     * @return
     */
    ResponseResult load_(ArticleHomeDto dto, Short loadtype, boolean firstPage);

    ResponseResult saveArticle(ArticleDto dto);
}
