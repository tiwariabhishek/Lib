package structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BinaryLifting {

    public static void main(String[] args) {
        FastScanner fs=new FastScanner();
        PrintWriter pr = new PrintWriter(System.out);
//        int T=fs.nextInt();
//        for (int tt=1; tt<=T; tt++) {
//        }

        int n = fs.nextInt();
        Node[] nodes = new Node[n];
        for(int i = 0; i < n; i++) nodes[i] = new Node(i + 1);
        for(int i = 1; i < n; i++) {
            int a = fs.nextInt() - 1, b = fs.nextInt() - 1;
            nodes[a].adj.add(nodes[b]);
            nodes[b].adj.add(nodes[a]);
        }
        nodes[0].dfs(null, 0);
        for(int e = 1; e < 20; e++)
            for(Node nn: nodes)
                if(nn.lift[e - 1] != null)
                    nn.lift[e] = nn.lift[e - 1].lift[e - 1];
        int q = fs.nextInt();
        while(q-- > 0) {
            Node a = nodes[fs.nextInt() - 1], b = nodes[fs.nextInt() - 1]; int c = fs.nextInt();
            Node lca = a.lca(b, 19);
            int totalDist = a.depth + b.depth - lca.depth * 2;
            if(totalDist <= c) {
                pr.println(b); continue;
            }

            int aDist = a.depth - lca.depth;
            if(c <= aDist) pr.println(a.goUp(c));
            else {
                int bUp = totalDist - c;
                pr.println(b.goUp(bUp));
            }
        }
        pr.flush();
        pr.close();
    }

    static class Node {
        int depth, id;
        Node[] lift = new Node[20];
        ArrayList<Node> adj = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }

        public void dfs(Node par, int depth) {
            if(par != null) adj.remove(par);
            this.depth = depth;
            lift[0] = par;
            for(int i = 0; i < adj.size(); i++) {
                if(adj.get(i) == par) continue;
                adj.get(i).dfs(this, depth + 1);
            }
        }

        public Node goUp(int nSteps) {
            if(nSteps == 0) return this;
            return lift[Integer.numberOfTrailingZeros(nSteps)].goUp(nSteps - Integer.lowestOneBit(nSteps));
        }

        public Node lca(Node o, int nJumps) {
            if(this == o) return this;
            if(depth != o.depth) {
                if(depth > o.depth) return goUp(depth - o.depth).lca(o, 19);
                return lca(o.goUp(o.depth - depth), 19);
            }
            if(lift[0] == o.lift[0]) return lift[0];
            while(lift[nJumps] == o.lift[nJumps]) nJumps--;
            return lift[nJumps].lca(o.lift[nJumps], nJumps);
        }

        public String toString() { return id + ""; }
    }

    static void sort(int[] a) {
        ArrayList<Integer> l=new ArrayList<>();
        for (int i:a) l.add(i);
        Collections.sort(l);
        for (int i=0; i<a.length; i++) a[i]=l.get(i);
    }

    static class FastScanner {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer("");
        String next() {
            while (!st.hasMoreTokens())
                try {
                    st=new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
        int[] readArray(int n) {
            int[] a=new int[n];
            for (int i=0; i<n; i++) a[i]=nextInt();
            return a;
        }
        long[] readLongArray(int n) {
            long[] a=new long[n];
            for (int i=0; i<n; i++) a[i]=nextLong();
            return a;
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }
}

