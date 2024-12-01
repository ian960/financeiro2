package financeiro.demo.pessoa;

import financeiro.demo.pessoa.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPessoa(@NotNull
                                     Long id,
                                     String nome,
                                     DadosEndereco endereco) {

}
