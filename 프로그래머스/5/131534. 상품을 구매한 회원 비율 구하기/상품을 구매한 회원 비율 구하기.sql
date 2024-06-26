WITH TOTAL_USERS AS (
    SELECT 
            YEAR(JOINED)    AS `YEAR`
        # ,   USER_ID
        ,   COUNT(*)        AS `USERS`
    FROM    USER_INFO

    WHERE   YEAR(JOINED) = 2021
)

# SELECT * FROM TOTAL_USERS;

,   PURCHASE_TABLE AS (
    SELECT 
            YEAR(A.SALES_DATE)      AS `YEAR`
        ,   MONTH(A.SALES_DATE)     AS `MONTH`
        # ,   A.USER_ID
        # ,   B.JOINED
        ,   COUNT(DISTINCT A.USER_ID)   AS `PURCHASED_USERS`
    FROM    ONLINE_SALE AS A

    JOIN    USER_INFO   AS B
            ON A.USER_ID = B.USER_ID

    WHERE   YEAR(B.JOINED) = 2021

    GROUP
    BY      `YEAR`, `MONTH`
)

# SELECT * FROM PURCHASE_TABLE;

SELECT 
        A.YEAR
    ,   A.MONTH
    ,   A.PURCHASED_USERS
    # ,   B.USERS
    ,   ROUND(A.PURCHASED_USERS / B.USERS, 1)   AS PUCHASED_RATIO
FROM    PURCHASE_TABLE      AS A
JOIN    TOTAL_USERS         AS B

ORDER
BY      A.YEAR, A.MONTH
