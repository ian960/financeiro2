package financeiro.demo.pessoa;

import financeiro.demo.pessoa.endereco.Endereco;

public record DadosDetalhamentoPessoa(Long id, String nome, Ativo ativo, Endereco endereco) {
    public DadosDetalhamentoPessoa (Pessoa pessoa) {
        this(pessoa.getId(),
                pessoa.getNome(),
                pessoa.getAtivo(),
                pessoa.getEndereco()  );
    }
}
