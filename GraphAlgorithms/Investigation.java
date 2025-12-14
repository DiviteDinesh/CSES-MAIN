/* Author : D. Dinesh */ 

import java.io.*;
import java.util.*;

public class Investigation {
    
    // Constants
    static final int MOD = (int) (1e9 + 7);
    static final int IMAX = Integer.MAX_VALUE;
    static final int IMIN = Integer.MIN_VALUE;
    static final long INF = (long) 1e17;
    static final String YES = "YES\n";
    static final String NO = "NO\n";
    static final String endl = "\n";
    
    // Local File Paths
    static final String INPUT_PATH = "C:/Users/geeth/MAANG/DSA_JAVA/CSES - main/input.txt";
    static final String OUTPUT_PATH = "C:/Users/geeth/MAANG/DSA_JAVA/CSES - main/output.txt";

    static BufferedReader br;
    static BufferedWriter bw;
    
//////////////////////////////////////////////////////////////////////////////////////////////////
    
    /* Solution goes here */
    // find min cost
    // min cost routes MOD
    // min cost min flights
    // min cost max flights
    static int nodes, edges;
    static List<int[]> g[];
    static boolean[] vis;
    static void solve() throws IOException{
        int[] nm = arrint();
        nodes = nm[0];
        edges = nm[1];
        g = new ArrayList[nodes+1];
        vis = new boolean[nodes+1];
        for(int i = 0; i<=nodes; i++)  g[i] = new ArrayList<>();
        for(int i = 0; i<edges; i++){
            int[] uvw = arrint();
            int u = uvw[0], v = uvw[1], wt = uvw[2];
            g[u].add(new int[]{v, wt});
        }
        long[] dist = new long[nodes+1];
        long[] ways = new long[nodes+1];
        long[] minNodes = new long[nodes+1];
        long[] maxNodes = new long[nodes+1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        ways[1] = 1;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a,b)->Long.compare(a[0], b[0]));
        pq.offer(new long[]{0, 1});
        while(!pq.isEmpty()){
            long[] temp = pq.poll();
            int u = (int) temp[1];
            long curr = temp[0];
            for(int[] edge : g[u]){
                int v = edge[0], wt = edge[1];
                long nd = wt + curr;
                if(dist[v] > nd){
                    dist[v] = nd;
                    minNodes[v] = minNodes[u] + 1;
                    maxNodes[v] = maxNodes[u] + 1;
                    ways[v] = ways[u] % MOD;
                    pq.offer(new long[]{nd, v});
                }
                else if(dist[v] == nd){
                    ways[v] = (ways[u] + ways[v]) % MOD;
                    minNodes[v] = min(minNodes[v], minNodes[u]+1);
                    maxNodes[v] = max(maxNodes[v], maxNodes[u]+1);
                }
            }
        }
        print(dist[nodes] + " " + ways[nodes] + " " + minNodes[nodes] + " " + maxNodes[nodes]);
    }
    //     long[] ans = {IMAX,0L,IMIN, IMAX}; // lowest, routes, MaxF, MinF 
    //     dfs(1, ans, 0, 0);
    //     long temp = ans[2];
    //     ans[2] = ans[3];
    //     ans[3] = temp;
    //     print(ans);
    // }
    // static void dfs(int u, long[] ans, long cost, long f){
    //     if(u == nodes){
    //         if(ans[0] > cost){
    //             ans[0] = cost;
    //             ans[1] = 1;
    //             ans[2] = f;
    //             ans[3] = f;
    //         }
    //         else if(ans[0] == cost){
    //             ans[1]++;
    //             ans[1] %= MOD;
    //             ans[2] = Math.max(ans[2], f);
    //             ans[3] = Math.min(ans[3], f);
    //         }
    //     }
    //     vis[u] = true;
    //     for(int[] edge : g[u]){
    //         int v = edge[0], wt = edge[1];
    //         dfs(v, ans, cost+wt, f+1);
    //     }
    // }
    
