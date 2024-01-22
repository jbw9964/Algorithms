
N = int(input())
string = input()


index = 0
count = 0
while index < len(string) : 
    
    remain_digit = len(string) - index
    if remain_digit < 3 : 
        count += 1
        break
    
    elif remain_digit == 3 : 
        first_char = int(string[index])
        second_char = int(string[index + 1])
        third_char = int(string[index + 2])
        fourth_char = 5
    
    else : 
        first_char = int(string[index])
        second_char = int(string[index + 1])
        third_char = int(string[index + 2])
        fourth_char = int(string[index + 3])

    assert first_char != 0, AssertionError

    if first_char > 6 :     # 2, 1 digits
        if third_char == 0  :   pass        # 1 digit
        else                :   index += 1  # 2 digit

    elif first_char == 6 :  # 3, 2, 1 digits
        
        if second_char < 4 :        # 3, 2 digits
            
            if fourth_char == 0 : index += 1    # 2 digit
            else                : index += 2    # 3 digit

        elif second_char == 4 :     # 3, 2 digits
            
            if third_char > 2 or fourth_char == 0   :   index += 1      # 2 digit
            else                                    :   index += 2      # 3 digit

        else :                      # 2, 1 digits
            if third_char == 3  :   pass        # 1 digit
            else                :   index += 1  # 2 digit

    else :                  # 3, 2, 1 digits
        
        if fourth_char != 0     :   index += 2  # 3 digit
        elif third_char != 0    :   index += 1  # 2 digit
        else                    :   pass        # 1 digit

    index += 1
    count += 1

print(count)
