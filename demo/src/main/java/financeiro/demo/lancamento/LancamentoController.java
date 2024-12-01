package financeiro.demo.lancamento;

import financeiro.demo.pessoa.PessoaRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("lancamentos")
@SecurityRequirement(name = "bearer-key")
public class LancamentoController {
    @Autowired
    private LancamentoRepository repository;


    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(
            @RequestBody @Valid DadosCadastroLancamento dados,
            UriComponentsBuilder uriBuilder) {

        // Buscando a pessoa com base no ID
        var pessoa = pessoaRepository.findById(dados.pessoaId())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        var lancamento = new Lancamento(dados, pessoa);
        repository.save(lancamento);
        var uri = uriBuilder.path("/lancamentos/{id}").buildAndExpand(lancamento.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoLancamento(lancamento));
    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemLancamento>> listar(Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemLancamento::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoLancamento> atualizar(@RequestBody @Valid DadosAtualizacaoLancamento dados) {
        // Recupera o lançamento pelo ID
        var lancamento = repository.getReferenceById(dados.id());

        // Atualiza as informações do lançamento
        lancamento.atualizarInformacoes(dados);

        // Retorna a resposta com os dados detalhados do lançamento
        return ResponseEntity.ok(new DadosDetalhamentoLancamento(lancamento));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var lancamento = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoLancamento(lancamento));
    }
}
