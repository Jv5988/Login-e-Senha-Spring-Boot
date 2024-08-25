package com.projcrud.login_senha.controllers;

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

import com.projcrud.login_senha.entity.User;
import com.projcrud.login_senha.service.User_Service;

@RestController
@RequestMapping("/")
public class User_Controller {
	
	@Autowired
	User_Service userService;
	
	@GetMapping("/")
	public ResponseEntity<List<User>> lista_usuarios(){
		List<User> user = userService.lista_usuarios();
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> registrar_usario(@RequestBody User user){
		User novo_user = userService.registrarUsuario(user.getEmail(), user.getSenha());
		return ResponseEntity.ok(novo_user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user){
		User autenticar = userService.AutenticarUsuario(user.getEmail(), user.getSenha());
		if(autenticar != null) {
			return ResponseEntity.ok("Login Realizado");
		}
			return ResponseEntity.status(404).body("Email ou Senha inválida");
	}		
	
	@PutMapping("/{id}/atualizar_senha")
	public ResponseEntity<String> atualizar_usuario(@PathVariable Long id, @RequestBody String nova_senha){
		User atualizar = userService.atualizarUsuario(id, nova_senha);
		if(atualizar != null) {
			return ResponseEntity.ok("Login Atualizado");
		}
			return ResponseEntity.status(404).body("Não foi atualizado");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar_usuario(@PathVariable Long id){
		userService.deletar_usuario(id);
		 return ResponseEntity.noContent().build(); //Usado normalmente em exclusão, pois não retorna nada
	}

}
