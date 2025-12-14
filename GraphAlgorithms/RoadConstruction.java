/* Author : D. Dinesh */

import java.io.*;

public class RoadConstruction {

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

    static void solve() throws Exception {
        
    }

    public static void main(String[] args) throws Exception {
        try { 
            fs = new FastScanner(new FileInputStream(INPUT_PATH)); 
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OUTPUT_PATH))); }
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

    // ------------------ MATH ------------------
    static long pow(long a,long b){ long r=1; while(b>0){ if((b&1)==1) r*=a; a*=a; b>>=1;} return r; }
    static int gcd(int a,int b){ while(b!=0){ int t=b; b=a%b; a=t;} return a; }
    static long gcd(long a,long b){ while(b!=0){ long t=b; b=a%b; a=t;} return a; }
    static long lcm(long a,long b){ return a/gcd(a,b)*b; }

    // ------------------ BINARY SEARCH ------------------
    static int lower_bound(int[] a,int x){ int l=0,r=a.length; while(l<r){ int m=(l+r)>>1; if(a[m]<x) l=m+1; else r=m;} return l; }
    static int upper_bound(int[] a,int x){ int l=0,r=a.length; while(l<r){ int m=(l+r)>>1; if(a[m]<=x) l=m+1; else r=m;} return l; }

}