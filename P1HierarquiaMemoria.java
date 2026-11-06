import java.util.Scanner;

public class P1HierarquiaMemoria {


	
	

	public static long somaPorLinha(int[][] matriz, int n) {
        long soma = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                soma = soma + matriz[i][j];
            }
        }
        return soma;
    }
 

    public static long somaPorColuna(int[][] matriz, int n) {
        long soma = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                soma = soma + matriz[i][j];
            }
        }
        return soma;
    }
 
   
    public static double arredonda(double valor) {
        return Math.round(valor * 100) / 100.0;
    }
 
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
 
        System.out.println("=== P1 - Experimento de Hierarquia de Memória ===");
 
        System.out.print("Número de repetições (mínimo 5): ");
        int repeticoes = entrada.nextInt();
        while (repeticoes < 5) {
            System.out.print("Use pelo menos 5 repetições: ");
            repeticoes = entrada.nextInt();
        }
 
        System.out.print("Quantos tamanhos de matriz (mínimo 3): ");
        int quantidade = entrada.nextInt();
        while (quantidade < 3) {
            System.out.print("Use pelo menos 3 tamanhos: ");
            quantidade = entrada.nextInt();
        }
 
        int[] tamanhos = new int[quantidade];
        for (int k = 0; k < quantidade; k++) {
            System.out.print("Tamanho " + (k + 1) + " (ex.: 1000, 2000, 4000): ");
            tamanhos[k] = entrada.nextInt();
        }
 
        double[] mediasLinha = new double[quantidade];
        double[] mediasColuna = new double[quantidade];
 
        for (int t = 0; t < quantidade; t++) {
            int n = tamanhos[t];
            System.out.println();
            System.out.println("--- Matriz " + n + " x " + n + " ---");
 
            
            int[][] matriz = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matriz[i][j] = 1;
                }
            }
 
            double totalLinha = 0;
            double totalColuna = 0;
            long somaLinha = 0;
            long somaColuna = 0;
 
            for (int r = 1; r <= repeticoes; r++) {
                long inicio = System.nanoTime();
                somaLinha = somaPorLinha(matriz, n);
                long fim = System.nanoTime();
                double msLinha = (fim - inicio) / 1000000.0; 
 
                inicio = System.nanoTime();
                somaColuna = somaPorColuna(matriz, n);
                fim = System.nanoTime();
                double msColuna = (fim - inicio) / 1000000.0;
 
                totalLinha = totalLinha + msLinha;
                totalColuna = totalColuna + msColuna;
 
                System.out.println("Execução " + r + ": linha = " + arredonda(msLinha)
                        + " ms | coluna = " + arredonda(msColuna) + " ms");
            }
 
            mediasLinha[t] = totalLinha / repeticoes;
            mediasColuna[t] = totalColuna / repeticoes;
 
          
            long esperado = (long) n * n;
            if (somaLinha == esperado && somaColuna == esperado) {
                System.out.println("Soma conferida: " + esperado + " (OK)");
            } else {
                System.out.println("ERRO: soma por linha = " + somaLinha
                        + ", por coluna = " + somaColuna + ", esperado = " + esperado);
            }
        }
 
  
        System.out.println();
        System.out.println("=== Resultado: tempo médio x tamanho ===");
        System.out.println("Tamanho\t\tLinha (ms)\tColuna (ms)\tColuna/Linha");
        for (int t = 0; t < quantidade; t++) {
            double razao = mediasColuna[t] / mediasLinha[t];
            System.out.println(tamanhos[t] + " x " + tamanhos[t] + "\t"
                    + arredonda(mediasLinha[t]) + "\t\t"
                    + arredonda(mediasColuna[t]) + "\t\t"
                    + arredonda(razao) + "x");
        }
    }
}
 
