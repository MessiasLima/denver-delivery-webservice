package br.com.cajumobile.delivery.repository;

import br.com.cajumobile.delivery.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagamentoRespository extends JpaRepository<FormaPagamento, Integer> {
}
