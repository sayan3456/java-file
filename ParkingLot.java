import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ParkingLot {
    private int capacity;
    private Map<Integer, Car> slots;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.slots = new HashMap<>();
        initializeParkingSlots();
    }

    private void initializeParkingSlots() {
        for (int i = 1; i <= capacity; i++) {
            slots.put(i, null);
        }
    }

    public void parkCar(String registrationNumber, String color) {
        for (int i = 1; i <= capacity; i++) {
            if (slots.get(i) == null) {
                slots.put(i, new Car(registrationNumber, color));
                System.out.println("Allocated slot number: " + i);
                return;
            }
        }
        System.out.println("Sorry, the parking lot is full.");
    }

    public void leave(int slotNumber) {
        if (slots.containsKey(slotNumber) && slots.get(slotNumber) != null) {
            slots.put(slotNumber, null);
            System.out.println("Slot number " + slotNumber + " is free.");
        } else {
            System.out.println("Invalid slot number or slot is already empty.");
        }
    }

    public void getRegistrationNumbersByColor(String color) {
        slots.values().stream()
                .filter(car -> car != null && car.getColor().equalsIgnoreCase(color))
                .forEach(car -> System.out.println(car.getRegistrationNumber()));
    }

    public void getSlotNumberByRegistrationNumber(String registrationNumber) {
        for (Map.Entry<Integer, Car> entry : slots.entrySet()) {
            if (entry.getValue() != null && entry.getValue().getRegistrationNumber().equalsIgnoreCase(registrationNumber)) {
                System.out.println("Slot number: " + entry.getKey());
                return;
            }
        }
        System.out.println("Not found");
    }

    public void getSlotNumbersByColor(String color) {
        slots.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getValue().getColor().equalsIgnoreCase(color))
                .forEach(entry -> System.out.println("Slot number: " + entry.getKey()));
    }
}

class Car {
    private String registrationNumber;
    private String color;

    public Car(String registrationNumber, String color) {
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColor() {
        return color;
    }
}

public class ParkingLotManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParkingLot parkingLot = null;

        if (args.length > 0) {
            parkingLot = new ParkingLot(Integer.parseInt(args[0]));
        } else {
            System.out.println("Enter parking lot capacity:");
            int capacity = scanner.nextInt();
            parkingLot = new ParkingLot(capacity);
        }

        while (true) {
            System.out.println("Enter command (Type 'exit' to quit):");
            String command = scanner.next();

            switch (command) {
                case "exit":
                    System.out.println("Exiting the system.");
                    System.exit(0);
                case "park":
                    System.out.println("Enter registration number and color:");
                    parkingLot.parkCar(scanner.next(), scanner.next());
                    break;
                case "leave":
                    System.out.println("Enter slot number to leave:");
                    parkingLot.leave(scanner.nextInt());
                    break;
                case "registration_numbers_for_cars_with_colour":
                    System.out.println("Enter color:");
                    parkingLot.getRegistrationNumbersByColor(scanner.next());
                    break;
                case "slot_number_for_registration_number":
                    System.out.println("Enter registration number:");
                    parkingLot.getSlotNumberByRegistrationNumber(scanner.next());
                    break;
                case "slot_numbers_for_cars_with_colour":
                    System.out.println("Enter color:");
                    parkingLot.getSlotNumbersByColor(scanner.next());
                    break;
                default:
                    System.out.println("Invalid command. Try again.");
            }
        }
    }
}
