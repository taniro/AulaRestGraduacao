package ufrn.br.aularestgraduacao.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ufrn.br.aularestgraduacao.domain.Usuario;
import ufrn.br.aularestgraduacao.repository.UsuarioRepository;


import java.util.Optional;


@Service
public class UsuarioService implements UserDetailsService {

    UsuarioRepository repository;

    UsuarioService(UsuarioRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optional = repository.findByUsername(username);

        if(optional.isPresent()) {
            return optional.get();
        }

        throw new UsernameNotFoundException("User not found");
    }
}