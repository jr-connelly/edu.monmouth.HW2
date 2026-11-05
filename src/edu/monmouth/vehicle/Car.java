package edu.monmouth.vehicle;

public class Car implements Vehicle {
	private int engineSize;
	private PowerTypes powerSource;

	public Car(PowerTypes powerSource, int engineSize) throws CarException {
		setPowerSource(powerSource);
		setEngineSize(engineSize);
	}

	public int getEngineSize() {
		return engineSize;
	}

	public void setEngineSize(int engineSize) throws CarException {
		if (engineSize < VehicleConstants.MINENGINESIZE || engineSize > VehicleConstants.MAXENGINESIZE) {
			throw new CarException(VehicleConstants.ENGINESIZEMSG + engineSize + " (must be between "
					+ VehicleConstants.MINENGINESIZE + " and " + VehicleConstants.MAXENGINESIZE + ")");
		}
		this.engineSize = engineSize;
	}

	public void setPowerSource(PowerTypes powerSource) throws CarException {
		if (powerSource == null) {
			throw new CarException(VehicleConstants.NULLPOWERMSG);
		}
		this.powerSource = powerSource;
	}

	public PowerTypes getPowerSource() {
		return powerSource;
	}

	@Override
	public void go() {
		System.out.println("Car is going");
	}

	@Override
	public void stop() {
		System.out.println("Car has stopped");
	}

	@Override
	public PowerTypes powerBy() {
		return powerSource;
	}

	@Override
	public String toString() {
		return "Car [engineSize=" + engineSize + ", powerSource=" + powerSource + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Car other = (Car) obj;
		return engineSize == other.engineSize && powerSource == other.powerSource;
	}

	@Override
	public int hashCode() {
		return java.util.Objects.hash(engineSize, powerSource);
	}
}
