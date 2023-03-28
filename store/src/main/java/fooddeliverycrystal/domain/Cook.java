package fooddeliverycrystal.domain;

import fooddeliverycrystal.StoreApplication;
import fooddeliverycrystal.domain.CookFinished;
import fooddeliverycrystal.domain.CookStarted;
import fooddeliverycrystal.domain.OrderAccepted;
import fooddeliverycrystal.domain.StatusUpdated;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Cook_table")
@Data
public class Cook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderId;

    private String foodId;

    private String option;

    private String status;

    @PostPersist
    public void onPostPersist() {
        OrderAccepted orderAccepted = new OrderAccepted(this);
        orderAccepted.publishAfterCommit();

        StatusUpdated statusUpdated = new StatusUpdated(this);
        statusUpdated.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        fooddeliverycrystal.external.Cook cook = new fooddeliverycrystal.external.Cook();
        // mappings goes here
        StoreApplication.applicationContext
            .getBean(fooddeliverycrystal.external.CookService.class)
            .updateStatus(cook);

        CookStarted cookStarted = new CookStarted(this);
        cookStarted.publishAfterCommit();

        CookFinished cookFinished = new CookFinished(this);
        cookFinished.publishAfterCommit();
    }

    public static CookRepository repository() {
        CookRepository cookRepository = StoreApplication.applicationContext.getBean(
            CookRepository.class
        );
        return cookRepository;
    }

    public void checkOrder(CheckOrderCommand checkOrderCommand) {
        OrderRejected orderRejected = new OrderRejected(this);
        orderRejected.publishAfterCommit();
    }

    public static void loadOrderInfo(OrderPlaced orderPlaced) {
        /** Example 1:  new item 
        Cook cook = new Cook();
        repository().save(cook);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(cook->{
            
            cook // do something
            repository().save(cook);


         });
        */

    }
}
