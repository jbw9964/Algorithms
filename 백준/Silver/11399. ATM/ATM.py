import re

Num = int(input())

Waiting_List = []

Waiting_string = str(input())
numbers = re.findall(r'\d+', Waiting_string)

for i in numbers : 
    Waiting_List.append(int(i))

Waiting_List.sort()

Prev = Waiting_List[0]
Result = Waiting_List[0]
for index in range(1, len(Waiting_List)) : 
    
    Result += Prev + Waiting_List[index]
    Prev += Waiting_List[index]
    
print(Result)