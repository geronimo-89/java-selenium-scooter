package chrometests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import setup.SetUpChrome;
import static setup.HomePageAnswers.getAnswerLineFromFile;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class HomeFAQAnswersTest extends SetUpChrome {

    private int questionNumber;
    private String expectedAnswer;

    public HomeFAQAnswersTest(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    @Parameterized.Parameters(name = "Тестовые данные: Вопрос {0}")
    public static Object[][] answersData() {
        return new Object[][]{
                {0},
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},
        };
    }

    @Test
    public void checkFAQAnswer() throws Exception {
        expectedAnswer = getAnswerLineFromFile(questionNumber);
        assertEquals(
                expectedAnswer,
                homePage
                        .scrollToQuestion(questionNumber)
                        .getAnswer(questionNumber)
        );
    }
}
