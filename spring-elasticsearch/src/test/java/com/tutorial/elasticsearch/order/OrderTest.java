package com.tutorial.elasticsearch.order;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderTest {
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    OrderRepository orderRepository;

    @Before
    public void before() {
        elasticsearchTemplate.deleteIndex(Order.class);
        elasticsearchTemplate.createIndex(Order.class);
        elasticsearchTemplate.putMapping(Order.class);
        elasticsearchTemplate.refresh(Order.class);
    }

    @Test
    public void testSave() {
        orderRepository.save(new Order());
        assertThat(orderRepository.findAll().getTotalElements(), is(1L));
    }

    @Test
    public void testPage(){
        orderRepository.save(new Order());
        orderRepository.save(new Order());
        Page<Order> page = orderRepository.findAll(new PageRequest(0, 1));
        assertThat(page.getContent().size(), is(1));
    }
}
