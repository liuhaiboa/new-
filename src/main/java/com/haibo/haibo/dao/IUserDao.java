package com.haibo.haibo.dao;

import com.haibo.haibo.po.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Administrator on 2017/12/25/025.
 */
public interface IUserDao extends CrudRepository<User,Integer>{

    //@Query("select c from user c where c.username=:u and c.password=:p")
    @Query("select c from User c where c.username=:u and c.password=:p")
    public User login(@Param("u") String username, @Param("p") String password);


    @Override
    User save(User user);

    @Query("select c from User c where c.userid=:id")
    public User pic(@Param("id") Integer userid);


    @Modifying
    @Query("update User set pagenum=:num where userid=:id")
    public int setPagenum(@Param("id") Integer userid,@Param("num") Integer pagenum);

    @Query("select pagenum from User c where c.userid=:id")
    public int getPagenum(@Param("id") Integer userid);


}
