package io.github.jpmenezes.faculdade.modules.cursos.services;

import io.github.jpmenezes.faculdade.modules.cursos.entities.Curso;
import io.github.jpmenezes.faculdade.modules.cursos.repositories.ICursoRepository;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    private ICursoRepository repository;

    public CursoService(ICursoRepository repository) {
        this.repository = repository;
    }

    public Curso execute(Curso curso){
        camposNulos(curso);
        nomeJahExistente(curso);
        Curso cursoExiste = repository.findByNome(curso.getNome());

        if(cursoExiste != null){
            throw new Error("Curso já existe!");
        }

        return repository.save(curso);
    }

    private void camposNulos(Curso curso) {
        if (curso == null || curso.getNome() == null || curso.getNome().isEmpty()
                || curso.getCargaHoraria() == null || curso.getCargaHoraria().isEmpty()
                || curso.getDescricao() == null || curso.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("Todos os campos do curso são obrigatórios.");
        }
    }

    private void nomeJahExistente(Curso curso) {
        if (repository.findByNome(curso.getNome()) != null) {
            throw new RuntimeException("Já existe um curso com esse nome.");
        }
    }
}
