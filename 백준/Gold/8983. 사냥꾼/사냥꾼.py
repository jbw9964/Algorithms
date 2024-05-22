
import sys

M, N, L = map(int, sys.stdin.readline().split(" "))

init_list = list(map(int, sys.stdin.readline().split(" ")))

dictionary = dict()
for _ in range(N) : 
    x, y = map(int, sys.stdin.readline().split(" "))
    
    if dictionary.__contains__(x) : 
        dictionary[x].append(y)
    else : 
        dictionary[x] = [y]

answer = 0

for init in init_list : 
    threshold_min = 0 if init - L < 0 else init - L
    threshold_max = init + L

    for x in dictionary.copy().keys() : 
        if x < threshold_min or x > threshold_max : continue
        
        dict_list = dictionary.pop(x)
        new_list = []

        for y in dict_list : 
            
            if abs(init - x) + y > L    : new_list.append(y)
            else                        : answer += 1

        if len(new_list) != 0 : dictionary[x] = new_list

print(answer)
