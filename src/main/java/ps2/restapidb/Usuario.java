package ps2.restapidb;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue
    private long id;
    private int matricula;
    private String nome;
    private String setor;
    private String senha;
    private String dataNasc;
    private String cidade;
    private String estado;
    private Long id_tipo_usuario;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private TipoUsuario tipoUsuario;

    public Usuario() {

    }

    public Usuario(long id, String nome, String setor, String senha, String dataNasc, String cidade, String estado, Long id_tipo_usuario, int matricula) {
        this.id = id;
        this.nome = nome;
        this.setor = setor;
        this.senha = senha;
        this.dataNasc = dataNasc;
        this.cidade = cidade;
        this.estado = estado;
        this.id_tipo_usuario = id_tipo_usuario;
        this.matricula = matricula;
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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipo) {
        this.tipoUsuario = tipo;
    }

    public Long getIdTipoUsuario() {
        return id_tipo_usuario;
    }

    public void setIdTipoUsuario(Long id) {
        this.id_tipo_usuario = id;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

}
