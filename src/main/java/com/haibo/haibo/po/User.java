package com.haibo.haibo.po;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/12/25/025.
 */
@Entity
public class User {
    public String getSun() {
        return sun;
    }

    public void setSun(String sun) {
        this.sun = sun;
    }

    private String sun;
    private int userid;
    private String reusername;
    private String repassword;

    private byte[] pic;

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public Integer getPagenum() {
        return pagenum;
    }

    public void setPagenum(Integer pagenum) {
        this.pagenum = pagenum;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    private Integer pagenum;
    private String picPath;
    private Set<News> newss=new HashSet<News>();

    @Id
    @Column(name = "userid", nullable = false)
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 50)
    public String getUsername() {
        return reusername;
    }

    public void setUsername(String username) {
        this.reusername = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return repassword;
    }

    public void setPassword(String password) {
        this.repassword = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userid != user.userid) return false;
        if (reusername != null ? !reusername.equals(user.reusername) : user.reusername != null) return false;
        if (repassword != null ? !repassword.equals(user.repassword) : user.repassword != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid;
        result = 31 * result + (reusername != null ? reusername.hashCode() : 0);
        result = 31 * result + (repassword != null ? repassword.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "user")
    public Set<News> getNewss() {
        return newss;
    }

    public void setNewss(Set<News> newss) {
        this.newss = newss;
    }
}