package com.sales.amazon.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sales.amazon.model.AProduct;

@Repository
public interface AmazonDAO extends MongoRepository<AProduct, Integer> {

}

