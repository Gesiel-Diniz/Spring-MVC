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

import br.com.clinica.entidades.Cliente;
import br.com.clinica.repositorios.RepositorioCliente;

@Controller
@RequestMapping("/clientes")
public class ClientesController {
	
	@Autowired
	private RepositorioCliente repositorioCliente;
	
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model){
		
		List<Cliente> listClientes = repositorioCliente.findAll();
		model.addAttribute("listClientes", listClientes);
		model.addAttribute("cliente", new Cliente());
		
		return "cliente.list.tiles";
		
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@ModelAttribute("cliente") @Valid Cliente novoCliente, BindingResult result, Model model){
		
		if(result.hasErrors()){
			model.addAttribute("listClientes", repositorioCliente.findAll());
			return "cliente.list.tiles";
		}
		
		repositorioCliente.save(novoCliente);
		return "redirect:/clientes/list";
		
	}
	
	
	@RequestMapping(value="/getEdt", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Cliente> getEdt(@RequestParam(name = "id", defaultValue="") String id){
		
		return repositorioCliente.findById(Long.parseLong(id));

	}
	
	@RequestMapping(value="/edt", method=RequestMethod.POST)
	public String edt(@ModelAttribute("cliente") @Valid Cliente edtCliente, BindingResult result, Model model){
		
		if(result.hasErrors()){
			model.addAttribute("listClientes", repositorioCliente.findAll());
			return "cliente.list.tiles";
		}
		
		repositorioCliente.save(edtCliente);
		return "redirect:/clientes/list";
		
	}
	
	@RequestMapping(value="/del/{id}", method=RequestMethod.GET)
	public String del(@PathVariable("id") Long id ){
		
		repositorioCliente.delete(id);
		return "redirect:/clientes/list";
		
	}
	
	@RequestMapping(value="/nome", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Cliente> pesquisa(@RequestParam(name = "nome", defaultValue="") String nomeCliente){
		
		return repositorioCliente.findByNome(nomeCliente);

	}
	
}

