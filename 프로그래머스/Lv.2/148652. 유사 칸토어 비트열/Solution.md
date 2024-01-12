
### [Source code](./유사 칸토어 비트열.py)

---

### 풀이 방법

- 칸투어 "문자열" (비트열) 을 트리형태로 바꿔 생각함.
- 이 때 트리의 모든 `leaf` 와 `node` 를 생성한다면 `메모리초과` 또는 `시간초과` 될 수 있음. 
- 그래서 트리 구조 중 skip 가능한 부분은 최대한 skip 하고, 무조건 생성해야 되는 부분만 생성한 후, 1 의 개수를 셈.

---

### 풀이 중 마주친 issue

- 초기 코드는 모든 `leaf`, `node` 를 생성해 1 의 개수를 세는 방식이었음. `시간초과` 로 인해 트리 구조로 바꿔 생각함.

---

### issue 해결 과정

```python
level = 0
l_leaf, r_leaf = l, r
while l_leaf != r_leaf : 
    l_leaf //= 5
    r_leaf //= 5
    level += 1

upper_level = n - level         # remaining upper levels of tree
stem_index = l_leaf             # corresponding index of leaf nodes
```

- 위 코드를 통해 트리 구조 중 skip 가능한 height 개수를 셈.
- `steam_index` 는 `far left leaf node` 와 `far right leaf node` 가 만나는 `node` 의 `index` 임.
- 이 `index` 에 해당하는 비트열이 무엇인지 알고 있다면 `(0 또는 1)`, 해당 부분부터 또다른 트리를 만들어 개수를 세면 되겠다 생각하였음.

```python
from collections import deque

index_queue = deque()
index_queue.appendleft(stem_index)

for _ in range(upper_level) :               # store which index (node of tree) has to be selected
    stem_index //= 5
    index_queue.appendleft(stem_index)

string = "1"
index_queue.popleft()

for _ in range(upper_level) :               # select last corresponding node
    string = gen_cantorian(string)
    string = string[index_queue.popleft() % 5]
```

- 위 코드를 통해 `index` 에 해당하는 비트열 값이 `string` 에 저장되게 됨. 이후 나머지 트리 구조를 생성한 후, 1 의 개수를 세면 끝.

---

### 추후 개선 사항

- 문제풀이 후 다른사람의 코드를 참조함. 해당 방식을 전혀 생각하지 못했어서 코드 리뷰 형식으로 개선 사항을 남김.

```python
def solution(n, l, r):
    answer = r - l + 1
    for num in range(l - 1, r):
        while num >= 1:
            a, b = divmod(num, 5)
            if b == 2 or a == 2:
                answer -= 1
                break
            num = a

    return answer
```

- 애초에 트리 구조를 이용하지 않고, <ins>**"정답을 잘 생각해보면 된다"**</ins> 라고 함.

- 칸투어 비트열은 `1` 에서 `11011` 이 생성되고, `0` 에서 `00000` 이 생성되는 규칙임.
- `1` --> `11011` --> `11011 11011 00000 11011 11011` 처럼 생성됨. 여기서 규칙을 찾을 수 있음.

- `0` 이 생성되는 규칙은  <ins>**`"이전 비트열 숫자가 00000 중 하나 였었거나"`**</ins>, <ins>**`"11011 중 0 이었을"`**</ins> 경우임.

- 이를 활용한 풀이가 위 코드임

```python
a, b = divmod(num, 5)
if b == 2 or a == 2:
    answer -= 1
```

- 주어진 `num` `(index of leaf node)` 을 5 로 나눴을 때, 그 묷을 `a`, 나머지를 `b` 로 받는다.
- 여기서 `a == 2` 는 `"이전 비트열 숫자가 00000 중 하나 였는지` 를 판단하는 논리고, `b == 2` 는 `"이전 비트열 숫자가 "11011 중 0 이었는지"` 판단하는 논리이다.

- 먼저 `b == 2` 는 직관적으로 생각할 수 있다. 애초에 `leaf node` 의 총 개수는 5 의 배수이고, `b == 2` 인 `index` 는 `"5 개 중 3 번째 원소"` 를 나타낸다. `(index 시작을 0 부터 세서 b == 2 인 지점이 3 번째 원소)`
-  즉, `b == 2` 는 `"5 개 중 3 번째 원소"`, `0` 일 수밖에 없다.

- 두번째로 `a == 2` 는 더 풀어서 설명하자면, `"이전 비트열 숫자가 10011 중 0 으로부터 파생된 것인지 확인하는"` 논리이다. 이는 트리 형태로 더 나타내면 직관적으로 이해할 수 있다.

```
# n = 2
            11011       11011       00000       11011       11011

# n = 3
11011   11011   00000   11011   11011 ...
```

- `a == 2` 는 위 트리 구조 중 `00000` 에 적용하는 논리이다. 애초에 `00000` 은 `11011` 중 `0` 에서 파생되었기 때문에 적용 가능하다.
