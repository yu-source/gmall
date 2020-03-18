package com.atguigu.gmall.pms.service;

import com.atguigu.gmall.pms.vo.AttrVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.pms.entity.AttrEntity;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;


/**
 * 商品属性
 *
 * @author zhijunYu
 * @email zhijun@yu.com
 * @date 2020-02-18 21:33:40
 */
public interface AttrService extends IService<AttrEntity> {

    PageVo queryPage(QueryCondition params);

    PageVo queryAttrsByCidOrTypePage(QueryCondition queryCondition, Long cid, Integer type);

    void saveAttrVo(AttrVo attr);
}

