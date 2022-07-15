package ufrn.br.aularestgraduacao.controller;


import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufrn.br.aularestgraduacao.domain.Cliente;
import ufrn.br.aularestgraduacao.dto.cliente.ClienteRequestDTO;
import ufrn.br.aularestgraduacao.dto.cliente.ClienteResponseDTO;
import ufrn.br.aularestgraduacao.service.ClienteService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    ClienteService service;
    ModelMapper modelMapper = new ModelMapper();


    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cliente> findAll(){
        return service.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<ClienteResponseDTO> findById(@PathVariable Long id){
        Optional<Cliente> c  = service.findById(id);
        if (c.isPresent()){
            Cliente cliente = c.get();
            ClienteResponseDTO clienteResponseDto = modelMapper.map(cliente, ClienteResponseDTO.class);
            clienteResponseDto.addHateoasLinks(cliente.getId());
            /*
            cliente.add(linkTo(ClienteController.class).slash(cliente.getId()).withSelfRel());
            cliente.add(linkTo(ClienteController.class).withRel("GET"));
            cliente.add(linkTo(ClienteController.class).slash(cliente.getId()).withRel("DELETE"));
            cliente.add(linkTo(ClienteController.class).slash(cliente.getId()).withRel("PUT"));
            cliente.add(linkTo(ClienteController.class).withRel("POST"));

            cliente.getEndereco().add(linkTo(EnderecoController.class).slash(cliente.getEndereco().getId()).withSelfRel());
            cliente.getEndereco().add(linkTo(EnderecoController.class).withRel("allEnderecos"));
            cliente.getEndereco().add(linkTo(EnderecoController.class).slash(cliente.getEndereco().getId()).withRel("delete"));

             */
            return ResponseEntity.ok().body(clienteResponseDto);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> insert(@RequestBody ClienteRequestDTO c) throws URISyntaxException {


        Cliente novo = modelMapper.map(c, Cliente.class);

        /*
        Cliente novo = new Cliente();
        novo.setNome(c.getNome());
        novo.setEndereco(c.getEndereco());
         */

        service.create(novo);

        //Cliente novo = service.create(c);
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
