import java.io.*;
import java.util.*;
 
public class RoadReparation {
 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
 
    static int nextInt() throws Exception {
        while (st == null || !st.hasMoreElements())
            st = new StringTokenizer(br.readLine());
        return Integer.parseInt(st.nextToken());
    }
 
    static class DSU {
        int[] parent, size;
        DSU(int n) {
            parent = new int[n+1];
            size = new int[n+1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }
        boolean union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a == b) return false;
            if (size[a] < size[b]) { int t=a; a=b; b=t; }
            parent[b] = a;
            size[a] += size[b];
            return true;
        }
    }
 
    public static void main(String[] args) throws Exception {
        int n = nextInt();
        int m = nextInt();
 
        int[][] edges = new int[m][3];
        for (int i = 0; i < m; i++) {
            edges[i][0] = nextInt();
            edges[i][1] = nextInt();
            edges[i][2] = nextInt();
        }
 
        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));
 
        DSU dsu = new DSU(n);
        long total = 0;
        int used = 0;
 
        for (int[] e : edges) {
            if (dsu.union(e[0], e[1])) {
                total += e[2];
                used++;
                if (used == n - 1) break;
            }
        }
 
        if (used != n - 1) {
            System.out.println("IMPOSSIBLE");
            return;
        }
 
        System.out.println(total);
    }
}