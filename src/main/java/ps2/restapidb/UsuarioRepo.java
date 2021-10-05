package ps2.restapidb;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepo extends CrudRepository<Usuario, Long>{
	
    @Query("select u from Usuario u where u.matricula = ?1 AND u.senha = ?2")
    Optional<Usuario> logar(int matricula, String senha);
    
    @Query("select u from Usuario u where u.matricula = ?1")
    Optional<Usuario> findByMatricula(int matricula);
}
