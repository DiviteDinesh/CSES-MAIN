// https://cses.fi/problemset/task/1193
// Labyrinth

/* Author : D. Dinesh */ 

import java.io.*;
import java.util.*;

public class Labyrinth {
    
    static final int MOD = (int) (1e9 + 7);
    static final int IMAX = Integer.MAX_VALUE;
    static final int IMIN = Integer.MIN_VALUE;
    static final String YES = "YES\n";
    static final String NO = "NO\n";
    static final String endl = "\n";
    
    static BufferedReader br;
    static BufferedWriter bw;
    
//////////////////////////////////////////////////////////////////////////////////////////////////
    
    /* Solution goes here */
    static final boolean LOCALIO = false;
    

    static void solve() throws IOException{
        int[] nm = arrint();
        int n = nm[0], m = nm[1];
        char[][] mat = matchar(n, m);
        outer : for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(mat[i][j] == 'A'){
                    dfs(i, j, mat);
                    break outer;
                }
            }
        }
    }
    static void dfs(int a, int b, char[][] mat) throws IOException{
        int n = mat.length, m = mat[0].length;
        Queue<int[]> q = new LinkedList<>(); // distante i j
        q.offer(new int[]{0, a, b});
        int[][][] dist = new int[n][m][3];
        for(int i = 0; i<n; i++) for(int j = 0; j<m; j++)
            dist[i][j][0] = IMAX;
        int[][] dir = {{0,1,'R'},{0,-1,'L'},{1,0,'D'},{-1,0,'U'}};
        int bi = -1, bj = -1;
        int ans = -1;
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int val = curr[0], i = curr[1], j = curr[2];
            if(mat[i][j] == 'B'){
                bi = i;
                bj = j;
                ans = val;
                break;
            } 
            for(int[] d : dir){
                int x = i + d[0], y = j + d[1];
                if( x >=0 && y>=0 && x<n && y <m && (mat[x][y] != '#') && 
                    dist[x][y][0] > val + 1){
                    dist[x][y] = new int[]{val + 1, i, j, d[2]};
                    q.offer(new int[]{val+1, x, y});
                }
            }
        }
        if(ans == -1) {
            print("NO" + endl);
            return;
        }
        print("YES" + endl);
        print(ans + endl);
        StringBuilder sb = new StringBuilder();
        while(ans-->0){
            sb.append((char)(dist[bi][bj][3]));
            int ai = dist[bi][bj][1];
            int aj = dist[bi][bj][2];
            bi = ai;
            bj = aj;
        }
        print(sb.reverse().toString()+endl);
    }
    
//////////////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) throws java.lang.Exception {
        if(!LOCALIO){
            br = new BufferedReader(new InputStreamReader(System.in));
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }
        else{
            br = new BufferedReader(new InputStreamReader(new FileInputStream("C:'\\Users\\geeth\\MAANG\\DSA_JAVA\\CSES - main\\input.txt")));
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\geeth\\MAANG\\DSA_JAVA\\CSES - main\\output.txt")));
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


