def gen_prime_table(max_num) : 
    prime_table = [
        2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 
        43, 47, 53, 59, 61, 67, 71, 73, 79,83, 89, 97
    ]

    for number in range(100, max_num + 1) : 
        flag = True
        for check_num in prime_table : 
            if number % check_num == 0 : 
                flag = False
                break
        
        if flag : 
            prime_table.append(number)    
    
    return prime_table

def solution(nums):
    answer = 0
    prime_table = gen_prime_table(1_000 * 3)

    for i in range(len(nums) - 2) : 
        for j in range(i + 1, len(nums) - 1) : 
            for k in range(j + 1, len(nums)) : 
                number = nums[i] + nums[j] + nums[k]
                if number in prime_table : 
                    answer += 1

    return answer