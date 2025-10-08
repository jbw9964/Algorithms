import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static List<Edge> edge;
    static long[] distance;
    static final int INF = Integer.MAX_VALUE >> 2;
    static int[][] graph;
    static boolean[] visited;

    static int E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        edge = new ArrayList<>();
        graph = new int[N][N];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            edge.add(new Edge(a, b, dist));
            graph[a][b] = 1;
        }

        int[] city = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) city[i] = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++) edge.get(i).dist -= city[edge.get(i).v];

        distance = new long[N]; Arrays.fill(distance, INF);
        visited = new boolean[N];
        if(!BellMan_Ford(S)) bw.write("gg");
        else if(BellMan_Ford_Check()) bw.write((city[S] - distance[E]) + "");
        else bw.write("Gee");

        bw.close();
        br.close();
    }

    static boolean BellMan_Ford(int start){
        distance[start] = 0;

        for(int i = 0; i < distance.length - 1; i++){
            for(Edge e : edge){
                if(distance[e.u] == INF) continue;
                if(distance[e.u] + e.dist < distance[e.v]){
                    distance[e.v] = distance[e.u] + e.dist;
                }
            }
        }

        return (distance[E] != INF);
    }

    static boolean BellMan_Ford_Check(){
        long[] prev = new long[distance.length];
        for(int i = 0; i < distance.length; i++) prev[i] = distance[i];

        for(Edge e : edge){
            if(distance[e.u] == INF) continue;
            if(distance[e.u] + e.dist < distance[e.v]){
                distance[e.v] = distance[e.u] + e.dist;
            }
        }

        for(int i = 0; i < distance.length; i++) {
            if(prev[i] != distance[i]){
                Arrays.fill(visited, false);
                if(canReach(i, E)) return false;
            }
        }
        return true;
    }

    static boolean canReach(int u, int v){
        if(u == v) return true;

        visited[u] = true;
        for(int i = 0; i < distance.length; i++){
            if(!visited[i] && graph[u][i] == 1 && canReach(i, v)) return true;
        }

        return false;
    }
}

class Edge{
    int u;
    int v;
    int dist;

    Edge(int u, int v, int dist) {
        this.u = u;
        this.v = v;
        this.dist = dist;
    }
}