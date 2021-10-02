package wsrest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="usuarios")
public class Usuario {
	@Id @GeneratedValue
	private long id;
	private String nome;
	private String setor;
	private String tipo;
	private String senha;
	private String dataNasc;
	private String cidade;
	private String estado;
	public Usuario() {

	}

	public Usuario(long id, String nome, String setor, String tipo, String senha, String dataNasc, String cidade, String estado) {
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.setor = setor;
		this.senha = senha;
		this.dataNasc = dataNasc;
		this.cidade = cidade;
		this.estado = estado;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	
}
