package ufrn.br.aularestgraduacao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco extends RepresentationModel<Endereco> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String rua;
}
