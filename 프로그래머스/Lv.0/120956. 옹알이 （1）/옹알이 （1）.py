def solution(babbling):
    answer = 0
    
    for string in babbling : 
        assert type(string) is str

        for check in ["aya", "ye", "woo", "ma"] : 
            string = string.replace(check, " ")

        flag = True
        for char in string : 
            assert type(char) is str

            if char.isalpha() : 
                flag = False
                break
        
        if flag : answer += 1

    return answer