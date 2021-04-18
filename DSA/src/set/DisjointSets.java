package set;

public class DisjointSets {
    public static int[] createSets(int size) {
        int[] p = new int[size];
        for (int i = 0; i < size; i++)
            p[i] = i;
        return p;
    }

    public static int root(int[] p, int x) {
        return x == p[x] ? x : (p[x] = root(p, p[x]));
    }

    public static boolean union(int[] p, int a, int b) {
        a = root(p, a);
        b = root(p, b);
        if (a != b) {
            if (Math.random() < 0.5)
                p[a] = b;
            else
                p[b] = a;
            return true;
        }
        return false;
    }

}