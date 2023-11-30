import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.*;
public class Main {
    static Scanner tec = new Scanner(System.in);
    static ArrayList<Filial> filiais = new ArrayList<>();
    static String linha;

    public static void main(String[] args) throws IOException {
        abrirEstoque();
        int soma = 0, e = 0, precoMenor, p = 0, opcao = -1;
        String titulo, categoria;
        double multi;

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
                    "10 - Criar uma nova filial\n" +
                    "11 - Buscar livros por código\n" +
                    "0 - Encerrar atividades\n" +
                    "===================================\n");

            System.out.println("Digite uma ação!");

            opcao = tec.nextInt();

            switch (opcao) {
                case 0:
                    System.out.println("Quer sair sem atualizar o estoque?\n"+
                            "0) Sair sem salvar\n"+
                            "9) Atualizar estoque");
                    opcao = tec.nextInt();
                    if(opcao == 0) {
                        break;
                    }else if (opcao == 9) {
                        salvarNoArquivo();
                       break;
                    }
                    break;
                case 1:
                    novoLivro();
                    break;
                case 2, 8:
                    listar();
                    break;
                case 3:
                    buscaTitulo();
                    break;
                case 4:
                    buscaCategoria();
                    break;
                case 5:
                    buscaValor();
                    break;
                case 6:
                    buscaEstoque();
                    break;
                case 7:
                    valorEstoque();
                    break;
                case 9:
                    salvarNoArquivo();
                    break;
                case 10:
                    novaFilial();
                    break;
                case 11:
                    buscaCodigo();
                    break;
                default:
                    System.out.println("Digite uma opção válida!");
                    break;
            }     }
    }
    public static void novoLivro(){
    System.out.println("Qual filial quer cadastrar?");
        int codigoFilial = tec.nextInt();
        boolean foundFilial = false;

        for(Filial filial : filiais) {
            if(codigoFilial == filial.codigo){
                foundFilial = true;
                filial.livros.add(new Livro());
            }
        }
        //filial.livros.add(new Livro());
        System.out.println("O que deseja fazer agora?");
    }
    public static void novaFilial(){
        filiais.add(new Filial());
        System.out.println("Filial criada com sucesso");
    }
    public static void salvarNoArquivo() throws IOException {

            FileWriter fileWriter = new FileWriter("src/Estoque.txt", true);
            PrintWriter writer = new PrintWriter(fileWriter);
            limpar();
            for (Filial filial : filiais) {
                String linha = "#FL" + filial.codigo + "," +
                        filial.nome + "," +
                        filial.endereco + "," +
                        filial.contato;
                writer.println(linha);

                for( Livro livro : filial.livros){
                    linha = livro.codigo + "," +
                            livro.titulo + "," +
                            livro.ano + "," +
                            livro. categoria + "," +
                            livro.editora + "," +
                            livro.valor + "," +
                            livro.qtdEstoque;
                    writer.println(linha);
                }
            }
            writer.close();
            fileWriter.close();

            System.out.println("Dados dos livros e filiais foram escritos no arquivo 'Estoque.txt'.");

    }
    public static void listar(){
        System.out.println("Em qual filial quer ver os livros?");
        int codigoFilial = tec.nextInt();
        boolean boofilial = false;
        for(Filial filial : filiais){
            if(codigoFilial == filial.codigo){
                boofilial = true;
                filial.info();
                for(Livro livro : filial.livros) {
                livro.info();
                }

            }
        }
        if(!boofilial){
            System.out.println("Filial não existe");
        }
    }
    public static void buscaTitulo() {

        System.out.print("Em qual filial você quer ver os livros: ");
        int codigofilial = tec.nextInt();
        tec.nextLine();
        boolean foundfilial = false;
        for(Filial filial : filiais){
            if (codigofilial == filial.codigo) {
                foundfilial = true;
                if (filial.livros.isEmpty()) {
                    System.out.println("Não existem livros nesta filial!");
                } else {
                    System.out.print("Digite o nome do livro que deseja buscar: ");
                    String nome = tec.nextLine();
                    boolean foundlivro = false;
                    for (Livro livro : filial.livros) {
                        if (nome.equals(livro.titulo)) {
                            livro.info();
                            foundlivro = true;
                        }
                    }
                    if (!foundlivro) {
                        System.out.println("Não existem livros com este título!\n");
                    }
                }
            }
        }
        if(!foundfilial){
            System.out.println("Filial inexistente!");
        }

    }
    public static void buscaCategoria(){

        System.out.print("Em qual filial você quer ver os livros: ");
        int codigofilial = tec.nextInt();
        tec.nextLine();
        boolean foundfilial = false;
        for(Filial filial : filiais){
            if (codigofilial == filial.codigo) {
                foundfilial = true;
                if (filial.livros.isEmpty()) {
                    System.out.println("Não existem livros nesta filial!");
                } else {
                    System.out.print("Digite a categoria do livro que deseja buscar: ");
                    String cat = tec.nextLine();
                    boolean foundlivro = false;
                    for (Livro livro : filial.livros) {
                        if (cat.equals(livro.categoria)) {
                            livro.info();
                            foundlivro = true;
                        }
                    }
                    if (!foundlivro) {
                        System.out.println("Não existem livros com essa categoria!");
                    }
                }
            }
        }
        if(!foundfilial){
            System.out.println("Filial inexistente!");
        }
    }
    public static void buscaValor(){
        tec.nextLine();
        System.out.print("Informe em qual filial deseja ver os livros: ");
        int codfilial = tec.nextInt();
        tec.nextLine();
        boolean foundfilial = false;
        for(Filial filial : filiais){
            if (codfilial == filial.codigo) {
                foundfilial = true;
                if (filial.livros.isEmpty()) {
                    System.out.println("Não existem livros nesta filial!");
                } else {
                    System.out.print("Digite o preço minimo dos livros que deseja: ");
                    double preco = tec.nextDouble();
                    tec.nextLine();
                    boolean foundpreco = false;
                    boolean precoprint = false;

                    for (Livro livro : filial.livros) {
                        if (preco <= livro.valor) {
                            foundpreco = true;
                            if (!precoprint) {
                                precoprint = true;
                            }
                            livro.info();
                        }
                    }
                    if(!foundpreco){
                        System.out.println("Não existem livros abaixo deste valor!");
                    }
                }
            }
        }
        if(!foundfilial){
            System.out.println("Filial inexistente!");
        }
    }
    public static void buscaEstoque(){
        tec.nextLine();
        System.out.print("Informe em qual filial deseja ver os livros: ");
        int codfilial = tec.nextInt();
        tec.nextLine();
        boolean foundfilial = false;
        for(Filial filial : filiais){
            if (codfilial == filial.codigo) {
                foundfilial = true;
                System.out.print("Digite a quantidade em estoque do livro que deseja buscar: ");
                int estoque = tec.nextInt();
                tec.nextLine();
                boolean foundestoque = false;
                boolean estoqueprint = false;

                for (Livro livro : filial.livros) {
                    if (estoque <= livro.qtdEstoque) {
                        foundestoque = true;
                        if (!estoqueprint) {
                            estoqueprint = true;
                        }
                        livro.info();
                    }
                }
                if(!foundestoque){
                    System.out.println("Não existe nenhum livro com mais que essa quantidade em estoque informada!");
                }
            }
        }
        if(!foundfilial){
            System.out.println("\nFilial inexistente!");
        }
    }
    public static void valorEstoque(){
        tec.nextLine();
        System.out.print("Informe em qual filial deseja ver os livros: ");
        int codfilial = tec.nextInt();
        tec.nextLine();
        boolean foundfilial = false;
        for(Filial filial : filiais){
            if (codfilial == filial.codigo) {
                foundfilial = true;
                if (filial.livros.isEmpty()) {
                    System.out.println("Não existem livros nesta filial!");
                } else {
                    double totalestoque = 0;
                    for (Livro livro : filial.livros) {
                        totalestoque += (livro.valor * livro.qtdEstoque);
                    }
                    System.out.println("O total de valor em estoque é R$ " + totalestoque);
                }
            }
        }
        if(!foundfilial){
            System.out.println("Filial inexistente!");
        }
    }
    public static void listarEstoque() throws FileNotFoundException {

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void abrirEstoque() throws FileNotFoundException {

        FileInputStream file = new FileInputStream("src/Estoque.txt");
        Scanner read = new Scanner(file);
        String[] SL;
        Filial atualFilial = null;

        while(read.hasNextLine()){
            linha = read.nextLine();
            SL = linha.split(",");

            if(SL[0].startsWith("#FL")){
                atualFilial = new Filial(Integer.parseInt(SL[0].replace("#FL","")),SL[1],SL[2],SL[3]);
                atualFilial.livros = new ArrayList<>();
                filiais.add(atualFilial);
            }
            if (atualFilial != null  && SL.length == 7){
                atualFilial.livros.add(new Livro(SL[0], SL[1], Integer.parseInt(SL[2]), SL[3], SL[4], Double.parseDouble(SL[5].replace("R$", "").replace(",", "").trim()), Integer.parseInt(SL[6])));
            }
        }
    }
    public static void limpar() throws IOException {
        String limpar = "src/Estoque.txt";
        FileWriter fileWriter = new FileWriter(limpar, false);
        fileWriter.close();
    }

    public static void buscaCodigo(){

        String title = "";
        String editora = "";
        String categoria = "";
        int ano = 0;
        tec.nextLine();
        System.out.print("Informe o código do livro que deseja buscar: ");
        String code = tec.nextLine();
        boolean foundlivro = false;
        for(Filial filial : filiais){
            for(Livro livro : filial.livros){
                if(code.equals(livro.codigo)){
                    foundlivro = true;
                    title = livro.titulo;
                    editora = livro.editora;
                    categoria = livro.categoria;
                    ano = livro.ano;
                }
            }
        }
        if(foundlivro){
            System.out.println(">>>>>Cod#"+ code);
            System.out.println("Titulo/Editora: " + title+"/"+editora);
            System.out.println("Categoria: "+ categoria);
            System.out.println("Ano: "+ ano);
        }
        else{
            System.out.println("Não existem livros com esse código");
        }

    }
}
