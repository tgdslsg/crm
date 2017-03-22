package com.sus.pojo;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
/**
 * Created by Administrator on 2017/3/18.
 */
@Entity
@Table(name = "t_user")
public class User implements Serializable{

    public static final Integer STATE_TRUE = 1;
    public static final Integer STATE_FALSE = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String password;
    @Column(name = "realname")
    private String realName;
    private String weixin;
    @Column(name = "createtime",insertable = false,updatable =false)
    private Timestamp createTime;
    @ManyToOne
    @JoinColumn(name = "roleid")
    private Role role;
    private Integer enable;
    @Column(name = "username")
    private String userName;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", weixin='" + weixin + '\'' +
                ", createTime=" + createTime +
                ", role=" + role +
                ", enable=" + enable +
                ", userName='" + userName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