//////////////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) throws java.lang.Exception {
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_PATH)));
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OUTPUT_PATH)));
        } catch (FileNotFoundException e) {
            br = new BufferedReader(new InputStreamReader(System.in));
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }
        
        // int t = Integer.parseInt(br.readLine());
        int t = 1;
        while (t-- > 0) {
            solve();
        }
        bw.flush();
    }
    
    // a pow b
    static long pow(int A, int b, int mod){ long res = 1, a = A;  while(b>0){  if((b&1)==1) res = (res * a) % mod;  a = (a * a) % mod;  b >>= 1;} return res; }
    static long pow(int A, int b){ long res = 1, a = A; while(b>0){  if((b&1)==1) res *= a;  a *= a;  b >>= 1; } return res; }
    // bs methods
    static int lower_bound(int[] ps, int x) { int low = 0, n = ps.length, high = n - 1, ans = n;  while (low <= high) {  int mid = (low + high) >> 1; if (ps[mid] >= x) { ans = mid; high = mid - 1; } else low  = mid + 1; } return ans; }
    static int upper_bound(int[] ps, int x) { int low = 0, n = ps.length, high = n - 1, ans = -1; while (low <= high) {  int mid = (low + high) >> 1; if (ps[mid] <= x) { ans = mid; low  = mid + 1; } else high = mid - 1; } return ans; }
    // general IO
    static int getInt() throws IOException { return Integer.parseInt(br.readLine()); }
    static long getLong() throws IOException { return Long.parseLong(br.readLine()); }
    static double getDouble() throws IOException { return Double.parseDouble(br.readLine()); }
    static void print() throws IOException {bw.write(endl);}
    static void print(int x) throws IOException {bw.write(x+"");}
    static void print(long x) throws IOException {bw.write(x+"");}
    static void print(double x, int precision) throws IOException { bw.write(String.format("%." + precision + "f", x)); }
    static void print(String s) throws IOException { bw.write(s); }
    // array IO
    static int[] arrint() throws IOException { String[] str = br.readLine().split(" "); int n = str.length;  int[] x = new int[n]; for (int i = 0; i < n; i++) x[i] = Integer.parseInt(str[i]); return x; }
    static long[] arrlong() throws IOException { String[] str = br.readLine().split(" "); int n = str.length; long[] x = new long[n]; for (int i = 0; i < n; i++) x[i] = Long.parseLong(str[i]); return x; }
    static double[] arrdouble() throws IOException { String[] str = br.readLine().split(" ");  int n = str.length; double[] x = new double[n]; for (int i = 0; i < n; i++) x[i] = Double.parseDouble(str[i]); return x; }
    static void print(int[] x) throws IOException { for (int i : x) bw.write(i + " "); bw.write(endl); }
    static void print(long[] x) throws IOException { for (long i : x) bw.write(i + " "); bw.write(endl); }
    static void print(double[] x, int precision) throws IOException { for (double i : x)  print(i, precision); bw.write(endl); }
    // matrix IO
    static int[][] matint(int n, int m) throws IOException { int[][] mat = new int[n][]; for (int i = 0; i < n; i++) mat[i] = arrint(); return mat; }
    static long[][] matlong(int n, int m) throws IOException { long[][] mat = new long[n][]; for (int i = 0; i < n; i++) mat[i] = arrlong(); return mat; }
    static char[][] matchar(int n, int m) throws IOException { char[][] mat = new char[n][]; for (int i = 0; i < n; i++) mat[i] = br.readLine().toCharArray(); return mat; }
    static void print(int[][] x) throws IOException { for (int[] i : x) print(i); bw.write(endl); }
    static void print(long[][] x) throws IOException { for (long[] i : x) print(i); bw.write(endl); }
    // general math
    static int min(int ... args){ int mini = IMAX; for(int i : args) mini = Math.min(mini, i); return mini; }
    static int max(int ... args){ int maxi = IMIN; for(int i : args) maxi = Math.max(maxi, i); return maxi; }
    static int sum(int ... args){ int sum = 0; for(int i : args) sum += i; return sum;}
    static long min(long ... args){ long mini = Long.MAX_VALUE; for(long i : args) mini = Math.min(mini, i); return mini; }
    static long max(long ... args){ long maxi = Long.MIN_VALUE; for(long i : args) maxi = Math.max(maxi, i); return maxi; }
    static long sum(long ... args){ long sum = 0; for(long i : args) sum += i; return sum;}

    static boolean isPrime(long n) { if (n <= 1) return false; if (n <= 3) return true; if (n % 2 == 0 || n % 3 == 0) return false; for (int i = 5; i * i <= n; i += 6) { if (n % i == 0 || n % (i + 2) == 0) return false; } return true; } 
    static int gcd(int a, int b) { while (b != 0) {  int temp = b; b = a % b; a = temp; } return a; } 
    static long gcd(long a, long b) {  while (b != 0) { long temp = b; b = a % b; a = temp; } return a; }
    static int lcm(int a, int b) { return (int)(((long)a * b ) / gcd(a, b)); }
    static long lcm(long a, long b) { return ((a * b) / gcd(a, b)); }
    
}