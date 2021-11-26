package ps2.restapidb;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EstadoSaudeRepo extends CrudRepository<EstadoSaude, Long>{
    @Query("select e from EstadoSaude e where e.usuario.id = ?1")
    Optional<List<EstadoSaude>> findByIdUsuario(long idUsuario);
    
    @Query("select e from EstadoSaude e where e.usuario.id = ?1 AND DATE(e.dtCriacao) >= ?2 ORDER BY e.dtCriacao ASC")
    Optional<List<EstadoSaude>> findByIdUsuarioAndInitialDate(long idUsuario, Date initial);
    
    @Query("select e from EstadoSaude e where e.usuario.id = ?1 AND (DATE(e.dtCriacao) >= ?2 AND DATE(e.dtCriacao) <= ?3) AND 1=1 ORDER BY e.dtCriacao ASC")
    Optional<List<EstadoSaude>> findByIdUsuarioAndInitiallEndDate(long idUsuario, Date initial, Date end);
}
