
import sys

N, M = map(int, sys.stdin.readline().split(" "))

unknown_list = []
dictionary = dict()

for _ in range(N) : 
    name = input()
    dictionary[name] = True

for _ in range(M) : 
    name = input()
    if dictionary.__contains__(name) : unknown_list.append(name)

unknown_list.sort()

print(len(unknown_list))
for name in unknown_list : print(name)
