package com.spring.data.tutorial.querydsl;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface PurseRepository extends JpaRepository<Purse,String>, QuerydslPredicateExecutor<Purse> {

    List<Purse> findAll(Predicate predicate);
}
