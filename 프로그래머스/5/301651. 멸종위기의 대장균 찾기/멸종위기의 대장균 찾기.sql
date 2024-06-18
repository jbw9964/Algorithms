WITH RECURSIVE GEN_TABLE AS (
    SELECT
            ID
        ,   1   AS `GEN`
    FROM    ECOLI_DATA
    WHERE   PARENT_ID IS NULL
    
    UNION   ALL
    
    SELECT  
            E.ID
        ,   G.GEN + 1
    FROM    ECOLI_DATA AS E
        ,   GEN_TABLE AS G
    
    WHERE   G.ID = E.PARENT_ID
)

,   NONE_LEAF_TABLE AS (
    SELECT
            PARENT_ID   AS `NONE_LEAF_ID`
        # ,   ID
    FROM    ECOLI_DATA

    WHERE   PARENT_ID IS NOT NULL

    GROUP
    BY      PARENT_ID
)

,   LEAF_TABLE AS (
    SELECT 
            A.ID
        # ,   B.NONE_LEAF_ID
    FROM    ECOLI_DATA      AS A
    LEFT
    JOIN    NONE_LEAF_TABLE AS B
            ON A.ID = B.NONE_LEAF_ID
    WHERE   B.NONE_LEAF_ID IS NULL
)


SELECT 
        COUNT(*)    AS `COUNT`
    # ,   A.ID
    ,   A.GEN       AS `GENERATION`
FROM    GEN_TABLE   AS A
JOIN    LEAF_TABLE  AS B
        ON A.ID = B.ID

GROUP
BY      `GENERATION`

ORDER
BY      `GENERATION`