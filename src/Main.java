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

/* package model;

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
}*/

package model;

import dao.CartaoDAO;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CartaoDAO cartaoDAO = new CartaoDAO(); // Instancia o DAO para acessar o banco

        // 📌 TESTE 1: Inserir um novo cartão no banco
        CartaoModel novoCartao = new CartaoModel(
                0, // O banco gera o ID automaticamente
                "1234567812345678", // Número do cartão válido
                "Pedro Silva", // Nome do titular
                "12/2026", // Data de expiração futura
                123, // CVV válido
                5000.00, // Limite total
                1000.00 // Saldo já utilizado
        );

        boolean insercaoSucesso = cartaoDAO.inserirCartao(novoCartao);
        System.out.println("✅ Cartão inserido com sucesso? " + insercaoSucesso);

        // 📌 TESTE 2: Listar todos os cartões cadastrados
        List<CartaoModel> cartoes = cartaoDAO.listarTodos();
        System.out.println("\n📋 Lista de Cartões no Banco:");
        for (CartaoModel cartao : cartoes) {
            System.out.println(cartao);
        }

        // 📌 TESTE 3: Buscar um cartão específico pelo ID
        if (!cartoes.isEmpty()) {
            int idParaBuscar = cartoes.get(0).getId(); // Pega o ID do primeiro cartão inserido
            CartaoModel cartaoBuscado = cartaoDAO.buscarPorId(idParaBuscar);
            System.out.println("\n🔍 Cartão encontrado por ID: " + cartaoBuscado);
        }

        // 📌 TESTE 4: Atualizar os dados do cartão
        if (!cartoes.isEmpty()) {
            CartaoModel cartaoParaAtualizar = cartoes.get(0);
            cartaoParaAtualizar.setSaldoUtilizado(2000.00); // Atualiza o saldo utilizado
            cartaoParaAtualizar.setNomeTitular("Pedro Oliveira"); // Muda o nome do titular

            boolean atualizacaoSucesso = cartaoDAO.atualizarCartao(cartaoParaAtualizar);
            System.out.println("\n✏️ Cartão atualizado com sucesso? " + atualizacaoSucesso);
        }

        // 📌 TESTE 5: Deletar um cartão do banco
        if (!cartoes.isEmpty()) {
            int idParaDeletar = cartoes.get(0).getId(); // Pega o ID do primeiro cartão
            boolean deletado = cartaoDAO.deletarCartao(idParaDeletar);
            System.out.println("\n🗑️ Cartão deletado com sucesso? " + deletado);
        }
    }
}
