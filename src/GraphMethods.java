import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * On my honor:
 *
 * - I have not used source code obtained from another student,
 * or any other unauthorized source, either modified or
 * unmodified.
 *
 * - All source code and documentation used in my program is
 * either my original work, or was derived by me from the
 * source code published in the textbook for this course.
 *
 * - I have not discussed coding details about this exam with
 * anyone other than my instructor, ACM/UPE tutors, or the TAs
 * assigned to this course. I have violated neither the spirit
 * nor letter of this restriction.
 *
 * @author Yaxuan Zhang
 * @version 12/06/2020
 *
 */
public class GraphMethods {

    /**
     *
     * Implement a breadth-first traversal.
     *
     * @param theGraph
     *            is a Graph object to traverse
     *
     * @param start
     *            is the index of the starting Node for the traversal
     *
     * @return a List<Node> of all Nodes in the order they were visited.
     *         The node with index start is visited first.
     */
    @SuppressWarnings("unchecked")
    public static List<Node> breadthFirstTraversal(Graph theGraph, int start) {
        // Initialize the output list
        List<Node> output = new LinkedList<Node>();

        int numNodes = theGraph.getNumNodes();
        boolean[] visited = new boolean[numNodes];

        Node firstnode = theGraph.getNode(start);

        Queue<Node> queue = new ArrayDeque<>();
        // enqueue the start element
        queue.offer(firstnode);
        visited[start] = true;

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            output.add(curNode);
            Iterator<Node> itr = curNode.getNeighbors();
            while (itr.hasNext()) {
                Node newnode = itr.next();
                int id = newnode.getID();
                if (visited[id]) {
                    continue;
                }
                visited[id] = true;
                queue.offer(newnode);
            }
        }

