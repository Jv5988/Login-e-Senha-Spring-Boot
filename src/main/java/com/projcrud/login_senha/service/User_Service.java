package com.projcrud.login_senha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projcrud.login_senha.entity.User;
import com.projcrud.login_senha.repository.User_Repository;

@Service
public class User_Service {
	
	@Autowired
	User_Repository userRepository;
	
	private BCryptPasswordEncoder criptoSenha = new BCryptPasswordEncoder();
	
	public List<User> lista_usuarios() {
		List<User> user = userRepository.findAll();
		return user;
	}
	
	public User registrarUsuario(String email, String senha) {
		System.out.println(senha);
		String criptocrafar = criptoSenha.encode(senha);
		 User user = new User(email, criptocrafar);
		 System.out.println(criptocrafar);
		 return userRepository.save(user);
	}
	
	public User AutenticarUsuario(String email, String senha) {
		User user = userRepository.findByEmail(email);
		if(user != null && criptoSenha.matches(senha, user.getSenha())) {
			return user;
		}
			return null;
	}
	
	public User atualizarUsuario(Long id, String nova_senha) {
		User user = userRepository.findById(id).orElse(null);
		if(user != null) {
			user.setSenha(criptoSenha.encode(nova_senha));
			return userRepository.save(user);
		}
			return null;
	}
	
	public void deletar_usuario(Long id) {
		userRepository.deleteById(id);
	}
}
