
from collections import deque

def BFS(pos_y, pos_x, level) : 
    global Table, N
    
    # create visit table
    Visit_Table = [[False for _ in range(N)] for _ in range(N)]

    # function to clarify adjacent node is in table
    InRange = lambda y, x : True if 0 <= x < N and 0 <= y < N else False

    Target_list = []            # list of nodes where shark can reach to it's position, and eat fish

    Adjacent_list = deque()     # queue that stores adjacent nodes to it's current position
    Adjacent_list.append([pos_y, pos_x, 0])
    
    while len(Adjacent_list) :  # search available adjacent nodes
        pos_y, pos_x, dist = Adjacent_list.popleft()

        if Visit_Table[pos_y][pos_x]    :   continue        # shark visited current node previously

        pos_y_up, pos_x_up          = pos_y - 1, pos_x
        pos_y_down, pos_x_down      = pos_y + 1, pos_x
        pos_y_left, pos_x_left      = pos_y, pos_x - 1
        pos_y_right, pos_x_right    = pos_y, pos_x + 1

        # does upper adjacent node available?
        if InRange(pos_y_up, pos_x_up) \
            and not Visit_Table[pos_y_up][pos_x_up] \
            and Table[pos_y_up][pos_x_up] <= level          :   Adjacent_list.append([pos_y_up, pos_x_up, dist + 1])
        
        # does lower adjacent node available?
        if InRange(pos_y_down, pos_x_down) \
            and not Visit_Table[pos_y_down][pos_x_down] \
            and Table[pos_y_down][pos_x_down] <= level      :   Adjacent_list.append([pos_y_down, pos_x_down, dist + 1])
        
        # does left adjacent node available?
        if InRange(pos_y_left, pos_x_left) \
            and not Visit_Table[pos_y_left][pos_x_left] \
            and Table[pos_y_left][pos_x_left] <= level      :   Adjacent_list.append([pos_y_left, pos_x_left, dist + 1])
        
        # does right adjacent node available?
        if InRange(pos_y_right, pos_x_right) \
            and not Visit_Table[pos_y_right][pos_x_right] \
            and Table[pos_y_right][pos_x_right] <= level    :   Adjacent_list.append([pos_y_right, pos_x_right, dist + 1])

        # can shark eat the fish of current node?
        if Table[pos_y][pos_x] < level and Table[pos_y][pos_x] != 0     :   Target_list.append([pos_y, pos_x, dist])
        
        Visit_Table[pos_y][pos_x] = True        # fill visit table


    # there is fish that shark can eat
    if len(Target_list) :
        # sort list via requirements    |   shortest distance, most upper left node
        Target_list = sorted(Target_list, key=lambda x : (x[2], x[0], x[1]))
        return Target_list[0]

    # there is no fish that shark can eat
    else    :   return None



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
