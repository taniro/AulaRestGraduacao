package ufrn.br.aularestgraduacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.br.aularestgraduacao.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
