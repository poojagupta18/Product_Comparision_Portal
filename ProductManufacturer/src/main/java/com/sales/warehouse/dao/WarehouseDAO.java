package com.sales.warehouse.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sales.warehouse.model.WarehouseProduct;

@Repository
public interface WarehouseDAO extends MongoRepository<WarehouseProduct, Integer>{

}
