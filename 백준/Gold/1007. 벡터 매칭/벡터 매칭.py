from math import sqrt, inf
from itertools import combinations

def solution() : 
    N = int(input())

    x_total, y_total = 0, 0

    vertex_list = []
    for _ in range(N) : 
        x, y = map(int, input().split())
        x_total, y_total = x_total + x, y_total + y
        vertex_list.append([x, y])
    
    combination_list = list(combinations(vertex_list, int(N / 2)))

    min_dist = inf
    for coord_to_add in combination_list[:int(len(combination_list) / 2)] : 
        
        x_sum, y_sum = 0, 0
        for x, y in coord_to_add : 
            x_sum, y_sum = x_sum + x, y_sum + y
        
        distance = sqrt((x_total - 2 * x_sum)**2 + (y_total - 2 * y_sum)**2)
        if min_dist > distance : min_dist = distance

    return min_dist

if __name__ == "__main__" : 
    T = int(input())

    result_list = []
    for _ in range(T) : 
        result_list.append(solution())

    for result in result_list : 
        print("{:.15f}".format(result))
