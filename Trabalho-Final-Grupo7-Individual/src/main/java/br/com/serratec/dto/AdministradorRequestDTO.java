package br.com.serratec.dto;

import br.com.serratec.entity.Administrador;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AdministradorRequestDTO {

	@NotBlank(message= "O nome não pode ser um campo nulo.")
	@Size(min = 10, message = "Por favor, informe seu nome e sobrenome.")
	@Size(max = 120, message = "O campo nome comporta no máximo 120 caracteres.")
	private String nome;
	
	@NotBlank(message = "O email não pode ser um campo nulo.")
	@Email
	private String email;
	
	@NotBlank(message = "A senha não pode ser um campo nulo.")
	@Size(min = 8, message = "A senha deve conter no mínimo 8 caracteres.")
	@Size(max = 30, message = "A senha não pode conter mais de 30 caracteres.")
	private String senha;
	
	private String confirmaSenha;
	
	public AdministradorRequestDTO() {
		
	}
	
	public AdministradorRequestDTO(Administrador administrador) {
		this.nome = administrador.getNome();
		this.email = administrador.getEmail();
		this.senha = administrador.getSenha();
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

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	@Override
	public String toString() {
		return "AdministradorRequestDTO [nome=" + nome + ", email=" + email + ", senha=" + senha + ", confirmaSenha="
				+ confirmaSenha + "]";
	}
}
