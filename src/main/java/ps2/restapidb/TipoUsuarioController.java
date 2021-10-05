package ps2.restapidb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TipoUsuarioController {
    @Autowired
    private TipoUsuarioRepo tipoUsuarioRepo;

    @GetMapping("/api/tipo-usuario")
    public List<TipoUsuario> getTipoUsuarios(){
        List<TipoUsuario> tipoUsuario = new ArrayList<>();
        tipoUsuarioRepo.findAll().forEach(tipoUsuario::add);
        return tipoUsuario;
    }

    @GetMapping("/api/tipo-usuario/{id}")
    public TipoUsuario getTipoUsuario(@PathVariable long id) {
        Optional<TipoUsuario> retorno = tipoUsuarioRepo.findById(id);
        TipoUsuario tipoUsuario = null;
        if(retorno.isPresent()) {
            tipoUsuario = retorno.get();
        }
        return tipoUsuario;
    }

    @PostMapping("/api/tipo-usuario")
    public TipoUsuario createTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
    	tipoUsuarioRepo.save(tipoUsuario);
        return tipoUsuario;
    }

    @PutMapping("/api/tipo-usuario/{id}")
    public TipoUsuario updateTipoUsuario(@RequestBody TipoUsuario a, @PathVariable long id) {
        TipoUsuario tipoUsuario = null;
    	tipoUsuario = this.getTipoUsuario(id);
        if(tipoUsuario != null) {
            tipoUsuarioRepo.save(a);
            tipoUsuario = a;
        }
        return a;
    }

    @DeleteMapping(value = "/api/tipo-usuario/{id}", produces = "application/json")
    public String deleteTipoUsuario(@PathVariable long id) {
        TipoUsuario tipoUsuario = this.getTipoUsuario(id);
        boolean result = false;
        if(tipoUsuario != null) {
        	tipoUsuarioRepo.delete(tipoUsuario);
            result = true;
        }
        return "{ \"sucess\" : " + (result ? "true" : "false" ) + " }";
    }
	
}
