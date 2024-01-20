N = int(input())
Num_list = list(map(int, input().split()))
Num_list = sorted(Num_list)

M = int(input())
Check_list = list(map(int, input().split()))

for num in Check_list : 
    l_index = 0
    r_index = N - 1

    flag = False
    while r_index - l_index > 1 : 
        mid_index = int((l_index + r_index) / 2)
        mid_value = Num_list[mid_index]

        if num == mid_value : 
            flag = True
            break
        
        elif num > mid_value : 
            l_index = mid_index
        
        else : 
            r_index = mid_index
    
    if num in [Num_list[r_index], Num_list[l_index]] : 
        flag = True
    
    print(1) if flag else print(0)
