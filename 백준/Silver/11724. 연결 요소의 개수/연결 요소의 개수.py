import sys
sys.setrecursionlimit(10**4)

def dfs(node, graph, visit):
    visit[node] = True

    for n in graph[node]:
        if not(visit[n]):
            dfs(n, graph, visit)

N, M = map(int, input().split())
graph = [[] for _ in range(N+1)]
visited = [False] * (N+1)
cnt = 0

for _ in range(M):
    x, y = map(int, input().split())
    graph[x].append(y)
    graph[y].append(x)

for i in range(1, N+1):
    if not(visited[i]):
        dfs(i, graph, visited)
        cnt += 1

print(cnt)