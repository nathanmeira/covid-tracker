package ps2.restapidb;

import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private TipoUsuarioRepo tipoUsuarioRepo;

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
    public String createUsuario(@RequestBody Usuario usuario) {
        Optional<TipoUsuario> optionTipoUsuario = tipoUsuarioRepo.findById(usuario.getIdTipoUsuario());
        if (!optionTipoUsuario.isPresent()) {
            return (
               "{ \"success\": false, \"usuario_id\": 0, \"usuario_tipo_id\": 0 }" 
            );
        }
        Optional<Usuario> optionUsu = usuarioRepo.findByMatricula(usuario.getMatricula());
      
        if(optionUsu.isPresent()){
            Usuario u = optionUsu.get();
            return (
               "{ \"success\": false, \"usuario_id\": "+ u.getId() +", \"usuario_tipo_id\": "+ u.getIdTipoUsuario()+ " }"
            );
        }        
        
        TipoUsuario l = optionTipoUsuario.get();
        usuario.setTipoUsuario(l);
        usuario.setSenha(usuario.getMatricula() + "");
    	usuarioRepo.save(usuario);
        return (
            "{ \"success\": true, \"usuario_id\": "+ usuario.getId() +", \"usuario_tipo_id\": "+ usuario.getIdTipoUsuario()+ " }"
        );
    }
    
    @PostMapping("/api/usuarios/csv")
    public ArrayList<Usuario> createUsuario(@RequestParam("file") MultipartFile file) {        
        try {
            LerCSV csv = new LerCSV();
            ArrayList<Usuario> usuarios =  csv.lerUsuario(file.getInputStream());
            for (Usuario u : usuarios) {
                Optional<Usuario> optionUsu = usuarioRepo.findByMatricula(u.getMatricula());
                if(optionUsu.isPresent()){
                    continue;
                }
                Optional<TipoUsuario> optionTipoUsuario = tipoUsuarioRepo.findById(u.getIdTipoUsuario());
                if (!optionTipoUsuario.isPresent()) {
                    continue;
                }
                TipoUsuario l = optionTipoUsuario.get();
                u.setTipoUsuario(l);
                u.setSenha(u.getMatricula() + "");
                usuarioRepo.save(u);
            }
            return usuarios;
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
    
    @PostMapping("/api/usuarios/logar")
    public Usuario logar(@RequestBody Usuario usuario) {
        Optional<Usuario> optionUsu = usuarioRepo.logar(usuario.getMatricula(), usuario.getSenha());
        Usuario usuEncontrado = null;
        if(optionUsu.isPresent()) {
            usuEncontrado = optionUsu.get();
        }
        return usuEncontrado;
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
