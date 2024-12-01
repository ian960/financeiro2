package financeiro.demo.lancamento;

import java.math.BigDecimal;
import java.util.Date;

public record DadosListagemLancamento(
        Long id,
        String descricao,
        Date dataVencimento,
        BigDecimal valor,
        TipoLancamento tipo
) {
    public DadosListagemLancamento(Lancamento lancamento) {
        this(
                lancamento.getId(),
                lancamento.getDescricao(),
                lancamento.getDataVencimento(),
                lancamento.getValor(),
                lancamento.getTipo()
        );
    }
}
