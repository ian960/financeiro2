package financeiro.demo.pessoa;

public record DadosListagemPessoa(Long id,
                                  String nome,
                                  Ativo ativo) {

    public DadosListagemPessoa(Pessoa pessoa){
        this(pessoa.getId(), pessoa.getNome(), pessoa.getAtivo());
    }
}
