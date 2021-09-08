package com.sales.amazon.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.sales.amazon.response.Response;

@FeignClient("ProductManufaturer")
public interface AProductManufacturerProxy {

	@GetMapping("warehouse/products")
	Response getProductDetailsFromManufacturer();
}
