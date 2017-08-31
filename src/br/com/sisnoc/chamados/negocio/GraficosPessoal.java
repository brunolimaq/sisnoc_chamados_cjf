package br.com.sisnoc.chamados.negocio;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;


import br.com.sisnoc.chamados.dao.PainelPessoalMetasDao;

import br.com.sisnoc.chamados.dao.util.MetasDao;
import br.com.sisnoc.chamados.modelo.Chamado;
import br.com.sisnoc.chamados.security.UsuarioSistema;
import br.com.sisnoc.chamados.service.GraficosPessoalService;


@Service
public class GraficosPessoal implements GraficosPessoalService {
	
	private int meta2h;
	private int meta4h;
	private int violados;
	private int chamadosMes;
	private int incidentesMes;
	private int requisicoesMes;

	private int pendencias;

	private int reabertosMes;
	private int metaReabertos;
	
	private ArrayList<String[]> listaPendenteEquipe;
	
	
	@Autowired
	@MetasDao
	PainelPessoalMetasDao metasDao;

	
	
	public GraficosPessoal() {
		super();
		
	}
	
	
	
	
	@Override
	public void calcMetas() {
		
	
		
		
		int countTotal = 0;
		int countMeta2h = 0;
		int countMeta4h = 0;
		int countViolados = 0;
		int countIncidentes = 0;
		int countChamados = 0;
		
		String perfil = "";
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		Collection<? extends GrantedAuthority> permissao = null;
		if (usuarioLogado  instanceof UsuarioSistema ) {
			   username = ( (UsuarioSistema)usuarioLogado).getUsuario().getNome();
			   permissao = ( (UsuarioSistema)usuarioLogado).getUsuario().getAuthority();
		} else {
		   username = usuarioLogado.toString();
		}
		
		
		for (GrantedAuthority autorizacao : permissao) {
			if (autorizacao.toString().equals("GESTOR")){

				perfil = "GESTOR";
	
			} 
			
		}
		
		
		
		
		ArrayList<Chamado> chamados = new ArrayList<Chamado>();
		
			
			try {
				chamados = metasDao.listaPainelPessoalMetas(perfil);
				this.setReabertosMes(metasDao.listaPainelPessoalReabertos(perfil));
				this.setPendencias(metasDao.listaPainelPessoalPendentes(perfil));
				this.setListaPendenteEquipe(metasDao.listaPainelGestorPendentes());
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
		
		
			//System.out.println("lista " + getListaPendenteEquipe());
		
			for (Chamado chamado : chamados) {
				
				countTotal++;
				if(chamado.getSla().equals("Violado")){
				countViolados++;
				}
				if(chamado.getSla2() <= 7200){
				countMeta2h++;
				}
				if(chamado.getSla2() <= 14400){
				countMeta4h++;
				}
				if(chamado.getTipo().equals("I")){
				countIncidentes++;
				}
				if(chamado.getTipo().equals("R")){
				countChamados++;
				}
				
				 
				} 
				 
			
			
				this.setMeta2h((countMeta2h*100)/countTotal);
				this.setMeta4h((countMeta4h*100)/countTotal);
				this.setMetaReabertos((this.getReabertosMes()*100)/countTotal);
				this.setViolados(countViolados);
				this.setRequisicoesMes(countTotal);
				this.setChamadosMes(countChamados);
				this.setIncidentesMes(countIncidentes);
				
	
		
	}
	public int getIncidentesMes() {
		return incidentesMes;
	}

	public void setIncidentesMes(int incidentesMes) {
		this.incidentesMes = incidentesMes;
	}

	public int getRequisicoesMes() {
		return requisicoesMes;
	}

	public void setRequisicoesMes(int requisicoesMes) {
		this.requisicoesMes = requisicoesMes;
	}

	public int getReabertosMes() {
		return reabertosMes;
	}

	public void setReabertosMes(int reabertosMes) {
		this.reabertosMes = reabertosMes;
	}

	public int getMetaReabertos() {
		return metaReabertos;
	}

	public void setMetaReabertos(int metaReabertos) {
		this.metaReabertos = metaReabertos;
	}

	@Override
	public String toString() {
		return "GraficosPessoal [meta2h=" + meta2h + ", meta4h=" + meta4h + ", violados=" + violados + ", chamadosMes="
				+ chamadosMes + "]";
	}
	
	
	public int getMeta2h() {
		return meta2h;
	}
	public void setMeta2h(int meta2h) {
		this.meta2h = meta2h;
	}
	public int getMeta4h() {
		return meta4h;
	}
	public void setMeta4h(int meta4h) {
		this.meta4h = meta4h;
	}
	public int getViolados() {
		return violados;
	}
	public void setViolados(int violados) {
		this.violados = violados;
	}
	public int getChamadosMes() {
		return chamadosMes;
	}
	public void setChamadosMes(int chamadosMes) {
		this.chamadosMes = chamadosMes;
	}
	public int getPendencias() {
		return pendencias;
	}


	public void setPendencias(int pendencias) {
		this.pendencias = pendencias;
	}


	
	public ArrayList<String[]> getListaPendenteEquipe() {
		return listaPendenteEquipe;
	}




	public void setListaPendenteEquipe(ArrayList<String[]> listaPendenteEquipe) {
		this.listaPendenteEquipe = listaPendenteEquipe;
	}
}
