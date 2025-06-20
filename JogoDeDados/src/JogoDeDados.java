
import java.util.*;

public class JogoDeDados {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Jogador> jogadores = new ArrayList<>();
        Dado d1 = new Dado();
        Dado d2 = new Dado();

        // Mostrar os últimos 5 vencedores
        Persistencia.exibirTop5();

        // Entrada
        System.out.print("\nQuantos jogadores? ");
        int num = sc.nextInt();
        sc.nextLine(); // limpa o buffer

        for (int i = 0; i < num; i++) {
            System.out.print("Nome do jogador " + (i + 1) + ": ");
            String nome = sc.nextLine();
            jogadores.add(new Jogador(nome));
        }

        // Jogar dados
        for (Jogador j : jogadores) {
            j.jogar(d1, d2);
        }

        // Identificar maior pontuação
        int maior = jogadores.stream().mapToInt(Jogador::getPontuacao).max().orElse(0);

        // Identificar vencedores
        Map<String, Integer> novosVencedores = new LinkedHashMap<>();
        System.out.println("\n🎉 Vencedor(es):");
        for (Jogador j : jogadores) {
            if (j.getPontuacao() == maior) {
                System.out.println(j.getNome() + " com " + j.getPontuacao() + " pontos!");
                novosVencedores.put(j.getNome(), j.getPontuacao());
            }
        }

        // Carregar dados anteriores
        Map<String, Integer> historico = Persistencia.carregarVencedores();

        // Atualizar vitórias
        for (String nome : novosVencedores.keySet()) {
            int vitoriasAnteriores = historico.getOrDefault(nome, 0);
            historico.put(nome, vitoriasAnteriores + 1);
        }

        // Salvar no arquivo
        Persistencia.salvarVencedores(historico, novosVencedores);

        sc.close();
    }
}
