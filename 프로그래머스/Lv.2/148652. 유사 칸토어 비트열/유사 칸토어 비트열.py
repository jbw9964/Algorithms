from collections import deque

def gen_cantorian(string : str) : 
    
    string_hold_out = str()

    for char in string : 
        
        if int(char) == 1 : 
            string_hold_out += "11011"
        else : 
            string_hold_out += "00000"
    
    return string_hold_out

def solution(n, l, r):
    l, r = l - 1, r - 1

    level = 0
    l_leaf, r_leaf = l, r
    while l_leaf != r_leaf : 
        l_leaf //= 5
        r_leaf //= 5
        level += 1

    upper_level = n - level         # remaining upper levels of tree
    stem_index = l_leaf             # corresponding index of leaf nodes

    index_queue = deque()
    index_queue.appendleft(stem_index)

    for _ in range(upper_level) :               # store which index (node of tree) has to be selected
        stem_index //= 5
        index_queue.appendleft(stem_index)

    string = "1"
    index_queue.popleft()

    for _ in range(upper_level) :                   # select last corresponding node
        string = gen_cantorian(string)
        string = string[index_queue.popleft() % 5]


    # gen lower levels
    for _ in range(level) :                     # generate lower tree (to leaf)
        string = gen_cantorian(string)
        l_leaf *= 5                             # calc index threshold

    string = string[l - l_leaf : r - l_leaf + 1]    # select string

    answer = 0
    for char in string :            # count answer
        if int(char) == 1 : 
            answer += 1

    return answer