package com.sus.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/3/18.
 */
@Entity
@Table(name = "t_role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "rolename")
    private String roleName;

}
