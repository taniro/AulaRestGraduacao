package ufrn.br.aularestgraduacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.br.aularestgraduacao.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
