package com.fishercoder.graph;

import com.fishercoder.common.utils.CommonUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class DepthFirstSearch {
    /**
     * A simple self contained program to illustrate how DFS works:
     * <p>
     * Initiall all vertices are colored white which means it's not visited yet;
     * Once we discovered one vertex, we will mart it to be gray, then we'll recursively visit all of its adjacent vertices;
     * after we completely visited all of its adjacent vertices, we'll mark this vertex to be black, indicating it's finished visiting.
     * Along the way, we timestamp each vertex with a start (discoveredTimestamp) and end time (finishedTimestamp).
     * <p>
     * DFS produces a depth-first forest which consists of depth-first trees.
     */

    static class DfsAlgorithm {

        int time = 0;
        TreeMap<Vertex, List<Vertex>> graph;

        public DfsAlgorithm(TreeMap<Vertex, List<Vertex>> graph) {
            this.graph = graph;
        }

        void dfs() {

            //initialize all vertices to be WHITE
            for (Vertex vertex : graph.keySet()) {
                vertex.color = Color.WHITE;
                vertex.parent = null;
            }

            //collect all vertices into a set to prep for traversal
            TreeSet<Vertex> allVertices = new TreeSet<>(new Comparator<Vertex>() {
                @Override
                public int compare(Vertex o1, Vertex o2) {
                    return o1.val - o2.val;
                }
            });
            for (Vertex vertex : graph.keySet()) {
                allVertices.add(vertex);
                for (Vertex v : graph.get(vertex)) {
                    allVertices.add(v);
                }
            }

            //dfs each vertex that is in WHITE color
            for (Vertex vertex : allVertices) {
                if (Color.WHITE == vertex.color) {
                    dfsVisit(vertex);
                }
            }
        }

        private void dfsVisit(Vertex vertex) {
            time++;
            vertex.discoveredTimestamp = time;
            vertex.color = Color.GRAY;
            List<Vertex> adjancyList = graph.get(vertex);
            for (Vertex v : adjancyList) {
                if (Color.WHITE.equals(v.color)) {
                    v.parent = vertex;
                    dfsVisit(v);
                }
            }
            vertex.color = Color.BLACK;
            time++;
            vertex.finishedTimestamp = time;
        }
    }

    public static void main(String... args) {
        /**use a treemap here to keep the entries sorted by its alphabetical order for easier tracking*/
        TreeMap<Vertex, List<Vertex>> graph = new TreeMap<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return o1.val - o2.val;
            }
        });
        Vertex vertexA = new Vertex('A');
        Vertex vertexB = new Vertex('B');
        Vertex vertexC = new Vertex('C');
        Vertex vertexD = new Vertex('D');
        Vertex vertexE = new Vertex('E');
        Vertex vertexF = new Vertex('F');

        graph.put(vertexA, Arrays.asList(vertexB, vertexD));
        graph.put(vertexB, Arrays.asList(vertexE));
        graph.put(vertexC, Arrays.asList(vertexE, vertexF));
        graph.put(vertexD, Arrays.asList(vertexB));
        graph.put(vertexE, Arrays.asList(vertexD));
        graph.put(vertexF, Arrays.asList(vertexF));

        for (Vertex key : graph.keySet()) {
            System.out.print(key + " -> ");
            CommonUtils.printList(graph.get(key));
        }
        DfsAlgorithm dfsAlgorithm = new DfsAlgorithm(graph);
        System.out.println("Starting DFS.");
        dfsAlgorithm.dfs();
        for (Vertex key : graph.keySet()) {
            System.out.println(key);
        }
        System.out.println("DFS finished.");
    }

    static class Vertex {
        Color color;
        Vertex parent;
        int discoveredTimestamp;
        int finishedTimestamp;
        char val;

        public Vertex(char val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "color=" + color +
                    ", parent=" + parent +
                    ", discoveredTimestamp=" + discoveredTimestamp +
                    ", finishedTimestamp=" + finishedTimestamp +
                    ", val=" + val +
                    '}';
        }
    }

    enum Color {
        WHITE,
        GRAY,
        BLACK,
    }
}
