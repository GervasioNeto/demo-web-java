package com.example.demo.controller;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.entity.Cliente;
import com.example.demo.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> buscarClientes() {
        return clienteService.findAllClientes();
    }

    @GetMapping("/{id}")
    private ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable Long id){
        return clienteService.findById(id)
                .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    private ClienteDTO criarCliente(@RequestBody Cliente cliente){
        return clienteService.saveCliente(cliente);
    }

    @PutMapping("/{id}")
    private ClienteDTO AtualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        return  clienteService.updateCliente(id, cliente);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> excluirCliente(@PathVariable Long id){
        clienteService.deleteCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
