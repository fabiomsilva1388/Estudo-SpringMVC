package br.com.treinaweb.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.springboot.entidades.Aluno;
import br.com.treinaweb.springboot.entidades.Instituicao;
import br.com.treinaweb.springboot.repositorios.RepositorioAluno;
import br.com.treinaweb.springboot.repositorios.RepositorioInstituicao;

@Controller
@RequestMapping("/alunos")
public class AlunosController {

	@Autowired
	private RepositorioAluno repositorioAluno;
	
	
	@Autowired
	private RepositorioInstituicao repositorioIntituicao;
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView resultado = new ModelAndView("aluno/index");
		resultado.addObject("alunos", repositorioAluno.findAll());
		return resultado;
	}
	
	@GetMapping("/inserir")
	public ModelAndView inserir() {
		ModelAndView resultado = new ModelAndView("aluno/inserir");
		//adiciona novo aluno fora pois possui o objeto instituição 
		Aluno aluno = new Aluno();
		//evita o nullPointer 
		aluno.setInstituicao(new Instituicao());
		resultado.addObject("aluno", aluno);
		//Atraves dele que vamos criar um select com todas as opções de instituições
		resultado.addObject("instituicoes", repositorioIntituicao.findAll());
		return resultado;
	}
	
	@PostMapping("/inserir")
	public String inserir(Aluno aluno) {
		repositorioAluno.save(aluno);
		return "redirect:/alunos/index";
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		Aluno aluno = repositorioAluno.getOne(id);
		ModelAndView resultado = new ModelAndView("aluno/editar");
		resultado.addObject("aluno", aluno);
		resultado.addObject("instituicoes", repositorioIntituicao.findAll());
		return resultado;
	}
	
	@PostMapping("/editar")
	public String editar(Aluno aluno) {
		repositorioAluno.save(aluno);
		return "redirect:/alunos/index";
	}
	
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		repositorioAluno.deleteById(id);
		return "redirect:/alunos/index";
	}
}
