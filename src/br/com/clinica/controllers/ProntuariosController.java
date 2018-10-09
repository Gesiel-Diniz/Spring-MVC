package br.com.clinica.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.clinica.entidades.Prontuario;
import br.com.clinica.repositorios.RepositorioAnimal;
import br.com.clinica.repositorios.RepositorioMedico;
import br.com.clinica.repositorios.RepositorioProntuario;

	@Controller
	@RequestMapping("/prontuarios")
	public class ProntuariosController {
		
		@Autowired
		private RepositorioAnimal repositorioAnimal;
		
		@Autowired
		private RepositorioMedico repositorioMedicos;
		
		@Autowired
		private RepositorioProntuario repositorioProntuario;
		
		
		@RequestMapping(value="/list", method=RequestMethod.GET)
		public String list(Model model){
			

			model.addAttribute("listProntuarios", repositorioProntuario.findAll());
			model.addAttribute("animais", repositorioAnimal.findAll());
			model.addAttribute("medicos", repositorioMedicos.findAll());
			model.addAttribute("prontuario", new Prontuario());
			
			return "prontuario.list.tiles";
			
		}
		
		@RequestMapping(value="/add", method=RequestMethod.POST)
		public String add(@ModelAttribute("Prontuario") @Valid Prontuario novoProntuario, BindingResult result, Model model){
			
			if(result.hasErrors()){
				model.addAttribute("listProntuarios", repositorioProntuario.findAll());
				model.addAttribute("animais", repositorioAnimal.findAll());
				model.addAttribute("medicos", repositorioMedicos.findAll());
				return "prontuario.list.tiles";
			}
			
			repositorioProntuario.save(novoProntuario);
			return "redirect:/prontuarios/list";
			
		}
		
		
		@RequestMapping(value="/getEdt", method=RequestMethod.GET, produces="application/json")
		public @ResponseBody List<Prontuario> getEdt(@RequestParam(name = "id", defaultValue="") String id){
			
			return repositorioProntuario.findById(Long.parseLong(id));

		}
		
		@RequestMapping(value="/edt", method=RequestMethod.POST)
		public String edt(@ModelAttribute("Prontuario") @Valid Prontuario edtProntuario, BindingResult result, Model model){
			
			if(result.hasErrors()){
				model.addAttribute("listProntuarios", repositorioProntuario.findAll());
				model.addAttribute("animais", repositorioAnimal.findAll());
				model.addAttribute("medicos", repositorioMedicos.findAll());
				return "prontuario.list.tiles";
			}
			
			repositorioProntuario.save(edtProntuario);
			return "redirect:/prontuario/list";
			
		}
		
		@RequestMapping(value="/del/{id}", method=RequestMethod.GET)
		public String del(@PathVariable("id") Long id ){
			
			repositorioProntuario.delete(id);
			return "redirect:/prontuarios/list";
			
		}
		
		@RequestMapping(value="/nome", method=RequestMethod.GET, produces="application/json")
		public @ResponseBody List<Prontuario> pesquisa(@RequestParam(name = "nome", defaultValue="") String nomeProntuario){
			
			return repositorioProntuario.findByNome(nomeProntuario);

		}
		
	}


