package br.com.sisnoc.chamados.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import br.com.sisnoc.chamados.dao.PainelUrlDao;
import br.com.sisnoc.chamados.dao.util.URLDAO;

public class VerificaUrlController {
	
	@Autowired
	@URLDAO
	private PainelUrlDao daoUrls;

	@RequestMapping("/url")
	public String listaUrls(Model model) throws ParseException {
		
		model.addAttribute("UrlsProducao", daoUrls.listaStatusUrls("Produção"));
		
		return "chamados/monitorurl";
	}
}
