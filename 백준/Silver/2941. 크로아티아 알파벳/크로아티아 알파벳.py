import sys
input = sys.stdin.readline

raw_string = str(input())

alpha_count = 0
index_count = 0
text = raw_string[index_count]
while True : 

    if text == 'c' : 
        if raw_string[index_count + 1] == '=' or raw_string[index_count + 1] == '-' : 
            index_count += 1

    elif text == 'd' : 
        
        if raw_string[index_count + 1] == '-' : 
            index_count += 1
        
        elif raw_string[index_count + 1] == 'z' and raw_string[index_count + 2] == '=' : 
            index_count += 2

    elif text == 'l' and raw_string[index_count + 1] == 'j' : 
        index_count += 1

    elif text == 'n' and raw_string[index_count + 1] == 'j' : 
        index_count += 1

    elif text == 's' and raw_string[index_count + 1] == '=' : 
        index_count += 1
    
    elif text == 'z' and raw_string[index_count + 1] == '=' : 
        index_count += 1

    alpha_count += 1
    index_count += 1
    text = raw_string[index_count]

    if text == '\0' or text == '\n' : break

print(alpha_count)