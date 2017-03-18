package com.sus.pojo;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
/**
 * Created by Administrator on 2017/3/18.
 */
@Entity
@Table(name = "t_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String password;
    @Column(name = "realname")
    private String realName;
    private String weixin;
    @Column(name = "createtime")
    private Timestamp createTime;
    @ManyToOne
    private Role role;
    private Integer enable;
    @Column(name = "username")
    private String userName;


}
