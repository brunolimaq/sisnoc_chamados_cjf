package br.com.sisnoc.chamados.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sisnoc.chamados.dao.PainelChamadosDao;
import br.com.sisnoc.chamados.dao.PainelSemSlaDao;
import br.com.sisnoc.chamados.dao.util.ChamadosDao;
import br.com.sisnoc.chamados.dao.util.SemSlaDao;

@Controller
public class EquipesController {

	
	@Autowired
	@ChamadosDao
	private PainelChamadosDao daoChamados;
	
	@Autowired
	@SemSlaDao
	private PainelSemSlaDao daoSemSla;

	

	@RequestMapping("/equipe_SO")
	public String lista_windows(Model model) throws ParseException{

		String equipe = "Analistas Sistemas Operacionais";
		String status = "andamento";

		model.addAttribute("chamadosEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"I"));
		
		status = "pendente";
		model.addAttribute("chamadosEquipePendente", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipePendente", daoChamados.listaPainelChamados(equipe, status,"I"));
		
		
		
		status = "andamento";
		model.addAttribute("osEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		status = "pendente";
		model.addAttribute("osEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));

		model.addAttribute("equipe", "Analistas Sistemas Operacionais");
		return "chamados/equipes";
	}

	@RequestMapping("/equipe_armazenamento")
	public String lista_storage(Model model) throws ParseException{

		String equipe = "Analistas Storage";
		String status = "andamento";

		model.addAttribute("chamadosEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"I"));
		
		
		status = "pendente";
		model.addAttribute("chamadosEquipePendente", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipePendente", daoChamados.listaPainelChamados(equipe, status,"I"));

		status = "andamento";
		model.addAttribute("osEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		status = "pendente";
		model.addAttribute("osEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		model.addAttribute("equipe", "Analistas Storage");
		return "chamados/equipes";
	}
	
	@RequestMapping("/equipe_app")
	public String lista_app(Model model) throws ParseException{

		String equipe = "Analistas Aplicações";
		String status = "andamento";

		model.addAttribute("chamadosEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"I"));
		
		
		status = "pendente";
		model.addAttribute("chamadosEquipePendente", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipePendente", daoChamados.listaPainelChamados(equipe, status,"I"));

		status = "andamento";
		model.addAttribute("osEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		status = "pendente";
		model.addAttribute("osEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		model.addAttribute("equipe", "Analistas Aplicações");
		return "chamados/equipes";
	}
	
	@RequestMapping("/equipe_Bd")
	public String lista_bd(Model model) throws ParseException{

		String equipe = "Analistas Banco de Dados";
		String status = "andamento";

		model.addAttribute("chamadosEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"I"));
		
		
		status = "pendente";
		model.addAttribute("chamadosEquipePendente", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipePendente", daoChamados.listaPainelChamados(equipe, status,"I"));

		status = "andamento";
		model.addAttribute("osEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		status = "pendente";
		model.addAttribute("osEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		model.addAttribute("equipe", "Analistas Banco de Dados");
		return "chamados/equipes";
	}
	
	@RequestMapping("/equipe_corp")
	public String lista_corp(Model model) throws ParseException{

		String equipe = "Analistas Serviços Corporativos";
		String status = "andamento";

		model.addAttribute("chamadosEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"I"));
		
		
		status = "pendente";
		model.addAttribute("chamadosEquipePendente", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipePendente", daoChamados.listaPainelChamados(equipe, status,"I"));

		status = "andamento";
		model.addAttribute("osEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		status = "pendente";
		model.addAttribute("osEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		model.addAttribute("equipe", "Analistas Serviços Corporativos");
		return "chamados/equipes";
	}
	
	@RequestMapping("/equipe_rede")
	public String lista_rede(Model model) throws ParseException{

		String equipe = "Analistas Redes";
		String status = "andamento";

		model.addAttribute("chamadosEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"I"));
		
		
		status = "pendente";
		model.addAttribute("chamadosEquipePendente", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipePendente", daoChamados.listaPainelChamados(equipe, status,"I"));

		status = "andamento";
		model.addAttribute("osEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		status = "pendente";
		model.addAttribute("osEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		model.addAttribute("equipe", "Analistas Redes");
		return "chamados/equipes";
	}
	
	@RequestMapping("/equipe_monit")
	public String lista_monit(Model model) throws ParseException{

		String equipe = "Analistas Monitoração";
		String status = "andamento";

		model.addAttribute("chamadosEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"I"));
		
		
		status = "pendente";
		model.addAttribute("chamadosEquipePendente", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipePendente", daoChamados.listaPainelChamados(equipe, status,"I"));

		status = "andamento";
		model.addAttribute("osEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		status = "pendente";
		model.addAttribute("osEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		model.addAttribute("equipe", "Analistas Monitoração");
		return "chamados/equipes";
	}
	
	@RequestMapping("/equipe_bkp")
	public String lista_bkp(Model model) throws ParseException{

		String equipe = "Analistas Backup";
		String status = "andamento";

		model.addAttribute("chamadosEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"I"));
		
		
		status = "pendente";
		model.addAttribute("chamadosEquipePendente", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipePendente", daoChamados.listaPainelChamados(equipe, status,"I"));

		status = "andamento";
		model.addAttribute("osEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		status = "pendente";
		model.addAttribute("osEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		model.addAttribute("equipe", "Analistas Backup");
		return "chamados/equipes";
	}
	
	@RequestMapping("/equipe_virt")
	public String lista_virt(Model model) throws ParseException{

		String equipe = "Analistas Virtualização";
		String status = "andamento";

		model.addAttribute("chamadosEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"I"));
		
		
		status = "pendente";
		model.addAttribute("chamadosEquipePendente", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipePendente", daoChamados.listaPainelChamados(equipe, status,"I"));

		status = "andamento";
		model.addAttribute("osEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		status = "pendente";
		model.addAttribute("osEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		model.addAttribute("equipe", "Analistas Virtualização");
		return "chamados/equipes";
	}
	
	@RequestMapping("/equipe_doc")
	public String lista_doc(Model model) throws ParseException{

		String equipe = "Documentadores";
		String status = "andamento";

		model.addAttribute("chamadosEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"I"));
		
		
		status = "pendente";
		model.addAttribute("chamadosEquipePendente", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipePendente", daoChamados.listaPainelChamados(equipe, status,"I"));

		status = "andamento";
		model.addAttribute("osEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		status = "pendente";
		model.addAttribute("osEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		model.addAttribute("equipe", "Documentadores");
		return "chamados/equipes";
	}
	
	@RequestMapping("/monitoradores")
	public String lista_monitoradores(Model model) throws ParseException{

		String equipe = "Monitoradores";
		String status = "andamento";

		model.addAttribute("chamadosEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"I"));
		
		
		status = "pendente";
		model.addAttribute("chamadosEquipePendente", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipePendente", daoChamados.listaPainelChamados(equipe, status,"I"));

		status = "andamento";
		model.addAttribute("osEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		status = "pendente";
		model.addAttribute("osEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		model.addAttribute("equipe", "Monitoradores");
		return "chamados/equipes";
	}
	
	@RequestMapping("/supervisor")
	public String lista_supervisor(Model model) throws ParseException{

		String equipe = "Supervisores Datacenter";
		String status = "andamento";

		model.addAttribute("chamadosEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipeAndamento", daoChamados.listaPainelChamados(equipe, status,"I"));
		
		
		status = "pendente";
		model.addAttribute("chamadosEquipePendente", daoChamados.listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidenteEquipePendente", daoChamados.listaPainelChamados(equipe, status,"I"));

		status = "andamento";
		model.addAttribute("osEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipeAndamento", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		status = "pendente";
		model.addAttribute("osEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Ordem de Servico"));
		model.addAttribute("tarefaEquipePendente", daoSemSla.listaPainelSemSla(equipe, status,"INFRA.Tarefas Internas"));
		
		model.addAttribute("equipe", "Supervisores Datacenter");
		return "chamados/equipes";
	}

}
