import heapq
import sys

input = sys.stdin.readline

Conf_num = int(input())
Conf_list = []

for i in range(Conf_num) : 
    start, end = map(int, input().split())
    Conf_list.append([start, end])

Conf_list.sort()

Heap_list = []
heapq.heappush(Heap_list, Conf_list[0][1])

for i in range(1, Conf_num) : 

    if Heap_list[0] <= Conf_list[i][0] : 
        heapq.heappushpop(Heap_list, Conf_list[i][1])
        continue
        
    heapq.heappush(Heap_list, Conf_list[i][1])

print(len(Heap_list))