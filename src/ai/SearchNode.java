package ai;

import org.newdawn.slick.geom.Vector2f;

public class SearchNode {
	
	private Vector2f position;
	private boolean walkable;
	
	private SearchNode[] neighbors;
	
	private SearchNode parent;
	// F Value
	private float DistanceToEnd;
	// G Value
	private float DistanceTraveled;
	
	private boolean inOpenList;
	private boolean inClosedList;
	
	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public boolean isWalkable() {
		return walkable;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

	public SearchNode[] getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(SearchNode[] neighbors) {
		this.neighbors = neighbors;
	}

	public SearchNode getParent() {
		return parent;
	}

	public void setParent(SearchNode parent) {
		this.parent = parent;
	}

	public float getDistanceToEnd() {
		return DistanceToEnd;
	}

	public void setDistanceToEnd(float distanceToEnd) {
		DistanceToEnd = distanceToEnd;
	}

	public float getDistanceTraveled() {
		return DistanceTraveled;
	}

	public void setDistanceTraveled(float distanceTraveled) {
		DistanceTraveled = distanceTraveled;
	}

	public boolean isInOpenList() {
		return inOpenList;
	}

	public void setInOpenList(boolean inOpenList) {
		this.inOpenList = inOpenList;
	}

	public boolean isInClosedList() {
		return inClosedList;
	}

	public void setInClosedList(boolean inClosedList) {
		this.inClosedList = inClosedList;
	}
	
}
