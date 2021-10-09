package ps2.restapidb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class SintomaController {
    @Autowired
    private SintomaRepo sintomaRepo;

    @GetMapping("/api/sintomas")
    public List<Sintoma> getSintoma(){
        List<Sintoma> sintomas = new ArrayList<>();
        sintomaRepo.findAll().forEach(sintomas::add);
        return sintomas;
    }

    @GetMapping("/api/sintomas/{id}")
    public Sintoma getSintoma(@PathVariable long id) {
        Optional<Sintoma> retorno = sintomaRepo.findById(id);
        Sintoma sintoma = null;
        if(retorno.isPresent()) {
            sintoma = retorno.get();
        }
        return sintoma;
    }

    @PostMapping("/api/sintomas")
    public Sintoma createSintoma(@RequestBody Sintoma sintoma) {
    	sintomaRepo.save(sintoma);
        return sintoma;
    }

    @PutMapping("/api/sintomas/{id}")
    public Sintoma updateSintoma(@RequestBody Sintoma a, @PathVariable long id) {
        Sintoma sintoma = null;
    	sintoma = this.getSintoma(id);
        if(sintoma != null) {
            sintomaRepo.save(a);
            sintoma = a;
        }
        return a;
    }

    @DeleteMapping(value = "/api/sintomas/{id}", produces = "application/json")
    public String deleteSintoma(@PathVariable long id) {
        Sintoma sintoma = this.getSintoma(id);
        boolean result = false;
        if(sintoma != null) {
        	sintomaRepo.delete(sintoma);
            result = true;
        }
        return "{ \"sucess\" : " + (result ? "true" : "false" ) + " }";
    }
	
}
