package ufrn.br.aularestgraduacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.br.aularestgraduacao.domain.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}
