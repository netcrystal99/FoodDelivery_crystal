package fooddeliverycrystal.external;

import java.util.Date;
import lombok.Data;

@Data
public class Cook {

    private Long id;
    private String orderId;
    private String foodId;
    private String option;
    private String status;
}
