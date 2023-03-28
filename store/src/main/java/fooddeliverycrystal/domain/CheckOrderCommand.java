package fooddeliverycrystal.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
public class CheckOrderCommand {

    private Boolean checkOrder;
}
