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


DELIMITER //

CREATE PROCEDURE getProductInventory(in warehouseId BIGINT)
BEGIN
    CALL productInputTempTable (warehouseId);
    CALL productOutputTempTable (warehouseId);
    SELECT p1.id, p1.name, IF(p2.amount is null, 0, (p1.amount - p2.amount)) as amount
    FROM productInputTable p1 left join productOutputTable p2 on p1.id = p2.id;
END //

DELIMITER ;

call getProductInventory(5);

DELIMITER //

CREATE PROCEDURE productInputAmount()
BEGIN
    DROP TEMPORARY TABLE if exists productInputAmountTable;
    create temporary table productInputAmountTable
    select product.id, product.name, IFNULL(sum(wbd.amount), 0) as amount
    from product
             left join warehouse_bill_detail wbd on product.id = wbd.product_id
             left join ware_house_bill whb on wbd.ware_house_bill_id = whb.id
             left join warehouse w on whb.warehouse_id = w.id
    group by product.id, product.name;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE productOutputAmount()
BEGIN
    DROP TEMPORARY TABLE if exists productOutputAmountTable;
    create temporary table productOutputAmountTable
    select product.id, product.name, IFNULL(sum(bd.amount), 0) as amount
    from product
             left join bill_detail bd on product.id = bd.product_id
             left join bill b on bd.bill_id = b.id
             left join warehouse w on b.warehouse_id = w.id
    group by product.id, product.name;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE productTotalAmount()
BEGIN
    CALL productInputAmount();
    CALL productOutputAmount();
    SELECT p1.id, p1.name, IF((p1.amount - p2.amount) < 0, 0, (p1.amount - p2.amount)) as amount
    FROM productInputAmountTable p1
             join productOutputAmountTable p2 on p1.id = p2.id;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE getAllChatBetweenTwoUser(IN userId1 BIGINT, IN userId2 BIGINT, IN size INT)
BEGIN
    DROP TEMPORARY TABLE if exists getChatBetweenTwoUserPagination;
    create temporary table getChatBetweenTwoUserPagination
    select *
    from market.chat
    where (receiver_id = userId1 and sender_id = userId2)
       or (receiver_id = userId2 and sender_id = userId1)
    order by chat.time desc
    limit size offset 0;
    select *
    from getChatBetweenTwoUserPagination
    order by time;
END //

DELIMITER ;
