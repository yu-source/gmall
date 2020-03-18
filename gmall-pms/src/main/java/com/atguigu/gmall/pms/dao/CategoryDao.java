package com.atguigu.gmall.pms.dao;

import com.atguigu.gmall.pms.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author zhijunYu
 * @email zhijun@yu.com
 * @date 2020-02-18 21:33:39
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
