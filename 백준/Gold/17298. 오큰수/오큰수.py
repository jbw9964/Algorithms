
import sys
from collections import deque

N = int(input())
numbers = list(map(int, sys.stdin.readline().split(" ")))

answers = [-1 for _ in range(N)]
stack = deque([0])

index = 1
while index < len(numbers) : 
    
    if len(stack) == 0 : 
        stack.append(index)
        index += 1
        continue

    peek = numbers[stack[-1]]
    if peek < numbers[index] : 
        answers[stack.pop()] = numbers[index]
    else    :
        stack.append(index)
        index += 1

print(*answers)
