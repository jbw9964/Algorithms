def solution(n, a, b):
    a, b = a - 1, b - 1

    answer = 0
    while a != b : 
        a //= 2
        b //= 2
        answer += 1

    return answer