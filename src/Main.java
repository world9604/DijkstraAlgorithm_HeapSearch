import javafx.util.Pair;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        int num = 6;
        List<Pair<Integer, Integer>>[] a = new ArrayList[num + 1];

        a[1] = new ArrayList<>();
        a[1].add(new Pair(2, 2));
        a[1].add(new Pair(3, 5));
        a[1].add(new Pair(4, 1));

        a[2] = new ArrayList<>();
        a[2].add(new Pair(1, 2));
        a[2].add(new Pair(3, 3));
        a[2].add(new Pair(4, 2));

        a[3] = new ArrayList<>();
        a[3].add(new Pair(1, 5));
        a[3].add(new Pair(2, 3));
        a[3].add(new Pair(4, 3));
        a[3].add(new Pair(5, 1));
        a[3].add(new Pair(6, 5));

        a[4] = new ArrayList<>();
        a[4].add(new Pair(1, 1));
        a[4].add(new Pair(2, 2));
        a[4].add(new Pair(3, 3));
        a[4].add(new Pair(5, 1));

        a[5] = new ArrayList<>();
        a[5].add(new Pair(3, 1));
        a[5].add(new Pair(4, 1));
        a[5].add(new Pair(6, 2));

        a[6] = new ArrayList<>();
        a[6].add(new Pair(3, 5));
        a[6].add(new Pair(5, 2));

        new Dijkstra(a, num).start(1);
    }
}

class Dijkstra {
    // 간선 정보 입니다.
    private List<Pair<Integer, Integer>>[] a;
    // 최소 비용 입니다.
    private int[] d;
    private int num;
    private final int INF = 100000000;

    Dijkstra(List<Pair<Integer, Integer>>[] a, int num) {
        this.a = a;
        this.num = num;
        this.d = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            this.d[i] = INF;
        }
    }

    void start(int start) {
        d[start] = 0;

        // 힙 구조 입니다.
        Queue<Pair<Integer, Integer>> priorityQueue = new PriorityQueue<>((pair1, pair2) -> {
            if (pair1.getValue() < pair2.getValue())
                return -1;
            else if (pair1.getValue() < pair2.getValue())
                return 1;
            else
                return 0;
        });
        priorityQueue.add(new Pair<>(start, 0));

        // 가까운 순서대로 처리하므로 큐를 사용 합니다.
        while (!priorityQueue.isEmpty()) {
            Pair<Integer, Integer> top = priorityQueue.poll();

            int current = top.getKey();
            int distance = top.getValue();

            // 최단 거리가 아닌 경우 스킵 합니다.
            if (d[current] < distance) continue;

            for (int i = 0; i < a[current].size(); i++) {
                // 선택된 노드의 인접 노드
                int next = a[current].get(i).getKey();
                // 선택된 노드를 인접 노드로 거쳐서 가는 비용
                int nextDistance = distance + a[current].get(i).getValue();
                // 기존의 최소 비용보다 더 저렴하다면 교체 합니다.
                if (nextDistance < d[next]) {
                    d[next] = nextDistance;
                    priorityQueue.add(new Pair<>(next, nextDistance));
                }
            }
        }

        for (int i = 1; i <= num; i++) {
            System.out.printf("%d ", d[i]);
        }
    }
}
