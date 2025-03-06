import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlaylistManager {
    private static List<Musica> playList = new ArrayList<>();
    private static final String FILE_NAME = "Playlist.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        carregarPlaylist();

        while (true) {
            System.out.println("\nMenu");
            System.out.println("1 - Adicinar Músicas");
            System.out.println("2 - Listar Músicas");
            System.out.println("3 - Salvar e Sair");
            System.out.print("Escolhar: ");

            int escolha = scanner.nextInt();
            scanner.nextLine();
            
            switch (escolha) {
                case 1:
                    adicionarMusica(scanner);
                    break;

                case 2:
                    listarMusicas();
                    break;

                case 3:
                    salvarPlaylist();
                    System.out.print("Playlist Salva! Gostaria de fazedr outra ação?");
                    break;

            
                default:
                    System.out.print("Erro! Tente novamente.");

            }
        }
    }
} 

private static void adicionarMusica(Scanner scanner) {
    System.out.print("Nome da música: ");
    String nome = scanner.nextLine();

    System.out.print("Nome do artista: ");
    String artista = scanner.nextLine();

    playList.add(new Musica(nome, artista));
    System.out.println("Música Adicionada");
}

private static void listarMusicas() {
    if (playList.isEmpty()) {
        System.out.print("Nenhuma música na playlist");

    }   else {
        System.out.println("Playlist: ");
        for(int i = 0; i < playList.size(); i++) {
            System.out.println((i+1) + ". " + playList.get(i));
        }
    }
}

private static void salvarPlaylist() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
        for(Musica musica : playList) {
            writer.write(musica.getNome()+";"+musica.getArtista()));
            writer.newLine();
        }
    } catch (IOException e) {
        System.out.println("Erro ao Salvar Playlist: " + e.getMessage());
    }

    private static void carregarPlaylist() {
        File arquivo = new File(FILE_NAME);
        if(!arquivo.exists()) {
            return;
        }
        try {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 2) {
                    playList.add(new Musica(partes[0], partes[1]));
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao Salvar Playlist: " + e.getMessage());
        }
    }
}

class Musica {

    private String nome;
    private String artista;
    
    public Musica(String nome, String artista) {
        this.nome = nome;
        this.artista = artista;
    }

    public String getNome(){
        return nome;
    }

    public String getArtista(){
        return artista;
    }

    public String toString() {
        return nome + " - " + artista;
    }
}