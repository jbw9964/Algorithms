WITH GRADE_TABLE AS (
    SELECT
            EMP_NO
        ,   AVG(SCORE)  AS `AVG_SCORE`
        ,   IF (AVG(SCORE) >= 96, 'S', 
                IF(AVG(SCORE) >= 90, 'A', 
                   IF(AVG(SCORE) >= 80, 'B', 'C')
                )
        )   AS 'GRADE'
    FROM    HR_GRADE

    GROUP
    BY      EMP_NO

    ORDER
    BY      EMP_NO
)

SELECT
        A.EMP_NO
    ,   B.EMP_NAME
    ,   A.GRADE
    ,   B.SAL * IF (A.GRADE LIKE 'S', 0.2, 
                   IF (A.GRADE LIKE 'A', 0.15, 
                      IF (A.GRADE LIKE 'B', 0.1, 0)
                      )
                   )
        AS `BONUS`
    
FROM    GRADE_TABLE     AS A
JOIN    HR_EMPLOYEES    AS B
        ON A.EMP_NO = B.EMP_NO