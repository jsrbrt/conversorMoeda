import java.io.IOException;

import java.util.InputMismatchException;

import java.util.Scanner;



public class PrincipalT{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int opcao = 0;
        double valor;
        String endereco, moeda1, moeda2, sigla1, sigla2;
        Calculadora calc = new Calculadora();

        do {
            try {
                System.out.println("****************************************");
                System.out.println("Welcome to the Currency converter XD");
                System.out.println("Choose one option from below:\n");
                System.out.println("1)Enter value");
                System.out.println("2)Leave");
                System.out.println("****************************************\n");
                opcao = scan.nextInt();
                
                if (opcao == 2) break;

                System.out.print("Enter the value: ");
                valor = scan.nextDouble();
                scan.nextLine();
                System.out.println("Enter the name of the original Currency: ");
                moeda1 = scan.nextLine();
                System.out.println("Enter the name of the final Currency: ");
                moeda2 = scan.nextLine();
                
                sigla1 = calc.retornarSigla(moeda1).replace(" ", "+");
                endereco = "https://v6.exchangerate-api.com/v6/c79957ae827de732a205498c/latest/" + sigla1;
                sigla2 = calc.retornarSigla(moeda2);

                System.out.printf("%.2f %s its equivalent to %.2f %s\n", valor, sigla1, calc.retornarValor(endereco, sigla2, valor), sigla2);
                //brazilian real united states dollar

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