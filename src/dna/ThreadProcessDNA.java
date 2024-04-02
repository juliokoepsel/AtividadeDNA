package dna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ThreadProcessDNA implements Runnable {
    private String nomeArquivo;

    public ThreadProcessDNA(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    @Override
    public void run() {
        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo + ".txt"))) {
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeArquivo + "-result.txt"))) {
                String linha;
                String linhaResultado;
                int numeroLinha = 0;
                boolean invalida;
                ArrayList<Integer> linhasInvalidas = new ArrayList<>();
                while ((linha = leitor.readLine()) != null) {
                    linhaResultado = "";
                    invalida = false;
                    for (int i = 0; i < linha.length(); i++) {
                        switch (linha.charAt(i)) {
                            case 'A':
                                linhaResultado += 'T';
                                break;
                            case 'T':
                                linhaResultado += 'A';
                                break;
                            case 'C':
                                linhaResultado += 'G';
                                break;
                            case 'G':
                                linhaResultado += 'C';
                                break;
                            default:
                                invalida = true;
                                break;
                        }
                        if (invalida) {
                            linhaResultado = "****FITA INVALIDA - " + linha;
                            linhasInvalidas.add(numeroLinha);
                            break;
                        }
                    }
                    escritor.append(linhaResultado + "\n");
                    numeroLinha++;
                }
                System.out.println(nomeArquivo + ".txt = " + "O total de fitas é " + numeroLinha);
                System.out.println(nomeArquivo + ".txt = " + "O total de fitas validas é " + (numeroLinha - linhasInvalidas.size()));
                System.out.println(nomeArquivo + ".txt = " + "O total de fitas invalidas é " + linhasInvalidas.size());
                System.out.println(nomeArquivo + ".txt = " + "as linhas invalidas são " + linhasInvalidas.toString());
                escritor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            leitor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
