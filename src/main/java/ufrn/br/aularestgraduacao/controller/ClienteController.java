package ufrn.br.aularestgraduacao.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufrn.br.aularestgraduacao.domain.Cliente;
import ufrn.br.aularestgraduacao.service.ClienteService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cliente> findAll(){
        return service.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        Optional<Cliente> c  = service.findById(id);
        if (c.isPresent()){
            return ResponseEntity.ok().body(c.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> insert(@RequestBody Cliente c) throws URISyntaxException {
        Cliente novo = service.create(c);
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
    public ResponseEntity<Cliente> update (@PathVariable Long id, @RequestBody Cliente c){
        if (service.findById(id).isPresent()){
            Cliente atualizado = service.update(c);
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
