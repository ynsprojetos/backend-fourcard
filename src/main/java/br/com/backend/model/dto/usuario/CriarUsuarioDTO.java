package br.com.backend.model.dto.usuario;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Schema(name = "CriarUsuario", description = "DTO criar usuário")
public class CriarUsuarioDTO {

    @Schema(description = "Email", example = "email@example.com")
    @NotNull
    @Email
    public String email;

    @Schema(description = "Nome", example = "Fulano de Tal")
    @NotNull
    @Length(min = 6)
    public String nome;

    @Schema(description = "Cpf", example = "00000000000")
    @NotNull
    @Length(min = 11)
    public String cpf;

    @Schema(description = "Sua senha", example = "suasenha")
    @NotNull
    @Length(min = 6)
    public String senha;
}
