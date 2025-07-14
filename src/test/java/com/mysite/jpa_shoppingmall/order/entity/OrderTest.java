package com.mysite.jpa_shoppingmall.order.entity;

import com.mysite.jpa_shoppingmall.item.constant.ItemSellStatus;
import com.mysite.jpa_shoppingmall.item.entity.Item;
import com.mysite.jpa_shoppingmall.item.repository.ItemRepository;
import com.mysite.jpa_shoppingmall.member.entity.Member;
import com.mysite.jpa_shoppingmall.member.repository.MemberRepository;
import com.mysite.jpa_shoppingmall.order.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
@Slf4j
class OrderTest {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    public Item createItem() {
        return Item.builder()
                .itemNm("테스트 상품")
                .price(10000)
                .itemDetail("상세설명")
                .itemSellStatus(ItemSellStatus.SELL)
                .stockNumber(100)
                .regTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
    }

    public Order createOrder() {
        Member member = Member.builder().build();
        memberRepository.save(member);
        Order order = Order.builder()
                .member(member)
                .build();

        for (int i = 0; i < 3; i++) {
            Item item = createItem();
            itemRepository.save(item);
            OrderItem orderItem = OrderItem.builder()
                    .item(item)
                    .count(10)
                    .orderPrice(1000)
                    .order(order)
                    .build();

            order.getOrderItems().add(orderItem);
        }

        orderRepository.save(order);
        return order;
    }

    @Test
    @DisplayName("영속성 전이 테스트")
    void cascadeTest() {
        // given
        Order order = new Order();
        for (int i = 0; i < 3; i++) {
            Item item = createItem();
            itemRepository.save(item);
            OrderItem orderItem = OrderItem.builder()
                    .item(item)
                    .count(10)
                    .orderPrice(1000)
                    .order(order)
                    .build();

            order.getOrderItems().add(orderItem);
        }

        orderRepository.saveAndFlush(order); // 저장과 동시에 flush 호출
        em.clear();

        // when
        Order savedOrder = orderRepository.findById(order.getId()).orElseThrow(EntityNotFoundException::new);

        // then
        assertThat(savedOrder.getOrderItems().size()).isEqualTo(3);

    }

    @Test
    @DisplayName("고아객체 제거 테스트")
    void orphanRemovalTest() {
        // given
        Order order = createOrder();

        // when
        order.getOrderItems().removeFirst();
        em.flush(); // CascadeType.REMOVE도 포함되기 때문에 DELETE from orderItem 쿼리문도 나감

        // then

    }
}