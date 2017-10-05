package com.spring.data.tutorial.querydsl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * This test has the purpose to verify if the search with a predicate returns the correct result
 * The input data should be at least two purses that matches the input predicate
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/initPurseTable.sql")
})
public class SearchManyPursesTest {

    @Autowired
    PurseRepository purseRepository;

    /**
     * predicate: width = 11.1
     */
    @Test
    public void searchManyByWidth() {
        PursePredicateBuilder pursePredicateBuilder = new PursePredicateBuilder();
        BigDecimal inputValue = BigDecimal.valueOf(11.1);
        pursePredicateBuilder.withWidth(inputValue);
        List<Purse> purseList = purseRepository.findAll(pursePredicateBuilder.build());
        assertNotNull(purseList);
        assertEquals(3,purseList.size());
        assertEquals(0, inputValue.compareTo(purseList.get(0).getWidth()));
        assertEquals(0, inputValue.compareTo(purseList.get(1).getWidth()));
        assertEquals(0, inputValue.compareTo(purseList.get(2).getWidth()));

    }

    /**
     * predicate: length = 5.5
     */
    @Test
    public void searchManyByLength() {
        PursePredicateBuilder pursePredicateBuilder = new PursePredicateBuilder();
        BigDecimal inputValue = BigDecimal.valueOf(5.5);
        pursePredicateBuilder.withLength(inputValue);
        List<Purse>  purseList = purseRepository.findAll(pursePredicateBuilder.build());
        assertNotNull(purseList);
        assertEquals(3,purseList.size());
        assertEquals(0, inputValue.compareTo(purseList.get(0).getLength()));
        assertEquals(0, inputValue.compareTo(purseList.get(1).getLength()));
        assertEquals(0, inputValue.compareTo(purseList.get(2).getLength()));
    }

    /**
     * predicate: purseType = CASUAL
     */
    @Test
    public void searchManyByPurseType() {
        PursePredicateBuilder pursePredicateBuilder = new PursePredicateBuilder();
        Purse.PurseType purseType = Purse.PurseType.CASUAL;
        pursePredicateBuilder.withPurseType(purseType);
        List<Purse>  purseList = purseRepository.findAll(pursePredicateBuilder.build());
        assertNotNull(purseList);
        assertEquals(2,purseList.size());
        assertEquals(purseType, purseList.get(0).getPurseType());
        assertEquals(purseType, purseList.get(1).getPurseType());
    }

    /**
     * predicate: length = 11.1 and width = 5.5
     */
    @Test
    public void searchManyByLengthAndWidth() {
        PursePredicateBuilder pursePredicateBuilder = new PursePredicateBuilder();
        BigDecimal inputWidth = BigDecimal.valueOf(11.1);
        BigDecimal inputLength =  BigDecimal.valueOf(5.5);
        pursePredicateBuilder
                .withLength(inputLength)
                .withWidth(inputWidth);
        List<Purse>  purseList = purseRepository.findAll(pursePredicateBuilder.build());
        assertNotNull(purseList);
        assertEquals(0, inputLength.compareTo(purseList.get(0).getLength()));
        assertEquals(0, inputLength.compareTo(purseList.get(1).getLength()));
        assertEquals(0, inputWidth.compareTo(purseList.get(0).getWidth()));
        assertEquals(0, inputWidth.compareTo(purseList.get(1).getWidth()));


    }
}
