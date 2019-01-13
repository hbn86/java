package com.social.qa.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Problem 和 Label的对应关系
 * 一个问题可以有多个标签
 * 一个标签也可以被多个问题使用
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 */
@Entity
@Table(name="tb_pl")
public class Pl implements Serializable{

    @Id
    private String problemid;//问答微服务问题表的主键
    @Id
    private String labelid;//基础微服务标签表的主键

    public String getProblemid() {
        return problemid;
    }

    public void setProblemid(String problemid) {
        this.problemid = problemid;
    }

    public String getLabelid() {
        return labelid;
    }

    public void setLabelid(String labelid) {
        this.labelid = labelid;
    }
}
