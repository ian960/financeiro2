package financeiro.demo.lancamento;

import financeiro.demo.pessoa.Pessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "lancamentos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Lancamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Date dataVencimento;
    private Date dataPagamento;
    private BigDecimal valor;
    private String observacao;

    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;

    @OneToOne
    @JoinColumn(name = "pessoa_id", nullable = false) // Define a chave estrangeira
    private Pessoa pessoa;

    public Lancamento(DadosCadastroLancamento dados, Pessoa pessoa) {
        this.descricao = dados.descricao();
        this.dataVencimento = dados.dataVencimento();
        this.dataPagamento = dados.dataPagamento();
        this.valor = dados.valor();
        this.observacao = dados.observacao();
        this.tipo = dados.tipo();
        this.pessoa = pessoa;
    }

    public void atualizarInformacoes(DadosAtualizacaoLancamento dados) {
        if (dados.descricao() != null) this.descricao = dados.descricao();
        if (dados.dataVencimento() != null) this.dataVencimento = dados.dataVencimento();
        if (dados.dataPagamento() != null) this.dataPagamento = dados.dataPagamento();
        if (dados.valor() != null) this.valor = dados.valor();
        if (dados.observacao() != null) this.observacao = dados.observacao();
        if (dados.tipo() != null) this.tipo = dados.tipo();
        if (pessoa != null) this.pessoa = pessoa;
    }
}
