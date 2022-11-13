package br.com.backend.model.dto.usuario;

import br.com.backend.model.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;
import java.util.Date;

@Schema(name = "Usuario", description = "DTO usuário")
public class UsuarioDTO implements Serializable {

    @Schema(description = "Id usuário", example = "1")
    public Long id;

    @Schema(description = "Email", example = "email@example.com")
    public String email;

    @Schema(description = "Nome", example = "Fulano de Tal")
    public String nome;

    @Schema(description = "Cpf", example = "000000000-00")
    public String cpf;

    @Schema(description = "Status", example = "true")
    public Boolean status;

    @Schema(description = "Criado em", example = "2022-03-13 19:21:37")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Date createdAt;

    @Schema(description = "Atualizado em", example = "2022-03-13 19:21:37")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Date updatedAt;

    static public UsuarioDTO entityToDTO(Usuario usuario){
        UsuarioDTO response = new UsuarioDTO();
        response.id = usuario.getId();
        response.email = usuario.getEmail();
        response.nome = usuario.getNome();
        response.status = usuario.getStatus();
        response.updatedAt = usuario.getUpdatedAt();
        response.createdAt = usuario.getCreatedAt();
        response.cpf = usuario.getCpf();
        return response;
    }
}
