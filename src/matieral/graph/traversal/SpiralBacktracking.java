//package matieral.graph.traversal;
//import java.util.*;
//import javafx.util.*;
///**
// * Test: https://leetcode.com/problems/robot-room-cleaner/
// */
//public class SpiralBacktracking {
//    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
//    Set<Pair<Integer, Integer>> visited = new HashSet<>();
//    Robot robot;
//
//    private void moveBack() {
//        robot.turnRight();
//        robot.turnRight();
//        robot.move();
//        robot.turnRight();
//        robot.turnRight();
//    }
//
//    private void backtrack(int row, int col, int d) {
//        visited.add(new Pair(row, col));
//        robot.clean();
//
//        for (int i = 0; i < 4; i++) {
//            int newD = (i + d) % 4;
//            int newRow = dirs[newD][0] + row;
//            int newCol = dirs[newD][1] + col;
//
//            if (!visited.contains(new Pair(newRow, newCol)) && robot.move()) {
//                backtrack(newRow, newCol, newD);
//                moveBack();
//            }
//            robot.turnRight();
//        }
//    }
//    public void cleanRoom(Robot robot) {
//        this.robot = robot;
//        backtrack(0, 0, 0);
//    }
//}
//
//class Robot {
//    public boolean move() {return true;}
//    public void turnLeft(){}
//    public void turnRight(){}
//    public void clean() {};
//}
