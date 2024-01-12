
### [Source code](./옹알이 （1）.py)

---

### 풀이 방법

- `Python` `str` class 의 method 를 이용해 해결. 
- `str.replace` 등을 이용.

---

### 풀이 중 마주친 issue

- 문제 이해하는데 좀 걸림.

---

### issue 해결 과정

- 그저 쉬운 문제겠거니 생각했지만 문제를 끝까지 읽지 않아 생긴 issue 였음.

---

### 추후 개선 사항

- 다른 사람의 코드를 보다 아래 코드를 발견함.

```python
import re

def solution(babbling):
    regex = re.compile("^(aya|ye|woo|ma)+$")
    cnt = 0
    for e in babbling:
        if regex.match(e):
            cnt += 1
    
    return cnt
```
- built-in module 중 `re` `(Regular Expression)` 을 이용한 풀이였음. 
- 어려운 문제로 갈수록 built-in module 을 사용하진 않지만, 그래도 어느정도는 알고 있으면 좋을 것 같다라는 생각이 듬.

