package ufrn.br.aularestgraduacao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufrn.br.aularestgraduacao.domain.Endereco;
import ufrn.br.aularestgraduacao.service.EnderecoService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Endereco> findAll(){
        return service.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Endereco> findById(@PathVariable Long id){
        Optional<Endereco> e  = service.findById(id);
        if (e.isPresent()){
            return ResponseEntity.ok().body(e.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Endereco> insert(@RequestBody Endereco e) throws URISyntaxException {
        Endereco novo = service.create(e);
        URI uri = new URI("/clientes/" + novo.getId());
        return ResponseEntity.created(uri).build();
    }

    /*
    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public Cliente insert(@RequestBody Cliente c) {
        return service.create(c);
    }*/


    @PutMapping("/{id}")
    public ResponseEntity<Endereco> update (@PathVariable Long id, @RequestBody Endereco e){
        if (service.findById(id).isPresent()){
            Endereco atualizado = service.update(e);
            return ResponseEntity.ok().body(atualizado);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update (@PathVariable Long id, @RequestBody Cliente c){
        return service
                .findById(id)
                .map( record -> {
                    Cliente atualizado = service.update(c);
                    return ResponseEntity.ok().body(atualizado);
                }).orElse(ResponseEntity.notFound().build());
    }*/

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id ){
        if (service.findById(id).isPresent()){
            service.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
