package ufrn.br.aularestgraduacao.service;

import org.springframework.stereotype.Service;
import ufrn.br.aularestgraduacao.domain.Endereco;
import ufrn.br.aularestgraduacao.repository.EnderecoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }

    public Endereco create(Endereco e){
        return repository.save(e);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Endereco update(Endereco e){
        return repository.saveAndFlush(e);
    }

    public Optional<Endereco> findById(Long id){
        return repository.findById(id);
    }

    public List<Endereco> findAll(){
        return repository.findAll();
    }
}
