
import math

def InRange(value, a, b) : 
    a, b = min(a, b), max(a, b)
    return True if a <= value and value <= b else False

def D(A) : 
    return A[0][0] * A[1][1] - A[0][1] * A[1][0]

X1, Y1, X2, Y2 = map(int, input().split())
X3, Y3, X4, Y4 = map(int, input().split())

# Ax + By = C
# A = Y1 - Y0
# B = X0 - X1
# C = X0 * Y1 - X1 * Y0

A1, B1, C1 = Y2 - Y1, X1 - X2, X1 * Y2 - X2 * Y1
A2, B2, C2 = Y4 - Y3, X3 - X4, X3 * Y4 - X4 * Y3

matrix_coef = [
    [A1, B1], 
    [A2, B2]
]

num = 0
point = None

Determinant = D(matrix_coef)

if Determinant == 0 : 
    gcd = math.gcd(A1, B1, C1)
    A1, B1, C1 = int(A1 / gcd), int(B1 / gcd), int(C1 / gcd)
    
    gcd = math.gcd(A2, B2, C2)
    A2, B2, C2 = int(A2 / gcd), int(B2 / gcd), int(C2 / gcd)

    if A1 == 0 and A2 == 0 : 
        (B1, C1) = (-B1, -C1) if B1 < 0 else (B1, C1)
        (B2, C2) = (-B2, -C2) if B2 < 0 else (B2, C2)
    
    else : 
        (A1, B1, C1) = (-A1, -B1, -C1) if A1 < 0 else (A1, B1, C1)
        (A2, B2, C2) = (-A2, -B2, -C2) if A2 < 0 else (A2, B2, C2)

    if A1 == A2 and B1 == B2 and C1 == C2 : 
        num = 1

        (X1, Y1), (X2, Y2) = min((X1, Y1), (X2, Y2)), max((X1, Y1), (X2, Y2))
        (X3, Y3), (X4, Y4) = min((X3, Y3), (X4, Y4)), max((X3, Y3), (X4, Y4))
        
        if X1 == X2 :                           # vertical line
            Y1, Y2 = min(Y1, Y2), max(Y1, Y2)
            Y3, Y4 = min(Y3, Y4), max(Y3, Y4)

            if Y1 < Y3 and InRange(Y2, Y3, Y4) : 
                if Y2 == Y3 : point = (X2, Y2)

            elif Y3 < Y1 and InRange(Y4, Y1, Y2) : 
                if Y4 == Y1 : point = (X4, Y4)

            elif (Y1 > Y3 and Y2 < Y4) or (Y3 > Y1 and Y4 < Y2) : 
                pass

            else : 
                num = 0

        else :                                  # horizontal or diagonal line
            # X1, X2 = min(X1, X2), max(X1, X2)
            # X3, X4 = min(X3, X4), max(X3, X4)
            
            if X1 < X3 and InRange(X2, X3, X4) : 
                if X2 == X3 : point = (X2, Y2)

            elif X3 < X1 and InRange(X4, X1, X2) : 
                if X4 == X1 : point = (X4, Y4)

            elif (X1 >= X3 and X2 <= X4) or (X3 >= X1 and X4 <= X2) : 
                pass

            else : 
                num = 0

else : 
    x_sol = D([
        [C1, B1],
        [C2, B2]
    ]) / Determinant    
    
    y_sol = D([
        [A1, C1],
        [A2, C2]
    ]) / Determinant

    if InRange(x_sol, X1, X2) and InRange(x_sol, X3, X4) and\
    InRange(y_sol, Y1, Y2) and InRange(y_sol, Y3, Y4) : 
        num = 1
        point = (x_sol, y_sol)

print(num)
if point is not None : 
    print("{:.15f} {:.15f}".format(*point))