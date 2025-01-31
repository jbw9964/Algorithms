import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );
    private static final Map<Integer, List<EdgeInfo>> childrenMapper = new HashMap<>();
    private static final Map<Integer, Integer> depthCache = new HashMap<>();

    private static int ANSWER = 0;

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());

        Stack<Integer> parents = new Stack<>();

        // record data
        boolean[] tempArr = new boolean[N + 1];
        while (N-- > 1) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (!tempArr[parent]) {
                childrenMapper.put(parent, new ArrayList<>(2));
                parents.add(parent);
                tempArr[parent] = true;
            }

            List<EdgeInfo> childEdges = childrenMapper.get(parent);
            childEdges.add(
                    new EdgeInfo(child, cost)
            );
        }

        int answer = 0;

        while (!parents.isEmpty()) {

            int parent = parents.pop();
            List<EdgeInfo> children = childrenMapper.get(parent);

            // available candidates for root depths
            List<Integer> depths = new ArrayList<>(children.size());

            for (EdgeInfo child : children) {
                // edge cost + subtree depths = depths from root
                int depth = child.cost + getTreeDepth(child.childIndex);
                depths.add(depth);
            }

            // radius of the tree
            depths = depths.stream().sorted(Comparator.reverseOrder()).limit(2L).collect(Collectors.toUnmodifiableList());
            answer = Math.max(
                    answer, depths.stream().reduce(0, Integer::sum)
            );

            // cache depths
            depthCache.put(parent, depths.get(0));
        }

        // System.out.println(depthCache);

        System.out.println(answer);
    }

    private static int getTreeDepth(int root) {
        // cache depth
        if (depthCache.containsKey(root)) {
            return depthCache.get(root);
        }

        List<EdgeInfo> children = childrenMapper.get(root);

        // no child exists
        if (children == null) {
            return 0;
        }

        // depth is chosen as largest
        int depth = 0;
        for (EdgeInfo child : children) {
            int childIndex = child.childIndex;
            int subTreeDepth = getTreeDepth(child.childIndex);

            // record cache
            depthCache.put(childIndex, subTreeDepth);
            depth = Math.max(depth, child.cost + getTreeDepth(childIndex));
        }

        return depth;
    }
}

class EdgeInfo {

    int childIndex, cost;

    public EdgeInfo(int childIndex, int cost) {
        this.childIndex = childIndex;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "EdgeInfo{" +
                "childIndex=" + childIndex +
                ", cost=" + cost +
                '}';
    }
}