from collections import deque

def main(init_pos, dst_pos) : 
    MAX_NUM = 100_000

    if dst_pos <= init_pos : 
        return [ i for i in range(dst_pos, init_pos + 1)]
    
    visit_list = [-1 for _ in range(MAX_NUM + 1)]
    BFS_list = deque()
    BFS_list.append(init_pos)

    while True : 
        current_pos = BFS_list.popleft()

        if current_pos == dst_pos : 
            break

        if 2 * current_pos <= MAX_NUM and 2 * current_pos > 0 \
            and visit_list[2 * current_pos] == -1 : 
            visit_list[2 * current_pos] = current_pos
            BFS_list.append(2 * current_pos)
        
        if current_pos + 1 <= MAX_NUM \
            and visit_list[current_pos + 1] == -1 :
            visit_list[current_pos + 1] = current_pos
            BFS_list.append(current_pos + 1)

        if current_pos - 1 > 0 \
            and visit_list[current_pos - 1] == -1 : 
            visit_list[current_pos - 1] = current_pos
            BFS_list.append(current_pos - 1)

    index = visit_list[dst_pos]
    result = [dst_pos, index]

    while index != init_pos : 
        result.append(visit_list[index])
        index = visit_list[index]
    
    return result

if __name__ == "__main__" : 
    # init_pos, dst_pos = 0, 100000
    init_pos, dst_pos = map(int, str(input()).split())
    result = main(init_pos, dst_pos)
    
    print(len(result) - 1)
    print(*result[::-1])
