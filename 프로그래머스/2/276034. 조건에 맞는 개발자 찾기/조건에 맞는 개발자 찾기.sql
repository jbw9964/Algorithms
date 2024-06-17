WITH NET_CODE AS (
    SELECT
            SUM(CODE)   AS `CODE`
    FROM    SKILLCODES
    WHERE   NAME LIKE 'C#'
    OR      NAME LIKE 'Python'
)

SELECT
        A.ID
    ,   A.EMAIL
    ,   A.FIRST_NAME
    ,   A.LAST_NAME
    # ,   A.SKILL_CODE
    # ,   B.CODE
FROM    DEVELOPERS      AS A
JOIN    NET_CODE        AS B
WHERE   A.SKILL_CODE & B.CODE > 0

ORDER
BY      ID
