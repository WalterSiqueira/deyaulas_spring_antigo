package com.etecja.deyaulas.Controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.etecja.deyaulas.Entities.Crianca;
import com.etecja.deyaulas.Repositories.CriancaRepository;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/crianca")
public class CriancaController {
    @Autowired
    private CriancaRepository criancaRepository;
    
    @GetMapping("/")
    public String listarAdultos(Model model) {
    	List<Crianca> crianca = criancaRepository.findAll();
    	model.addAttribute("crianca", crianca);
    	return "ListarCriancas";
    }
    
    @GetMapping("/cadastro")
    public String CadastroCrianca(Model model) {
        return "CadastroCrianca";
    }

    @GetMapping("/successo")
    public String successo() {
        return "successo";
    }

    @GetMapping("/existente")
    public String existente() {
        return "existente";
    }

    @PostMapping("/cadastrar")
    public String salvarUsuario(@RequestParam String nome, @RequestParam String email, @RequestParam String senha) {
        Crianca crianca = new Crianca();
        Crianca criancaExistente = criancaRepository.findByEmail(email);
        if (criancaExistente != null) {
            return "redirect:/crianca/existente";
        } else {
            crianca.setNome(nome);
            crianca.setEmail(email);
            crianca.setSenha(senha);
            criancaRepository.save(crianca);
            return("redirect:/crianca/successo");
        }
    }
}
