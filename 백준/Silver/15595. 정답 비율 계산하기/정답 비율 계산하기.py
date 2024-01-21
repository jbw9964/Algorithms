
N = int(input())

user_dict = {}
corr_dict = {}

for _ in range(N) : 
    input_list = list(map(str, input().split()))

    name = input_list[1]
    state = input_list[2]
    
    if name == "megalusion" : continue

    if name in corr_dict.keys() : continue

    if name not in user_dict.keys() : user_dict[name] = 0

    if state == "4" : 
        corr_dict[name] = True
    else : 
        user_dict[name] += 1

sum = len(corr_dict)
for name in corr_dict.keys() : 
    sum += user_dict[name]

if sum == 0 : print(0)
else        : print("{:.15f}".format(len(corr_dict) / sum * 100))
