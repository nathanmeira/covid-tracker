package ps2.restapidb;

;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class EstadoSaudeController {
    @Autowired
    private EstadoSaudeRepo estadoSaudeRepo;
    
    @Autowired
    private UsuarioRepo usuarioRepo;
    
    @Autowired
    private SintomaRepo sintomaRepo;

    @GetMapping("/api/estado-saude")
    public List<EstadoSaude> getEstadosSaude(){
        List<EstadoSaude> estados = new ArrayList<>();
        estadoSaudeRepo.findAll().forEach(estados::add);
        return estados;
    }

    @GetMapping("/api/estado-saude/{id}")
    public EstadoSaude getEstadoSaude(@PathVariable long id) {
        Optional<EstadoSaude> retorno = estadoSaudeRepo.findById(id);
        EstadoSaude estado = null;
        if(retorno.isPresent()) {
            estado = retorno.get();
        }
        return estado;
    }

    @PostMapping("/api/estado-saude")
    public String createEstadoSaude(@RequestBody EstadoSaude estado) {   
        
        Optional<Usuario> optionUsuario = usuarioRepo.findById(estado.getIdUsuario());
        if (!optionUsuario.isPresent()) {
            return (
               "{ \"success\": false, \"estado_saude_id\": 0 }" 
            );
        }
        
        List<Sintoma> sintomas = new ArrayList<>();
        for (Object s : estado.sintomas) {
            if(s instanceof Integer){                
                Integer a = new Integer((int) s);
                Optional<Sintoma> optionSintoma = sintomaRepo.findById(a.longValue());
                if (optionUsuario.isPresent()) {
                    sintomas.add(optionSintoma.get());
                }
            }
        }
       
       estado.setSintomas(sintomas);
       estado.setUsuario(optionUsuario.get());
       estadoSaudeRepo.save(estado);
       return (
        "{ \"success\": true, \"estado_saude_id\": "+ estado.getId() + " }"
       );
    }	
    
    @GetMapping("/api/estado-saude/usuario/{id}")
    public List<EstadoSaude> getEstadoSaudeUsuario(@PathVariable long id) {
        Optional<List<EstadoSaude>> retorno = estadoSaudeRepo.findByIdUsuario(id);
        List<EstadoSaude> estado = null;
        if(retorno.isPresent()) {
            estado = retorno.get();
        }
        return estado;
    }
}
