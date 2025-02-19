//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
/*public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
    }
}*/


package model;

public class Main {
    public static void main(String[] args) {
        try {
            // Criando um cartão válido
            CartaoModel cartao = new CartaoModel(1, "1234567812345678", "Pedro Alves", "12/2026", 123);

            // Exibindo os detalhes do cartão (dados mascarados)
            System.out.println(cartao);

            // Tentando criar um cartão com número inválido
            CartaoModel cartaoInvalido = new CartaoModel(2, "1234", "Erro Exemplo", "12/2026", 123);

        } catch (IllegalArgumentException e) {
            // Exibe a mensagem de erro se houver problemas com os dados do cartão
            System.out.println("Erro: " + e.getMessage());
        }

        try {
            // Tentando criar um cartão com CVV inválido
            CartaoModel cartaoCvvErrado = new CartaoModel(3, "1234567812345678", "Maria Souza", "12/2026", 99);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro no CVV: " + e.getMessage());
        }

        try {
            // Tentando criar um cartão com data de expiração inválida (passada)
            CartaoModel cartaoExpirado = new CartaoModel(4, "8765432187654321", "João Santos", "01/2020", 456);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro na data de expiração: " + e.getMessage());
        }
    }
}

