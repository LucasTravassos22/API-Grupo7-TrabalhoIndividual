package br.com.serratec.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.serratec.dto.AdministradorRequestDTO;
import br.com.serratec.dto.AdministradorResponseDTO;
import br.com.serratec.entity.Administrador;
import br.com.serratec.exception.ConfirmaSenhaException;
import br.com.serratec.exception.EmailException;
import br.com.serratec.repository.AdministradorRepository;

@Service
public class AdministradorService {

	@Autowired
	private AdministradorRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public  List<AdministradorResponseDTO> listar() {
		
		List<Administrador> administradores = repository.findAll();
		
		return administradores.stream().map((administrador) -> new AdministradorResponseDTO(administrador)).collect(Collectors.toList());
	}
	
	public AdministradorResponseDTO inserir(AdministradorRequestDTO administrador) {
		
		if (administrador == null) {
	        throw new IllegalArgumentException("O DTO administrador não pode ser nulo");
	    } if (!administrador.getSenha().equals(administrador.getConfirmaSenha())) {
			
			throw new ConfirmaSenhaException("Confirmação de senha incorreta!");
		} if (repository.findByEmail(administrador.getEmail())!=null) {
			
			throw new EmailException("Esse email já existe no banco de dados.");
		}
		
		Administrador a = new Administrador(administrador);
		System.out.println(a.toString());
		a.setSenha(encoder.encode(administrador.getSenha()));
		repository.save(a);
		
		return new AdministradorResponseDTO(a);
	}
	
	public Administrador atualizar(Long id, Administrador administrador) throws EmailException {
		if (!repository.existsById(id)) {
			throw new RuntimeException("Administrador não encontrado.");
		}
		Administrador administradorExistente = repository.findById(id).orElseThrow(() -> new RuntimeException("Administrador não encontrado."));
		if (!administradorExistente.getEmail().equals(administrador.getEmail()) && repository.findByEmail(administrador.getEmail()) != null) {
			throw new EmailException("Esse email já existe no banco de dados.");
		}
		administrador.setId(id);
		administrador.setSenha(encoder.encode(administrador.getSenha()));
		return repository.save(administrador);
	}
	
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
}
