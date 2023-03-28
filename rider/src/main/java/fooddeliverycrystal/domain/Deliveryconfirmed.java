package fooddeliverycrystal.domain;

import fooddeliverycrystal.domain.*;
import fooddeliverycrystal.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class Deliveryconfirmed extends AbstractEvent {

    private Long id;

    public Deliveryconfirmed(Delivery aggregate) {
        super(aggregate);
    }

    public Deliveryconfirmed() {
        super();
    }
}
