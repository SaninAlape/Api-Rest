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
        if (docenteRepository.existsByNumeroDocumento(docente.getNumeroDocumento())) {
            throw new IllegalArgumentException("Ya existe un docente con este número de documento");
        }
        return docenteRepository.save(docente);
    }
    
    public Docente update(Docente docente) {
        var existeDocente = docenteRepository.findById(docente.getId());
        
        if (existeDocente.isPresent()) {
            return docenteRepository.save(docente);
        } else {
            return null;
        }
    }
    
    public boolean delete(int id) {
        try {
            if (docenteRepository.existsById(id)) {
                docenteRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception ex) {
            return false;
        }
    }
}