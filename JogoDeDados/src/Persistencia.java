
import java.io.*;
import java.util.*;

public class Persistencia {
    private static final String CAMINHO = "vencedores.csv";

    public static Map<String, Integer> carregarVencedores() {
        Map<String, Integer> vencedores = new LinkedHashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length >= 3) {
                    String nome = partes[0];
                    int vitorias = Integer.parseInt(partes[2]);
                    vencedores.put(nome, vitorias);
                }
            }
        } catch (IOException e) {
            // Arquivo pode não existir na primeira execução
        }

        return vencedores;
    }

    public static void salvarVencedores(Map<String, Integer> vencedores, Map<String, Integer> pontuacoes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO))) {
            for (Map.Entry<String, Integer> entry : vencedores.entrySet()) {
                String nome = entry.getKey();
                int vitorias = entry.getValue();
                int pontuacao = pontuacoes.getOrDefault(nome, 0);
                writer.write(nome + "," + pontuacao + "," + vitorias);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os vencedores: " + e.getMessage());
        }
    }

    public static void exibirTop5() {
        List<String> linhas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Nenhum histórico de vencedores encontrado.");
            return;
        }

        System.out.println("\n📝 Últimos 5 ganhadores:");
        int inicio = Math.max(0, linhas.size() - 5);
        for (int i = inicio; i < linhas.size(); i++) {
            System.out.println(linhas.get(i));
        }
    }
}
