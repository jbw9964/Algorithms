import sys

def GCD(a, b) : 
    if b > a        : a, b = b, a
    while b != 0    : a, b = b, a % b
    return a

def div(a, b) : 
    if b == 1 : 
        return a
    
    count = 0

    while a - b >= 0 : 
        count += 1
        a -= b
    
    return count

def main() : 
    num =  int(input())
    num_list = list(map(int, input().split()))
    num_list.reverse()

    assert len(num_list) == num, AssertionError()

    upper, lower = num_list[0], 1

    if num > 1 : 
        for i in range(1, len(num_list)) : 
            x_i = num_list[i]
            temp_upper = x_i * upper + lower
            temp_lower = upper
            gcd = GCD(temp_upper, temp_lower)

            upper = div(temp_upper, gcd)
            lower = div(temp_lower, gcd)

    gcd = GCD(upper, lower)
    upper = div(upper, gcd)
    lower = div(lower, gcd)
    
    if lower == 1 : 
        print(upper)
    else : 
        print("{}/{}".format(upper, lower))

if __name__ == "__main__" : 
    main()