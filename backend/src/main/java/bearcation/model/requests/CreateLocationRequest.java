package bearcation.model.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateLocationRequest {
    private Long ownerId;
    private String name;
    private String address;
    private String description;
    private Double price;
    private Double latitude;
    private Double longitude;
}
