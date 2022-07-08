package ufrn.br.aularestgraduacao.service;


import org.springframework.stereotype.Service;
import ufrn.br.aularestgraduacao.domain.Cliente;
import ufrn.br.aularestgraduacao.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente create(Cliente c){
        return repository.save(c);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Cliente update(Cliente c){
        return repository.saveAndFlush(c);
    }

    public Optional<Cliente> findById(Long id){
        return repository.findById(id);
    }

    public List<Cliente> findAll(){
        return repository.findAll();
    }
}
