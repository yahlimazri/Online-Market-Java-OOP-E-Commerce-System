package ShaharAndYahli;

public class AddressFactory {
    public static Address createAddress(String streetName, String buildingNumber, String city, String state) {
        return new Address(streetName, buildingNumber, city, state);
    }
}
