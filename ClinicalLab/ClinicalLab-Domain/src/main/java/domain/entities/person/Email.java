package domain.entities.person;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    private String emailText;
    
    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public Email(String emailText) {
        validateEmail(emailText);
        this.emailText = emailText;
    }

    public String getEmailText() {
        return emailText;
    }

    public void setEmailText(String emailText) {
        validateEmail(emailText);
        this.emailText = emailText;
    }

    private void validateEmail(String emailText) {
        if (emailText == null || emailText.isEmpty()) {
            throw new IllegalArgumentException("O e-mail não pode ser nulo ou vazio.");
        }
        Matcher matcher = pattern.matcher(emailText);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Formato de e-mail inválido: " + emailText);
        }
    }
}
