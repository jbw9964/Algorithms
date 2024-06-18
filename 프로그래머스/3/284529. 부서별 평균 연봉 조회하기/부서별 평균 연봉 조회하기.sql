WITH AVG_SAL_TABLE AS (
    SELECT
            DEPT_ID
        ,   ROUND(AVG(SAL), 0)  AS `AVG_SAL`
    FROM    HR_EMPLOYEES

    GROUP
    BY      DEPT_ID
)

SELECT 
        A.DEPT_ID
    ,   B.DEPT_NAME_EN
    ,   A.AVG_SAL
FROM    AVG_SAL_TABLE   AS A
JOIN    HR_DEPARTMENT   AS B
        ON A.DEPT_ID = B.DEPT_ID

ORDER
BY      A.AVG_SAL DESC