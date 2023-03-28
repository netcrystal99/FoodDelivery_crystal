package fooddeliverycrystal.infra;

import fooddeliverycrystal.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping(value="/cooks")
@Transactional
public class CookController {

    @Autowired
    CookRepository cookRepository;

    @RequestMapping(
        value = "cooks/{id}/checkorder",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Cook checkOrder(
        @PathVariable(value = "id") Long id,
        @RequestBody CheckOrderCommand checkOrderCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /cook/checkOrder  called #####");
        Optional<Cook> optionalCook = cookRepository.findById(id);

        optionalCook.orElseThrow(() -> new Exception("No Entity Found"));
        Cook cook = optionalCook.get();
        cook.checkOrder(checkOrderCommand);

        cookRepository.save(cook);
        return cook;
    }
}
