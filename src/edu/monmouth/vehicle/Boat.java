package edu.monmouth.vehicle;

public class Boat implements Vehicle {
	private int boatLength;
	private PowerTypes powerSource;

	public Boat(int boatLength) throws BoatException {
		this(VehicleConstants.DEFAULTBOATPOWER, boatLength);
	}

	public Boat(PowerTypes powerSource, int boatLength) throws BoatException {
		setPowerSource(powerSource);
		setBoatLength(boatLength);
	}

	public int getBoatLength() {
		return boatLength;
	}

	public void setBoatLength(int boatLength) throws BoatException {
		if (boatLength < VehicleConstants.MINBOATLENGTH || boatLength > VehicleConstants.MAXBOATLENGTH) {
			throw new BoatException(VehicleConstants.BOATLENGTHMSG + boatLength + " (must be between "
					+ VehicleConstants.MINBOATLENGTH + " and " + VehicleConstants.MAXBOATLENGTH + ")");
		}
		this.boatLength = boatLength;
	}

	public void setPowerSource(PowerTypes powerSource) throws BoatException {
		if (powerSource == null) {
			throw new BoatException(VehicleConstants.NULLPOWERMSG);
		}
		this.powerSource = powerSource;
	}

	public PowerTypes getPowerSource() {
		return powerSource;
	}

	@Override
	public void go() {
		System.out.println("Boat is going");
	}

	@Override
	public void stop() {
		System.out.println("Boat has stopped");
	}

	@Override
	public PowerTypes powerBy() {
		return powerSource;
	}

	@Override
	public String toString() {
		return "Boat [boatLength=" + boatLength + ", powerSource=" + powerSource + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Boat other = (Boat) obj;
		return boatLength == other.boatLength && powerSource == other.powerSource;
	}

	@Override
	public int hashCode() {
		return java.util.Objects.hash(boatLength, powerSource);
	}
}
