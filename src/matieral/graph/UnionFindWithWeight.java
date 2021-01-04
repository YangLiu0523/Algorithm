package matieral.graph;
import java.util.*;


/**
 * Test: https://leetcode.com/problems/evaluate-division/
 */

class GraphInfo{
    String GID;
    double weight;
    public GraphInfo(String GID, double weight) {
        this.GID = GID;
        this.weight = weight;
    }
}

class UnionFindWithWeight {
    public GraphInfo find(Map<String, GraphInfo> graph, String nodeId) {
        if (!graph.containsKey(nodeId)) {
            graph.put(nodeId, new GraphInfo(nodeId, 1.0));
        }
        else {
            GraphInfo graphInfo = graph.get(nodeId);
            if (!graphInfo.GID.equals(nodeId)) {
                GraphInfo nextInfo = find(graph, graphInfo.GID);
                graph.put(nodeId, new GraphInfo(nextInfo.GID, nextInfo.weight * graphInfo.weight));
            }
        }
        return graph.get(nodeId);
    }

    public void union(Map<String, GraphInfo> graph, String dividend, String divisor, double quotient) {
        GraphInfo dividendEntry = find(graph, dividend);
        GraphInfo divisorEntry = find(graph, divisor);

        if (!dividendEntry.GID.equals(divisorEntry.GID)) {
            graph.put(dividendEntry.GID, new GraphInfo(divisorEntry.GID, quotient * divisorEntry.weight / dividendEntry.weight));
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, GraphInfo> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String dividend = equations.get(i).get(0), divisor = equations.get(i).get(1);
            double quotient = values[i];

            union(graph, dividend, divisor, quotient);
        }

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String dividend = queries.get(i).get(0), divisor = queries.get(i).get(1);
            if (!graph.containsKey(dividend) || !graph.containsKey(divisor)) {
                res[i] = -1;
            }
            else {
                GraphInfo dividendInfo = find(graph, dividend);
                GraphInfo divisorInfo = find(graph, divisor);
                if (!dividendInfo.GID.equals(divisorInfo.GID)) {
                    res[i] = -1;
                }
                else {
                    res[i] = dividendInfo.weight / divisorInfo.weight;
                }
            }
        }
        return res;
    }
}