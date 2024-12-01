package financeiro.demo.pessoa;

import financeiro.demo.pessoa.endereco.Endereco;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "pessoas")
@Entity(name = "Pessoa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;


    @Embedded
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private Ativo ativo;

    public Pessoa(DadosCadastroPessoa dados) {
        this.nome = dados.nome();
        this.ativo = dados.ativo();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes( DadosAtualizacaoPessoa dados) {
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }
}
