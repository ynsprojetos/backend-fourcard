package br.com.backend.service;


import br.com.backend.model.dto.usuario.AtualizarUsuarioDTO;
import br.com.backend.model.dto.usuario.CriarUsuarioDTO;
import br.com.backend.model.dto.usuario.UsuarioDTO;
import br.com.backend.model.entity.Usuario;
import br.com.backend.model.dto.Message;
import br.com.backend.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> list() {
        List<UsuarioDTO> list = usuarioRepository.findAlive()
                .stream()
                .map(UsuarioDTO::entityToDTO)
                .collect(Collectors.toList());
        return list;
    }

    public UsuarioDTO findById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findByIdOptional(id);

        if (usuario.isEmpty()) {
            throw new WebApplicationException(Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new Message("Usuário não encontrado!", Response.Status.NOT_FOUND.getStatusCode()))
                    .build()
            );
        }

        return UsuarioDTO.entityToDTO(usuario.get());
    }

    public UsuarioDTO create(CriarUsuarioDTO criarUsuario) {
        Usuario usuario = new Usuario();
        usuario.setNome(criarUsuario.nome);
        usuario.setEmail(criarUsuario.email);

        String salt = BCrypt.gensalt();
        String senha = BCrypt.hashpw(criarUsuario.senha, salt) ;

        usuario.setSenha(senha);

        Optional<Usuario> usuarioExiste = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioExiste.isPresent()) {
            throw new WebApplicationException(Response
                    .status(Response.Status.CONFLICT)
                    .entity(new Message("Usuário já existe!", Response.Status.CONFLICT.getStatusCode()))
                    .build()
            );
        }

        usuarioRepository.persist(usuario);

        return UsuarioDTO.entityToDTO(usuario);
    }

    public UsuarioDTO update(AtualizarUsuarioDTO atualizarUsuario, Long id) {

        Usuario usuario = usuarioRepository.findById(id);

        if (Objects.isNull(usuario)) {
            throw new WebApplicationException(Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new Message("Usuário não encontrado!", Response.Status.NOT_FOUND.getStatusCode()))
                    .build()
            );
        }

        usuario.setNome(atualizarUsuario.nome);
        usuario.setSenha(atualizarUsuario.senha);

        usuarioRepository.persist(usuario);
        return UsuarioDTO.entityToDTO(usuario);
    }

    public void delete(Long id) {

        Usuario usuario = usuarioRepository.findById(id);

        if (Objects.isNull(usuario)) {
            throw new WebApplicationException(Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new Message("Usuário não encontrado!", Response.Status.NOT_FOUND.getStatusCode()))
                    .build()
            );
        }

        usuarioRepository.delete(usuario);
    }
}
