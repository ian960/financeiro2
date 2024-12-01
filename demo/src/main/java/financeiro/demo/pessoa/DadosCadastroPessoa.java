package financeiro.demo.pessoa;

import financeiro.demo.pessoa.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DadosCadastroPessoa(
        @NotBlank
        String nome,
        @NotNull
        @Valid
        DadosEndereco endereco,
        @NotNull
        Ativo ativo) {
}
