WITH TEMP_TABLE AS (
    SELECT
            FISH_TYPE
        ,   AVG(IFNULL(LENGTH, 10)) AS `AVG`
        ,   COUNT(*)                AS `COUNT`
        ,   MAX(LENGTH)             AS `MAX`
    FROM    FISH_INFO

    GROUP
    BY      FISH_TYPE
)

SELECT 
        `COUNT` AS `FISH_COUNT`
    ,   `MAX`   AS `MAX_LENGTH`
    ,   FISH_TYPE
FROM    TEMP_TABLE

WHERE   `AVG` >= 33

ORDER
BY      FISH_TYPE