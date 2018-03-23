package com.haibo.haibo.service;

import com.haibo.haibo.dao.INewsDao;
import com.haibo.haibo.po.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/12/27/027.
 */
@Service
@Transactional
public class NewsService {
    @Autowired
    private INewsDao dao;

   public Page<News> queryZ(Pageable pageable){
        return dao.queryZ(pageable);
    }

    public News save(News news){
       return dao.save(news);
    }

    public void delz(Integer id){dao.delz(id);}
}