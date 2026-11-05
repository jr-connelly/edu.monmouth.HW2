package edu.monmouth.vehicle;

public class Scooter implements Vehicle {
	
private int energy;
private PowerTypes powerSource;

public Scooter(int energy, PowerTypes powerSource) {
	setEnergy(energy);
	setPowerSource(powerSource);
}


public int getEnergy() {
	return energy;
}

public PowerTypes getPowerSource() {
	return powerSource;
}


public void setEnergy(int energy) {
	this.energy = energy;
}

public void setPowerSource(PowerTypes powerSource) {
	this.powerSource = powerSource;
}

@Override
public void go() {
	System.out.println("Schwoosh!");
}
@Override
public void stop() {
	System.out.println("Brake!");
}
@Override
public PowerTypes powerBy() {
	return powerSource;
}
@Override
public String toString() {
	return "Scooter [energy=" + energy + ", powerSource=" + powerSource + "]";
}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Scooter other = (Scooter) obj;
		return energy == other.energy && powerSource == other.powerSource;
	}

	@Override
	public int hashCode() {
		return java.util.Objects.hash(energy, powerSource);
	}
}
