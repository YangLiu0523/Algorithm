package matieral.topical_program;
import java.util.*;
/**
 * Test: https://leetcode.com/problems/number-of-corner-rectangles/
 * Test: https://leetcode.com/problems/rectangle-overlap/
 * Test: https://leetcode.com/problems/rectangle-area/
 * Test: https://leetcode.com/problems/rectangle-area-ii/
 * Test: https://leetcode.com/problems/perfect-rectangle/
 */

public class RectangleQuestion {
    public int countCornerRectangles(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        Map<Integer, Set<Integer>> columnsInRow = new HashMap<>();
        for (int i = 0; i < m; i++) {
            columnsInRow.put(i, new HashSet<>());
        }

        Map<Integer, Integer> cntMap = new HashMap<>();

        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    columnsInRow.get(i).add(j);
                    for (int k = i - 1; k >= 0; k--) {
                        int key = k * 200 + i;
                        if (columnsInRow.get(k).contains(j)) {
                            ret += cntMap.getOrDefault(key, 0);
                            cntMap.put(key, cntMap.getOrDefault(key, 0) + 1);
                        }
                    }
                }
            }
        }
        return ret;
    }

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return (Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0])) && (Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]));
    }

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = 0;
        if (Math.min(C, G) > Math.max(A, E) && Math.min(D, H) > Math.max(B, F)) {
            int l1 = Math.max(A, E);
            int r1 = Math.min(C, G);
            int d1 = Math.max(B, F);
            int u1 = Math.min(D, H);
            area1 = (r1 - l1) * (u1 - d1);
        }

        return (C - A) * (D - B) + (H - F) * (G - E) - area1;
    }

    int OPEN = 0;
    int CLOSE = 1;
    public int rectangleArea(int[][] rectangles) {
        List<RecEvent> events = new ArrayList<>();
        for (int[] rec : rectangles) {
            events.add(new RecEvent(rec[1], OPEN, rec[0], rec[2]));
            events.add(new RecEvent(rec[3], CLOSE, rec[0], rec[2]));
        }
        Collections.sort(events);

        List<int[]> intervals = new ArrayList<>();
        long ans = 0;
        int baseHeight = events.get(0).height;

        for (RecEvent event : events) {
            long span = 0;
            int prev = -1;
            for (int[] interval : intervals) {
                prev = Math.max(prev, interval[0]);
                span += Math.max(0, interval[1] - prev);
                prev = Math.max(prev, interval[1]);
            }
            ans += span * (event.height - baseHeight);

            if (event.type == OPEN) {
                intervals.add(new int[]{event.from, event.to});
                Collections.sort(intervals, (a, b) -> a[0] - b[0]);
            }
            else {
                for (int i = 0; i < intervals.size(); i++) {
                    if (intervals.get(i)[0] == event.from && intervals.get(i)[1] == event.to) {
                        intervals.remove(i);
                        break;
                    }
                }
            }

            baseHeight = event.height;
        }

        ans %= 1_000_000_007;
        return (int) ans;
    }

    public boolean isRectangleCover2(int[][] rectangles) {
        int[] xRange = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        PriorityQueue<RecRectangle> pq = new PriorityQueue<>();
        for (int[] rec : rectangles) {
            Rectangle rectangle = new Rectangle(rec[0], rec[1], rec[2], rec[3]);
            pq.offer(new RecRectangle(rec[1], rectangle));
            pq.offer(new RecRectangle(rec[3], rectangle));
            xRange[0] = Math.min(xRange[0], rec[0]);
            xRange[1] = Math.max(xRange[1], rec[2]);
        }

        TreeSet<Rectangle> xSet = new TreeSet<>(new Comparator<Rectangle>() {
            @Override
            public int compare(Rectangle o1, Rectangle o2) {
                if (o1.x2 <= o2.x1) return -1;
                else if (o1.x1 >= o2.x2) return 1;
                else return 0; //intersects!!
            }
        });

        int xCurrRange = 0;
        while(!pq.isEmpty()) {
            int h = pq.peek().h;
            while (!pq.isEmpty() && pq.peek().h == h) {
                RecRectangle event = pq.poll();
                if (event.rectangle.y1 == event.h) { // Enter
                    if (!xSet.add(event.rectangle)) {
                        return false;
                    }
                    xCurrRange += (event.rectangle.x2 - event.rectangle.x1);
                }else { // Leave
                    xSet.remove(event.rectangle);
                    xCurrRange -= (event.rectangle.x2 - event.rectangle.x1);
                }
            }

            if (!pq.isEmpty() && xCurrRange != xRange[1] - xRange[0]) {
                return false;
            }
        }
        return true;
    }
}

class RecEvent implements Comparable<RecEvent> {
    int height;
    int type;
    int from;
    int to;

    public RecEvent(int height, int type, int from, int to) {
        this.height = height;
        this.type = type;
        this.from = from;
        this.to = to;
    }

    public int compareTo(RecEvent that) {
        return this.height - that.height;
    }
}



class RecRectangle implements Comparable<RecRectangle> {
    int h;
    Rectangle rectangle;

    public RecRectangle(int h, Rectangle rectangle) {
        this.h = h;
        this.rectangle = rectangle;
    }

    @Override
    public int compareTo(RecRectangle that) {
        // If the height is the same, return the first show up one
        return this.h == that.h ? this.rectangle.y1 - that.rectangle.y1: this.h - that.h;
    }
}


class Rectangle{
    int y1;
    int y2;
    int x1;
    int x2;

    public Rectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
}

class Event implements Comparable<Event> {
    int h;
    Rectangle rectangle;

    public Event(int h, Rectangle rectangle) {
        this.h = h;
        this.rectangle = rectangle;
    }

    @Override
    public int compareTo(Event that) {
        // If the height is the same, return the first show up one
        return this.h == that.h ? this.rectangle.y1 - that.rectangle.y1: this.h - that.h;
    }
}