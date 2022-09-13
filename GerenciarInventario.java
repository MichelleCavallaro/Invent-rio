package cli;

import modelo.Produto;

import java.awt.desktop.SystemEventListener;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/***
 * ----PROJETO INVENTARIO----
 * CADASTRAR PRODUTOS
 * ENTRADA PRODUTOS
 * VENDA PRODUTOS
 * LISTAR PRODUTOS
 * ESTOQUE ATUAL DA LOJA
 */

public class GerenciarInventario {

    public List<Produto> listaProd = new ArrayList<>();

    public static void main(String[] args) {
        GerenciarInventario gi = new GerenciarInventario();
        Scanner sc = new Scanner(System.in);
        ExibirMenu(gi, sc);

    }

    private static void ExibirMenu(GerenciarInventario gi, Scanner sc) {
        int opc = 0;
        do{
            System.out.println("---------MENU---------");
            System.out.println("1. Cadastrar Produto.");
            System.out.println("2. Entrada de Produto.");
            System.out.println("3. Saída de Produto.");
            System.out.println("4. Listar Produtos.");
            System.out.println("5. Estoque Atual.");
            System.out.println("9. Sair.");
            System.out.println("----------------------");
            System.out.printf("Digite um oção: ");
            opc = Integer.parseInt(sc.nextLine());
            switch (opc){
                case 1:
                    gi.execCadastrar(sc);
                    break;
                case 2:
                    gi.execEntrada(sc);
                    break;
                case 3:
                    gi.execSaida(sc);
                    break;
                case 4:
                    gi.execListar(sc);
                    break;
                case 5:
                    gi.execEstoqueAtual();
                    break;
                case 9:
                    System.out.println("FIM...");
                    break;
                default:
                    System.out.println("Digite uma opcao valida!");
            }
        }while(opc!=9);
    }

    public void execCadastrar(Scanner sc){
        Produto produto = new Produto();
        System.out.printf("Digite o codigo do produto: ");
        int cod = Integer.parseInt(sc.nextLine());
        produto.setCodigo(cod);
        if(!localizar(cod)){
            System.out.printf("Digite o nome do produto: ");
            produto.setNome(sc.nextLine());
            System.out.printf("Digite a quantidade: ");
            produto.setQtd(Integer.parseInt(sc.nextLine()));
            System.out.printf("Digite o preço do produto: ");
            produto.setPreco(Double.parseDouble(sc.nextLine()));
            listaProd.add(produto);
        }else{
            System.out.println("Codigo ja existente!");
        }


    }
    public void execListar(Scanner sc){
        System.out.println("Cód  - Nome          -  Qtd -   Preço");
        for(Produto p: listaProd){
            System.out.printf("%4s - %-13s - %4s - %7s \n", p.getCodigo(), p.getNome(),
                    Integer.toString(p.getQtd()), Double.toString(p.getPreco()));
        }
    }

    public void execEntrada(Scanner sc){
        int cod;
        System.out.printf("Digite o codigo do produto:");
        cod = Integer.parseInt(sc.nextLine());
        if(localizar(cod)){
            for(Produto p: listaProd) {
                if (cod == p.getCodigo()) {
                    int valorEnt;
                    System.out.printf("Digite um valor de entrada: ");
                    valorEnt = Integer.parseInt(sc.nextLine());
                    if(valorEnt<=0){
                        System.out.println("Não é possivel valor MENOR ou igual ZERO.");

                    }else {
                        p.setQtd(p.getQtd() + valorEnt);
                        break;
                    }
                }
            }
        }else {
            System.out.println("Erro! Produto nao encontrado.");
        }
    }
    public void execSaida(Scanner sc){
        int cod;
        System.out.printf("Digite o codigo do produto:");
        cod = Integer.parseInt(sc.nextLine());
        if(localizar(cod)){
            for(Produto p: listaProd) {
                if (cod == p.getCodigo()) {
                    int valorEnt;
                    System.out.printf("Digite um valor de saida: ");
                    valorEnt = Integer.parseInt(sc.nextLine());
                    if (valorEnt > p.getQtd()){
                        System.out.println("Venda impossivel! Valor venda maior que estoque.");
                    }else if (valorEnt<=0) {
                        System.out.println("Não é possivel valor MENOR ou igual ZERO.");
                    }else {
                        p.setQtd(p.getQtd() - valorEnt);
                        break;
                    }
                }
            }
        }else {
            System.out.println("Erro! Produto nao encontrado.");
        }
    }
    public boolean localizar(int codigo) {
        for(Produto p: listaProd) {
            if (codigo == p.getCodigo()) {
                return true;
            }
        }return false;
    }
    public void execEstoqueAtual(){
        if (!listaProd.isEmpty()){
            System.out.println("------------------------------------------------");
            System.out.println("Cód  - Nome          -  Qtd -   Preço -   TOTAL");
            double patrimonio = 0;
            for(Produto p: listaProd){
                double soma = p.getPreco() * p.getQtd();
                patrimonio += soma;
                System.out.printf("%4s - %-13s - %4s - %7s - %7s \n", p.getCodigo(), p.getNome(),
                        Integer.toString(p.getQtd()), Double.toString(p.getPreco()), Double.toString(soma));
            }
            System.out.println("------------------------------------------------");
            System.out.printf("Patrimonio Total: %6.2f", patrimonio);
            System.out.println("------------------------------------------------\n");
        }else {
            System.out.println("Nao contem item no inventario.");
        }
    }
}