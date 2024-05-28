package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.serratec.entity.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long>{

	//metodos pra buscar pelo email e nome
	Administrador findByEmail(String email);
	Administrador findByNome(String nome);
}
