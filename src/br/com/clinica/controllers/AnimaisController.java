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

import br.com.clinica.entidades.Animal;
import br.com.clinica.repositorios.RepositorioAnimal;
import br.com.clinica.repositorios.RepositorioCliente;

	@Controller
	@RequestMapping("/animais")
	public class AnimaisController {
		
		@Autowired
		private RepositorioAnimal repositorioAnimal;
		
		@Autowired
		private RepositorioCliente repositorioCliente;
		
		
		@RequestMapping(value="/list", method=RequestMethod.GET)
		public String list(Model model){
			

			model.addAttribute("listAnimais", repositorioAnimal.findAll());
			model.addAttribute("clientes", repositorioCliente.findAll());
			model.addAttribute("animal", new Animal());
			
			return "animal.list.tiles";
			
		}
		
		@RequestMapping(value="/add", method=RequestMethod.POST)
		public String add(@ModelAttribute("Animal") @Valid Animal novoAnimal, BindingResult result, Model model){
			
			if(result.hasErrors()){
				model.addAttribute("listAnimais", repositorioAnimal.findAll());
				model.addAttribute("clientes", repositorioCliente.findAll());
				return "animal.list.tiles";
			}
			
			repositorioAnimal.save(novoAnimal);
			return "redirect:/animais/list";
			
		}
		
		
		@RequestMapping(value="/getEdt", method=RequestMethod.GET, produces="application/json")
		public @ResponseBody List<Animal> getEdt(@RequestParam(name = "id", defaultValue="") String id){
			
			return repositorioAnimal.findById(Long.parseLong(id));

		}
		
		@RequestMapping(value="/edt", method=RequestMethod.POST)
		public String edt(@ModelAttribute("Animal") @Valid Animal edtAnimal, BindingResult result, Model model){
			
			if(result.hasErrors()){
				model.addAttribute("listAnimais", repositorioAnimal.findAll());
				model.addAttribute("clientes", repositorioCliente.findAll());
				return "animal.list.tiles";
			}
			
			repositorioAnimal.save(edtAnimal);
			return "redirect:/animais/list";
			
		}
		
		@RequestMapping(value="/del/{id}", method=RequestMethod.GET)
		public String del(@PathVariable("id") Long id ){
			
			repositorioAnimal.delete(id);
			return "redirect:/animais/list";
			
		}
		
		@RequestMapping(value="/nome", method=RequestMethod.GET, produces="application/json")
		public @ResponseBody List<Animal> pesquisa(@RequestParam(name = "nome", defaultValue="") String nomeAnimal){
			
			return repositorioAnimal.findByNome(nomeAnimal);

		}
		
	}


