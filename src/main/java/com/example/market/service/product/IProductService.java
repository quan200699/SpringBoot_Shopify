package com.example.market.service.product;

import com.example.market.model.Category;
import com.example.market.model.Product;
import com.example.market.service.IGeneralService;

public interface IProductService extends IGeneralService<Product> {
    Iterable<Product> findAllByCategory(Category category);

    Iterable<Product> findAllBySaleOffGreaterThanZero();

    Iterable<Product> findAllByPriceCondition(double minValue, double maxValue);
}
