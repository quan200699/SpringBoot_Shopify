package com.example.market.service.product;

import com.example.market.model.Category;
import com.example.market.model.Product;
import com.example.market.model.query.IProductWarehouse;
import com.example.market.service.IGeneralService;

public interface IProductService extends IGeneralService<Product> {
    Iterable<Product> findAllByCategory(Category category);

    Iterable<Product> findAllBySaleOffGreaterThanZero();

    Iterable<Product> findAllByPriceCondition(double minValue, double maxValue);

    Iterable<Product> findAllProductOrderByDate();

    Iterable<Product> findAllByNameContaining(String name);

    Iterable<Product> findAll(int page, int size);

    Iterable<IProductWarehouse> findAllProductInventoryAmount();
}
