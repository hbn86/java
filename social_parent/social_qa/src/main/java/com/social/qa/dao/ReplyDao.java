package com.social.qa.dao;

import com.social.qa.pojo.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author： HBN
 * @Date：2019/1/5 23:27
 */

/**
 * 数据访问接口
 */
public interface ReplyDao extends JpaRepository<Reply, String>, JpaSpecificationExecutor<Reply> {

}
