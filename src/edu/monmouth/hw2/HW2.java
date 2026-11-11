package edu.monmouth.hw2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import edu.monmouth.vehicle.Boat;
import edu.monmouth.vehicle.BoatException;
import edu.monmouth.vehicle.Car;
import edu.monmouth.vehicle.CarException;
import edu.monmouth.vehicle.PowerTypes;
import edu.monmouth.vehicle.Scooter;
import edu.monmouth.vehicle.Vehicle;

public class HW2 {

	public static void main(String[] args) throws BoatException {
		if (args.length != HW2Constants.EXPECTED_ARGS) {
			System.err.println("Usage: HW2 <outputFile> <stringsFile> <vehiclesFile>");
			System.exit(HW2Constants.EXIT_BAD_USAGE);
		}
		if (!initialize(args[HW2Constants.OUTPUT_ARG])) {
			System.exit(HW2Constants.EXIT_INIT_FAILED);
		}

		List<String> strings = new ArrayList<>();
		List<Vehicle> vehicles = new ArrayList<>();

		try {
			loadStrings(args[HW2Constants.STRINGS_ARG], strings);
		} catch (IOException e) {
			System.err.println("Cannot read strings file: " + e.getMessage());
		}
		try {
			loadVehicles(args[HW2Constants.VEHICLES_ARG], vehicles);
		} catch (IOException e) {
			System.err.println("Cannot read vehicles file: " + e.getMessage());
		}

		System.out.println("===== STRING LIST =====");
		exercise(strings, "Added Later");

		System.out.println();
		System.out.println("===== VEHICLE LIST =====");
		try {
			exercise(vehicles, new Car(PowerTypes.ELECTRICITY, 2));
		} catch (CarException e) {
			System.err.println("Could not create Car to add: " + e.getMessage());
		}

		System.out.println();
		System.out.println("===== contains() =====");
		try {
			Vehicle inList = new Car(PowerTypes.GASOLINE, 4);
			Vehicle notInList = new Boat(PowerTypes.DEISEL, 77);
			System.out.println("contains(" + inList + ") -> " + vehicles.contains(inList));
			System.out.println("contains(" + notInList + ") -> " + vehicles.contains(notInList));
		} catch (CarException | BoatException e) {
			System.err.println("Could not create Vehicle for contains test: " + e.getMessage());
		}

		System.out.println("***Homework 2 Modification***");
		System.out.println(" ");
		System.out.println("*** size() ***");
		System.out.println("String list has " + strings.size() + " elements");
		System.out.println("Vehicle list has " + vehicles.size() + " elements");

		System.out.println();
		System.out.println("*** ListIterator: STRING LIST ***");
		printBothWays(strings);

		System.out.println();
		System.out.println("*** ListIterator: VEHICLE LIST ***");
		printBothWays(vehicles);

		System.out.println();
		System.out.println("*** remove(Object) ***");
		Vehicle toRemove = new Boat(PowerTypes.ELECTRICITY, 42);
		System.out.println("remove(" + toRemove + ") -> " + vehicles.remove(toRemove));
		System.out.println("Vehicle list has " + vehicles.size() + " elements");
		printBothWays(vehicles);
	}

	/** Prints the list first to last, then last to first, with one ListIterator. */
	private static <T> void printBothWays(List<T> list) {
		ListIterator<T> it = list.listIterator();
		System.out.println("--- Forward ---");
		while (it.hasNext()) {
			System.out.println("  " + it.next());
		}
		System.out.println("--- Backward ---");
		while (it.hasPrevious()) {
			System.out.println("  " + it.previous());
		}
	}

	/** Redirect System.out and System.err to the same file. */
	private static boolean initialize(String outputFile) {
		try {
			PrintStream ps = new PrintStream(new FileOutputStream(outputFile), true);
			System.setOut(ps);
			System.setErr(ps);
			return true;
		} catch (FileNotFoundException | SecurityException e) {
			System.err.println("Cannot open output file " + outputFile + ": " + e.getMessage());
			return false;
		}
	}

	private static void loadStrings(String fileName, List<String> list) throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = in.readLine()) != null) {
				list.add(line);
			}
		}
	}

	private static void loadVehicles(String fileName, List<Vehicle> list) throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
			String line;
			int lineNumber = 0;
			while ((line = in.readLine()) != null) {
				lineNumber++;
				try {
					list.add(parseVehicle(line));
				} catch (CarException | BoatException | IllegalArgumentException e) {
					System.err.println(
							"Invalid line " + lineNumber + " [" + line + "] discarded: " + e.getMessage());
				}
			}
		}
	}

	/**
	 * Splits and converts only. Range and null validation is delegated to the
	 * Vehicle constructors. Nothing is trimmed or corrected.
	 */
	private static Vehicle parseVehicle(String line) throws CarException, BoatException {
		String[] f = line.split(HW2Constants.FIELD_SEPARATOR, -1);
		if (f.length != HW2Constants.VEHICLE_FIELDS) {
			throw new IllegalArgumentException(
					"expected " + HW2Constants.VEHICLE_FIELDS + " fields but found " + f.length);
		}
		PowerTypes power;
		try {
			power = PowerTypes.valueOf(f[1]);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("unknown power type '" + f[1] + "'");
		}
		int size;
		try {
			size = Integer.parseInt(f[2]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("size '" + f[2] + "' is not an integer");
		}
		switch (f[0]) {
		case HW2Constants.CAR_CODE:
			return new Car(power, size);
		case HW2Constants.BOAT_CODE:
			return new Boat(power, size);
		case HW2Constants.SCOOTER_CODE:
			return new Scooter(size, power);
		default:
			throw new IllegalArgumentException("unknown vehicle type '" + f[0] + "'");
		}
	}

	/** Demonstrates isEmpty, iterator, remove(index) and add on any List<T>. */
	private static <T> void exercise(List<T> list, T toAdd) {
		System.out.println("isEmpty() -> " + list.isEmpty());
		print("Initial contents", list);

		T removed = list.remove(0);
		System.out.println("remove(0) removed: " + removed);
		print("After remove(0)", list);

		list.add(toAdd);
		System.out.println("add() added: " + toAdd);
		print("After add", list);
	}

	private static <T> void print(String title, List<T> list) {
		System.out.println("--- " + title + " (size " + list.size() + ") ---");
		Iterator<T> it = list.iterator();
		while (it.hasNext()) {
			System.out.println("  " + it.next());
		}
	}
}
