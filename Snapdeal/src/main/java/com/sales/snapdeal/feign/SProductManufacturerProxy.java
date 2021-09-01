package com.sales.snapdeal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.sales.snapdeal.response.SnapdealResponse;

@FeignClient(name="ProductManufaturer", url = "localhost:8080")
public interface SProductManufacturerProxy {

	@GetMapping("warehouse/products")
	SnapdealResponse getProductDetailsFromManufacturer();
}
