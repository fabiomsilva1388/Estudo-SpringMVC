package br.com.treinaweb.springboot.entidades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Instituicao {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length = 30)
	private String nome;
	
	@Column(length = 100)
	private String endereco;
	
	//Utilzando set pois não permite alunos repetidos na lista
	//uma instituição possui varios alunos
	//mappedBy indica por onde eles se "ligam"
	@OneToMany(mappedBy = "instituicao")
	private Set<Aluno> alunos;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
}
