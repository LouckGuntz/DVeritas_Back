package com.dveritas.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dveritas.model.Usuario;
import com.dveritas.service.UsuarioService;


@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })

@RestController

@RequestMapping(path = "/dveritas/usuarios/") 

public class UsuarioController {

	private final UsuarioService usuarioService;
	

	public UsuarioController (UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping
	public List<Usuario> getUsuarios(){
		return usuarioService.leerUsuarios();
	}
	
	@GetMapping(path="{id}")
	public Usuario getUsuario(@PathVariable("id")Long id) {
		return usuarioService.leerUsuario(id);
	}
	
	@PostMapping
    public ResponseEntity<Map<String, String>> registrarUsuario(@RequestBody Usuario usuario) {
        Map<String, String> respuesta = new HashMap<>();
        try {
            usuarioService.crearUsuario(usuario);
            respuesta.put("mensaje", "Usuario registrado exitosamente");
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            respuesta.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(respuesta);
        }
    }
	
	@PutMapping(path = "{Id}")
	public void updateUsuario(@PathVariable("Id") Long Id, @RequestParam(required = false) String nombre,
			@RequestParam(required = false) String correo, @RequestParam(required = false) String password,
			@RequestParam(required = false) String avatar) {

		usuarioService.actualizarUsuario(Id, nombre, correo, password, avatar);
	}

	  @GetMapping("totalusuarios")
	   public long totalUsuarios() {
	        return usuarioService.numeroUsuarios();
	    }

}