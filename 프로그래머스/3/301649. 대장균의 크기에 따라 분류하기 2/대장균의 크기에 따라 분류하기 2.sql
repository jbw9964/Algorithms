WITH PERCENTAGE AS (
    SELECT
            ID
        ,   PERCENT_RANK() OVER (
            ORDER BY SIZE_OF_COLONY DESC
        )   AS `COLONY_RATIO`
    FROM ECOLI_DATA
)

SELECT 
        A.ID
    ,   IF (
        B.COLONY_RATIO <= 0.25, 'CRITICAL',
            IF (B.COLONY_RATIO <= 0.5,  'HIGH',
                IF (B.COLONY_RATIO <= 0.75, 'MEDIUM','LOW')
        )
    )   AS `COLONY_NAME`
FROM    ECOLI_DATA      AS A
JOIN    PERCENTAGE      AS B
        ON A.ID = B.ID
        
ORDER 
BY      A.ID
