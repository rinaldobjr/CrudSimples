package br.com.projeto.crudsimples.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.crudsimples.model.Pessoa;
import br.com.projeto.crudsimples.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
    private PessoaService pessoaService;
    
    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        Pessoa pessoaCriada = pessoaService.salvar(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaCriada);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Pessoa pessoaAtualizada = pessoaService.atualizar(id, pessoa);
        return ResponseEntity.ok(pessoaAtualizada);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> consultarPessoa(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaService.buscarPorId(id);
        return ResponseEntity.ok(pessoa);
    }
    
    @GetMapping
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        List<Pessoa> pessoas = pessoaService.buscarTodos();
        return ResponseEntity.ok(pessoas);
    }
    
}
