package com.sus.pojo;

import lombok.Data;

import javax.persistence.*;
import java.security.Timestamp;

/**
 * Created by tgdsl on 2017/3/18.
 */
@Entity
@Table(name="t_document")
@Data
public class Document {
    public static final String TYPE_DIR = "dir";
    public static final String TYPE_DOC = "doc";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private String name;
    private String size;
    private Timestamp creattime;
    private String creatuser;
    private String type;
    private String filename;
    private String md5;
    private Integer fid;
    private String contenttype;


}
