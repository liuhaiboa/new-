package com.haibo.haibo.dao;

import com.haibo.haibo.po.News;
import com.haibo.haibo.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/12/27/027.
 */
public interface INewsDao extends CrudRepository<News,Integer> {

    @Query("select a from News a where a.rootid=0")
   public Page<News> queryZ(Pageable pageable);

    @Override
    public News save(News news);



    @Modifying
    @Query("delete from News where id=:id or rootid=:id")
    public int delz(@Param("id")Integer id);
}
