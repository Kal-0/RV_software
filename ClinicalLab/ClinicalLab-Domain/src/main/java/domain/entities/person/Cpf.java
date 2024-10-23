package domain.entities.person;

public class Cpf {
    private String cpf;

    // Expressão regular para validar o formato do CPF
    private static final String CPF_PATTERN = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"
    		+ "|\\d{9}-\\d{2}"
    		+ "|\\d{11}";

    public Cpf(String cpf) {
        validateCpf(cpf);
        this.cpf = formatCpf(cpf);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        validateCpf(cpf);
        this.cpf = formatCpf(cpf);
    }

    // Método para validar o formato do CPF
    private void validateCpf(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("O CPF não pode ser nulo ou vazio.");
        }
        if (!cpf.matches(CPF_PATTERN)) {
            throw new IllegalArgumentException("Formato de CPF inválido: " + cpf);
        }
//        if (!isValidCpf(cpf)) {
//            throw new IllegalArgumentException("CPF inválido: " + cpf);
//        }
    }

    

    // Método para calcular o dígito verificador do CPF
    private int calculateCpfDigit(String cpf) {
        int sum = 0;
        for (int i = 0; i < cpf.length(); i++) {
            sum += (cpf.charAt(i) - '0') * (cpf.length() + 1 - i);
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }

 // Método para validar se o CPF possui dígitos verificadores válidos
    private boolean isValidCpf(String cpf) {
        // Remove os caracteres não numéricos (pontos e hífen) do CPF
        cpf = cpf.replaceAll("\\D", "");

        // CPF com todos os dígitos iguais é inválido
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            // Calcula os dois dígitos verificadores e verifica se são válidos
            int digito1 = calculateCpfDigit(cpf.substring(0, 9));
            int digito2 = calculateCpfDigit(cpf.substring(0, 9) + digito1);

            return cpf.equals(cpf.substring(0, 9) + digito1 + digito2);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return false;
        }
    }
    
    // Método para formatar o CPF no padrão "XXX.XXX.XXX-YY"
    private String formatCpf(String cpf) {
        cpf = cpf.replaceAll("\\D", ""); // Remove todos os caracteres não numéricos
        return String.format("%s.%s.%s-%s", 
            cpf.substring(0, 3), 
            cpf.substring(3, 6), 
            cpf.substring(6, 9), 
            cpf.substring(9, 11)
        );
    }
    
    
}
