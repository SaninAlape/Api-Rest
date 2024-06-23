package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.models.Estudianet;
import com.example.demo.repositories.EstudianteRepository;


@Service
public class EstudianteService {
	
	@Autowired
	private EstudianteRepository  estudianteRepository;
	
	public List<Estudianet>getall(){
		
		return (List<Estudianet>)estudianteRepository.findAll();
	}
	
	public Estudianet save(Estudianet estudiante) {
		return estudianteRepository.save(estudiante);	
	}
	
	
	public Estudianet update(Estudianet estudianet) {
		
		var existeEstudiante = estudianteRepository.findById(estudianet.getId());
		
		
		if(!existeEstudiante.isEmpty()) {
			return estudianteRepository.save(estudianet);
		}else {
			return null;
		}
		
		
	}
	

	public boolean delete(int id) {
		
		try {
			if(estudianteRepository.existsById(id)) {
				
				estudianteRepository.deleteById(id);
				return true;
			}
			
			return false;
		}catch(Exception ex) {
			
			return false;
		}
		
		
	}
	
	
}
