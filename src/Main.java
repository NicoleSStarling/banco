//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
/*public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
    }
}


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
}  */

package model;

public class Main {
    public static void main(String[] args) {
        try {
            // Criando um cartão válido com limite e saldo inicial
            CartaoModel cartao = new CartaoModel(1, "1234567812345678", "Pedro Silva", "12/2026", 123, 5000.00, 1000.00);

            // Exibindo os detalhes do cartão
            System.out.println("🟢 Cartão Criado com Sucesso:");
            System.out.println(cartao);
            System.out.println("Saldo Disponível: " + cartao.getSaldoDisponivel());

            // Tentando definir um saldo maior que o limite (deve lançar erro)
            System.out.println("\n🔴 Testando erro: Saldo maior que o limite");
            cartao.setSaldoUtilizado(6000.00); // ERRO esperado
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erro esperado: " + e.getMessage());
        }

        try {
            // Criando um cartão com número inválido (menos de 16 dígitos)
            System.out.println("\n🔴 Testando erro: Número de cartão inválido");
            CartaoModel cartaoInvalido = new CartaoModel(2, "1234", "Erro Exemplo", "12/2026", 123, 3000.00, 500.00);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erro esperado: " + e.getMessage());
        }

        try {
            // Criando um cartão com data de expiração inválida (passada)
            System.out.println("\n🔴 Testando erro: Data de expiração inválida");
            CartaoModel cartaoExpirado = new CartaoModel(3, "8765432187654321", "João Santos", "01/2020", 456, 4000.00, 100.00);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erro esperado: " + e.getMessage());
        }

        try {
            // Criando um cartão com CVV inválido (menos de 3 dígitos)
            System.out.println("\n🔴 Testando erro: CVV inválido");
            CartaoModel cartaoCvvInvalido = new CartaoModel(4, "1234567812345678", "Maria Souza", "12/2026", 99, 2000.00, 500.00);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erro esperado: " + e.getMessage());
        }

        try {
            // Criando um cartão com limite negativo (deve lançar erro)
            System.out.println("\n🔴 Testando erro: Limite negativo");
            CartaoModel cartaoLimiteNegativo = new CartaoModel(5, "1234567812345678", "Carlos Mendes", "12/2026", 987, -1000.00, 500.00);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erro esperado: " + e.getMessage());
        }
    }
}


