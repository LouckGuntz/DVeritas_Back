package com.dveritas.service;

import com.dveritas.repository.PublicacionRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dveritas.model.Publicacion;
import com.dveritas.model.Usuario;

@Service

public class PublicacionService {
	
	 @Autowired
	private UsuarioService usuarioService;

	private final PublicacionRepository publicacionRepository;

	@Autowired
	public PublicacionService(PublicacionRepository publicacionRepository) {

		this.publicacionRepository = publicacionRepository;
		

	}

	public List<Publicacion> leerPublicaciones() {

		return publicacionRepository.findAll();

	}

	public Publicacion leerPublicacion(Long Id) {

		return publicacionRepository.findById(Id)
				.orElseThrow(() -> new IllegalStateException("El Producto " + "con el id " + Id + " no existe."));

	}

	public void crearPublicacion(Publicacion publicacion) {
		 Usuario usuario = usuarioService.leerUsuario(publicacion.getUsuario().getId());
		    Date fecha = new Date(); // fecha actual
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	        String fechaStr = formatter.format(fecha);
	        publicacion.setFecha_publicacion(fechaStr);
		    publicacion.setUsuario(usuario);
		    publicacionRepository.save(publicacion);
	}

	public void borrarPublicacion(Long id) {
		if (publicacionRepository.existsById(id)) {
			publicacionRepository.deleteById(id);
		}
	}

	public long numeroPublicaciones() {
        return publicacionRepository.contadorPublicaciones();
    }
	
	public List<Publicacion> leerPublicacionUsuario(Long Id) {
        return publicacionRepository.findByUsuarioId(Id);
    }
}
