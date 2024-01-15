
### [Source code](./오픈채팅방.py)

---

### 풀이 방법

- `uid` 에 대한 hash table 을 생성한다. `kye` 는 `uid`, `value` 는 `이름` 으로 생성한다.

---

### 풀이 중 마주친 issue

- 없음

---

### issue 해결 과정

- 없음

---

### 추후 개선 사항

- 아래 코드는 숏 코딩으로 제출한 사람의 코드이다.

```python
def solution(record):
    user_id = {
        EC.split()[1]:EC.split()[-1] \
            for EC in record \
                if EC.startswith('Enter') \
                or EC.startswith('Change')
    }

    return [
        f'{user_id[E_L.split()[1]]}님이 들어왔습니다.' \
            if E_L.startswith('Enter') \
            else f'{user_id[E_L.split()[1]]}님이 나갔습니다.' \
                for E_L in record \
                    if not E_L.startswith('Change')
    ]
```

- 연산 횟수를 줄일 수 있는 부분을 참고할 수 있을 것 같다.
