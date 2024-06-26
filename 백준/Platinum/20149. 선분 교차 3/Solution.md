
### [Source code](./선분 교차 3.py)

---

### 풀이 방법

- 배웠던 선형대수를 이용해 풀이하였다.
- 점의 위치를 통해 두 선의 직선 방정식을 구하고, 이에 따른 행렬 방정식 $(A\mathbf{x} = B)$ 을 이용한다.
    - [`Cramer's Rule`](https://ko.wikipedia.org/wiki/%ED%81%AC%EB%9D%BC%EB%A9%94%EB%A5%B4_%EB%B2%95%EC%B9%99)
    - [`Invertible matrix`](https://en.wikipedia.org/wiki/Invertible_matrix) : 행렬값이 0 이면 두 직선이 평행한 이유

- 만약 계수행렬 $A$ 의 행렬값 $\mathbf{det(A)}$ 이 0 이라면, 주어진 두 직선은 평행하다. `(두 직선이 아예 동일한 직선이거나, 평행해 아예 만나지 안는 경우)`
- 해당 경우 이에 맞춰 추가 검사가 필요하다. `(두 직선이 동일한지, 만약 동일하다면 무한개의 접점이 있는지, 아니면 단일 접점만 존재하는지)`

- 만약 행렬값이 0 이 아니라면 크래머 법칙을 이용해, 접점의 위치를 판별한다. 
- 이후 접점의 위치가 두 선분 위에 존재하는지 판별한다. `(접점이 두 선분 위에 존재하는지 아닌지 판별, 위에 존재하지 않다면 문제 조건에 의해 접점 개수 0 으로 생각.)`

---

### 풀이 중 마주친 issue

- 처음에 선형대수를 이용해 풀 생각을 못했었음. 옛날에 하던대로 그냥 방정식 세우고 풀어서 접점 구하는 방식으로 했었음.
- 그러다보니 계산실수가 많아지고 코드가 너무 복잡해져 풀기 어려웠었음.
    - 주어진 직선이 horizontal or vertical 일 때, 계산 중 계수가 0 이여서 `ZeroDivisionError` 가 자주 났었음.
    - 이를 해결하기위해 코드 추가하고 case 계속 추가하고 하다보니 너무 복잡해져 있었음.

---

### issue 해결 과정

- 예전에 배웠던 선형대수 지식을 이용해 해결. 쓸데없이 복잡하게 방정식 직접 세워서 풀지 말고, 크래머 공식을 이용해 해결합. `(크래머 공식을 이용하면 직선이 horizontal, vertical 이든 상관없이 해결 가능)`

- `(P.S)` : 행렬값이 0 이면 두 직선이 평행한 이유.
    - 계수 행렬 $A$ 의 행렬값이 0 이면, $A$ 는 `singular matrix` 임. matrix 가 singular 하므로, 비가역 행렬이고, 역행렬이 존재하지 않음.
    - 또한 행렬값이 0 이기 때문에, $A$ 의 [`rank`](https://en.wikipedia.org/wiki/Rank_(linear_algebra)) 가 `(2 여야 하는데)` 1 임을 알 수 있음.
    - 어찌됐든 행렬값이 0 인 이유는 $A$ 의 `row vector` 가 `linearly dependent` 하기 때문이고, 이는 두 직선이 평행하거나 동일한 직선일 수밖에 없음.
    - 두 직선이 동일한 경우는 $A$ `row vector` 간 비율이 $B$ 의 `row vector` 간 비율과 동일한 경우이다. 반면 평행한 경우는 비율이 다른 경우이다.

---

### 추후 개선 사항

- 문제 풀이 후 다른 사람의 코드를 참조하니 [`CCW`](https://www.acmicpc.net/blog/view/27) 라는 방식을 이용한 것을 확인.
- 쉽게 말해 한 직선에 대해 외적을 이용해, 나머지 두 점의 위치가 직선 좌, 우로 위치하는지 확인하는 방식이다.

- `CCW` 를 이용하면 `"선분이 교차 할지 않할지"` 는 편히 판단할 수 있다. 하지만 교점을 구하는 경우 크래머 공식을 구하는게 훨씬 쉬워 보인다.

- `CCW` 라는 걸 처음 봐서 기록 겸 적어놓음. 다른 문제에서는 몰라도 이번 문제처럼 교점을 정확히 구해야 하는 경우, 크래머 공식이 훨씬 쉬울듯.

