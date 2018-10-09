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

import br.com.clinica.entidades.Medico;
import br.com.clinica.repositorios.RepositorioMedico;

@Controller
@RequestMapping("/medicos")
public class MedicosController {
	
	@Autowired
	private RepositorioMedico repositorioMedico;
	
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model){
		
		List<Medico> listMedicos = repositorioMedico.findAll();
		model.addAttribute("listMedicos", listMedicos);
		model.addAttribute("medico", new Medico());
		
		return "medico.list.tiles";
		
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@ModelAttribute("medico") @Valid Medico novoMedico, BindingResult result, Model model){
		
		if(result.hasErrors()){
			model.addAttribute("listMedicos", repositorioMedico.findAll());
			return "medico.list.tiles";
		}
		
		repositorioMedico.save(novoMedico);
		return "redirect:/medicos/list";
		
	}
	
	
	@RequestMapping(value="/getEdt", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Medico> getEdt(@RequestParam(name = "id", defaultValue="") String id){
		
		return repositorioMedico.findById(Long.parseLong(id));

	}
	
	@RequestMapping(value="/edt", method=RequestMethod.POST)
	public String edt(@ModelAttribute("medico") @Valid Medico edtMedico, BindingResult result, Model model){
		
		if(result.hasErrors()){
			model.addAttribute("listMedicos", repositorioMedico.findAll());
			return "medico.list.tiles";
		}
		
		repositorioMedico.save(edtMedico);
		return "redirect:/medicos/list";
		
	}
	
	@RequestMapping(value="/del/{id}", method=RequestMethod.GET)
	public String del(@PathVariable("id") Long id ){
		
		repositorioMedico.delete(id);
		return "redirect:/medicos/list";
		
	}
	
	@RequestMapping(value="/nome", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Medico> pesquisa(@RequestParam(name = "nome", defaultValue="") String nomeMedico){
		
		return repositorioMedico.findByNome(nomeMedico);

}
	
}
