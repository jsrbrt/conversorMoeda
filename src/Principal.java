import java.io.IOException;

import java.util.InputMismatchException;

import java.util.Scanner;



public class Principal{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int opcao = 0;
        double valor;
        String endereco, moeda;
        Calculadora calc = new Calculadora();

        do {
            try {
                System.out.println("****************************************");
                System.out.println("Seja bem vindo ao conversor de moedas XD");
                System.out.println("Insira uma das opções abaixo:\n");
                System.out.println("1)Dólar -> Real Brasileiro");
                System.out.println("2)Dólar -> Peso Colombiano");
                System.out.println("3)Dólar -> Peso Argentino ");
                System.out.println("4)Real Brasileiro -> Dólar");
                System.out.println("5)Peso Colombiano -> Dólar");
                System.out.println("6)Peso Argentino  -> Dólar");
                System.out.println("7)Sair");
                System.out.println("****************************************\n");
                opcao = scan.nextInt();
                
                if (opcao == 7) break;
                
                System.out.print("Insira o valor: ");
                valor = scan.nextDouble();

                switch (opcao) {
                    case 1:
                        endereco = "https://v6.exchangerate-api.com/v6/c79957ae827de732a205498c/latest/USD";
                        moeda = "BRL";
                        break;
                    case 2:
                        endereco = "https://v6.exchangerate-api.com/v6/c79957ae827de732a205498c/latest/USD";
                        moeda = "COP";
                        break;
                    case 3:
                        endereco = "https://v6.exchangerate-api.com/v6/c79957ae827de732a205498c/latest/USD";
                        moeda = "ARS";
                        break;
                    case 4:
                        endereco = "https://v6.exchangerate-api.com/v6/c79957ae827de732a205498c/latest/BRL";
                        moeda = "USD";
                        break;
                    case 5:
                        endereco = "https://v6.exchangerate-api.com/v6/c79957ae827de732a205498c/latest/COP";
                        moeda = "USD";
                        break;
                    case 6:
                        endereco = "https://v6.exchangerate-api.com/v6/c79957ae827de732a205498c/latest/ARS";
                        moeda = "USD";
                        break;
                    default:
                        System.out.println("Insira um número da lista!!");
                        continue;
                }
                System.out.printf("%.2f %s é igual a %.2f USD", valor, moeda, calc.retornarValor(endereco, moeda, valor));
                

                } catch (InputMismatchException e) {
                    System.out.println("Insira um número!");
                    scan.nextLine(); 
                    continue;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } 
        } while (true);
        
        scan.close();
    }

    
}