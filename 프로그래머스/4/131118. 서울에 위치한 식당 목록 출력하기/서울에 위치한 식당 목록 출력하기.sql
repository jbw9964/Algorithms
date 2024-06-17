WITH REST_IN_SEOUL AS (
    SELECT
            REST_ID
        ,   REST_NAME
        ,   FOOD_TYPE
        ,   FAVORITES
        ,   ADDRESS
    FROM    REST_INFO

    WHERE   ADDRESS LIKE '서울%'
)

# SELECT * FROM REST_IN_SEOUL

,   SCORE_TABLE AS (
    SELECT 
            REST_ID
        # ,   REVIEW_SCORE
        ,   SUM(REVIEW_SCORE)   AS `TOTAL_SCORE`
        ,   COUNT(*)            AS `REVIEW_NUM`
    FROM    REST_REVIEW

    GROUP
    BY      REST_ID

    ORDER
    BY      REST_ID
)

SELECT
        A.REST_ID
    ,   A.REST_NAME
    ,   A.FOOD_TYPE
    ,   A.FAVORITES
    ,   A.ADDRESS
    # ,   B.TOTAL_SCORE
    # ,   B.REVIEW_NUM
    ,   ROUND(B.TOTAL_SCORE / B.REVIEW_NUM, 2)  AS `SCORE`
FROM    REST_IN_SEOUL   AS A
JOIN    SCORE_TABLE     AS B
        ON A.REST_ID = B.REST_ID

ORDER
BY      `SCORE` DESC, A.FAVORITES DESC