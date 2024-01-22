
N = int(input())
string = input()

index = 0
count = 0

while index < len(string) - 3 : 
    num_1 = int(string[index])
    num_2 = int(string[index + 1])
    num_3 = int(string[index + 2])
    num_4 = int(string[index + 3])

    check_num = 100 * num_1 + 10 * num_2 + num_3

    if check_num <= 641 and num_4 != 0  :   index += 3      # check whether 3 digit is possible
    elif num_3 != 0                     :   index += 2      # check whether 2 digit is possible
    else                                :   index += 1      # only 1 digit is possible
    
    count += 1

remain_digit = len(string) - index

if remain_digit == 0        :   pass
elif remain_digit < 3       :   count += 1
else : 
    num_1 = int(string[index])
    num_2 = int(string[index + 1])
    num_3 = int(string[index + 2])

    check_num = 100 * num_1 + 10 * num_2 + num_3

    if check_num <= 641 and remain_digit != 4   :   count += 1
    else                                        :   count += 2

print(count)
