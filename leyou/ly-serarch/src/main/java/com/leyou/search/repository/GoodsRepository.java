package com.leyou.search.repository;

import com.leyou.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: cuzz
 * @Date: 2018/11/10 15:47
 * @Description:
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods, Long>{
}
