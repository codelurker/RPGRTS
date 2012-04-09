package gui.building;

public enum BuildingType {
	COMMAND_CENTER_ALLY(0),
	BASIC_TURRET_ALLY(1);
	
	private int value;
	
	private BuildingType(int value) {
		this.value = value;
	}
}