        return output;
    }


    /**
     * Implement a depth first traversal
     *
     * @param theGraph
     *            is a Graph object to traverse
     *
     * @param start
     *            is the index of the starting Node for the traversal
     *
     * @return a List<Node> of all Nodes in the order they were visited.
     *         The node with index start is visited first.
     */
    public static List<Node> depthFirstTraversal(Graph theGraph, int start) {
        // Initialize the output list
        List<Node> output = new LinkedList<Node>();

        int numNodes = theGraph.getNumNodes();
        boolean[] visited = new boolean[numNodes];
        Node firstnode = theGraph.getNode(start);

        dfsHelper(firstnode, output, visited);

        return output;

    }


    /**
     * Helper method of conducting dfs
     * 
     * @param node
     *            current node of dfs
     * @param output
     *            the output of list of node
     * @param visited
     *            an array that keep track of
     *            whether a node has been visited or not
     */
    @SuppressWarnings("unchecked")
    private static void dfsHelper(
        Node node,
        List<Node> output,
        boolean[] visited) {
        if (visited[node.getID()] || output.size() == visited.length) {
            return;
        }
        output.add(node);
        visited[node.getID()] = true;
        Iterator<Node> itr = node.getNeighbors();
        Stack<Node> stack = new Stack<>();
        while (itr.hasNext()) {
            Node newnode = itr.next();
            if (visited[newnode.getID()]) {
                continue;
            }
            stack.add(newnode);
        }

        while (!stack.isEmpty()) {
            Node childnode = stack.pop();
            dfsHelper(childnode, output, visited);
        }
    }


    /**
     * Find the length of the shortest path between two nodes in the graph.
     *
     * @param theGraph
     *            is a Graph object
     *
     * @param start
     *            is the index of the starting Node
     *
     * @param end
     *            is the index of target Node
     *
     * @return an int specifying the length of the shortest path between
     *         the Nodes at start and end. If start = end, return 0.
     */
    public static int shortestDistance(Graph theGraph, int start, int end) {
        // Initialize the output integer
        int output = 0;

        if (start == end) {
            return output;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(start, 0);

        Queue<Node> queue = new ArrayDeque<>();
        Node startnode = theGraph.getNode(start);
        queue.offer(startnode);

        while (!queue.isEmpty()) {
            Node curnode = queue.poll();
            int distance = map.get(curnode.getID()) + 1;
            @SuppressWarnings("unchecked")
            Iterator<Node> itr = curnode.getNeighbors();
            while (itr.hasNext()) {
                Node childNode = itr.next();
                if (childNode.getID() == end) {
                    return distance;
                }
                if (map.containsKey(childNode.getID())) {
                    continue;
                }
                map.put(childNode.getID(), distance);
                queue.offer(childNode);
            }
        }

        return output;
    }


    /**
     * Find the longest shortest path from start to any other Node in the graph
     *
     * @param theGraph
     *            is a Graph object
     *
     * @param start
     *            is an int array of starting indices
     *
     * @return an int value specifying the length of the longest shortest path
     */
    public static int longestShortestPath(Graph theGraph, int start) {
        // Initialize the output integer
        int output = 0;

        for (int i = 0; i < theGraph.getNumNodes(); i++) {
            if (i == start) {
                continue;
            }
            output = Math.max(output, shortestDistance(theGraph, start, i));
        }

        return output;

    }


    /**
     * Find the shortest path between two nodes in the graph.
     *
     * @param theGraph
     *            is a Graph object
     *
     * @param start
     *            is the index of the starting Node
     *
     * @param end
     *            is the index of target Node
     *
     * @return a List<Node> of all Nodes visited on the shortest path between
     *         start and end, in the order that they were visited.
     */
    @SuppressWarnings("rawtypes")
    public static List<Node> shortestPath(Graph theGraph, int start, int end) {

        List<Node> output = new LinkedList<Node>();

        int nodeNum = theGraph.getNumNodes();
        // record the previous node
        Node[] pre = new Node[nodeNum];
        // record distance from start
        Pair[] distance = new Pair[nodeNum];
        boolean[] visited = new boolean[nodeNum];

        // initialize
        for (int i = 0; i < nodeNum; i++) {
            if (i == start) {
                Pair pair = new Pair(0, Integer.MAX_VALUE);
                distance[i] = pair;
                continue;
            }

            Pair pair = new Pair(Integer.MAX_VALUE, Integer.MAX_VALUE);
            distance[i] = pair;
        }

        int startId = 0;

        for (int i = 0; i < nodeNum; i++) {

            int minNode = minNode(theGraph, visited, distance);
            Node nextMin = theGraph.getNode(minNode);
            // can not get to
            if (distance[minNode].getDistance() == Integer.MAX_VALUE) {
                break;
            }
            visited[minNode] = true;
            Iterator itr = nextMin.getNeighbors();

            while (itr.hasNext()) {
                Node next = (Node)itr.next();

                int index = next.getID();
                if (distance[index].getDistance() <= distance[minNode]
                    .getDistance() + 1) {
                    continue;
                }
                distance[index].setDistance(distance[minNode].getDistance()
                    + 1);
                pre[index] = nextMin;
                distance[index].setPreID(startId++);
            }

        }

        // can not get to
        if (distance[end].getDistance() == Integer.MAX_VALUE) {
            return output;
        }

        // follow the path from end to start
        // add them to the output
        int num = end;
        // add the end node
        output.add(theGraph.getNode(end));
        while (num != start) {
            Node toAdd = pre[num];
            output.add(0, toAdd);
            num = toAdd.getID();

        }
        return output;
    }


    /**
     * get the minimum distance from distance array, follow the
     * neighbor sequence
     * 
     * @param theGraph
     *            graph
     * @param visited
     *            an array that keep track of
     *            whether a node has been visited or not
     * @param distance
     *            distance array
     * @return minimum distance node ID
     */
    public static int minNode(
        Graph theGraph,
        boolean[] visited,
        Pair[] distance) {
        int minId = 0;

        //find the first unvisited to start
        for (int i = 0; i < theGraph.getNumNodes(); i++) {
            if (!visited[i]) {
                minId = i;
                break;
            }
        }
        

        // find the lowest one
        for (int i = 0; i < theGraph.getNumNodes(); i++) {
            if (visited[i]) {
                continue;
            }
            if (distance[minId].getDistance() > distance[i].getDistance()) {
                minId = i;
            }
        }

        
        for (int i = 0; i < theGraph.getNumNodes(); i++) {
            if (visited[i]) {
                continue;
            }
            if (distance[minId].getDistance() != distance[i].getDistance()) {
                continue;
            }
            if (distance[i].getPreID() == Integer.MAX_VALUE) {
                continue;
            }
            if (distance[i].getPreID() >= distance[minId].getPreID()) {
                continue;
            }
            minId = i;

        }

        return minId;

    }


    /**
     * Determine whether there exists a path meeting the goal.
     *
     * @param theGraph
     *            is a Graph object
     *
     * @param start
     *            is an int array of starting indices
     *
     * @param end
     *            is the index of target Node
     *
     * @return a List<Node> specifying the shortest path from ANY start
     *         Node to the target Node
     */
    public static List<Node> pathExists(Graph theGraph, int[] start, int end) {
        // Initialize the output list
        List<Node> output = new LinkedList<Node>();

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < start.length; i++) {
            List<Node> curPath = shortestPath(theGraph, start[i], end);
            // no path can be found between this element and end
            if (curPath.size() == 0) {
                continue;
            }
            // found a smaller one
            if (curPath.size() < min) {
                output = curPath;
                min = curPath.size();
            }
        }

        return output;
    }
}
