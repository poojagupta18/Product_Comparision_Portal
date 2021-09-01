package com.sales.snapdeal.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sales.snapdeal.model.SnapdealProduct;

@Repository
public interface SnapdealDAO extends MongoRepository<SnapdealProduct, Integer> {

}
