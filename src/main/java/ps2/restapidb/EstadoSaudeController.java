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
       
       estado.setUsuario(optionUsuario.get());
       estadoSaudeRepo.save(estado);
       return (
        "{ \"success\": true, \"estado_saude_id\": "+ estado.getId() + " }"
       );
    }	
}
