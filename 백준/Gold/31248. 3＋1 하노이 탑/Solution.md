
### [Source code](./3＋1 하노이 탑.py)

---

### 풀이 방법

- 해당 문제의 점화식을 생각하는 건 (비교적) 쉬웠음. 문제는 이동 순서를 출력하는 부분이었음.

- 평범한 하노이 탑의 점화식은, $H_{n} = 2H_{n - 1} + 1 = 2^{n} - 1$ 임. 잘 생각해서 유도 가능함.
    하지만 기둥이 4 개이고, `D` 기둥에 한번 놓으면 더이상 이동 불가능한, 문제의 탑의 경우 조금 다름.

- 우선 점화식은 다음과 같음.
    $H^{'}_{n} = H_{n - 2} + 3 + H^{'}_{n - 2} = 2^{n - 2} + H^{'}_{n - 2} + 2$

- 이에 대해 설명을 넣자면, 문제의 탑은 기둥 `D` 에 대한 조건이 추가됨.
    때문에 평범한 하노이 탑보다 더 적은 이동 횟수로 목표 완수가 가능함. 이 `"적은 이동 횟수"` 가 나타나는 부분에 주목하면 됨.

- 디스크가 4 개 인 경우에 대해 생각하면, 원판 2 개를 `B (또는 C)` 기둥으로 옮길때는 원본 하노이와 동일함.
    하지만 처음 위치 `A` 에 디스크 2 개가 남았을 때부터 이동이 달라짐. 3 번째 큰 디스크를 `A --> C` 로 옮기고, 가장 큰 디스크를 `A --> D`, 3 번째를 `C --> D` 로 옮겨야 됨.
    옮긴 후, `B` 에 위치한 원판 2 개를 `(결국에)` `D` 로 옮겨야 됨. 여기서 규칙을 찾을 수 있음.

- $n$ 개의 원판이 있다 하면, $n - 2$ 개의 원판을 `B (또는 C)` 로 `"평범하게"` 옮긴 후, 가장 큰 원판 2 개를 `D` 로 옮기고, `B (또는 C)` 에 위치한 $n - 2$ 개의 원판을 `("원래 목표와 동일하게")` `D` 로 옮기면 됨.
    즉, 점화식을 $H^{'}_{n} = H_{n - 2} + 3 + H^{'}_{n - 2}$ 으로 나타낼 수 있음. `(큰 원판 2 개를 D 로 옮기는데는 항상 3 번만 이동하면 됨.)`

- 여기까지는 자력으로 생각하였고, 앞서 말햇듯이 문제는 이동 순서를 출력하는 부분이었음.

---

### 풀이 중 마주친 issue

- 점화식을 생각하고 이를 바탕으로 아래처럼 구현함.

```python
N = int(input())

origin_num_1 = 1
origin_num_2 = 3
origin_move_1 = "AC"
origin_move_2 = "ACABCB"

main_num_1 = 1
main_num_2 = 3
main_move_1 = "AD"
main_move_2 = "ACADCD"


# origin_{n} = 2 * origin_{n - 1} + 1
def move_origin(n) -> tuple[int, str] : 
    if n == 1       :   return origin_num_1, origin_move_1
    elif n == 2     :   return origin_num_2, origin_move_2
    
    num, sequence_head = move_origin(n - 1)

    if n % 2 == 1 :         # A <--> B
        sequence_tail = sequence_head.replace("A", "X").replace("B", "A").replace("X", "B")
        return 2 * num + 1, sequence_head + "AC" + sequence_tail
    else :                  # A <--> C
        sequence_tail = sequence_head.replace("A", "X").replace("C", "A").replace("X", "C")
        return 2 * num + 1, sequence_head + "AB" + sequence_tail


# main_{n} = origin_{n - 2} + main_{n - 2} + 3
def move_main(n) -> tuple[int, str] : 
    if n == 1       :   return main_num_1, main_move_1
    if n == 2       :   return main_num_2, main_move_2

    num_head, sequence_head = move_origin(n - 2)
    num_tail, sequence_main = move_main(n - 2)

    if n % 2 == 1 :         # A <--> C
        sequence_tail = sequence_main.replace("A", "X").replace("C", "A").replace("X", "C")
        return num_head + num_tail + 3, sequence_head + "ABADBD" + sequence_tail
    else :                  # A <--> B
        sequence_tail = sequence_main.replace("A", "X").replace("B", "A").replace("X", "B")
        return num_head + num_tail + 3, sequence_head + "ACADCD" + sequence_tail

num, move = move_main(N)

print(num)
for i in range(int(len(move) / 2)) : 
    print(move[2 * i], move[2 * i + 1])
    pass
```

- 코드 주석을 보면 `A <--> C` 와 같은 부분이 있음. 이를 설명하자면, 
    `"n - 2 개의 기둥을 옮기는 순서를 알고있다하자. (평범한 하노이 + 문제의 하노이)"`
    `"n 개 기둥을 옮기기 위해 n - 2 개의 기둥을 "평범하게" 옮겨 C 에 위치에 뒀다 하자."`
    `"이제 n - 2 개 기둥을 D 로 옮겨야 되는데, 우리는 n - 2 의 원래 순서를 알고 있었으므로, 그 순서에서 A 와 C 를 서로 바꿔주면 되지 않을까?"`
    `"원래 알고 있는 순서는 A 에서 시작한 순서이므로, 이를 C 로 바꿔서 생각하면 되지 않을까?"`
    라고 생각했다. 즉, `"n - 2 기둥을 옮긴 후, A 기둥과 C 기둥 위치를 서로 바꾸면 되지 않을까?"` 라고 생각했다.

- 예를 들어 5 개 원팡을 생각했을 때, 3 개의 원판을 `C` 로 옮겨다 하자. 그러면 이 때 `"물리적으로"` `A` 와 `C` 기둥 위치를 서로 바꾸면 되지 않을까? 라는 발상이었다.
- 하지만 이에 헛점이 있었다.

---

### issue 해결 과정

- 문제를 풀다 도저히 모르겠어 해당 코드를 참조하였다. [`link`](https://neogr.tistory.com/13)

```python
def move(tiles, s, e):
    if tiles == 0:
        return

    move(tiles-1, s, 3-s-e)
    print('ABC'[s], 'ABC'[e])
    move(tiles-1, 3-s-e, e)

n = int(input())

dp = [0,1]
for i in range(2,21):
    dp.append(2**(i-2)-1 + 3 + dp[i-2])
print(dp[n])

disk_pos = 0
while n >= 2:
    move(n-2, disk_pos, 2-disk_pos)
    print('ABC'[disk_pos], 'B')
    print('ABC'[disk_pos], 'D')
    print('B', 'D')

    n -= 2
    disk_pos = 2-disk_pos

if n == 1:
    print('ABC'[disk_pos], 'D')
```

- 코드를 보면 애초에 `"기둥이 위치할 곳을 점찍어"` 주는 것을 볼 수 있다. 기둥이 처음에 위치하는 곳 `(s)` 과 중간에 방문할 곳 `(e)` 을 고려해서 구현하였다.
- 이처럼 구현하면 이전 실패한 코드처럼 막 `"순서를 바꾸고"` 그럴 필요 없이, 그냥 함수에 전달 인자만 바꿔주니 훨씬 간단하고 직관적이다.

- 이제 기존 아이디어가 왜 틀렸는지 말하자면, 정확히 말해 `"반만 맞았다"`. `"기둥의 시작 위치를 바꾸고 replace 하는 방식"` 자체는 틀리지 않았지만, 세부적으로 `replace` 하는 방식이 맞지않아 틀렸다. 아래 코드를 보자.

```python
def move_origin(n) -> tuple[int, str] : 
    if n == 1       :   return origin_num_1, origin_move_1

    elif n == 2     :   return origin_num_2, origin_move_2
    
    num, sequence_head = move_origin(n - 1)

    if n % 2 == 1 :         # A --> B | B --> C | C --> A
        sequence_tail = sequence_head.replace("C", "X").replace("B", "C").replace("A", "B").replace("X", "A")
        return 2 * num + 1, sequence_head + "AC" + sequence_tail
    
    else :                  # C --> B | B --> A | A --> C
        sequence_tail = sequence_head.replace("A", "X").replace("B", "A").replace("C", "B").replace("X", "C")
        return 2 * num + 1, sequence_head + "AB" + sequence_tail

def move_main(n) -> tuple[int, str] : 
    if n == 1       :   return main_num_1, main_move_1
    if n == 2       :   return main_num_2, main_move_2

    num_head, sequence_head = move_origin(n - 2)
    num_tail, sequence_main = move_main(n - 2)

    if n % 2 == 1 :         # C --> B | B --> A | A --> C
        sequence_tail = sequence_main.replace("A", "X").replace("B", "A").replace("C", "B").replace("X", "C")
        
        return num_head + num_tail + 3, sequence_head + "ABADBD" + sequence_tail

    else :                  # A --> B | B --> C | C --> A
        sequence_tail = sequence_main.replace("C", "X").replace("B", "C").replace("A", "B").replace("X", "A")
        return num_head + num_tail + 3, sequence_head + "ACADCD" + sequence_tail
```

- 이전 코드와 다른점은 그저 `A <--> B` 와 같은 방식이 아닌 `A`, `B`, `C` 셋을 바꾸는 방식인 점이다.
- 올바른 출력값과 기존 코드의 출력값을 비교하여 바꾸는 방식을 비교하였다. 논리적으로 어떻게 되는지는 모르겠으나, 바꾸는 방식을 `A`, `B`, `C` 가 순환하는 방식임을 발견하였다.
- 이에 맞춰 코드를 수정했고 제출하니 정답처리 되었다.

---

### 추후 개선 사항

- 애초에 `"기둥이 위치할 곳을 점찍는"` 방식으로 구현하였다면 훨씬 쉬운 문제였다. 하지만 이를 귀찮을 것 같아 생각하지 않았고, 그 결과 먼 길을 따라 문제를 해결하였다.
- 그나마 다행인 점은 아이디어 자체는 틀리지 않았다는 것을 확인한 점이고, 그럼에도 불구하고 나쁜점은 논리적으로 왜 저렇게 되는지 이해하지 못했다는 점이다.

- 이를 논리적으로 이해하려면 어떻게 해야될지는 잘 모르겠다. 너무 막막하다.
