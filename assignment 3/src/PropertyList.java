import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PropertyList {
    private Property head;
    private static String dataLocation = "H:\\Study\\Java\\FH\\CS 1B\\assignment 3\\src\\text.txt";

    public PropertyList() {
        this.head = null;
    }

    public void insert(Property property) {
        if (head == null) {
            this.head = property;
        } else {
            property.setNext(this.head);
            this.head = property;
        }
    }

    public String getAllProperties() {
        Property property = this.head;
        String allProperties = String.format(property.format, "Address", "Price", "Year", "Other Info");
        while (property != null) {
            allProperties += property.toString();
            property = property.getNext();
        }
        return allProperties;
    }

    public String getSingleFamilyHouse() {
        Property property = this.head;
        String allSFH = String.format(property.format, "Address", "Price", "Year", "Other Info");
        while (property != null) {
            if (property instanceof SingleFamilyHouse) {
                allSFH += property.toString();
            }
            property = property.getNext();
        }
        return allSFH;
    }

    public String getCondo() {
        Property property = this.head;
        String allCondo = String.format(property.format, "Address", "Price", "Year", "Other Info");
        while (property != null) {
            if (property instanceof Condo) {
                allCondo += property.toString();
            }
            property = property.getNext();
        }
        return allCondo;
    }

    public String searchByPriceRange(double min, double max) {
        Property property = this.head;
        String properties = String.format(property.format, "Address", "Price", "Year", "Other Info");
        while (property != null) {
            if ((property.getOfferedPrice() > min) && (property.getOfferedPrice() < max)) {
                properties += property.toString();
            }
            property = property.getNext();
        }
        return properties;
    }

    public void initialize() {
        //Read text file in format: <property type>;<address>;<offerPrice>;<year built>;<property-specific data>
        BufferedReader reader = null;
        String line;
        try {
            reader = Files.newBufferedReader(Paths.get(dataLocation), StandardCharsets.US_ASCII);
            while ((line = reader.readLine()) != null) {
                String[] dataParts = line.split(";");
                String address = dataParts[1];
                double offerPrice = Double.parseDouble(dataParts[2]);
                int year = Integer.parseInt(dataParts[3]);
                if (dataParts[0].equalsIgnoreCase("SFH")) {
                    Property singleFamilyHouse = new SingleFamilyHouse(address, offerPrice, year, Integer.parseInt(dataParts[4]));
                    insert(singleFamilyHouse);
                } else if (dataParts[0].equalsIgnoreCase("Condo")) {
                    Property condo = new Condo(address, offerPrice, year, Double.parseDouble(dataParts[4]));
                    insert(condo);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    }
}
