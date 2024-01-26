
def BFS(pos_y, pos_x, level) : 
    global Table, N
    
    # create visit table
    Visit_Table = [[False for _ in range(N)] for _ in range(N)]

    # function to clarify adjacent node is in table
    InRange = lambda y, x : True if 0 <= x < N and 0 <= y < N else False

    Target_list = []            # list of nodes where shark can reach to it's position, and eat fish

    Adjacent_list = [[pos_y, pos_x, 0]]     # list of nodes where adjacent will be stored
    Visit_Table[pos_y][pos_x] = True
    
    while len(Adjacent_list) : 
        temp_list = []

        for pos_y, pos_x, dist in Adjacent_list : 
            
            pos_y_up, pos_x_up          = pos_y - 1, pos_x
            pos_y_down, pos_x_down      = pos_y + 1, pos_x
            pos_y_left, pos_x_left      = pos_y, pos_x - 1
            pos_y_right, pos_x_right    = pos_y, pos_x + 1
            
            for adjacent_y, adjacent_x in [     # check for adjacent
                [pos_y_up, pos_x_up],
                [pos_y_down, pos_x_down],
                [pos_y_left, pos_x_left],
                [pos_y_right, pos_x_right],
            
            ] : 
                # if adjacent node can be reach,
                if InRange(adjacent_y, adjacent_x) \
                    and not Visit_Table[adjacent_y][adjacent_x] \
                    and Table[adjacent_y][adjacent_x] <= level : 
                    
                    Visit_Table[adjacent_y][adjacent_x] = True              # fill visit table
                    temp_list.append([adjacent_y, adjacent_x, dist + 1])    # add to temporal list

                    # if adjacent node can be eatable, add to Target_list
                    if Table[adjacent_y][adjacent_x] < level and Table[adjacent_y][adjacent_x] != 0 : 
                        Target_list.append([adjacent_y, adjacent_x, dist + 1])

        # if available fish exists, return to main
        if Target_list :        # available fish will be always in shortest distance
            Target_list = sorted(Target_list, key=lambda x : (x[2], x[0], x[1]))
            return Target_list[0]
        
        # if not, intialize adjacents
        else    :   Adjacent_list = temp_list
    
    # if we checked all moveable nodes & there is no available fish, return None
    return None


N = int(input())

Table = [None for _ in range(N)]
for i in range(N) : 
    row = list(map(int, input().split()))
    Table[i] = row


POS, LEVEL = None, 2
for i in range(N) :             # define initial position of shark
    for j in range(N) : 
        if Table[i][j] == 9 : 
            POS = (i, j)
            Table[i][j] = 0
            break


count = 0
level_count = 0
while True : 
    search_result = BFS(*POS, LEVEL)

    if search_result is None :          # no available node exists
        break

    pos_y, pos_x, dist = search_result
    Table[pos_y][pos_x] = 0             # shark ate fish

    POS = (pos_y, pos_x)                # initialize current position
    count += dist                       # add time that were taken to move it's position

    level_count += 1
    if LEVEL == level_count :           # shark level up
        LEVEL += 1
        level_count = 0

print(count)
