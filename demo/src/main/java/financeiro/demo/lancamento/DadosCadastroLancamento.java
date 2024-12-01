package financeiro.demo.lancamento;

import java.math.BigDecimal;
import java.util.Date;




public record DadosCadastroLancamento(
        String descricao,
        Date dataVencimento,
        Date dataPagamento,
        BigDecimal valor,
        String observacao,
        TipoLancamento tipo,
        Long pessoaId
) {
}
