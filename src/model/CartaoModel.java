/*package model;

import java.time.LocalDate;  // Importa LocalDate para manipular datas corretamente
import java.time.format.DateTimeFormatter; // Para formatar a data de expiração

 public class CartaoModel {
    private int id; //  Identificador unico do cartão.
    private String numero; // Numeroo do cartão
    private  String nomeTitular;
    private LocalDate dataExpiracao; //Usa localDate ao invés de String para representar a data.
    private  int cvv;

    //Construtor vazio (permite criar um objeto sem inicializar os valores)
    public CartaoModel() {}

    //Construtor com parâmetros
    public CartaoModel (int id, String numero, String nomeTitular, String dataExpiracao, int cvv) {
        this.id = id; // Define o ID do cartão.
        setNumero(numero); // Chama o setter para aplicar a validação do numero do cartão.
        this.nomeTitular = nomeTitular; // Define o nome do titular do cartão.
        setDataExpiracao(dataExpiracao); // Converte a String para LocalDate e valida a data.
        setCvv(cvv); //Chama o setter para definir e validar o cvv.
    }

    //Getters e Setters

    public int getId() {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    //Getter para o número do cartão (retorna o numero mascarado por segurança)
    public String getNumero() {
        //Exibe apenas os 4 ultimos digitos do cartão, mascarando os outros.
        return "**** **** **** " + numero.substring(numero.length() - 4);
    }

    //Setter para definir o número do cartão com validação
    public void setNumero (String numero) {
        // Verifica se o numero tem exatamente 16 digitos e contém apenas números
        if (numero.matches("\\d{16}")) {
            this.numero = numero;
        } else {
            //Se o numero for invalido, lança uma exceção para evitar dados incorretos.
            throw new IllegalArgumentException("Número do cartão inválido! Deve conter exatamente 16 dígitos numéricos.");
        }
    }

    //Getter para o nome do titular do cartão
    public String getNomeTitular() {
        return nomeTitular;
    }

    //Setter para definir o nome do titular do cartão.
    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    //Getter para data de expiração (retorna a data formatada como MM/YYYY)
    public String getDataExpiracao() {
        return dataExpiracao.format(DateTimeFormatter.ofPattern("MM/yyyy"));
    }

    //Setter para definir a data de expiração com validação
    public void setDataExpiracao(String dataExpiracao) {
        try {
            // Define um formatador para converter a data de String para LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");

            // Converte a String para LocalDate assumindo o primeiro dia do mês (01/MM/yyyy)
            this.dataExpiracao = LocalDate.parse("01/" + dataExpiracao, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            //Verifica se a data de expiração já passou.
            if (this.dataExpiracao.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Data de expiração inválida! O cartão já expirou.");
            }
        } catch (Exception e) {
            //See o formato for inválido, lança uma exceção informando o erro.
            throw new IllegalArgumentException("Formato da data inválido! Use MM/yyyy.");
        }
        }

        //Getter para o CVV (retorna o valor mascarado por segurança).
        public int getCvv() {
            return 000; // O CVV nunca é exibido diretamente por motivos de segurança
    }

    //Setter para definir o CVV com validação
    public void setCvv(int cvv) {
        //Verifica se o CVV tem 3 ou 4 digitos
        if (cvv >= 100 && cvv <= 9999) {
            this.cvv = cvv;
        } else {
            // Se o CVV for inválido, lança uma exceção para evitar dados incorretos
            throw new IllegalArgumentException("CVV inválido! Deve conter 3 ou 4 dígitos.");
        }
    }

    //Sobrescreve o métodos toString para exibir os dados do cartão de forma segura.
    @Override
    public String toString() {
        return "CartaoModel{" +
                "id=" + id +  // Exibe o ID do cartão
                ", numero='" + getNumero() + '\'' + // Usa o getter para exibir o número mascarado
                ", nomeTitular='" + nomeTitular + '\'' + // Exibe o nome do titular
                ", dataExpiracao='" + getDataExpiracao() + '\'' + // Exibe a data formatada
                ", cvv='***'" + // O CVV não é mostrado por segurança
                '}';
    }
    }*/

package model;

import java.time.LocalDate;  // Importa LocalDate para manipular datas corretamente
import java.time.format.DateTimeFormatter; // Para formatar a data de expiração

