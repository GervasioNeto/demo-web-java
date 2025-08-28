package com.example.demo.services;

import com.example.demo.entity.Aluno;
import com.example.demo.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> findAllAlunos(){
        return alunoRepository.findAll();
    }

    public Optional<Aluno> findById(Long id){
        return alunoRepository.findById(id);
    }

    public Aluno saveAluno(Aluno aluno){
        return alunoRepository.save(aluno);
    }

    public void deleteAluno(Long id){
        alunoRepository.deleteById(id);
    }

    public Aluno updateAluno(Long id, Aluno updatedAluno){
        return alunoRepository.findById(id).map(aluno -> {
            aluno.setNome(updatedAluno.getNome());
            aluno.setEmail(updatedAluno.getEmail());
            aluno.setSenha(updatedAluno.getSenha());
            aluno.setDataNascimento(updatedAluno.getDataNascimento());
            return alunoRepository.save(aluno);
        }).orElseThrow(() -> new RuntimeException("Aluno não existe"));
    }
}
