package matieral.patterns;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/meeting-scheduler/
 * Test: https://leetcode.com/problems/the-skyline-problem/
 * Test: https://leetcode.com/problems/rectangle-area-ii/
 * Test: https://leetcode.com/problems/perfect-rectangle/
 *
 */
public class SweepLine {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int[] slot : slots1) {
            if (slot[1] - slot[0] >= duration) {
                pq.offer(slot);
            }
        }
        for (int[] slot : slots2) {
            if (slot[1] - slot[0] >= duration) {
                pq.offer(slot);
            }
        }

        int[] prev = pq.poll();
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (prev[1] - curr[0] >= duration) {
                return new ArrayList<>(Arrays.asList(curr[0], curr[0] + duration));
            }
            prev = curr;
        }
        return new ArrayList<>();
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<SkylineEvent> events = new ArrayList<>();
        for (int i = 0; i < buildings.length; i++) {
            events.add(new SkylineEvent(buildings[i][0], buildings[i][2], -1));
            events.add(new SkylineEvent(buildings[i][1], buildings[i][2], 1));
        }

        Collections.sort(events);

        TreeMap<Integer, Integer> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        List<List<Integer>> ans = new ArrayList<>();
        for (SkylineEvent e : events) {
            if(e.type == -1) {
                if (map.isEmpty() || map.firstKey() < e.h) {
                    ans.add(new ArrayList<>(Arrays.asList(e.loc, e.h)));
                }
                map.put(e.h, map.getOrDefault(e.h, 0) + 1);
            }
            else {
                map.put(e.h, map.get(e.h) - 1);
                if (map.get(e.h) == 0) map.remove((Integer)e.h);

                if (!map.isEmpty() && e.h > map.firstKey()) {
                    ans.add(new ArrayList<>(Arrays.asList(e.loc, map.firstKey())));
                }
                else if (map.isEmpty()) {
                    ans.add(new ArrayList<>(Arrays.asList(e.loc, 0)));

                }
            }
        }
        return ans;
    }

    int OPEN = 0;
    int CLOSE = 1;
    public int rectangleArea(int[][] rectangles) {
        List<recEvent> events = new ArrayList<>();
        for (int[] rec : rectangles) {
            events.add(new recEvent(rec[1], OPEN, rec[0], rec[2]));
            events.add(new recEvent(rec[3], CLOSE, rec[0], rec[2]));
        }
        Collections.sort(events);

        List<int[]> intervals = new ArrayList<>();
        long ans = 0;
        int baseHeight = events.get(0).height;

        for (recEvent event : events) {
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

    public boolean isRectangleCover(int[][] rectangles) {
        int[] xRange = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        PriorityQueue<Event> pq = new PriorityQueue<>();
        for (int[] rec : rectangles) {
            Rectangle rectangle = new Rectangle(rec[0], rec[1], rec[2], rec[3]);
            pq.offer(new Event(rec[1], rectangle));
            pq.offer(new Event(rec[3], rectangle));
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
                Event event = pq.poll();
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

class SkylineEvent implements Comparable<SkylineEvent>{
    int loc;
    int h;
    int type;
    public SkylineEvent(int loc, int h, int type) {
        this.loc = loc;
        this.h = h;
        this.type = type;
    }

    @Override
    public int compareTo(SkylineEvent o) {
        if (this.loc == o.loc && this.type == o.type) {
            if (this.type == -1) { // Entry
                return o.h - this.h;
            }
            else return this.h - o.h;
        }
        else return this.loc == o.loc ? this.type - o.type : this.loc - o.loc;
    }
}

class recEvent implements Comparable<recEvent> {
    int height;
    int type;
    int from;
    int to;

    public recEvent(int height, int type, int from, int to) {
        this.height = height;
        this.type = type;
        this.from = from;
        this.to = to;
    }

    public int compareTo(recEvent that) {
        return this.height - that.height;
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

