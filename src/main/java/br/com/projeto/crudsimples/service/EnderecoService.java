package br.com.projeto.crudsimples.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.crudsimples.model.Endereco;
import br.com.projeto.crudsimples.model.Pessoa;
import br.com.projeto.crudsimples.repository.EnderecoRepository;
import br.com.projeto.crudsimples.repository.PessoaRepository;

@Service
public class EnderecoService {

	@Autowired
    private PessoaRepository pessoaRepository;
	
	@Autowired
    private EnderecoRepository enderecoRepository;
	
	public Endereco adicionarEndereco(Long idPessoa, Endereco endereco) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(idPessoa);
        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            if (pessoa.getEnderecos() == null) {
                pessoa.setEnderecos(new ArrayList<Endereco>());
            }
            pessoa.getEnderecos().add(endereco);
            pessoaRepository.save(pessoa);
            return endereco;
        }
        return null;
    }

    public List<Endereco> buscarEnderecos(Long idPessoa) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(idPessoa);
        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            return pessoa.getEnderecos();
        }
        return null;
    }
    
    public Endereco definirEnderecoPrincipalDaPessoa(Long idPessoa, Long idEndereco) {
    	Optional<Pessoa> pessoaOptional = pessoaRepository.findById(idPessoa);
    	Optional<Endereco> enderecoOptional = enderecoRepository.findById(idPessoa);
        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            Endereco endereco = enderecoOptional.get();
            if (pessoa.getEnderecos() != null) {
                for (Endereco end : pessoa.getEnderecos()) {
                    if (end.getId().equals(idEndereco)) {
                    	end.setPrincipal("OK");
                        enderecoRepository.save(endereco);
                        return end;
                    }
                }
            }
        }
        return null;
    }

    public Endereco atualizarEndereco(Long idPessoa, Long idEndereco, Endereco endereco) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(idPessoa);
        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            if (pessoa.getEnderecos() != null) {
                for (Endereco end : pessoa.getEnderecos()) {
                    if (end.getId().equals(idEndereco)) {
                        end.setLogradouro(endereco.getLogradouro());
                        end.setCep(endereco.getCep());
                        end.setNumero(endereco.getNumero());
                        end.setCidade(endereco.getCidade());
                        pessoaRepository.save(pessoa);
                        return end;
                    }
                }
            }
        }
        return null;
    }
}
