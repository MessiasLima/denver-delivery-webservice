package br.com.cajumobile.delivery.repository;

import br.com.cajumobile.delivery.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
