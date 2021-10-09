package ps2.restapidb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="sintomas")
public class Sintoma {

    @Id @GeneratedValue
    private long id;
    private String nome;

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
