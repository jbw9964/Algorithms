
# origin_{n} = 2 * origin_{n - 1} + 1
def move_Hanoi(n : int, pos_start : str, pos_dst : str) : 
    
    if n == 1   :   return print(pos_start, pos_dst)
    
    pos_mid =   "A" if "A" not in (pos_start, pos_dst) \
        else    "B" if "B" not in (pos_start, pos_dst) \
        else    "C"

    if n == 2   :       # pos_mid must be specified to make Hanoi tower at pos_dst
        print(
            "{} {}".format(pos_start, pos_mid),
            "{} {}".format(pos_start, pos_dst),
            "{} {}".format(pos_mid, pos_dst),
            sep="\n"
        )
        return
    
    move_Hanoi(n - 1, pos_start, pos_mid)       # put auxiliary tower to aside
    print("{} {}".format(pos_start, pos_dst))   # put largest disk to pos_dst
    move_Hanoi(n - 1, pos_mid, pos_dst)         # put auxiliary tower to pos_dst

    return


# main_{n} = origin_{n - 2} + main_{n - 2} + 3
def move_Hanoi_4(n : int, pos_start : str, pos_dst : str) : 
    
    if n == 1   :   return print(pos_start, "D")
    
    elif n == 2 : 
        print(
            "{} {}".format(pos_start, pos_dst),
            "{} D".format(pos_start),
            "{} D".format(pos_dst),
            sep="\n"
        )
        return

    pos_mid =   "A" if "A" not in (pos_start, pos_dst) \
        else    "B" if "B" not in (pos_start, pos_dst) \
        else    "C"
    
    move_Hanoi(n - 2, pos_start, pos_dst)       # put auxiliary tower to aside
    print(
        "{} {}".format(pos_start, pos_mid),     # put secondary largest disk to pos_mid
        "{} D".format(pos_start),               # pur largest disk to D
        "{} D".format(pos_mid),                 # put secondary largest disk to D
        sep="\n"
    )
    move_Hanoi_4(n - 2, pos_dst, pos_mid)       # put auxiliary tower to D
    
    return

N = int(input())

DP_Hanoi = [                        # recurrence relation of original Hanoi tower : 2^{n} - 1
    2**i - 1 \
        for i in range(1, N - 1)    # gen untill n = N - 2
]
DP_Hanoi_4 = [1, 3]

index = 2
while len(DP_Hanoi_4) < N   :       # gen DP    :   Hanoi_4_{n} = Hanoi_{n - 2} + 3 + Hanoi_4_{n - 2}
    DP_Hanoi_4.append(
        DP_Hanoi[index - 2] + DP_Hanoi_4[index - 2] + 3
    )
    index += 1

# print(N, len(DP_Hanoi), len(DP_Hanoi_4))
print(DP_Hanoi_4[N - 1])
move_Hanoi_4(N, "A", "C")
