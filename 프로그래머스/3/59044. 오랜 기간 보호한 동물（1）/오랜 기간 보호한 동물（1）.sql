SELECT
        A.NAME
    ,   A.DATETIME AS `DATETIME`
    # ,   B.DATETIME AS `OUT`
FROM ANIMAL_INS AS A
LEFT JOIN ANIMAL_OUTS AS B
    ON A.ANIMAL_ID = B.ANIMAL_ID
    WHERE B.DATETIME IS NULL
ORDER BY A.DATETIME
LIMIT 3;