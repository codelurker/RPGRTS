package ai;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

import map.Map;

/*
 * A* Algorithm notes
 * 		
 * Terms:
 * 		G Value - Each node has a G Value -> determines how far the entity has to travel from the starting node to this node.
 * 		Heuristic Function - estimates how far the entity has to move to get from one node to another
 * 		F Value - Each node has a F Value -> How far the entity has traveled to get to this node and how much further it needs to travel (F = G+H)
 * 		Open List - List of all the nodes that are being considered to be included in the final path
 * 		Closed List - List of all the nodes that have already been looked at. (Used to make sure we don't double check nodes)
 * 		Parent reference - Each node will have a reference to the node that added this node to the Open List
 * 
 * Algorithm:
 * 		1. Clear the Open and Closed Lists and reset each node's F and G Values
 * 		2. Set the start node's G Value = 0 and F Value to the distance between the start node and end node (H function) and add
 * 		   it to the Open List.
 * 		3. While there are still nodes to consider in the Open List:
 * 			a. Loop through the Open List and find the node with the smallest F value (Active Node)
 * 			b. If the Open List is empty or no node can be found -> no path -> break loop
 * 			c. If the Active Node is the end node, find and return the final path.
 * 			d. Else, for each of the Active Node's neighbors
 * 				 i. Make sure the neighbor can be walked on
 * 				ii. Calculate G for the neighbor (Active Node G + cost of moving to the neighbor)
 * 			   iii. If the neighbor is not in either the Open List or Closed List
 * 					1. Set the neighbor G = Active Node G + cost of moving to neighbor
 * 					2. Set the neighbor F = new G + distance between neighbor and end node
 * 					3. Set the neighbor parent node = Active Node
 * 					4. Add neighbor to Open List
 * 				iv. Else, if the neighbor is either in the Open List or Closed List
 * 					1. If the new G is less than the neighboring nodes G, do the same steps as (iii.1-4)
 * 			e. Remove Active Node from the Open List and add it to the Closed List
 */

public class Pathfinder {
	
	private Map map;
	private SearchNode[][] searchNodes;
	
	private List<SearchNode> openList = new ArrayList<SearchNode>();
	private List<SearchNode> closedList = new ArrayList<SearchNode>();
	
	public Pathfinder(Map map) {
		this.map = map;
		initializeSearchNodes();
	}
	
	public void initializeSearchNodes() {
		searchNodes = new SearchNode[map.getTileMap().getWidth()][map.getTileMap().getHeight()];
		
		for (int x = 0; x < map.getTileMap().getWidth(); x++) {
			for (int y = 0; y < map.getTileMap().getHeight(); y++) {
				SearchNode node = new SearchNode();
				node.setPosition(new Vector2f(x, y));
				Image tImg = map.getTileMap().getTileImage(x, y, 1);
				if (tImg != null) {
					node.setWalkable(false);
				} else {
					node.setWalkable(true);
				}
				
				if (node.isWalkable()) {
					node.setNeighbors(new SearchNode[4]);
					searchNodes[x][y] = node;
				}
			}
		}
		
		// Create neighbors for each search node
		for (int x = 0; x < map.getTileMap().getWidth(); x++) {
			for (int y = 0; y < map.getTileMap().getHeight(); y++) {
				SearchNode node = searchNodes[x][y];
				if (node == null || !node.isWalkable())
					continue;
				
				Vector2f[] neighbors = new Vector2f[] {
					new Vector2f(x, y - 1), // Above 1
					new Vector2f(x, y + 1), // Down 1
					new Vector2f(x - 1, y), // Left 1
					new Vector2f(x + 1, y)  // Right 1
				};
				
				for (int i = 0; i < neighbors.length; i++) {
					Vector2f neighborPos = neighbors[i];
					
					if (neighborPos.x < 0 || neighborPos.x > map.getTileMap().getWidth() - 1 ||
							neighborPos.y < 0 || neighborPos.y > map.getTileMap().getHeight() - 1) {
						continue;
					}
					
					SearchNode neighbor = searchNodes[(int)neighborPos.x][(int)neighborPos.y];
					
					if (neighbor == null || !neighbor.isWalkable())
						continue;
					
					node.getNeighbors()[i] = neighbor;
				}
			}
		}
	}
	
