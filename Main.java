import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner tec = new Scanner(System.in);

        int soma = 0, e = 0, precoMenor, p = 0, opcao = -1;
        String titulo, categoria;
        double multi;
        double[] array = new double[99];

        ArrayList<Livro> livros = new ArrayList<>();

        while (true) {
            System.out.println("" +
                    "===================================\n" +
                    "1 - Cadastrar novo livro\n" +
                    "2 - Listar livros\n" +
                    "3 - Buscar livros por nome\n" +
                    "4 - Buscar livros por categoria\n" +
                    "5 - Buscar livros por preço\n" +
                    "6 - Busca por quantidade em estoque\n" +
                    "7 - Valor total no estoque\n" +
                    "8 - Listar livros no estoque\n" +
                    "9 - Gravar os novos livros no estoque\n" +
                    "0 - Encerrar atividades\n" +
                    "===================================\n");

            System.out.println("Digite uma ação!");

            opcao = tec.nextInt();

            if (opcao == 0) {
                System.out.println("Programa finalizado!");
                break;
            }

            switch (opcao) {

                case 1:
                    livros.add(new Livro());
                    System.out.println("O que deseja fazer agora?");
                    break;
                case 2:
                    for (int j = 0; j < livros.size(); j++) {
                        livros.get(j).info();
                    }
                    break;
                case 3:
                    System.out.println("Informe o titulo do livro a ser procurado");

                    tec.nextLine();
                    titulo = tec.nextLine();

                    for (int j = 0; j < livros.size(); j++) {
                        if (titulo.equals(livros.get(j).titulo)) {
                            livros.get(j).info();
                        } else {
                            System.out.println("Título não encontrado!");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Informe a categoria do livro a ser procurado");

                    tec.nextLine();
                    categoria = tec.nextLine();

                    for (int j = 0; j < livros.size(); j++) {
                        if (categoria.equals(livros.get(j).categoria)) {
                            livros.get(j).info();
                        } else {
                            System.out.println("Categoria não encontrada!");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Informe o valor do livro a ser procurado");

                    double preco = tec.nextDouble();

                    for (int j = 0; j < livros.size(); j++) {
                        if (preco == livros.get(j).valor) {
                            livros.get(j).info();
                            p++;
                        } else {
                            System.out.println("Valor não encontrado!");
                        }
                    }
                    precoMenor = livros.size() - p;
                    System.out.println("Exitem " + precoMenor + " livros com o menor que R$" + preco);
                    break;
                case 6:
                    System.out.println("Informe o estoque do livro a ser procurado");

                    int estoque = tec.nextInt();

                    for (int j = 0; j < livros.size(); j++) {
                        if (estoque >= livros.get(j).qtdEstoque) {
                            livros.get(j).info();
                        } else {
                            System.out.println("Estoque não encontrado!");
                        }
                    }
                    break;
                case 7:
                    while (e < livros.size()) {
                        array[e] = livros.get(e).qtdEstoque * livros.get(e).valor;
                        e++;
                    }
                    for (int c = 0; c < array.length; c++) {
                        soma += array[c];
                    }
                    System.out.println("O valor total contido no estoque é: " + soma + "R$");
                    break;
                default:
                    System.out.println("Digite uma opção válida!");
                    break;
                case 8:
                    FileInputStream registro = new FileInputStream("src/Estoque.txt");
                    Scanner registrar = new Scanner(registro);
                    String linha;
                    String[] SL;

                    try (BufferedReader reader = new BufferedReader(new FileReader("src/Estoque.txt"))) {
                        while ((linha = reader.readLine()) != null) {

                            System.out.println(linha);

                            String[] dados = linha.split(",");
                            String codigo = dados[0];
                            String titulo1 = dados[1];
                            String ano = dados[2];
                            String categoria1 = dados[3];
                            String editora = dados[4];
                            String valor = dados[5];
                            String qtdEstoque = dados[6];

                            new Livro(dados[0], dados[1], Integer.parseInt(dados[2]), dados[3], dados[4].trim(), Double.parseDouble(dados[5].replace("R$", "").replace(",", "").trim()), Integer.parseInt(dados[6]));
                        }
                    }
            break;
                case 9:
                    try {
                        FileWriter fileWriter = new FileWriter("src/Estoque.txt", true);
                        BufferedWriter writer = new BufferedWriter(fileWriter);

                        for (Livro livro : livros) {
                            String texto = livro.getCodigo() + "," + livro.getTitulo() + "," + livro.getAno() + "," +
                                    livro.getCategoria() + "," + livro.getEditora() + "," + livro.getValor() + "," + livro.getQtdEstoque();
                            writer.write(texto);
                            writer.newLine();
                        }

                        writer.close();
                        fileWriter.close();

                        System.out.println("Dados dos livros foram escritos no arquivo 'Estoque.txt'.");
                    } catch (IOException o) {
                        o.printStackTrace();
                    }
            }
        }
    }
}