package segundoteste;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Segundo empresaRh = new Segundo();
		Scanner scanner = new Scanner(System.in);
		
		int opcao = -1;
		
		System.out.println("==== Controle de Candidatos ====");
		
		while(opcao != 0) {
			System.out.println("\nSelecione uma opção");
			System.out.println("1 - Registrar candidato");
			System.out.println("2 - Marcar entrevista para o candidato");
			System.out.println("3 - Desqualificar candidato");
			System.out.println("4 - Verificar status do candidato");
			System.out.println("5 - Aprovar candidato");
			System.out.println("6 - Listar candidatos");
			System.out.println("0 - Sair do sistema");
			System.out.print("Opção: ");
			
			opcao = scanner.nextInt();
			scanner.nextLine();
			
			switch (opcao) {
			case 0: 
				System.out.println("Você saiu do sistema.");
				break;
				
			case 1:
				try {
					System.out.println("Digite o nome do candidato: ");
					String nome = scanner.nextLine();
					int id;
					id = empresaRh.iniciarProcesso(nome);
					if(id != -1) {						
						System.out.println("O candidato " + nome + " foi registrado com sucesso. Seu ID é: "+ id);;
					}
				} catch(Exception e) {
					System.out.println("Erro: " + e.getMessage());
				}
				break;
			
			case 2:
				try {
					System.out.println("Digite o ID do candidato: ");
					int idCandidato = Integer.parseInt(scanner.nextLine());
					empresaRh.marcarEntrevista(idCandidato);
				} catch (Exception e){
					System.out.println("Erro: " + e.getMessage());
				}
				break;
			
			case 3: 
				try {
					System.out.print("Digite o código do candidato: ");
                    int codCandidato = scanner.nextInt();
                    scanner.nextLine();
                    empresaRh.desqualificarCandidato(codCandidato);
				} catch(Exception e) {
					System.out.println("Erro: " + e.getMessage());
				}
				break;
			
			case 4:
				try {
					System.out.println("Digite o código do candidato: ");
					int codCandidato = scanner.nextInt();
					scanner.nextLine();
					String status = empresaRh.verificarStatusCandidato(codCandidato);
					System.out.println(status);
				} catch(Exception e) {
					System.out.println("Erro: " + e.getMessage());
				}
				break;
				
			case 5:
				try {
					System.out.println("Digite o código do candidato: ");
					int codCandidato = scanner.nextInt();
					scanner.nextLine();
					empresaRh.aprovarCandidato(codCandidato);
				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
				}
				break;
			
			case 6: 
				try {
					List<String> aprovados = empresaRh.obterAprovados();
					System.out.println("Candidatos aprovados:");
					for (String nomeAprovado : aprovados) {
						System.out.println(nomeAprovado);
					}
				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
				}
				break;
				
			default:
				System.out.println("Opção inválida. Insira uma opção válida.");
				break;
			}
		}
		
		scanner.close();
	}

}
