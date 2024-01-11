
### [Source code](./Joint Attack.py)

### 풀이 방법

- 유클리드 호제법을 이용해 GCD 계산.
- $ y_{n} = x_{n} + \frac{1}{y_{n - 1}} = \frac{x_{n} * y_{n - 1} \ + \ 1}{y_{n - 1}} $ 인 사실을 이용, dynamic 하게 풀려고 함.
- $ y_{n} = \frac{\mathbf{upper_{n}}}{\mathbf{lower_{n}}} = \frac{x_{n} * \mathbf{upper_{n - 1}} \ + \ \mathbf{lower_{n - 1}}}{\mathbf{upper_{n - 1}}} $ 임을 이용.
- $ \mathbf{upper_{0}} = x_{0},\ \mathbf{lower_{0}} = 1 $

### 풀이 중 마주친 어려움

- GCD 를 이용해 가능한 나눠줄 수 있는 수를 나눠주려고 했음. 

```python
# 이런 방식으로 처음에 만들었음
upper_n = int(upper_n_1 / gcd)
lower_n = int(lower_n_1 / gcd)
```

- 하지만 `upper`, `lower` 값이 아주 커지면서 `upper_n_1 / gcd` 에 `round off error` 가 발생, 답이 틀리게 나왔음.


### 어려움 극복 과정

- `round off error` 를 없애기 위해 새로운 함수 `div` 를 정의, `/` 연산자 대신 반복 뺄셈을 이용해 계산.
