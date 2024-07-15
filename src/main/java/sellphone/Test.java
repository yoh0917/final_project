package sellphone;
import java.util.regex.Pattern;

public class Test {
	
	
	
    public static boolean isValidEmail(String email) {
        // Regular expression to validate email format, including no consecutive dots
        String emailRegex = "^[a-zA-Z0-9][a-zA-Z0-9._%+-]*@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        // Compile pattern
        Pattern pattern = Pattern.compile(emailRegex);

        // Match email format
        return pattern.matcher(email).matches();
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(isValidEmail("test@example.com")); // true
        System.out.println(isValidEmail("test..example@example.com")); // false
        System.out.println(isValidEmail("test@ example.com")); // false
        System.out.println(isValidEmail("test@@example.com")); // false
        System.out.println(isValidEmail("@example.com")); // false
        System.out.println(isValidEmail("test@.com")); // false
        System.out.println(isValidEmail("test@com.")); // false
    }
}