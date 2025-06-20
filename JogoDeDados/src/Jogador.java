
public class Jogador {
    private String nome;
    private int pontuacao;

    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacao = 0;
    }

    public void jogar(Dado d1, Dado d2) {
        this.pontuacao = d1.rolar() + d2.rolar();
        System.out.println(nome + " tirou: " + pontuacao);
    }

    public String getNome() {
        return nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }
}
