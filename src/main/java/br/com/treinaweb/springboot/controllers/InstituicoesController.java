package br.com.treinaweb.springboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.springboot.entidades.Instituicao;
import br.com.treinaweb.springboot.repositorios.RepositorioInstituicao;

@Controller
@RequestMapping("/instituicoes")
public class InstituicoesController {
	
	@Autowired
	private RepositorioInstituicao repositorioInstituicao;
	
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView resultado = new ModelAndView("instituicao/index");
		List<Instituicao> instituicoes = repositorioInstituicao.findAll();
		resultado.addObject("instituicoes", instituicoes);
		
		return resultado;
	}
	
	@GetMapping("/inserir")
	public ModelAndView inserir() {
		ModelAndView resultado = new ModelAndView("instituicao/inserir");
		resultado.addObject("instituicao", new Instituicao());
		return resultado;
	}
	
	
	@PostMapping("/inserir")
	public String inserir(Instituicao instituicao) {
		repositorioInstituicao.save(instituicao);
		return "redirect:/instituicoes/index";
	}
	
	
	//PathVariable para notificar que o id passando como parametro tem uma relação com id passado na rota
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		Instituicao instituicao = repositorioInstituicao.getOne(id);
		ModelAndView resultado = new ModelAndView("instituicao/editar");
		resultado.addObject("instituicao", instituicao);
		return resultado;
	}
	
	@PostMapping("/editar")
	public String editar(Instituicao instituicao) {
		repositorioInstituicao.save(instituicao);
		return "redirect:/instituicoes/index";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		repositorioInstituicao.deleteById(id);
		return "redirect:/instituicoes/index";
	}
	
	
	/*Optional do java 8 indica que pode recer ou não uma String(caso exclusivo do exemplo, podendo ser alterado ou não)
		optinal String pois para esse método pode ou não receber um nome, caso receba retorna o que achar com esse nome
		caso não recebe retorna a lista total
	  @ResponseBody indica que a lista de instituição sera serializada para dentrro da resposta da requisação
		isto é vai retornar de fato uma lista de instituições
		tratativa para receber na rota um parametro vazia
		solução passar um array de rotas, onde um recebe o nome e o outro não vai receber paramentro algum */
	@GetMapping({"/pesquisarPorNome/{nome}", "/pesquisarPorNome"})
	public @ResponseBody List<Instituicao> pesquisarPorNome(@PathVariable Optional<String> nome) {
		//retorna true caso possua valor e false quando não possui valor
		if(nome.isPresent()) {
			return repositorioInstituicao.findByNomeContaining(nome.get());
		}else {
			return repositorioInstituicao.findAll();
		}
	}
}










