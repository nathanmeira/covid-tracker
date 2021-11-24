package ps2.restapidb;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EstadoSaudeRepo extends CrudRepository<EstadoSaude, Long>{
    @Query("select e from EstadoSaude e where e.usuario.id = ?1 and 1=1")
    Optional<List<EstadoSaude>> findByIdUsuario(long idUsuario);
}
