import java.util.Scanner;
public class Livro {
     String titulo;
     String codigo;
     String editora;
     String categoria;
     int ano, qtdEstoque;
     double valor;

    public Livro(){
        Scanner tec = new Scanner(System.in);

        System.out.println("Qual o titulo do livro?");
        this.titulo = tec.nextLine();
        System.out.println("Qual o código do livro?");
        this.codigo = tec.nextLine();
        System.out.println("Qual a editora do livro?");
        this.editora = tec.nextLine();
        System.out.println("Qual a categoria do livro?");
        this.categoria = tec.nextLine();
        System.out.println("Qual o ano do livro?");
        this.ano = tec.nextInt();
        System.out.println("Qual o estoque do livro?");
        this.qtdEstoque = tec.nextInt();
        System.out.println("Qual o valor do livro?");
        this.valor = tec.nextDouble();


    }

    public Livro(String codigo, String titulo, int ano, String categoria, String editora, double valor, int qtdEstoque){

        this.codigo = codigo;
        this.titulo = titulo;
        this.ano = ano;
        this.categoria = categoria;
        this.editora = editora;
        this.valor = valor;
        this.qtdEstoque = qtdEstoque;
    }

    public void info(){
        System.out.println("=========================================================");
        System.out.println(">>>>>>" + this.codigo);
        System.out.println("Titulo/Editora: " + this.titulo + "/" + this.editora);
        System.out.println("Categoria: " + this.categoria);
        System.out.println("Ano: " + this.ano);
        System.out.println("Estoque: " + this.qtdEstoque + " unidades");
        System.out.println("Valor: R$" + this.valor);
        double total = this.valor * this.qtdEstoque;
        System.out.println("Valor total em estoque: R$" + total);
        System.out.println("=========================================================");
    }
}