package model;

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
    }

