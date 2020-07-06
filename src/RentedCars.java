
import java.util.LinkedList;
import java.util.List;

public class RentedCars extends CarRentalSystem {

	private List<String> cars = new LinkedList<>();

	public void addCar(String plateNo) {
		cars.add(plateNo);
	}

	public boolean removeCar(String plateNo) {
		try {
			cars.remove(plateNo);
		} catch (NullPointerException e) {
			System.out.println("Masina nu exista in lista");
			return false;
		}
		return true;
	}

	public int getCarsNo() {
		return this.cars.size();
	}

	public List<String> getCars() {
		return cars;
	}

	public boolean isEmpty() {
		if (cars.isEmpty()) {
			return true;
		}
		return false;
	}

}
