
### [Source code](./아기 상어.py)

- [x] 세부 알고리즘 바꿔 재풀이 [`(이전 코드)`](./아기 상어_prev.py)
- 해당 Solution 은 [`(이전 코드)`](./아기 상어_prev.py) 에 맞춰 작성됨.
---

### 풀이 방법

- `BFS` 를 이용해 문제를 풀 수 있다.
- `BFS` 를 이용해 현재 이동 가능한 위치 중 생선을 먹을 수 있는 위치를 찾는다.
- 먹을 수 있는 위치를 모두 찾아 저장하고, 이를 거리 순 `(+ most upper left)` 으로 정렬하면 맨 첫째 원소가 이동할 위치이다.

---

### 풀이 중 마주친 issue

- 처음에 문제를 이해하는데 시간이 걸렸다.
- 문제 조건 중 다음 2 조건을 읽지 않고 넘어가 초반에 이해하는데 시간이 걸렸따.
    - `아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다. 따라서, 크기가 같은 물고기는 먹을 수 없지만, 그 물고기가 있는 칸은 지나갈 수 있다.`
    - `아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다. 예를 들어, 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3이 된다.`

---

### issue 해결 과정

- 위 조건을 고려하지 않고 문제의 예제를 생각하여 구현하는데 정확한 조건을 알 수 없었고, 그 때문에 풀이가 늦어졌다.
- 문제를 제대로 읽고 해결하였다.

---

### 추후 개선 사항

- 문제를 끝까지 제대로 읽어야 한다.
- 그리고 다른 사람의 풀이를 확인했는데 모두 `BFS` 를 이용했다. 하지만 똑같이 `BFS` 를 이용해도 시간차이가 어느정도 있는 것을 확인했다.
    - 나는 `188ms` 인 반면, 아래 코드는 `44ms` 이다. [`link`](https://www.acmicpc.net/source/71773658)

```python
from sys import stdin

input = stdin.readline


# 상어 좌표 리턴. 상어 초기 위치는 0으로 변환
def find_shark():
    for i in range(n):
        for j in range(n):
            if space[i][j] == 9:
                space[i][j] = 0
                return i, j


def find_food(point):
    x, y = point # 상어 위치
    dx = [-1, 0, 0, 1]
    dy = [0, -1, 1, 0]
    visited = [[False] * n for _ in range(n)] # 탐색 리스트
    visited[x][y] = True
    start = [point]
    time = 0

    while start:
        time += 1
        new_start = []
        can_eat = []
        for x, y in start:
            for j in range(4):
                nx = x + dx[j]
                ny = y + dy[j]
                # 상어 이동조건
                if 0 <= nx < n and 0 <= ny < n and space[nx][ny] <= shark_size and not visited[nx][ny]:
                    # 상어 먹을 수 있는 조건 (자기보다 작은 물고기만 먹을 수 있음)
                    if 0 < space[nx][ny] < shark_size:
                        can_eat.append((nx, ny))
                    visited[nx][ny] = True
                    new_start.append([nx, ny])

        if can_eat:
            # 물고기 먹는 우선순위 : 거리 -> 가장 위 -> 가장 왼쪽
            can_eat.sort()
            x, y = can_eat[0]
            space[x][y] = 0
            return x, y, time
        start = new_start[:]
    return None


n = int(input())
space = [list(map(int, input().split())) for _ in range(n)]

position = find_shark()
result = 0 # 시간
eat = 0 # 현재 먹은 물고기 마릿수
shark_size = 2 # 상어 크기
while True:
    food = find_food(position)
    if food is None:
        break
    *position, t = food
    result += t
    eat += 1
    if eat == shark_size:
        shark_size += 1
        eat = 0

print(result)
```

- 이처럼 성능이 차이나는 이유는, 윗 코드 `find_food` 함수 `while` 문 중간에 `if can_eat : ` 부분 때문이다.
- 위 코드는 `start` 리스트에 인접한 `node` 를 확인해 집어넣고 있다.
    이동 가능하면 `start` 에 집어넣고, 이동 가능하면서 해당 위치 생선을 먹을 수 있다면 `can_eat` 리스트에 집어넣는다.
- 이를 통해 `BFS` 를 진행하며 최단 거리의 생선을 발견했을 때, 탐색을 즉시 멈추고 잘 가공해서 `return` 하고 있다.
    `can_eat` 리스트에는 최단 경로 생선들의 위치가 넣어진다. 이를 `sort` 해 좌표와 이동 시간을 `return` 하고 있다.

- 해당 문제를 풀며 위 생각을 하긴 했다. 하지만 이를 구현할 용기가 좀처럼 나지않아 `"그냥 다 탐색하지 뭐..."` 라는 생각으로 구현하지 않았다.
- 성능 차이가 꽤 나므로 이를 이용해 문제를 다시 한번 풀어봐야 겠다.
    - [x] 세부 알고리즘 바꿔 재풀이 [`(이전 코드)`](./아기 상어_prev.py)

