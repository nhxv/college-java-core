import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;

public class CarDealer {

    private Vehicle[] vehicles;
    private Vehicle[] cleanVehicles;
    private String location;
    private int vehiclesCount;
    private static String dealerBrand = "FH Car Dealership";
    private static int maxVehicles = 1024;

    //default constructor
    public CarDealer() {
        this.vehicles = new Vehicle[maxVehicles];
        this.vehiclesCount = 0;
        this.location = "Lost City";
    }

    //non-default constructor
    public CarDealer(String location) {
        this();
        this.location = location;
    }

    //static accessor
    public String getDealerBrand() {
        return dealerBrand;
    }

    public int getMaxVehicles() {
        return maxVehicles;
    }

    //mutator
    public void setLocation(String location) {
        this.location = location;
    }

    public void setCountVehicles(int vehiclesCount) {
        this.vehiclesCount = vehiclesCount;
    }

    //run program
    public void init() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String data;
        System.out.println("Welcome to Foothill Car Dealership at " + this.location + "... Loading vehicles from DB ... Please wait ...\n");
        while (true) {
            System.out.print("Enter Make;Model;Year;Price [Ford Taurus;2014;14578.99] or [END;] to quit:");
            data = reader.readLine();
            //check to exit the loop
            if (data.equals("END;")) {
                break;
            }
            String[] dataParts = data.split(";");
            Vehicle vehicle = new Vehicle (dataParts[0], dataParts[1], Integer.parseInt(dataParts[2]), Double.parseDouble(dataParts[3]));
            //check if array has enough space before adding the vehicle
            if (this.vehiclesCount <= maxVehicles - 1) {
                vehicles[this.vehiclesCount] = vehicle;
            } else {
                System.out.println("Not enough space. We can only have " + maxVehicles + " vehicle(s) in our inventory.");
                break;
            }
            this.vehiclesCount++;
        }
        //clean up null values
        this.cleanVehicles = Arrays.stream(this.vehicles).filter(Objects::nonNull).toArray(Vehicle[]::new);
    }
    //run smart search menu
    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command;
        String makeQuery;
        String searchQuery;
        do {
            menu();
            sortByYear();
            //get user input
            System.out.print("\nEnter your choice: ");
            command = reader.readLine();
            switch (command) {
                case "1":
                    //show vehicle inventory
                    viewInventory();
                    break;
                case "2":
                    //show reserve vehicle
                    viewReserveVehicle();
                    break;
                case "3":
                    //reserve a vehicle
                    System.out.print("Enter Make;Model;Year;Price: ");
                    String reserveVehicleData = reader.readLine();
                    String[] dataParts = reserveVehicleData.split(";");
                    Vehicle vehicle = new Vehicle (dataParts[0], dataParts[1], Integer.parseInt(dataParts[2]), Double.parseDouble(dataParts[3]));
                    reserveVehicle(vehicle);
                    break;
                case "4":
                    //search vehicle by make and model
                    System.out.print("Enter vehicle make: ");
                    makeQuery = reader.readLine();
                    System.out.print("Enter vehicle model: ");
                    searchQuery = reader.readLine();
                    searchMakeModel(makeQuery, searchQuery);
                    break;
            }
        } while (!command.equals("5"));
        quit();
    }

    //display smart search menu -- called from run
    public void menu() {
        System.out.println("\n                      SMART SEARCH");
        System.out.println("1. View Vehicle Inventory");
        System.out.println("2. View reserve vehicle");
        System.out.println("3. Reserve a vehicle");
        System.out.println("4. Search by make and model");
        System.out.println("5. Quit");
    }

    //sort array by year -- called from run
    public void sortByYear() {
        Arrays.sort(this.cleanVehicles);
    }

    //show any vehicle in the database that's not reserved -- option 1
    public void viewInventory() {
        //check if there's any vehicle available to show
        if (isAllReserved() || this.cleanVehicles[0] == null) {
            System.out.println("No vehicle found.");
        } else {
            System.out.println("         --------------------------------------------");
            System.out.println("         |           VEHICLE INVENTORY       |");
            System.out.println("         --------------------------------------------\n");
            System.out.println("MAKE & MODEL                  YEAR      PRICE");
            System.out.println("----------------------------------------------------\n");
            for (Vehicle vehicle : this.cleanVehicles) {
                //check if vehicle should be printed in vehicle inventory
                if (vehicle != null && !vehicle.getReserved()) {
                    System.out.println(vehicle);
                }
            }
        }
    }

    //show any vehicle that's reserved -- option 2
    public void viewReserveVehicle() {
        //check if there's any reserved vehicle
        if (!isAnyReserved()) {
            System.out.println("No vehicle found.");
        } else {
            System.out.println("         --------------------------------------------");
            System.out.println("         |           VEHICLE RESERVE       |");
            System.out.println("         --------------------------------------------\n");
            System.out.println("MAKE & MODEL                  YEAR      PRICE");
            System.out.println("----------------------------------------------------\n");
            for (Vehicle vehicle : cleanVehicles) {
                if (vehicle.getReserved()) {
                    System.out.println(vehicle);
                }
            }
        }
    }

    //reserve the vehicle inside array -- option 3
    public  void reserveVehicle(Vehicle vehicle) {
        //check if the vehicle is in the database
        if (!isContainVehicle(vehicle)) {
            System.out.println("Vehicle not available.");
        } else {
            for (Vehicle vehicleInArr : cleanVehicles) {
                if (vehicleInArr.equals(vehicle)) {
                    //check if vehicle in the database is reserved
                    if (vehicleInArr.getReserved()) {
                        System.out.println("Vehicle not available");
                    } else {
                        vehicleInArr.setReserved(true);
                    }
                }
            }
        }
    }

    //search for matching make and model -- option 4
    public void searchMakeModel(String makeQuery, String modelQuery) {
        if (!isContainMakeModel(makeQuery, modelQuery)) {
            System.out.println("No vehicle found.");
        }
        for (Vehicle vehicle : this.cleanVehicles) {
            //display any non-reserved vehicle that matches the search queries
            if (vehicle.getMake().equals(makeQuery) && vehicle.getModel().equals(modelQuery) && !vehicle.getReserved()) {
                System.out.println(vehicle);
            }
        }
    }

    //quit the program -- option 5
    public void quit() {
        System.out.println("Thanks for using " + dealerBrand + " at " + location);
    }

    //check if every cleanVehicles are reserved -- called from viewInventory and searchMakeModel -- option 1
    public boolean isAllReserved() {
        for (Vehicle vehicle : this.cleanVehicles) {
            if (!vehicle.getReserved()) {
                return false;
            }
        }
        return true;
    }

    //check if there's any reserved vehicle -- called from viewReserveVehicle -- option 2
    public boolean isAnyReserved() {
        for (Vehicle vehicle : this.cleanVehicles) {
            if (vehicle.getReserved()) {
                return true;
            }
        }
        return false;
    }

    //check if array of cleanVehicles contain a vehicle -- called from reserveVehicle -- option 3
    public boolean isContainVehicle(Vehicle vehicle) {
        for (Vehicle vehicleInArr : cleanVehicles) {
            if (vehicleInArr.equals(vehicle)) {
                return true;
            }
        }
        return false;
    }

    //check if array contain any non-reserved vehicle matching make and model queries -- called from searchMakeModel -- option 4
    public boolean isContainMakeModel(String makeQuery, String modelQuery) {
        for (Vehicle vehicle : this.cleanVehicles) {
            if (vehicle.getMake().equals(makeQuery) && vehicle.getModel().equals(modelQuery)) {
               if (!vehicle.getReserved()) {
                   return true;
               }
            }
        }
        return false;
    }

}
