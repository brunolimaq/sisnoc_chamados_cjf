package br.com.sisnoc.chamados.controller;



import java.security.Principal;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class SegurancaController {
	

	@RequestMapping("/login")
	public String login(Principal principal) {
//        SecurityContext context = SecurityContextHolder.getContext();
//
//        Authentication authentication = context.getAuthentication();
        
		
		if (principal !=null) {
			return "redirect:/";
		}
		return "chamados/login";
	}
	
	
}
