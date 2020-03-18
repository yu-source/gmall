package com.atguigu.gmall.search;

import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;
import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.pms.entity.SpuInfoEntity;
import com.atguigu.gmall.search.feign.GmallPmsClient;
import com.atguigu.gmall.search.feign.GmallWmsClient;
import com.atguigu.gmall.search.repository.GoodsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GmallSearchApplicationTests {

    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private GmallPmsClient pmsClient;
    @Autowired
    private GmallWmsClient wmsClient;

    @Test
    void importData() {
        Long pageNum = 1l;
        Long pageSize = 100l;

        // 分页查询spu
        QueryCondition condition = new QueryCondition();
        condition.setPage(pageNum);
        condition.setLimit(pageSize);
        Resp<List<SpuInfoEntity>> listResp = this.pmsClient.querySpuPage(condition);
        List<SpuInfoEntity> spuInfoEntities = listResp.getData();



        // 遍历spu查询spu下的sku

        // 把sku转化为goodsVO

        // 批量导入es中

    }

}
