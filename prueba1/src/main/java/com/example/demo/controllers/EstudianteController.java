package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.Estudianet;
import com.example.demo.models.Dto.Response;
import com.example.demo.services.EstudianteService;


@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
	
	@Autowired
	private EstudianteService  estudianteService;
	
	@GetMapping
	public ResponseEntity<List<Estudianet>> getAll(){	
		return ResponseEntity.ok(estudianteService.getall());
	}
	
	@PostMapping
	public ResponseEntity<Estudianet> save(@RequestBody Estudianet estudianet){
		return ResponseEntity.ok(estudianteService.save(estudianet));
		
	}
	
	@PutMapping
	public ResponseEntity<Response> update(@RequestBody Estudianet estudianet){
		
		var actualizarEstudiante = estudianteService.update(estudianet);
		
		if(actualizarEstudiante == null) {
		
			return ResponseEntity.ok(new Response<>("Estudiante no existe"));
		}else {
			
			return ResponseEntity.ok(new Response<>("Estudiante Actualizado",actualizarEstudiante.getId()));

		}
				
	}
	
	@DeleteMapping("/{id}")
	
	public ResponseEntity<Response>delete(@PathVariable("id") int id) {
	
	if (estudianteService.delete(id)) {
		return ResponseEntity.ok(new Response<>("Estudiante Eliminado"));
	}else {
		return ResponseEntity.ok(new Response<>("Estudiante no se elimino"));
	}
	
	}	
}
