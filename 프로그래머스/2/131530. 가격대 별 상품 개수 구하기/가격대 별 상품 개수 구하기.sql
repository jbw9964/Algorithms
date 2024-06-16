SELECT (PRICE DIV 10000) * 10000 AS PRICE_GROUP
    , COUNT(*) 
    FROM PRODUCT
    GROUP BY PRICE_GROUP
    ORDER BY PRICE_GROUP;
    
