package setup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HomePageAnswers {
    public static String getAnswerLineFromFile(int questionNumber) throws IOException {
        String answer = Files.readAllLines(Paths.get("src\\files\\answers.txt")).get(questionNumber);
        return answer;
    }
}


