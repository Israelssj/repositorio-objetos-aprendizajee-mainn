package com.itst.repositorio_objetos_aprendizaje.service;

import com.itst.repositorio_objetos_aprendizaje.model.Usuario;
import com.itst.repositorio_objetos_aprendizaje.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Registrar usuario verificando si el correo ya existe
    public Usuario registerUsuario(Usuario usuario) {
        Optional<Usuario> existingUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        if (existingUsuario.isPresent()) {
            throw new IllegalArgumentException("El correo ya está registrado.");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }


    public Usuario saveOrUpdate(Usuario usuario) {
        if (usuario.getIdUsuario() != null) {
            Optional<Usuario> existingUser = usuarioRepository.findById(usuario.getIdUsuario());
            if (existingUser.isPresent()) {
                String existingPassword = existingUser.get().getPassword();
                String newPassword = usuario.getPassword();
                if (!passwordEncoder.matches(newPassword, existingPassword)) {
                    usuario.setPassword(passwordEncoder.encode(newPassword));
                } else {
                    usuario.setPassword(existingPassword);
                }
            }
        } else {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    public boolean existsById(Integer idUsuario) {
        return usuarioRepository.existsById(idUsuario);
    }

    public void deleteById(Integer idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }
}
