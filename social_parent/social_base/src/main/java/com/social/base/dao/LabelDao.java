package com.social.base.dao;

import com.social.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author： HBN
 * @Date：2019/1/1 22:10
 */

public interface LabelDao extends JpaRepository<Label, String>, JpaSpecificationExecutor<Label>{


}
