package dna;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<String> nomes = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            nomes.add("arquivosDNA/dna-" + i);
        }
        
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (String nome : nomes) {
            executorService.execute(new ThreadProcessDNA(nome));
        }
        executorService.shutdown();

        System.out.println("Main finalizado.");
    }
}
