package com.example.demo.services;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.entity.Endereco;
import com.example.demo.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EndrecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> findAllEnderecos(){
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> findById(Long id){
        return enderecoRepository.findById(id);
    }

    public Endereco saveEndereco(Endereco endereco){
        return enderecoRepository.save(endereco);
    }

    public void deleteEndereco(Long id){
        enderecoRepository.deleteById(id);
    }

    public Endereco updateEndereco(Long id, Endereco updatedEndereco){
        return enderecoRepository.findById(id)
                .map( endereco -> {
                    endereco.setLogradouro(updatedEndereco.getLogradouro());
                    endereco.setNumero(updatedEndereco.getNumero());
                    endereco.setComplemento(updatedEndereco.getComplemento());
                    endereco.setCep(updatedEndereco.getCep());

                    return enderecoRepository.save(endereco);
                })
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado."));
    }

}
