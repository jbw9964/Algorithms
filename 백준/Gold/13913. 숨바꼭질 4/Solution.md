
### [Source code](./숨바꼭질 4.py)

---

### 풀이 방법

- `BFS` 기반으로 초기 지점에서 목표 지점에 도달할 때까지 탐색
- `Deque` 를 이용해 `pop` 연산에 사용되는 시간을 줄이고, visit table 을 이용해 dynamic 하게 문제를 해결


---

### 풀이 중 마주친 어려움

- 초기 코드는 아래처럼 만들었음.

```python
BFS_list = [(0, [init_pos])]
Result = None

while True : 
    move, history = BFS_list.pop(0)
    current_pos = history[-1]

    print(move, history)

    if current_pos == dst_pos : 
        Result = (move, history)
        break

    else : 
        move_left = (
            move + 1, 
            history.copy().append(current_pos - 1)
        )
        move_right = (
            move + 1, 
            history.copy().append(current_pos + 1)
        )
        move_tp = (
            move + 1, 
            history.copy().append(2 * current_pos)
        )
        BFS_list.append(move_left)
        BFS_list.append(move_right)
        BFS_list.append(move_tp)


print(Result[0])
print(*Result[1])
```

- 초기 코드의 핵심은 `BFS` 를 `Queue` (or `Deque`) 를 굳이 이용하지 않고 그냥 `list` 를 이용한다는 점과,
- `dict` 를 이용해 visit 한 위치를 기록하고, `BFS_list` 에 움직이는데 걸린 시간, 움직인 이전 기록을 계속 저장하는 거였음.

- 하지만 제출하니 `시간초과`, `메모리초과` 등의 에러를 확인하여 막힘.


---

### 어려움 극복 과정

- 찾아보니 애초에 `Deque` 와 `list` 의 차이를 간과했음. 동일한 역할을 수행할 수 있지만 `pop` 연산에서 `time complexity` 차이가 심했음. [`(link)`](https://wellsw.tistory.com/122)

- 그리고 `BFS_list` 에 움직인 이전 기록들을 계속 저장하며 메모리초과 에러가 발생하는 것을 깨달음.
- 이를 해결할 방안이 떠오르지 않아 다른 사람의 코드를 확인. [`(link)`](https://paris-in-the-rain.tistory.com/102)

- 결국 정답은 이전 모든 기록을 저장하는 방식이 아니었음. <ins>**해당 위치에 도달하게 된 이전 위치**</ins> 만 저장하면 되는 것이었음.

```python
visit_list = [-1 for _ in range(MAX_NUM + 1)]
# ...
# ...
visit_list[next_pos] = current_pos
```


---

### 추후 개선 사항

- `BFS` 만 사용하면 그냥 풀리겠지 하며 자만했음. 
- 문제에서 주어진 입력 범위와 메모리 사용량 등을 보고 `"가늠할 수 있는"` 능력을 키워야 되겠음
