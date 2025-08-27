Rope_num = int(input())

Rope_list = []
for i in range(Rope_num) : 
    temp = int(input())
    Rope_list.append(temp)

Rope_list.sort()

temp_max_weight = Rope_list[0] * Rope_num
indiv_weight = Rope_list[0]

for i in range(1, Rope_num) : 

    max_weight = Rope_list[i] * (Rope_num - i)

    if max_weight > temp_max_weight : 
        temp_max_weight = max_weight

print(temp_max_weight)