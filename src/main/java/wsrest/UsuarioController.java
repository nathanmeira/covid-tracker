package wsrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {
	@Autowired
	private UsuarioRepo usuarioRepo;
	
	@GetMapping("/api/usuarios")
	public List<Usuario> getUsuarios(){
		List<Usuario> usuarios = new ArrayList<>();
        usuarioRepo.findAll().forEach(usuarios::add);
        return usuarios;
	}
	
	@GetMapping("/api/usuarios/{id}")
    public Usuario getUsuario(@PathVariable long id) {
        Optional<Usuario> retorno = usuarioRepo.findById(id);
        Usuario usuario = null;
        if(retorno.isPresent()) {
        	usuario = retorno.get();
        }
        return usuario;
    }

    @PostMapping("/api/usuarios")
    public Usuario createAssociado(@RequestBody Usuario usuario) {
    	usuarioRepo.save(usuario);
        return usuario;
    }

    @PutMapping("/api/usuarios/{id}")
    public Usuario updateUsuario(@RequestBody Usuario a, @PathVariable long id) {
        Usuario usuario = null;
    	usuario = this.getUsuario(id);
        if(usuario != null) {
        	usuarioRepo.save(a);
        	usuario = a;
        }
        return a;
    }

    @DeleteMapping(value = "/api/usuarios/{id}", produces = "application/json")
    public String deleteUsuario(@PathVariable long id) {
        Usuario usuario = this.getUsuario(id);
        boolean result = false;
        if(usuario != null) {
        	usuarioRepo.delete(usuario);
            result = true;
        }
        return "{ \"sucess\" : " + (result ? "true" : "false" ) + " }";
    }
	
}
