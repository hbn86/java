package com.social.base.service;

import com.social.base.dao.LabelDao;
import com.social.base.pojo.Label;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author： HBN
 * @Date：2019/1/1 22:14
 */
@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll() {
        return labelDao.findAll();
    }

    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    public void update(Label label) {
        labelDao.save(label);
    }

    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    /**
     * 条件查询
     * @param label
     * @return
     */
    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root  表示当前操作实体对象，也就是添加条件的实体类
             * @param query
             * @param cb
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //new一个list集合，来存放所有的条件
                ArrayList<Object> list = new ArrayList<>();
                //if (StringUtils.isEmpty(label.getLabelname()))
                //是否提供了标签名称的条件
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    //where labelname like "%小明%"
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                //是否提供了标签的状态信息
                if (label.getState() != null && !"".equals(label.getState())){
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }
                //是否提供了推荐信息
                if (!StringUtils.isEmpty(label.getRecommend())){
                    Predicate recommend = cb.equal(root.get("recommend"), label.getRecommend());
                    list.add(recommend);
                }
                //new一个数组作为最终返回值的条件
                Predicate[] parr = new Predicate[list.size()];
                parr = list.toArray(parr);
                //where labelname like "%小明%" and state = "1"
                return cb.and(parr);
            }
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        //封装一个分页对象
        Pageable pageable = PageRequest.of(page-1, size);
        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //new一个list集合，来存放所有的条件
                ArrayList<Object> list = new ArrayList<>();
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    //where labelname like "%小明%"
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if (label.getState() != null && !"".equals(label.getState())){
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }
                //new一个数组作为最终返回值的条件
                Predicate[] parr = new Predicate[list.size()];
                parr = list.toArray(parr);
                //where labelname like "%小明%" and state = "1"
                return cb.and(parr);
            }
        }, pageable);
    }
}
