
### [Source code](./예쁜 숫자.py)

---

### 풀이 방법

- 트리 구조와 진법과의 관계를 이용, 규칙을 찾아 문제를 풀 수 있다.
- 스스로 풀려다 도저히 모르겠어 다음 솔루션을 참고하였다. [`link`](https://www.teferi.net/ps/problems/boj/3152)

```python
def is_pretty(n, p):
    count = [0] * 3
    while n:
        n, m = divmod(n, p)
        if m > 2:
            return False
        count[m] += 1
    
    return (
        count[1] == 1 and count[2] > 0
    ) or (
        count[1] == 2 and count[2] == 0
    )


def main():
    p, *n = [int(x) for x in input().split()]
    print(' '.join(
        '1' if is_pretty(n_i, p) else '0' \
            for n_i in n
    ))


if __name__ == '__main__':
    main()
```

- 코드를 보면 간단한 modulus 연산 만으로 문제를 풀 수 있다. 이는 문제의 트리 구조와 진법의 관계를 교묘하게 이용했기 때문이다.

- 문제에 제시된 트리는 `l_child : P * root`, `r_child : P * root + 1` 인 구조이다. 그런데 여기서 트리에 존재하는 모든 `node` 를 `P` 진법으로 나타내보자. 그러면 아주 신기한 현상이 일어난다.

- <ins>**트리의 모든 `node` 는 `P` 진법의 `0` 또는 `1` 로만 이루어져 있다!**</ins>
    이는 사실 생각해보면 당연한 논리이다. 어느 `node` 의 `child` 는 `P *` 연산이 이루어진다. 이에 `+ 0` 또는 `+ 1` 을 할 뿐이다.
    그런데 이를 `P` 진법으로 생각하면 `P *` 연산은 `lshift` 연산으로 생각할 수 있다. 
    따라서 어느 `node` 의 `l_child` 의 첫째자리수는 항상 `0`, `r_child` 는 `1` 이 되고, 이는 트리의 모든 `node` 가 `0` 또는 `1` 로만 이루어져 있음을 나타낸다.

    - 예시를 들어 생각하자. `P = 5` 라 하였을 때, `root : 1` 부터 생각하자.
        `1` 을 `5` 진수로 나타내면 (예를들어) `00001` 이다. 이 중 `l_child` 는 `5` 를 곱해야 하므로, `00001 --> 00010` 이 된다. 또한 `r_child` 는 `00010 + 00001 --> 00011` 이 된다.
        그런데 여기서 `l_child`, `r_child` 의 `child` 에도 똑같은 연산이 이뤄진다. `lshift` 를 진행하고 여기서 `1` 을 더 추가할 뿐이다.
        즉, 트리의 모든 `node` 는 `P` 진법으로 `0` 또는 `1` 로 이루어진 것이다.

- **트리의 모든 `node` 가 `0` 또는 `1` 로만 이루어져 있기 때문에, `"예쁜 숫자"` 의 조건을 상당히 완화시킬 수 있다.**

- 일단 첫째로, <ins>**`"예쁜 숫자"` 는 `P` 진법으로 `0`, `1`, `2` 로만 이루어져 있다. 이 외의 숫자가 들어있다면 가 `"예쁜 숫자"` 아니다.**</ins>
    - 문제에서 제시한 `"예쁜 숫자"` 의 조건은 `"트리 내의 서로 다른 두 노드에 매겨진 두 숫자의 합으로 표현 할 수 있는 방법이 한 가지"` 인 숫자이다.
    - 그런데 애초에 트리의 모든 `node` 는 `0` 또는 `1` 로만 이루어져 있다. 때문에 이를 이용한 조합 외의 숫자는 절대 `"예쁜 숫자"` 가 될 수 없다.
    - 즉, `"예쁜 숫자"` 는 `0`, `1`, `2` 로만 이루어져 있을 수밖에 없다.

- 앞선 조건을 선행해, 우리가 확인하는 수를 `P` 진법으로 나타내 확인했다 하자. 그렇다면 앞 조건을 만족하기만 하면 주어진 수는 `"예쁜 숫자"` 일까?
    예를들어 생각해 보자. `P = 5, 11201 (801)` 이라 하자. 앞서 `"예쁜 숫자"` 의 조건을 간단히 하면 다음과 같다.
    - 주어진 숫자는 트리 내 <ins>**서로 다른**</ins> 두 노드의 합으로 이루어 질 수 있어야 한다.
    - 숫자의 조합은 <ins>**오직 한가지**</ins> 이어야만 한다.
    
    `11201` 은 서로 다른 두 노드의 합으로 이루어진 것이 맞다. 앞서 확인했듯 모든 트리의 `node` 는 `0, 1` 로만 이루어져 있다.
    - 더 나아가 생각하면 트리는 `"n 자리 bit 에 조합 가능한 모든 0 과 1 의 조합"`을 만들어낸다. `(bitwise 가 0 또는 1 로만 이루어져 있다면, 해당 값은 트리 내 어느 곳에는 존재한다.)`
    하지만 `11201` 을 만들어 내는 조합이 **오직 한가지**인가? 그렇지 않다.
    - `11101 + 00100` 또는 `01101 + 10100` 등의 조합이 가능하다.
    - 다만 여기서 `10201 + 01000` 과 같은 조합은 생각하지 않는다. `10201` 은 트리에 속해있지 않기 때문이다.

- 이를 통해 숫자를 구성하는 `0`, `1`, `2` 의 개수에 따라 가능한 경우의 수가 바뀌는 것을 알 수 있다. 우리는 이 중 경우의 수가 오직 하나인지 판단해야 한다.
    - 우선 주어진 수의 `bitwise` 를 모두 확인해, `0, 1, 2` 의 개수를 알고있다 하자.

    - 우선 <ins>**`count(1) >= 3` 인 경우**</ins>를 생각해보자. 이 경우 앞서 보였던 예시처럼 여러 경우의 수가 존재한다. 따라서 해당 경우는 <ins>**`"예쁜 숫자"` 가 될 수 없다.**</ins>

    - `count(1) >= 3` 인 경우를 배제했으므로, 남은 경우는 `count(1) == 0, 1, 2` 인 경우 뿐이다. 해당 조건과 다른 숫자를 고려해 생각하자.

    - 먼저 <ins>**`count(1) == 0` 인 경우**</ins>를 생각하자. `count(0)` 은 큰 의미가 없으므로, `count(2)` 에 집중해 생각할 수 있다. 
    그런데 생각해보면 해당 경우에 `count(2) != 0` 이면, 절대 `"예쁜 숫자"` 가 될 수 없다. <ins>이 숫자를 생성해 낼 수 있는 조합은, 어느 같은 노드를 2 번 더하는 조합밖에 없기 때문이다.</ins>
    즉, `count(1) == 0` 이면 <ins>**`"예쁜 숫자"` 가 될 수 없다.**</ins> `(2200 = 1100 + 1100)`
    
    - 다음으로 <ins>**`count(1) == 1` 인 경우**</ins>이다. 해당 조건에서 만약 `count(2) == 0` 이면, 숫자를 조합하는 경우의 수는 존재하지 않는다. `(숫자를 만들 수 있는 경우는 자기 자신 + 0 뿐이다.)`
    따라서 해당 경우, `count(1) == 1` 이면서 <ins>**`count(2) != 0` 이면 `"예쁜 숫자"`**</ins> 임을 알 수 있다. `(1020 = 1010 + 0010)`

    - 마지막으로 <ins>**`count(1) == 2` 인 경우**</ins>이다. 만약 여기서 <ins>**`count(2) != 0` 이라면, 해당 수는 `"예쁜 숫자"` 일 수 없다.**</ins> 앞서 `count(1) >= 3` 일 때의 이유와 동일하다. 여러개의 경우의 수가 존재하기 때문이다. `(1102 = 1101 + 0001 || 0101 + 1001 ...)`

- 위 과정을 통해 주어진 수가 `"예쁜 숫자"` 인지 판별하는 조건을 알아보았다. 이를 정리하면 다음과 같다.
    - 1. 주어진 숫자를 `P` 진수로 나타내었을 때, `0, 1, 2` 를 제외한 숫자가 있다면 `"예쁜 숫자"` 가 아니다.
    - 2. 주어진 수를 이루는 `1, 2` 의 개수를 센 후, 다음 조건에 따라 `"예쁜 숫자"` 인지 아닌지 확인할 수 있다.
        - `count(1) == 1 AND count(2) != 0` 이면 `"예쁜 숫자"` 이다.
        - `count(1) == 2 AND count(2) == 0` 이면 `"예쁜 숫자"` 이다.
        - 이 외의 경우는 모두 `"예쁜 숫자"` 가 아니다. `(count(1) == 0 OR count(1) >= 3)`


---

### 풀이 중 마주친 issue

- 이딴게 `silver 2`?? 개어려운데...

---

### issue 해결 과정

- 없음

---

### 추후 개선 사항

- 문제 풀이 후, 다른 풀이를 찾다 해당 풀이를 찾았다. [`link`](https://www.acmicpc.net/source/67441229)

```python
from itertools import combinations
from collections import deque

def traverse_tree(p, limit):
    # Set to store the unique vertex values
    vertex_values = set()
    # Queue for breadth-first traversal
    queue = deque([1])  # Starting with the root node value 1

    while queue:
        # Dequeue the next node value
        node_value = queue.popleft()
        # Ensure we do not exceed the limit
        if node_value > limit:
            break
        # Add the current node value to the set of vertex values
        vertex_values.add(node_value)
        # Calculate the values of the left and right children
        left_child_value = p * node_value
        right_child_value = p * node_value + 1
        # Enqueue the children values for further traversal
        queue.append(left_child_value)
        queue.append(right_child_value)

    return vertex_values

def is_pretty(vertex_values, number):
    # Counter to keep track of the number of pairs that sum up to the given number
    sum_pair_counter = 0

    # Iterate through all unique pairs of numbers in the vertex values set
    for pair in combinations(vertex_values, 2):
        if sum(pair) == number:
            sum_pair_counter += 1
            # If more than one pair results in the sum, it's not a pretty number
            if sum_pair_counter > 1:
                return 0

    # If exactly one pair results in the sum, it's a pretty number
    return 1 if sum_pair_counter == 1 else 0

def check_pretty_numbers(p, n1, n2, n3, n4):
    # Determine the limit for tree traversal based on the maximum of n1, n2, n3, n4
    limit = max(n1, n2, n3, n4)
    # Traverse the tree and collect the vertex values
    vertex_values = traverse_tree(p, limit)
    # Check whether each of n1, n2, n3, n4 is a pretty number
    pretty_n1 = is_pretty(vertex_values, n1)
    pretty_n2 = is_pretty(vertex_values, n2)
    pretty_n3 = is_pretty(vertex_values, n3)
    pretty_n4 = is_pretty(vertex_values, n4)
    return pretty_n1, pretty_n2, pretty_n3, pretty_n4

p, n1, n2, n3, n4 = map(int, input().split())
result = check_pretty_numbers(p, n1, n2, n3, n4)
print(' '.join(map(str, result)))
```

- 사실 해당 풀이는 문제의 요구 사항에 맞춰진 풀이가 아니다. 나도 처음엔 그냥 트리를 만들고 일일이 확인하거나 `DFS` 를 이용하면 될 줄 알았지만, 정말 아주 안좋은 worst case 의 경우 무조건 시간초과가 되기 때문이다. `(시간초과 + 메모리초과 일 수 있다.)`
- 만약 문제의 `P` 가 작고, 찾고싶은 숫자는 아주 큰 경우가 그러하다. `P` 가 작기 때문에 트리의 `height` 에 따른 `node` 값이 작게 변하고, 반면 찾고싶은 수가 매우 크기 때문에 `height` 는 많이 필요해지기 때문이다. `(대충 생각해봐도 O(2^{n}) 보다도 오래 걸린다.)`
- 그래서 위 방법 말고 다른 방식을 계속 생각하다 모르겠어서 솔루션을 찾았었다. 근데 이 방법으로도 맞춰진다하니 이걸로 한번 다시 풀어봐야겠다.

    - [x] 다시 풀기
