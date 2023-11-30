import java.util.ArrayList;
import java.util.Scanner;

public class Filial {
    String nome, endereco, contato;
    int codigo;

    ArrayList<Livro> livros;

    public Filial(){
        Scanner tec = new Scanner(System.in);

        System.out.println("Qual o nome da filial?");
        this.nome = tec.nextLine();
        System.out.println("Qual o código da filial?");
        this.codigo = tec.nextInt();
        System.out.println("Qual o endereço da filial?");
        tec.nextLine();
        this.endereco = tec.nextLine();
        System.out.println("Qual o contato da  filial?");
        this.contato = tec.nextLine();
        livros = new ArrayList<>();
    }
    public Filial(int codigo, String nome, String endereco, String contato){

        this.nome = nome;
        this.endereco = endereco;
        this.codigo = codigo;
        this.contato = contato;
        this.livros = new ArrayList<>();
    }

    public void info(){

        System.out.println("=========================================================");
        System.out.println(">>>>>> #FL" + this.codigo);
        System.out.println("Nome: " + this.nome);
        System.out.println("Endereço : " + this.endereco);
        System.out.println("Contato: " + this.contato);
        System.out.println("=========================================================");

        /*for(Livro livro : livros){
            livro.info();
        }*/
    }
}
