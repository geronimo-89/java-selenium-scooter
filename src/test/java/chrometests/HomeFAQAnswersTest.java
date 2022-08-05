package chrometests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import setup.SetUpChrome;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class HomeFAQAnswersTest extends SetUpChrome {

    private int questionNumber;
    private String expectedAnswer;

    public HomeFAQAnswersTest(int questionNumber, String expectedAnswer) {
        this.questionNumber = questionNumber;
        this.expectedAnswer = expectedAnswer;
    }

    private static final String EXPECTED_ANSWER_0 = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    private static final String EXPECTED_ANSWER_1 = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    private static final String EXPECTED_ANSWER_2 = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    private static final String EXPECTED_ANSWER_3 = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    private static final String EXPECTED_ANSWER_4 = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    private static final String EXPECTED_ANSWER_5 = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    private static final String EXPECTED_ANSWER_6 = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    private static final String EXPECTED_ANSWER_7 = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    @Parameterized.Parameters(name = "Тестовые данные: Вопрос {0} - ответ {1}")
    public static Object[][] answersData() {
        return new Object[][]{
                {0, EXPECTED_ANSWER_0},
                {1, EXPECTED_ANSWER_1},
                {2, EXPECTED_ANSWER_2},
                {3, EXPECTED_ANSWER_3},
                {4, EXPECTED_ANSWER_4},
                {5, EXPECTED_ANSWER_5},
                {6, EXPECTED_ANSWER_6},
                {7, EXPECTED_ANSWER_7},
        };
    }

    @Test
    public void checkFAQAnswer() throws Exception {
        assertEquals(
                expectedAnswer,
                homePage
                        .scrollToQuestion(questionNumber)
                        .getAnswer(questionNumber)
        );
    }
}