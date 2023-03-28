package fooddeliverycrystal.domain;

import fooddeliverycrystal.CustomerApplication;
import fooddeliverycrystal.domain.MenuSelected;
import fooddeliverycrystal.domain.OrderCanceled;
import fooddeliverycrystal.domain.OrderPlaced;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String customerId;

    private String foodId;

    private String option;

    private String adddress;

    private String status;

    @PostPersist
    public void onPostPersist() {
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        fooddeliverycrystal.external.Pay pay = new fooddeliverycrystal.external.Pay();
        // mappings goes here
        CustomerApplication.applicationContext
            .getBean(fooddeliverycrystal.external.PayService.class)
            .payForMeal(pay);

        MenuSelected menuSelected = new MenuSelected(this);
        menuSelected.publishAfterCommit();

        OrderCanceled orderCanceled = new OrderCanceled(this);
        orderCanceled.publishAfterCommit();
    }

    public static OrderRepository repository() {
        OrderRepository orderRepository = CustomerApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    public static void orderForMeal(Paid paid) {
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        OrderPlaced orderPlaced = new OrderPlaced(order);
        orderPlaced.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(paid.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);

            OrderPlaced orderPlaced = new OrderPlaced(order);
            orderPlaced.publishAfterCommit();

         });
        */

    }

    public static void updateStatus(StatusUpdated statusUpdated) {
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process
        
        repository().findById(statusUpdated.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);


         });
        */

    }
}
