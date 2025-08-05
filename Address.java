package ShaharAndYahli;

public class Address {
    private String streetName;
    private String buildingNumber;
    private String city;
    private String state;

    public Address(String streetName, String buildingNumber, String city, String state) {
        if (streetName == null || streetName.isEmpty() || 
            buildingNumber == null || buildingNumber.isEmpty() ||
            city == null || city.isEmpty() || 
            state == null || state.isEmpty()) 
        {
            throw new IllegalArgumentException("All address fields must be provided and non-empty.");
        }
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.city = city;
        this.state = state;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return streetName + ", " + buildingNumber + ", " + city + ", " + state;
    }
}
