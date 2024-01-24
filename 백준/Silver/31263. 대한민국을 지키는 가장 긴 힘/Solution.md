
### [Source code](./대한민국을 지키는 가장 긴 힘.py)

---

### 풀이 방법

- 주어진 문자열을 잘 해독하면 되는 문제.
- 문제에서 주어진 대로 `"쉽게"` 구현하면 쉽게 풀 수 있음.

---

### 풀이 중 마주친 issue

- 초기 코드는 대충 아래처럼 생겼음.

```python

index = 0
count = 0
while index < len(string) : 

    ...

    if first_char > 6 :     # 2, 1 digits
        if third_char == 0  :   pass        # 1 digit
        else                :   index += 1  # 2 digit

    elif first_char == 6 :  # 3, 2, 1 digits
        if second_char < 4 :        # 3, 2 digits
            
            if fourth_char == 0 : index += 1    # 2 digit
            else                : index += 2    # 3 digit

        elif second_char == 4 :     # 3, 2 digits
            
            if third_char > 2 or fourth_char == 0   :   index += 1      # 2 digit
            else                                    :   index += 2      # 3 digit

        else :                      # 2, 1 digits
            if third_char == 3  :   pass        # 1 digit
            else                :   index += 1  # 2 digit

    else :                  # 3, 2, 1 digits
        if fourth_char != 0     :   index += 2  # 3 digit
        elif third_char != 0    :   index += 1  # 2 digit
        else                    :   pass        # 1 digit

    index += 1
    count += 1

print(count)
```

- 쓸데없이 너무 복잡하게 생각함. 
- 코드를 보면 첫번째 글자가 `6` 일 때, 최대로 큰 숫자가 가능한지 복잡하게 확인함.
    한마디로 각 조건에 따라 `3`, `2` 글자가 모두 가능한지 확인하느랴 코드가 길어지고, 그러느랴 제작한 알고리즘이 맞는지 아닌지 파악을 못함.
    `(초기 코드 제출하니 틀림. 주어진 자리수가 1 ~ 5 인 경우에만 정답이었음.)` [`(22점, 추가 설명은 문제 내용 확인)`](https://www.acmicpc.net/problem/31263)

---

### issue 해결 과정

- 풀이 중, 질문 게시판에 해당 글을 봄. [`link`](https://www.acmicpc.net/board/view/134614) 여기서 영감을 얻음.

```python
n = int(input())
s=input()

count=0
m=0

while len(s) > 2:
    if int(s[m:m+3]) <= 641:
        s = s[3:]
        count+=1

print(count if len(s) ==0 else count+1)
```

- 코드를 보면 `int()` 를 이용해 주어진 문자열이 `641` 이하인지 확인하고 있음.
    초기 코드처럼 쓸데없이 한글자씩 확인할 필요 없이, 이렇게 묶고 `int` 로 convert 하면 매우 쉬워지는 것을 깨달음. 이를 이용해 구현함.

---

### 추후 개선 사항

- 자꾸 생각하는 방식이 왔다갔다 하는 것 같음. 어쩔땐 `C` 처럼 생각하고, 어쩔땐 `Python` 처럼 생각함.
    앞으론 가장 편하게 구현 가능한 방식을 생각해고, 그에 맞춰 생각해야 함.