	private void resetSearchNodes() {
		openList.clear();
		closedList.clear();
		
		for (int x = 0; x < map.getTileMap().getWidth(); x++) {
			for (int y = 0; y < map.getTileMap().getHeight(); y++) {
				
				SearchNode node = searchNodes[x][y];
				
				if (node == null)
					continue;
				
				node.setInOpenList(false);
				node.setInClosedList(false);
				
				node.setDistanceToEnd(Float.MAX_VALUE);
				node.setDistanceTraveled(Float.MAX_VALUE);
			}
		}
	}
	
	// Returns the node with the smallest distance to the end node.
	private SearchNode findBestNode() {
		SearchNode node = openList.get(0);
		float smallestDistanceToGoal = Float.MAX_VALUE;
		
		for (int i = 0; i < openList.size(); i++) {
			if (openList.get(i).getDistanceToEnd() < smallestDistanceToGoal) {
				node = openList.get(i);
				smallestDistanceToGoal = node.getDistanceToEnd();
			}
		}
		
		return node;
	}
	
	// Return the final path
	private List<Vector2f> FindFinalPath(SearchNode startNode, SearchNode endNode) {
		closedList.add(endNode);
		
		SearchNode parentNode = endNode.getParent();
		while (parentNode != startNode) {
			closedList.add(parentNode);
			parentNode = parentNode.getParent();
		}
		
		List<Vector2f> finalPath = new ArrayList<Vector2f>();
		
		for (int i = closedList.size() - 1; i >= 0; i--) {
			finalPath.add(new Vector2f(closedList.get(i).getPosition().x * 32, closedList.get(i).getPosition().y * 32));
		}
		
		return finalPath;
	}
	
	public List<Vector2f> FindPath(Vector2f start, Vector2f end) {
		
		if (start == end)
			return new ArrayList<Vector2f>();
		
		resetSearchNodes();
		
		SearchNode startNode = searchNodes[(int)start.x][(int)start.y];
		SearchNode endNode = searchNodes[(int)end.x][(int)end.y];
		startNode.setInOpenList(true);
		startNode.setDistanceToEnd(start.distance(end));
		startNode.setDistanceTraveled(0);
		openList.add(startNode);
		
		while (openList.size() > 0) {
			SearchNode activeNode = findBestNode();
			
			if (activeNode == null)
				break;
			
			if (activeNode == endNode) {
				return FindFinalPath(startNode, endNode);
			} else {
				for (SearchNode neighbor : activeNode.getNeighbors()) {
					if (neighbor == null || !neighbor.isWalkable()) {
						continue;
					}
					
					float distanceTraveled = activeNode.getDistanceTraveled() + 1;
					float heuristic = neighbor.getPosition().distance(end);
					
					if (!neighbor.isInClosedList() || !neighbor.isInOpenList()) {
						neighbor.setDistanceTraveled(distanceTraveled);
						neighbor.setDistanceToEnd(distanceTraveled + heuristic);
						neighbor.setParent(activeNode);
						neighbor.setInOpenList(true);
						openList.add(neighbor);
					} else if (neighbor.isInClosedList() || neighbor.isInOpenList()) {
						if (distanceTraveled < neighbor.getDistanceTraveled()) {
							neighbor.setDistanceTraveled(distanceTraveled);
							neighbor.setDistanceToEnd(distanceTraveled + heuristic);
							neighbor.setParent(activeNode);				
						}
					}
				}
			}
			openList.remove(activeNode);
			activeNode.setInClosedList(true);
		}
		
		return new ArrayList<Vector2f>();
	}
}
