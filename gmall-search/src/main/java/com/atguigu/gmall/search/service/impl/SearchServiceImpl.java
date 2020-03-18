package com.atguigu.gmall.search.service.impl;

import com.atguigu.gmall.search.service.SearchService;
import com.atguigu.gmall.search.vo.SearchParamVo;
import com.atguigu.gmall.search.vo.SearchResponseVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public SearchResponseVo search(SearchParamVo searchParam) {
        try {
            SearchRequest searchRequest = new SearchRequest(new String[]{"goods"}, buildDsl(searchParam));
            SearchResponse response = this.restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据用户发送的参数构建查询条件
     */
    private SearchSourceBuilder buildDsl(SearchParamVo searchParam) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 1. 构建查询条件和过滤条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        sourceBuilder.query(boolQueryBuilder);

        // 1.1. 构建匹配查询
        String keyword = searchParam.getKeyword();
        //判断是否为空
        if(StringUtils.isEmpty(keyword)){
            // 打广告，可以放默认信息
            return null;
        }
        boolQueryBuilder.must(QueryBuilders.matchQuery("title", keyword).operator(Operator.AND));

        // 1.2. 构建过滤条件
        // 1.2.1. 构建品牌过滤
        Long[] brandId = searchParam.getBrandId();
        //判断是否为空
        if(brandId != null && brandId.length != 0){
            boolQueryBuilder.filter(QueryBuilders.termsQuery("brandId", brandId));
        }

        // 1.2.2. 构建分类的过滤
        Long[] categoryId = searchParam.getCategoryId();
        if(categoryId != null && categoryId.length != 0){
            boolQueryBuilder.filter(QueryBuilders.termsQuery("categoryId", categoryId));
        }

        // 1.2.3. 构建价格区间
        Double priceFrom = searchParam.getPriceFrom();
        Double priceTo = searchParam.getPriceTo();
        // 判断起始和终止价格是否为null
        if(priceFrom != null || priceTo != null){
            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("price");
            if(priceFrom != null){
                rangeQuery.gte(priceFrom);
            }
            if(priceTo != null){
                rangeQuery.lte(priceTo);
            }
            boolQueryBuilder.filter(rangeQuery);
        }

        // 1.2.4. 构建是否有货的过滤
        Boolean store = searchParam.getStore();
        if(store != null){
            boolQueryBuilder.filter(QueryBuilders.termQuery("store", store));
        }

        // 1.2.5. 构建规格参数的嵌套过滤    33:3000-4000-5000
        String[] props = searchParam.getProps();
        if(props != null && props.length != 0){
            for (String prop : props) {
                String[] attr = StringUtils.split(prop, ":");
                // 判断用户传递的参数是否合法
                if(attr == null || attr.length != 2){
                    continue;
                }
                BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
                // 规格参数id
                boolQuery.must(QueryBuilders.termQuery("attrs.attrId", attr[0]));
                // 规格参数值
                boolQuery.must(QueryBuilders.termsQuery("attrs.attrValue", StringUtils.split(attr[1], "-")));
                boolQueryBuilder.filter(QueryBuilders.nestedQuery("attrs", boolQuery, ScoreMode.None));
            }
        }

        // 2. 构建排序  order=1:desc  （0：得分 1：价格  2：销量 3：新品）



        return sourceBuilder;
    }
}
