package info806.GestionBD.api;

import info806.GestionBD.service.SuivisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("suivis")
@RestController
public class SuivisController {

    private final SuivisService suivisService;

    @Autowired
    public SuivisController(SuivisService suivisService) {
        this.suivisService = suivisService;
    }

    @GetMapping
    public String getAllSuivis(){
        return suivisService.getAllSuivis().toString();
    }
}
