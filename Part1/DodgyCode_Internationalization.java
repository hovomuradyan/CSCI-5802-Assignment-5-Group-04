import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class DodgyCode_Internationalization {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        System.out.println(line.length());
    }
}