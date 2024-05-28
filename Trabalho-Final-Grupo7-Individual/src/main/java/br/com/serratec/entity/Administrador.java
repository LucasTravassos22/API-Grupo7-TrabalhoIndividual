package br.com.serratec.entity;

import br.com.serratec.dto.AdministradorRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Administrador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message= "O nome não pode ser um campo nulo.")
	@Size(min = 10, message = "Por favor, informe seu nome e sobrenome.")
	@Size(max = 120, message = "O campo nome comporta no máximo 120 caracteres.")
	private String nome;
	
	@NotBlank(message = "O email não pode ser um campo nulo.")
	@Email
	private String email;
	
	@NotBlank(message = "A senha não pode ser um campo nulo.")
	@Size(min = 8, message = "A senha deve conter no mínimo 8 caracteres.")
	private String senha;
	
	public Administrador() {
		super();
	}

	public Administrador(AdministradorRequestDTO administrador) {
		this.nome = administrador.getNome();
		this.email = administrador.getEmail();
		this.senha = administrador.getSenha();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Administrador [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + "]";
	}
}
