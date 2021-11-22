package ps2.restapidb;

import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity

@Table(name="sintomas")
public class Sintoma {

    @Id @GeneratedValue
    private long id;
    private String nome;

    @ManyToMany
    @JoinTable(
        name = "estado_saude_sintomas",
        joinColumns = @JoinColumn(name = "sintoma_id"),
        inverseJoinColumns = @JoinColumn(name = "estado_saude_id")
    )
    private Set<EstadoSaude> estadosSaude;

    public Sintoma() {

    }

    public Sintoma(long id, String nome) {
            this.id = id;
            this.nome = nome;
    }

    public long getId() {
            return id;
    }

    public void setId(long id) {
            this.id = id;
    }

    public String getNome() {
            return nome;
    }

    public void setNome(String nome) {
            this.nome = nome;
    }	
}
