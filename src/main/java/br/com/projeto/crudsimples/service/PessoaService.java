package br.com.projeto.crudsimples.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.crudsimples.model.Pessoa;
import br.com.projeto.crudsimples.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Optional<Pessoa> buscarPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    public List<Pessoa> buscarTodos() {
        return pessoaRepository.findAll();
    }

    public void deletar(Long id) {
        pessoaRepository.deleteById(id);
    }

    public Pessoa atualizar(Long id, Pessoa pessoa) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        if (pessoaOptional.isPresent()) {
            Pessoa pessoaExistente = pessoaOptional.get();
            pessoaExistente.setNome(pessoa.getNome());
            pessoaExistente.setDataNascimento(pessoa.getDataNascimento());
            pessoaExistente.setEnderecos(pessoa.getEnderecos());
            return pessoaRepository.save(pessoaExistente);
        }
        return null;
    }
    
}
