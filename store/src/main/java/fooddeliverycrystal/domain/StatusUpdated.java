package fooddeliverycrystal.domain;

import fooddeliverycrystal.domain.*;
import fooddeliverycrystal.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class StatusUpdated extends AbstractEvent {

    private Long id;

    public StatusUpdated(Cook aggregate) {
        super(aggregate);
    }

    public StatusUpdated() {
        super();
    }
}
