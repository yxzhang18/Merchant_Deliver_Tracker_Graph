import student.TestCase;

/**
 * @author CS Staff
 * @version 2020-11-17
 */
public class GraphMethodsTest extends TestCase {

    private GraphMethods theMethods;
    
    private Graph theGraph;
    
    /**
     * The Test setup
     */
    public void setUp() throws Exception {
        theGraph = new Graph(3);
        theGraph.addEdge(0, 1);
        theGraph.addEdge(1, 2);
        theGraph.addEdge(2, 0);
        
        theMethods = new GraphMethods();
    }

    /**
     *  testBreadthFirstTraversal
     */
    @SuppressWarnings("static-access")
    public void testBreadthFirstTraversal() {
        assertEquals(3, theMethods.breadthFirstTraversal(theGraph, 0).size());
        
    }

    /**
     *  testDepthFirstTraversal
     */
    @SuppressWarnings("static-access")
    public void testDepthFirstTraversal() {
        assertEquals(3, theMethods.depthFirstTraversal(theGraph, 0).size());
        
    }

    /**
     *  testShortestDistance
     */
    @SuppressWarnings("static-access")
    public void testShortestDistance() {
        
        assertEquals(1, theMethods.shortestDistance(theGraph, 0, 1));
        
    }

    /**
     *  testLongestShortestPath
     */
    @SuppressWarnings("static-access")
    public void testLongestShortestPath() {
        
        assertEquals(2, theMethods.longestShortestPath(theGraph, 0));
        
        
    }

    /**
     *  testShortestPath
     */
    @SuppressWarnings("static-access")
    public void testShortestPath() {
        assertEquals(2, theMethods.shortestPath(theGraph, 0, 1).size());
        
    }
    
    /**
     *  testPathExists
     */
    @SuppressWarnings("static-access")
    public void testPathExists() {
        int[] nodeList1 = new int[theGraph.getNumNodes()];   
        assertEquals(2, theMethods.pathExists(theGraph, nodeList1, 1).size());
        
    }

}
