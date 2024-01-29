
P, *n_list = [int(num) for num in input().split()]

Result = []
for num in n_list : 
    
    flag = True
    count_0 = 0
    count_1 = 0
    count_2 = 0
    while num > 0 : 
        
        quotient, remain = divmod(num, P)

        if remain > 2 : 
            flag = False
            break
        
        count_0 += 1 if remain == 0 else 0
        count_1 += 1 if remain == 1 else 0
        count_2 += 1 if remain == 2 else 0

        num = quotient
    
    if flag : 
        
        if (count_1 != 1 or count_2 == 0) and (count_1 != 2 or count_2 != 0) :
            Result.append(0)

        else : 
            Result.append(1)

    else : 
        Result.append(0)

print(*Result)
