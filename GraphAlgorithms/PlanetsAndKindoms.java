/* Author : D. Dinesh */

import java.io.*;
import java.util.*;

public class PlanetsAndKindoms {

    static final int MOD = (int)(1e9 + 7);
    static final int IMAX = Integer.MAX_VALUE;
    static final int IMIN = Integer.MIN_VALUE;
    static final long INF = (long)1e17;
    static final String YES = "YES\n";
    static final String NO = "NO\n";
    static final String endl = "\n";

    static final String INPUT_PATH = "C:/Users/geeth/MAANG/DSA_JAVA/CSES - main/input.txt";
    static final String OUTPUT_PATH = "C:/Users/geeth/MAANG/DSA_JAVA/CSES - main/output.txt";

    static FastScanner fs;
    static BufferedWriter bw;

    static int nodes, edges;
    static List<Integer>[] g, r;
    static boolean[] vis;
    static Stack<Integer> stk;
    static int[] ans;
    static void dfs(int u){
        vis[u] = true;
        for(int v : g[u]) if(!vis[v]) dfs(v);
        stk.push(u);
    }
    static void dfs1(int u, int par){
        vis[u] = true;
        ans[u-1] = par;
        for(int v : r[u]) if(!vis[v]) dfs1(v, par);
    }
    static void solve() throws Exception {
        int[] nm = arrint(2);
        nodes = nm[0]; edges = nm[1];
        g = new ArrayList[nodes+1];
        r = new ArrayList[nodes+1];
        stk = new Stack<>();
        for(int i = 0; i<=nodes; i++){
            g[i] = new ArrayList<>();
            r[i] = new ArrayList<>();
        }
        for(int i = 0; i<edges; i++){
            int[] uv = arrint(2);
            int u = uv[0], v = uv[1];
            g[u].add(v);
            r[v].add(u);
        }
        vis = new boolean[nodes+1];
        for(int i = 1; i<=nodes; i++) if(!vis[i]) dfs(i);
        vis = new boolean[nodes+1];
        ans = new int[nodes];
        int cnt = 1;
        while(!stk.isEmpty()){
            int curr = stk.pop();
            if(!vis[curr]) dfs1(curr, cnt++);
        }
        print(cnt-1+endl);
        print(ans);
    }   

    public static void main(String[] args) throws Exception {
        try { fs = new FastScanner(new FileInputStream(INPUT_PATH)); bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OUTPUT_PATH))); }
        catch (Exception e) { fs = new FastScanner(System.in); bw = new BufferedWriter(new OutputStreamWriter(System.out)); }
        int t = 1;
        // int t = getInt();
        while(t-->0) solve();
        bw.flush();
    }

    // ------------------ FAST SCANNER ------------------
    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16]; private int ptr = 0, len = 0; private final InputStream in;
        FastScanner(InputStream in) { this.in = in; }
        private int readByte() throws IOException { return (ptr < len) || ((len = in.read(buffer)) > 0 && (ptr = 0) == 0) ? buffer[ptr++] : -1; }
        int nextInt() throws IOException { int c, s = 1, v = 0; while ((c = readByte()) <= 32); if (c == '-') { s = -1; c = readByte(); } while (c > 32) { v = v * 10 + (c - '0'); c = readByte(); } return v * s; }
        long nextLong() throws IOException { int c, s = 1; long v = 0; while ((c = readByte()) <= 32); if (c == '-') { s = -1; c = readByte(); } while (c > 32) { v = v * 10 + (c - '0'); c = readByte(); } return v * s; }
    }

    // ------------------ INPUT HELPERS ------------------
    static int getInt() throws Exception { return fs.nextInt(); }
    static long getLong() throws Exception { return fs.nextLong(); }

    static int[] arrint(int n) throws Exception { int[] a = new int[n]; for(int i=0;i<n;i++) a[i]=fs.nextInt(); return a; }
    static long[] arrlong(int n) throws Exception { long[] a = new long[n]; for(int i=0;i<n;i++) a[i]=fs.nextLong(); return a; }

    static int[][] matint(int n,int m) throws Exception { int[][] a=new int[n][m]; for(int i=0;i<n;i++) for(int j=0;j<m;j++) a[i][j]=fs.nextInt(); return a; }
    static long[][] matlong(int n,int m) throws Exception { long[][] a=new long[n][m]; for(int i=0;i<n;i++) for(int j=0;j<m;j++) a[i][j]=fs.nextLong(); return a; }

    // ------------------ OUTPUT HELPERS ------------------
    static void print(String s) throws Exception { bw.write(s); }
    static void print(int x) throws Exception { bw.write(x+""); }
    static void print(long x) throws Exception { bw.write(x+""); }
    static void print(char x) throws Exception { bw.write(x+""); }
    static void print(int[] arr) throws Exception { for(int i : arr) bw.write(i + " "); bw.write(endl);}
    static void print(long[] arr) throws Exception { for(long i : arr) bw.write(i + " "); bw.write(endl);}
    static void print(char[] arr) throws Exception { for(char i : arr) bw.write(i + " "); bw.write(endl);}


    // ------------------ MATH ------------------
    static long pow(long a,long b){ long r=1; while(b>0){ if((b&1)==1) r*=a; a*=a; b>>=1;} return r; }
    static int gcd(int a,int b){ while(b!=0){ int t=b; b=a%b; a=t;} return a; }
    static long gcd(long a,long b){ while(b!=0){ long t=b; b=a%b; a=t;} return a; }
    static long lcm(long a,long b){ return a/gcd(a,b)*b; }

    // ------------------ BINARY SEARCH ------------------
    static int lower_bound(int[] a,int x){ int l=0,r=a.length; while(l<r){ int m=(l+r)>>1; if(a[m]<x) l=m+1; else r=m;} return l; }
    static int upper_bound(int[] a,int x){ int l=0,r=a.length; while(l<r){ int m=(l+r)>>1; if(a[m]<=x) l=m+1; else r=m;} return l; }

}