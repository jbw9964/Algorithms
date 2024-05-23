
import sys
from collections import deque

class Node : 
    data = None
    
    def __init__(self, data) :
        self.data = data
    
    def __str__(self) -> str :
        return str(self.data)

    def getData(self) : return self.data
class Circle : 
    N, M, R = 0, 0, 0
    node_list = None
    
    def __init__(self, N : int, M : int, R : int) -> None:
        self.N = N
        self.M = M
        self.R = R % (2 * (N + M - 2))
        self.node_list = deque()
    
    def addNode(self, node : Node) : 
        assert type(self.node_list) is deque
        self.node_list.append(node)
    
    def addData(self, data) : 
        newNode = Node(data)
        self.addNode(newNode)
    
    def rotate(self) : 
        assert type(self.node_list) is deque
        for _ in range(self.R) : 
            self.node_list.append(self.node_list.popleft())

    def popNode(self) : 
        assert type(self.node_list) is deque
        return self.node_list.popleft()

    def popData(self) : 
        assert type(self.node_list) is deque
        node = self.node_list.popleft()
        assert type(node) is Node
        return node.getData()
    
    def __str__(self) -> str :
        return str(self.node_list)

class CircleManager : 
    N, M, R = 0, 0, 0
    circleList = []
    
    def __init__(self, N : int, M : int, R : int) -> None:
        self.N = N
        self.M = M
        self.R = R
        
        while N > 0 and M > 0 : 
            newCircle = Circle(N, M, R)
            self.circleList.append(newCircle)
            
            N -= 2
            M -= 2
    
    def rotate(self) : 
        for circle in self.circleList : circle.rotate()
    
    def assignData(self, array : list[list]) : 
        assert len(array) == self.N and len(array[0]) == self.M
        
        ROW = len(array)
        COL = len(array[0])
        
        for i in range(self.numOfCircles) : 
            currentCircle = self.circleList[i]
            assert type(currentCircle) is Circle
            
            for j in range(i, COL - i - 1) : 
                currentCircle.addData(array[i][j])
            
            for j in range(i, ROW - i - 1) : 
                currentCircle.addData(array[j][COL - i - 1])
            
            for j in range(COL - i - 1, i, -1) : 
                currentCircle.addData(array[ROW - i - 1][j])
            
            for j in range(ROW - i - 1, i, -1) : 
                currentCircle.addData(array[j][i])
    
    def toArray(self) : 
        array = [[0 for _ in range(self.M)] for _ in range(N)]
        
        ROW = len(array)
        COL = len(array[0])
        
        for i in range(self.numOfCircles) : 
            currentCircle = self.circleList[i]
            assert type(currentCircle) is Circle
            
            for j in range(i, COL - i - 1) : 
                array[i][j] = currentCircle.popData()
            
            for j in range(i, ROW - i - 1) : 
                array[j][COL - i - 1] = currentCircle.popData()
            
            for j in range(COL - i - 1, i, -1) : 
                array[ROW - i - 1][j] = currentCircle.popData()
            
            for j in range(ROW - i - 1, i, -1) : 
                array[j][i] = currentCircle.popData()
        
        return array

    @property
    def numOfCircles(self) : return len(self.circleList)
    
    def showData(self) : 
        for i in range(self.numOfCircles) : 
            print("[Circle {:2d}] \t: {:s}".format(i + 1, self.circleList[i]))


N, M, R = map(int, sys.stdin.readline().split())
manager = CircleManager(N, M, R)

array = []
for _ in range(N) : 
    row = list(map(int, sys.stdin.readline().split()))
    array.append(row)

# def showArray(array : list[list]) : 
#     for row in array : 
#         for element in row : 
#             print("{:3d}".format(element), end=" ")
#         print()

manager.assignData(array)
manager.rotate()

array = manager.toArray()
for row in array : print(*row)
