import sys
input = sys.stdin.readline

Num = int(input())

Num_list = [0]
temp = list(map(int, input().split()))

for num in temp : 
    Num_list.append(num)

Table = [0 for i in range(Num + 1)]

for i in range(1, Num + 1) : 
    for j in range(1, i) : 
        if Num_list[j] < Num_list[i] and Table[i] < Table[j]: 
            Table[i] = Table[j]
    Table[i] += 1
    
print(max(Table))