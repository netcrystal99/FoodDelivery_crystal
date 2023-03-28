package fooddeliverycrystal.external;

import org.springframework.stereotype.Service;

@Service
public class CookServiceImpl implements CookService {

    /**
     * Fallback
     */
    public Cook getCook(Long id) {
        Cook cook = new Cook();
        return cook;
    }
}