public class CartaoModel {
    private int id; // Identificador único do cartão.
    private String numero; // Número do cartão (mascarado no getter)
    private String nomeTitular; // Nome do titular do cartão
    private LocalDate dataExpiracao; // Usa LocalDate para representar a data corretamente
    private int cvv; // Código de segurança do cartão
    private double limiteTotal; // Limite total do cartão
    private double saldoUtilizado; // Valor já utilizado do limite do cartão

    // Construtor vazio (permite criar um objeto sem inicializar os valores)
    public CartaoModel() {}

    // Construtor com parâmetros
    public CartaoModel(int id, String numero, String nomeTitular, String dataExpiracao, int cvv, double limiteTotal, double saldoUtilizado) {
        this.id = id; // Define o ID do cartão
        setNumero(numero); // Aplica a validação do número do cartão
        this.nomeTitular = nomeTitular; // Define o nome do titular
        setDataExpiracao(dataExpiracao); // Converte a String para LocalDate e valida a data
        setCvv(cvv); // Aplica a validação do CVV
        setLimiteTotal(limiteTotal); // Define o limite total do cartão
        setSaldoUtilizado(saldoUtilizado); // Define o saldo utilizado
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter para o número do cartão (retorna o número mascarado por segurança)
    public String getNumero() {
        return "**** **** **** " + numero.substring(numero.length() - 4);
    }

    // Setter para definir o número do cartão com validação
    public void setNumero(String numero) {
        if (numero.matches("\\d{16}")) {
            this.numero = numero;
        } else {
            throw new IllegalArgumentException("Número do cartão inválido! Deve conter exatamente 16 dígitos numéricos.");
        }
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    // Getter para a data de expiração (retorna a data formatada como MM/yyyy)
    public String getDataExpiracao() {
        return dataExpiracao.format(DateTimeFormatter.ofPattern("MM/yyyy"));
    }

    // Setter para definir a data de expiração com validação
    public void setDataExpiracao(String dataExpiracao) {
        try {
            this.dataExpiracao = LocalDate.parse("01/" + dataExpiracao, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            if (this.dataExpiracao.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Data de expiração inválida! O cartão já expirou.");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato da data inválido! Use MM/yyyy.");
        }
    }

    // Getter para o CVV (retorna um valor mascarado por segurança)
    public int getCvv() {
        return 000; // O CVV nunca é exibido diretamente
    }

    // Setter para definir o CVV com validação
    public void setCvv(int cvv) {
        if (cvv >= 100 && cvv <= 9999) {
            this.cvv = cvv;
        } else {
            throw new IllegalArgumentException("CVV inválido! Deve conter 3 ou 4 dígitos.");
        }
    }

    // Getter para o limite total do cartão
    public double getLimiteTotal() {
        return limiteTotal;
    }

    // Setter para definir o limite total do cartão (não pode ser negativo)
    public void setLimiteTotal(double limiteTotal) {
        if (limiteTotal < 0) {
            throw new IllegalArgumentException("O limite total do cartão não pode ser negativo.");
        }
        this.limiteTotal = limiteTotal;
    }

    // Getter para o saldo utilizado do cartão
    public double getSaldoUtilizado() {
        return saldoUtilizado;
    }

    // Setter para definir o saldo utilizado (não pode ultrapassar o limite total)
    public void setSaldoUtilizado(double saldoUtilizado) {
        if (saldoUtilizado < 0) {
            throw new IllegalArgumentException("O saldo utilizado não pode ser negativo.");
        }
        if (saldoUtilizado > limiteTotal) {
            throw new IllegalArgumentException("O saldo utilizado não pode ser maior que o limite total.");
        }
        this.saldoUtilizado = saldoUtilizado;
    }

    // Método para verificar o saldo disponível no cartão
    public double getSaldoDisponivel() {
        return limiteTotal - saldoUtilizado;
    }

    // Sobrescreve o método toString para exibir os dados do cartão de forma segura
    @Override
    public String toString() {
        return "CartaoModel{" +
                "id=" + id +
                ", numero='" + getNumero() + '\'' +
                ", nomeTitular='" + nomeTitular + '\'' +
                ", dataExpiracao='" + getDataExpiracao() + '\'' +
                ", cvv='***'" + // CVV mascarado por segurança
                ", limiteTotal=" + limiteTotal +
                ", saldoUtilizado=" + saldoUtilizado +
                ", saldoDisponivel=" + getSaldoDisponivel() +
                '}';
    }
}


