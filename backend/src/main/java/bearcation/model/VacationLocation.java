package bearcation.model;
// Note: feel free to change anything in here
public class VacationLocation {

    private String name;
    private String description;
    private Integer slots_available;
    private String address;
    private Double price;
    private Double overall_rating;

    public VacationLocation(String name, String description, Integer slots_available, String address, Double price) {
        this.name = name;
        this.description = description;
        this.slots_available = slots_available;
        this.address = address;
        this.price = price;
    }

    // FIXME: I don't really know what is required here
    public String getFacilityDetails() {
        return null;
    }

    public void changeFacilityAvailability(Integer slots_available) {
        this.slots_available = slots_available;
    }

    public void changeFacilityDetails(String name, String description, Integer slots_available, String address, Double price) {
        this.name = name;
        this.description = description;
        this.slots_available = slots_available;
        this.address = address;
        this.price = price;
    }

}
