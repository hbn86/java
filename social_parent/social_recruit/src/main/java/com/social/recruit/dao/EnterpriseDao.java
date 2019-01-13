package com.social.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.social.recruit.pojo.Enterprise;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface EnterpriseDao extends JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise>{
	/**
	 * 查询热门企业列表
	 * @param ishot
	 * @return
	 */
	public List<Enterprise> findTop15ByIshot(String ishot);//where ishot = "?"
}
