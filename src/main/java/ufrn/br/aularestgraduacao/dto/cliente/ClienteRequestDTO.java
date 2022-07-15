package ufrn.br.aularestgraduacao.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufrn.br.aularestgraduacao.domain.Endereco;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDTO {
    String nome;
    Endereco endereco;
}
