package fooddeliverycrystal.domain;

import fooddeliverycrystal.domain.*;
import fooddeliverycrystal.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class MenuSelected extends AbstractEvent {

    private Long id;

    public MenuSelected(Order aggregate) {
        super(aggregate);
    }

    public MenuSelected() {
        super();
    }
}
