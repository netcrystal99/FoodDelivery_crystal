package fooddeliverycrystal.domain;

import fooddeliverycrystal.CustomerApplication;
import fooddeliverycrystal.domain.Paid;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Pay_table")
@Data
public class Pay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @PostPersist
    public void onPostPersist() {
        Paid paid = new Paid(this);
        paid.publishAfterCommit();
    }

    public static PayRepository repository() {
        PayRepository payRepository = CustomerApplication.applicationContext.getBean(
            PayRepository.class
        );
        return payRepository;
    }
}
