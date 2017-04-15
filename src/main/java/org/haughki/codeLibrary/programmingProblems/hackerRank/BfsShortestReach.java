package org.haughki.codeLibrary.programmingProblems.hackerRank;

import java.io.File;
import java.io.IOException;
import java.util.*;


// https://www.hackerrank.com/challenges/bfsshortreach
public class BfsShortestReach {
    public static void main(String[] args) throws IOException {
        // note: see hackerRank for a description of this confusing input
        String input = args[0];
        Scanner in;
        if (args.length > 1)
            in = new Scanner(new File(input).toPath());    
        else
            in = new Scanner(input);

        //Scanner in = new Scanner(System.in);
        int queries = in.nextInt();
        for (int i = 0; i < queries; i++) {
            int nodes = in.nextInt();
            int edges = in.nextInt();
            //System.out.println(nodes + " - " + edges);
            List<GraphNode> nodeList = new ArrayList<>(nodes);
            for (int j = 0; j < nodes; j++) {
                nodeList.add(new GraphNode(j + 1));
            }

            for (int j = 0; j < edges; j++) {
                int n1 = in.nextInt();
                int n2 = in.nextInt();
                //System.out.println(n1 + " <--> " + n2);
                nodeList.get(n1 - 1).addAdjacent(nodeList.get(n2 -1));
            }

            // Everything up to this point is just parsing the input.  The algorithm begins here.
            GraphNode strt = nodeList.get(in.nextInt() - 1);
            //System.out.println("searching...");
            // For each node, unless it's the start node, find the shortest path from start --> this node.
            for (int j = 0; j < nodes; j++) {
                if (strt.val != j + 1) {
                    BreadthSearch start = new BreadthSearch(strt);
                    BreadthSearch other = new BreadthSearch(nodeList.get(j), start);
                    start.setOther(other);
                    
                    // TODO: all of this -1, -2 nonsense is because of the return from .step().  Figure out a better way.
                    int distance = -1;
                    while (distance == -1) {
                        int sdist = start.step();
                        if (sdist < 0) {
                            distance = other.step();
                            if (sdist == -1 && distance == -2)
                                distance = -1;  // keep start obj searching
                        } else
                            distance = sdist;
                    }
                    if (distance == -2)
                        System.out.print(-1);
                    else
                        System.out.print(distance);
                    if (j < nodes - 1)
                        System.out.print(" ");
                }
            }
            if (i < queries - 1)
                System.out.print("\r\n");
        }
    }
}

class GraphNode {
    int val;
    LinkedHashSet<GraphNode> adjacent = new LinkedHashSet<>();

    GraphNode(int val) {
        this.val = val;
    }
    
    void addAdjacent(GraphNode node) {
        if (!this.adjacent.contains(node))
            this.adjacent.add(node);
        if (!node.adjacent.contains(this))
            node.adjacent.add(this);
    }

//    public List<Node> getAdjacent() {
//        List<Node> copy = new ArrayList<>(adjacent.size());
//        Collections.copy(copy, adjacent);
//        return copy;
//    }

    @Override
    public String toString() {
        return "Node: " + val;
    }
}

/**
 * A breadth first search optimized to find the shortest path between two nodes.  It's meant to work together in 
 * concert with another (other) instance of this same class.  The two classes each work by "stepping" towards each other,
 * one step at a time.  After each step, a separate manager class checks to see if the two searches have "met".  If so,
 * that's the shortest path between the two start nodes (this _should_ be true as a consequence of doing two breadth first
 * searches in the graph, each starting from a different node; i.e., that's just the way this is supposed to work).
 *
 *  Note that one step searches all of the nodes at a given distance from start.  I.e., if, at the current step, there
 * are 72 nodes which are 12 units away from the start, and we searched all of these, the next step will search all nodes
 * adjacent to all 72 of the "12 unit nodes" -- i.e., all nodes up to 18 units (where one step is 6 units).
 * 
 * Note:  I have no confidence that this is an optimal solution to the problem, esp. in terms of time complexity.
 * There's a LOT of work left here to do to make this into anything real.
 */
class BreadthSearch {

    private Queue<GraphNode> searchQueue = new ArrayDeque<>();
    private Set<GraphNode> visited = new HashSet<>();
    private Map<GraphNode, Integer> nodeDistance = new HashMap<>();  // a map of node:distanceToNode for _this_ search
    private BreadthSearch other;
    private GraphNode start;

    BreadthSearch(GraphNode startNode, BreadthSearch other) {
        this.other = other;
        this.start = startNode;
        this.visited.add(this.start);
        this.searchQueue.add(this.start);
        this.nodeDistance.put(this.start, 0);
    }

    BreadthSearch(GraphNode startNode) {
        this(startNode, null);
    }
    
    public void setOther(BreadthSearch other) {
        this.other = other;
    }
    
    // TODO: the return value of this method is a mess -- totally confusing.  Figure out a better way.

    /**
     * @return If step finds a path, returns the shortest distance.  If step found nothing but there're still items
     * in the searchQueue, returns -1.  If the searchQueue is empty, returns -2.
     */
    int step() {
        if (!searchQueue.isEmpty()) {
            GraphNode current = searchQueue.peek();
            Integer currentDistance = nodeDistance.get(current);
            Integer stepDistance = currentDistance;
            if (currentDistance == null)
                throw new IllegalStateException("Node <" + current.val + "> should be in the map for currentDistance!");
            int shortestDistance = Integer.MAX_VALUE;
            // TODO: instead of this while loop, a better way would be to queue all "same distance nodes" as a group
            // e.g., in a list), and pull them all at once -- then foreach instead of while.
            while (currentDistance.equals(stepDistance)) {
                current = searchQueue.remove();
                for (GraphNode n : current.adjacent) {
                    if (!visited.contains(n)) {
                        int EDGE_DISTANCE = 6;  // in this graph, each edge is 6 units long
                        if (other.visited.contains(n)) {
                            Integer otherDistance = other.nodeDistance.get(n);
                            if (otherDistance == null)
                                throw new IllegalStateException("Node <" + n.val + "> should be in the map for otherDistance!");
                            shortestDistance = Math.min(shortestDistance, currentDistance + otherDistance + EDGE_DISTANCE);
                        } else {
                            nodeDistance.put(n, currentDistance + EDGE_DISTANCE);
                            visited.add(n);
                            searchQueue.add(n);
                        }
                    }
                }
                // TODO: see above todo -- this code is all for the while loop.  yuck.
                if (!searchQueue.isEmpty()) {
                    current = searchQueue.peek();
                    currentDistance = nodeDistance.get(current);
                    if (currentDistance == null)
                        throw new IllegalStateException("Node <" + current.val + "> should be in the map for currentDistance!");
                } else
                    break;
            }
            if (shortestDistance < Integer.MAX_VALUE)
                return shortestDistance;
            return -1;
        }
        return -2;
    }
}
