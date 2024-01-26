
def BFS() : 
    global TABLE, N, M

    Adjacent_list = []
    Visit_table = [[False for _ in range(M)] for _ in range(N)]

    for i in range(N) : 
        for j in range(M) : 
            if TABLE[i][j] : 
                Adjacent_list.append([i, j])
                Visit_table[i][j] = True

    InRange = lambda y, x : True if 0 <= y < N and 0 <= x < M else False

    count = 0
    while len(Adjacent_list) : 
        temp_list = []

        for pos_y, pos_x in Adjacent_list : 
            
            pos_y_U, pos_x_U = pos_y - 1, pos_x
            pos_y_D, pos_x_D = pos_y + 1, pos_x
            pos_y_L, pos_x_L = pos_y, pos_x - 1
            pos_y_R, pos_x_R = pos_y, pos_x + 1
            pos_y_UL, pos_x_UL = pos_y - 1, pos_x - 1
            pos_y_UR, pos_x_UR = pos_y - 1, pos_x + 1
            pos_y_DL, pos_x_DL = pos_y + 1, pos_x - 1
            pos_y_DR, pos_x_DR = pos_y + 1, pos_x + 1

            for adjacent_y, adjacent_x in [
                [pos_y_U, pos_x_U], [pos_y_D, pos_x_D], [pos_y_L, pos_x_L], [pos_y_R, pos_x_R],
                [pos_y_UL, pos_x_UL], [pos_y_UR, pos_x_UR], [pos_y_DL, pos_x_DL], [pos_y_DR, pos_x_DR],
            ] : 
                if InRange(adjacent_y, adjacent_x) \
                    and not Visit_table[adjacent_y][adjacent_x] \
                    and not TABLE[adjacent_y][adjacent_x] : 
                    temp_list.append([adjacent_y, adjacent_x])
                    Visit_table[adjacent_y][adjacent_x] = True

        if len(temp_list)   :   count += 1
        
        Adjacent_list = temp_list
    
    return count


N, M = map(int, input().split())
TABLE = [None for _ in range(N)]
for i in range(N) : 
    row = list(map(int, input().split()))
    TABLE[i] = row

print(BFS())