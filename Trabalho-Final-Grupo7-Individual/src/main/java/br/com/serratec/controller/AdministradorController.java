package br.com.serratec.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.serratec.dto.AdministradorRequestDTO;
import br.com.serratec.dto.AdministradorResponseDTO;
import br.com.serratec.entity.Administrador;
import br.com.serratec.exception.EmailException;
import br.com.serratec.service.AdministradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {
	
	@Autowired
	private AdministradorService service;
	
	@PostMapping
	@Operation(summary="Insere os dados de um administrador.", description="Inserir Administrador.")
	@ApiResponses(value= {
	@ApiResponse(responseCode="201", description="Administrador adcionado."),
	@ApiResponse(responseCode="401", description="Erro de autenticação."),
	@ApiResponse(responseCode="403", description="Não há permissão para acessar o recurso."),
	@ApiResponse(responseCode="404", description="Recurso não encontrado."),
	@ApiResponse(responseCode="505", description="Exceção interna da aplicação."),
	})
	public ResponseEntity<AdministradorResponseDTO> inserir(@RequestBody AdministradorRequestDTO administrador) {
		
		AdministradorResponseDTO a = service.inserir(administrador);
		
		return ResponseEntity.created(null).body(a);
	}
	
	@GetMapping
	@Operation(summary="Lista todos os administradores registrados.", description="Listagem de Administradores.")
	@ApiResponses(value= {
	@ApiResponse(responseCode="200", description="Retorna todos os administradores."),
	@ApiResponse(responseCode="401", description="Erro de autenticação."),
	@ApiResponse(responseCode="403", description="Não há permissão para acessar o recurso."),
	@ApiResponse(responseCode="404", description="Recurso não encontrado."),
	@ApiResponse(responseCode="505", description="Exceção interna da aplicação."),
	})
	public List<AdministradorResponseDTO> listar() {
		
		return service.listar();
	}
	
	@PutMapping("{id}")
	@Operation(summary="Atualiza os dados de um administrador.", description="Atualizar Administrador.")
	@ApiResponses(value= {
	@ApiResponse(responseCode="200", description="Administrador Atualizado."),
	@ApiResponse(responseCode="401", description="Erro de autenticação."),
	@ApiResponse(responseCode="403", description="Não há permissão para acessar o recurso."),
	@ApiResponse(responseCode="404", description="Recurso não encontrado."),
	@ApiResponse(responseCode="505", description="Exceção interna da aplicação."),
	})
	public ResponseEntity<Administrador> atualizar(@PathVariable Long id, @Valid @RequestBody Administrador administrador) {
		
		try {
			administrador = service.atualizar(id, administrador);
			
			return ResponseEntity.ok(administrador);
		} catch (EmailException e) {
			
			return ResponseEntity.unprocessableEntity().body(null);
		} catch (RuntimeException e) {
			
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("{id}")
	@Operation(summary="Deleta um administrador.", description="Deletar Administrador.")
	@ApiResponses(value= {
	@ApiResponse(responseCode="200", description="Administrador Removido."),
	@ApiResponse(responseCode="401", description="Erro de autenticação."),
	@ApiResponse(responseCode="403", description="Não há permissão para acessar o recurso."),
	@ApiResponse(responseCode="404", description="Recurso não encontrado."),
	@ApiResponse(responseCode="505", description="Exceção interna da aplicação."),
	})
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if(service.existsById(id)) {
			service.deletar(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
