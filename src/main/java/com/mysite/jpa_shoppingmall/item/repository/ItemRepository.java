package com.mysite.jpa_shoppingmall.item.repository;

import com.mysite.jpa_shoppingmall.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemNm(String itemNm);
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);
    List<Item> findByPriceLessThan(Integer price);
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

}
