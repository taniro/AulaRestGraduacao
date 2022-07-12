package ufrn.br.aularestgraduacao.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import ufrn.br.aularestgraduacao.controller.ClienteController;
import ufrn.br.aularestgraduacao.controller.EnderecoController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@NoArgsConstructor
public class ClienteResponseDto extends RepresentationModel<ClienteResponseDto> {
    //Long id;
    String nome;
    //Boolean admin;
    //Endereco endereco;

    public void addHateoasLinks(Long id ){
        this.add(linkTo(ClienteController.class).slash(id).withSelfRel());
        this.add(linkTo(ClienteController.class).withRel("GET"));
        this.add(linkTo(ClienteController.class).slash(id).withRel("DELETE"));
        this.add(linkTo(ClienteController.class).slash(id).withRel("PUT"));
        this.add(linkTo(ClienteController.class).withRel("POST"));


        //this.getEndereco().add(linkTo(EnderecoController.class).slash(this.getEndereco().getId()).withSelfRel());
        //this.getEndereco().add(linkTo(EnderecoController.class).withRel("allEnderecos"));
        //this.getEndereco().add(linkTo(EnderecoController.class).slash(this.getEndereco().getId()).withRel("delete"));
    }
}
