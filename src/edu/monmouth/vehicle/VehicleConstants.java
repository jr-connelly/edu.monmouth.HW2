package edu.monmouth.vehicle;

public class VehicleConstants {
	public static final int MINENGINESIZE = 1;
	public static final int MAXENGINESIZE = 199;
	public static final int MINBOATLENGTH = 1;
	public static final int MAXBOATLENGTH = 1300;
	public static final PowerTypes DEFAULTBOATPOWER = PowerTypes.FUELOIL;

	public static final String ENGINESIZEMSG = "Invalid engine size: ";
	public static final String BOATLENGTHMSG = "Invalid boat length: ";
	public static final String NULLPOWERMSG = "Power source must not be null";

	private VehicleConstants() {
	}
}
