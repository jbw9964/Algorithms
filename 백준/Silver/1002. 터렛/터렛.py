T = int(input())

for _ in range(T) : 
    x1, y1, r1, x2, y2, r2 = map(int, input().split())

    if x1 == x2 and y1 == y2 : 
        if r1 == r2 :   print(-1)
        else        :   print(0)
    
    else : 
        dist_square = ((x2 - x1)**2 + (y2 - y1)**2)
        max_radius = max(r1, r2)

        if max_radius**2 > dist_square : 
            radius = min(r1, r2)

            if (max_radius - radius)**2 == dist_square      :   print(1)
            elif (max_radius - radius)**2 < dist_square     :   print(2)
            else                                            :   print(0)
        
        else : 
            radius_sum_square = (r1 + r2)**2

            if dist_square > radius_sum_square      :   print(0)
            elif dist_square == radius_sum_square   :   print(1)
            else                                    :   print(2)
