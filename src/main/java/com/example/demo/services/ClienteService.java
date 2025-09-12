package com.example.demo.services;


import com.example.demo.dto.ClienteDTO;
import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> findAllClientes(){
        return clienteRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<ClienteDTO> findById(Long id){
        return clienteRepository.findById(id).map(this::toDTO);
    }

    public ClienteDTO saveCliente(Cliente cliente){
        Cliente clienteSaved = clienteRepository.save(cliente);
        return toDTO(clienteSaved);
    }

    public ClienteDTO updateCliente(Long id, Cliente updatedCliente){
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNome(updatedCliente.getNome());
            cliente.setCpf(updatedCliente.getCpf());
            cliente.setEmail(updatedCliente.getEmail());
            cliente.setDataNascimento(updatedCliente.getDataNascimento());
            cliente.setEndereco(updatedCliente.getEndereco());
            cliente.setTelefone(updatedCliente.getTelefone());
            cliente.setSenha(updatedCliente.getSenha());

            Cliente clienteUpdated = clienteRepository.save(cliente);
            return toDTO(clienteUpdated);
        }).orElseThrow(() -> new RuntimeException("Cliente não existe"));
    }

    public void deleteCliente(Long id){
        clienteRepository.deleteById(id);
    }

    //Mapper
    private ClienteDTO toDTO(Cliente cliente){
        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone());
    }
}
