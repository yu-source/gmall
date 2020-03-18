package com.atguigu.gmall.search.repository;

import com.atguigu.gmall.search.vo.GoodsVo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GoodsRepository extends ElasticsearchRepository<GoodsVo, Long> {

}
