package br.com.cajumobile.delivery.service;

import br.com.cajumobile.delivery.model.Cliente;
import br.com.cajumobile.delivery.model.enun.StatusCliente;
import br.com.cajumobile.delivery.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvarCliente(Cliente cliente) {
        if (cliente.getStatus() == null) {
            cliente.setStatus(StatusCliente.ATIVO);
        }
        return clienteRepository.save(cliente);
    }

}
