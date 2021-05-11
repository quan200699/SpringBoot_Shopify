package com.example.market.repository;

import com.example.market.model.Category;
import com.example.market.model.Product;
import com.example.market.model.query.IProductImage;
import com.example.market.model.query.IProductWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Iterable<Product> findAllByCategory(Category category);

    @Query("select p from Product p where p.saleOff>0")
    Iterable<Product> findAllBySaleOffGreaterThanZero();

    @Query("select p from Product  p where p.price >= ?1 and p.price <= ?2")
    Iterable<Product> findAllByPriceCondition(double minValue, double maxValue);

    @Query("select p from Product p order by p.createdDate asc ")
    Iterable<Product> findAllProductOrderByDate();

    Iterable<Product> findAllByNameContaining(String name);

    @Query(value = "call market.productTotalAmount()",nativeQuery = true)
    Iterable<IProductWarehouse> findAllProductInventoryAmount();

    @Query(value = "select product_best_sell_view.id, product_best_sell_view.name, image.url, price " +
            "from market.product_best_sell_view left join market.image on product_best_sell_view.id = image.id;",
    nativeQuery = true)
    Iterable<IProductImage> getAllProductBestSell();
}
