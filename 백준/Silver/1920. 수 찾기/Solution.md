
### [Source code](./수 찾기.py)

---

### 풀이 방법

- 전형적인 binary search 를 이용해 해결.

---

### 풀이 중 마주친 issue

- binary search 의 세부적인 부분이 잘 기억나지 않아, 마지막 부분에 그냥 뭉뚱그려 구현함.

---

### issue 해결 과정

```python
if num in [Num_list[r_index], Num_list[l_index]] : 
    flag = True
```

---

### 추후 개선 사항

- 다른 사람의 해결 방법을 확인해봄.

```python
import sys

n = int(sys.stdin.readline())

x = list(map(int,sys.stdin.readline().split()))
    
lis = set(x)

m = int(sys.stdin.readline())

y = list(map(int,sys.stdin.readline().split()))

for i in y:
    if i in lis:
        print(1)
    else:
        print(0)
```

- binary search 를 사용하기 위해선, 주어진 배열이 sort 되어있어야 함.
- python list 의 sort 는 (아마도) quick sort 는 이용하는데, 이는 `O(nlog(n))` 임.
- 그런데 잘 생각해보면 sort 하지 않고 배열에 맞춰진 hash table 만 있어도 됨. `(set)`

- 즉, sort 하는데 걸리는 시간보다 hash table 을 만드는데 걸리는 시간이 더 짧을 수 있음. 그래서 내 코드는 `996 ms` 걸린 반면, 위 솔루션은 `168 ms` 밖에 걸리지 않음.

- 그저 문제가 `이진탐색 분류` 로 되어있어 이러한 점을 간과함. 앞으로 문제풀기 전, 여러 방식을 생각하고 어느 방식이 제일 빠른지 비교할 수 있어야 됨.

