package com.example.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.Docente;
import com.example.demo.repositories.DocenteRepository;

@Service
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    public List<Docente> getAll() {
        return (List<Docente>) docenteRepository.findAll();
    }

    public Docente save(Docente docente) {
        if (isTipoDocumentoAndNumeroDocumentoExists(docente.getTipoDocumento(), docente.getNumeroDocumento())) {
            throw new IllegalArgumentException(" Ya existe un docente con  este mismo número de documento");
        }
        return docenteRepository.save(docente);
    }

    public Docente update(Docente docente) {
        if (!docenteRepository.existsById(docente.getId())) {
            throw new IllegalArgumentException("Docente no existe");
        }
        if (isTipoDocumentoAndNumeroDocumentoExists(docente.getTipoDocumento(), docente.getNumeroDocumento())) {
            throw new IllegalArgumentException(" Ya existe un docente con este mismo número de documento");
        }
        return docenteRepository.save(docente);
    }

    public boolean delete(int id) {
        if (!docenteRepository.existsById(id)) {
            throw new IllegalArgumentException("Docente no existe");
        }
        try {
            docenteRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private boolean isTipoDocumentoAndNumeroDocumentoExists(String tipoDocumento, String numeroDocumento) {
        return docenteRepository.existsByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento);
    }
}
