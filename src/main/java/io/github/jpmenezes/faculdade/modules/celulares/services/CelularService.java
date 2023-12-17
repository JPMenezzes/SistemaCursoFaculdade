package io.github.jpmenezes.faculdade.modules.celulares.services;

import io.github.jpmenezes.faculdade.modules.celulares.entities.Celular;
import io.github.jpmenezes.faculdade.modules.celulares.repositories.ICelularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CelularService {

    private final ICelularRepository celularRepository;

    @Autowired
    public CelularService(ICelularRepository celularRepository) {
        this.celularRepository = celularRepository;
    }

    public Celular execute(Celular celular) {
        validarCelular(celular);
        // Adicione outras validações conforme necessário

        // Se tudo estiver válido, salvar o celular
        return celularRepository.save(celular);
    }

    // Adicione outras validações e lógicas conforme necessário

    private void validarCelular(Celular celular) {
        if (celular.getNumero() == null || celular.getEstudante() == null) {
            throw new IllegalArgumentException("Número de celular e estudante são obrigatórios.");
        }
    }
}
