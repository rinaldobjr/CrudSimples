package br.com.projeto.crudsimples.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.projeto.crudsimples.model.Endereco;
import br.com.projeto.crudsimples.service.EnderecoService;

public class EnderecoController {


	@Autowired
    private EnderecoService enderecoService;
	
    @PostMapping("/{id}/endereco")
    public ResponseEntity<Endereco> criarEnderecoParaPessoa(@PathVariable Long id, @RequestBody Endereco endereco) {
        Endereco enderecoCriado = enderecoService.adicionarEndereco(id, endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoCriado);
    }
    
    @GetMapping("/{id}/enderecos")
    public ResponseEntity<List<Endereco>> listarEnderecosDaPessoa(@PathVariable Long id) {
        List<Endereco> enderecos = enderecoService.buscarEnderecos(id);
        return ResponseEntity.ok(enderecos);
    }
    
    @PutMapping("/{idPessoa}/endereco/{idEndereco}/principal")
    public ResponseEntity<Endereco> definirEnderecoPrincipalDaPessoa(@PathVariable Long idPessoa, @PathVariable Long idEndereco) {
        Endereco endereco = enderecoService.definirEnderecoPrincipalDaPessoa(idPessoa, idEndereco);
        return ResponseEntity.ok(endereco);
    }
    
    //atualizarEndereco
}
