
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CarRentalSystem {

	private static Scanner sc = new Scanner(System.in);
	private static Map<String, String> rentedCars = new HashMap<String, String>(100, 0.5f);
	private static Map<String, RentedCars> carOwners = new Hashtable<String, RentedCars>();

	private static String getPlateNo() {
		System.out.println("Introduceti numarul de inmatriculare:");
		return sc.nextLine();

	}

	private static String getOwnerName() {
		System.out.println("Introduceti numele proprietarului:");
		return sc.nextLine();
	}

	private boolean rentCar(String plateNo, String ownerName) {
		if (!rentedCars.containsKey(plateNo)) {
			if (carOwners.containsKey(ownerName)) {
				RentedCars cars = carOwners.get(ownerName);
				cars.addCar(plateNo);
			} else {
				RentedCars cars2 = new RentedCars();
				cars2.addCar(plateNo);
				carOwners.put(ownerName, cars2);
			}
			rentedCars.put(plateNo, ownerName);
			System.out.println("Autoturismul " + plateNo + " a fost inchiriat cu succes de catre " + ownerName);

			return true;
		}
		System.out.println("Autoturismul este deja inchiriat");
		return false;

	}

	private boolean isCarRented(String plateNo) {
		if (rentedCars.containsKey(plateNo)) {
			System.out.println("Masina este deja inchiriata");
			return true;
		}
		System.out.println("Masina nu este inchiriata");
		return false;
	}

	private boolean returnCar(String plateNo) {
		try {
			String ownerName = rentedCars.get(plateNo);
			rentedCars.remove(plateNo);
			
			if (carOwners.containsKey(ownerName)) {
				RentedCars cars3 = carOwners.get(ownerName);
				cars3.removeCar(plateNo);
			}
			return true;
		} catch (NullPointerException e) {
			System.out.println("Exception");
		}
		return false;
	}

	private boolean getRentedCar(String plateNo) {
		if (rentedCars.containsKey(plateNo)) {
			System.out.println("Proprietarul temporar este " + rentedCars.get(plateNo));
			return true;
		}
		System.out.println("Acest autovehicul nu exista in baza de date");
		return false;
	}
	
	private int getTotalRented() {
		return rentedCars.size();
	}

	private int getCarsNumber(String ownerName) {

			try {
			RentedCars cars4 = carOwners.get(ownerName);
			return cars4.getCarsNo();
		} catch (NullPointerException e) {
			System.out.println("Exception");
		}
		return 0;
	}

	private static List<String> getCarsList(String ownerName) {

		if (carOwners.containsKey(ownerName)) {
			RentedCars cars5 = carOwners.get(ownerName);
			return cars5.getCars();
		}
		return null;
	}

	private static void printCommandsList() {
		System.out.println("help         - Afiseaza aceasta lista de comenzi");
		System.out.println("add          - Adauga o noua pereche (masina, sofer)");
		System.out.println("check        - Verifica daca o masina este deja luata");
		System.out.println("remove       - Sterge o masina existenta din hashtable");
		System.out.println("getOwner     - Afiseaza proprietarul curent al masinii");
		System.out.println("totalRented  - Afiseaza proprietarul curent al masinii");
		System.out.println("getCarsNo    - Afiseaza proprietarul curent al masinii");
		System.out.println("getCarsList  - Afiseaza proprietarul curent al masinii");
		System.out.println("quit         - Inchide aplicatia");
	}

	public void run() {
		boolean quit = false;
		while (!quit) {
			System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
			String command = sc.nextLine();
			switch (command) {
			case "help":
				printCommandsList();
				break;
			case "add":
				rentCar(getPlateNo(), getOwnerName());
				break;
			case "check":
				isCarRented(getPlateNo());
				break;
			case "remove":
				if (returnCar(getPlateNo())) {
					System.out.println("Masina a fost stearsa cu succes");
				} else {
					System.out.println("Acest autovehicul nu exista in baza de date");
				}
				break;
			case "getOwner":
				getRentedCar(getPlateNo());
				break;
			case "totalRented":
				System.out.println("Numarul de masini inchiriate este: " + getTotalRented());
				break;
			case "getCarsNo":
				System.out.println(getCarsNumber(getOwnerName()));
				break;
			case "getCarsList":
				System.out.println(getCarsList(getOwnerName()));
				break;
			case "quit":
				System.out.println("Aplicatia se inchide...");
				quit = true;
				break;
			default:
				System.out.println("Unknown command. Choose from:");
				printCommandsList();
			}
		}
	}

	public static void main(String[] args) {

		new CarRentalSystem().run();

	}
}