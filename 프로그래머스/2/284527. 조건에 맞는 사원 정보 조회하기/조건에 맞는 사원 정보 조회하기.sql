WITH SCORE_TABLE AS (
    SELECT
            EMP_NO
        ,   SUM(SCORE)  AS `SCORE`
    FROM    HR_GRADE

    GROUP
    BY      EMP_NO
    
    ORDER
    BY      `SCORE` DESC
    
    LIMIT   1
)

SELECT
        A.SCORE
    ,   A.EMP_NO
    ,   B.EMP_NAME
    # ,   B.DEPT_ID
    ,   B.POSITION
    ,   B.EMAIL
FROM    SCORE_TABLE     AS A
JOIN    HR_EMPLOYEES    AS B
        ON A.EMP_NO = B.EMP_NO