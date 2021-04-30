DELIMITER //

CREATE PROCEDURE productInputTempTable(in warehouseId BIGINT)
BEGIN
    DROP TEMPORARY TABLE if exists productInputTable;
    create temporary table productInputTable
    select product.id, product.name, amount
    from ware_house_bill
             left join warehouse_bill_detail wbd on ware_house_bill.id = wbd.ware_house_bill_id
             left join product on wbd.product_id = product.id
             left join warehouse on ware_house_bill.warehouse_id = warehouse.id
    where warehouse.id = warehouseId;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE productOutputTempTable(in warehouseId BIGINT)
BEGIN
    DROP TEMPORARY TABLE if exists productOutputTable;
    create temporary table productOutputTable
    select product.id, product.name, amount
    from bill
             left join bill_detail bd on bill.id = bd.bill_id
             left join product on bd.product_id = product.id
             left join warehouse on bill.warehouse_id = warehouse.id
    where warehouse.id = warehouseId;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE getProductInventory(in warehouseId BIGINT)
BEGIN
    CALL productInputTempTable (warehouseId);
    CALL productOutputTempTable (warehouseId);
    SELECT p1.id, p1.name, (p1.amount - p2.amount) as amount
    FROM productInputTable p1 join productOutputTable p2 on p1.id = p2.id;
END //

DELIMITER ;

call getProductInventory(5);


