package segundoteste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Segundo {
	private static int geradorId = 1;
	private Map<Integer, String> candidatos;
	private Map<Integer, String> status;
	
	public Segundo() {
		candidatos = new HashMap<>();
		status = new HashMap<>();
	}
	
	public int iniciarProcesso(String nome) throws IllegalArgumentException {
		if(nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Nome inválido.");
		}
		
		if(!nome.matches("[a-zA-Z]+")) {
			throw new IllegalArgumentException("Nome inválido.");
		}
		
		for(Map.Entry<Integer, String> entry : candidatos.entrySet()) {
			if(entry.getValue().equals(nome)) {
				throw new IllegalArgumentException("Candidato já participa do processo.");
			}
		}
		
		int id = geradorId;
		geradorId++;
		candidatos.put(id,  nome);
		status.put(id, "Recebido");
		return id;
	}
	
	public void marcarEntrevista(int codCandidato) throws IllegalArgumentException {
		if(candidatos.containsKey(codCandidato)) {
			if(status.get(codCandidato).equals("Recebido")) {
				status.put(codCandidato, "Qualificado");
				String nome = candidatos.get(codCandidato);
				System.out.println("Entrevista marcada para o cadidato "+ nome + ".");
			} else {
				throw new IllegalArgumentException("Candidato não encontrado.");
			}
		} else {
			throw new IllegalArgumentException("Candidato não encontrado.");
		}
	}
	
	public void desqualificarCandidato(int codCandidato) throws IllegalArgumentException {
		if(candidatos.containsKey(codCandidato)) {
			String statusCandidato = status.get(codCandidato);
			String nome = candidatos.get(codCandidato);
			if(statusCandidato.equals("Recebido") || statusCandidato.equals("Qualificado") || statusCandidato.equals("Aprovado")) {
				candidatos.remove(codCandidato);
				status.remove(codCandidato);
				System.out.println("O candidato " + nome + " foi desqualificado do processo seletivo.");
			} 
		} else {
			throw new IllegalArgumentException("Candidato não encontrado.");
		}
	}
	
	public String verificarStatusCandidato(int codCandidato) throws IllegalArgumentException {
		String nome = candidatos.get(codCandidato);
		String statusCandidato = status.get(codCandidato);

		if(!candidatos.containsKey(codCandidato)) {
			throw new IllegalArgumentException("Candidato não encontrado.");
		}
		
		return "Status do candidato " + nome + " é: " + statusCandidato +".";
	}
	
	public void aprovarCandidato(int codCandidato) throws IllegalArgumentException{
		if(candidatos.containsKey(codCandidato)) {
			String statusCandidato = status.get(codCandidato);
			if(statusCandidato.equals("Qualificado")) {				
				status.put(codCandidato, "Aprovado");
				System.out.println("Candidato com id: " + codCandidato + " foi aprovado.");
			} else {
				throw new IllegalArgumentException("Candidato não encontrado.");
			}
		} else {
			throw new IllegalArgumentException("Candidato não encontrado.");
		}
	}
	
	public List<String> obterAprovados() throws IllegalArgumentException {
		List<String> aprovados = new ArrayList<>();
		for(Map.Entry<Integer, String> entry : status.entrySet()) {
			int id = entry.getKey();
			String statusCandidato = entry.getValue();
			if(statusCandidato.equals("Aprovado")) {
				String nome = candidatos.get(id);
				aprovados.add(nome);
			}
		}
		
		if(aprovados.isEmpty()) {
			throw new IllegalArgumentException("Candidato não encontrado.");
		}
		
		return aprovados;
	}
}
