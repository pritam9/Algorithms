package bathroom_skalls;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Stalls {

	private static class Pair implements Comparable<Pair> {
        private final long number, times;

        public Pair(long number, long times) {
            this.number = number;
            this.times = times;
        }

        @Override
        public int compareTo(Pair o) {
            return Long.compare(number, o.number);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCount = in.nextInt();
        for (int testNo = 1; testNo <= testCount; testNo++) {
            solve(testNo, in);
        }
    }

    private static void solve(int testNo, Scanner in) {
        long n = in.nextLong();
        long k = in.nextLong();
        System.out.println("Case #" + testNo + ": " + calc(n, k));

    }

    private static String calc(long n, long k) {
        PriorityQueue<Pair> queue = new PriorityQueue<>(Comparator.reverseOrder());
        queue.add(new Pair(n, 1));
        while (k > 0) {
            Pair pair = queue.poll();
            if (k <= pair.times) {
                return pair.number / 2 + " " + (pair.number - 1) / 2;
            } else {
                k -= pair.times;
                if (pair.number / 2 > 0) {
                    queue.add(new Pair(pair.number / 2, pair.times));
                }
                if ((pair.number - 1) / 2 > 0) {
                    queue.add(new Pair((pair.number - 1) / 2, pair.times));
                }
            }
        }
        throw new RuntimeException();
    }


}
