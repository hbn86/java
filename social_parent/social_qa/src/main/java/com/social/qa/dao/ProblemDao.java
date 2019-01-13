package com.social.qa.dao;

import com.social.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author： HBN
 * @Date：2019/1/5 23:32
 */
public interface ProblemDao extends JpaRepository<Problem, String>,JpaSpecificationExecutor<Problem> {

    /**
     * 获取最新回答的问题列表
     * @param labelid   问题的所属标签（分类）
     * @param pageable  分页信息
     * @return
     */
    @Query("select p from Problem p where p.id in (select problemid from p1 where labelid = ?1) and p.replytime is not null order by p.replytime desc")
    Page<Problem> findNewReplyList(String labelid, Pageable pageable);

    /**
     * 获取热门回答的问题列表
     * @param labelid   问题的所属标签
     * @param pageable  分页信息
     * @return
     * nativeQuery = true 的时候，表示是SQL
     */
    @Query(value = "select * from tb_problem p where p.id in (select problemid from tb_p1 where labelid = ?1) and reply != 0 order by reply desc", nativeQuery = true)
    Page<Problem> findHotReplyList(String labelid, Pageable pageable);

    /**
     * 获取等待回到的问题列表
     * @param labelid
     * @param pageable
     * @return
     */
    @Query(value = "select * from tb_proble p where p.id in (select problemid from tb_p1 where labelid = ?1) and reply = 0 order by createtime desc", nativeQuery = true)
    Page<Problem> findWaitReplyList(String labelid, Pageable pageable);
}
