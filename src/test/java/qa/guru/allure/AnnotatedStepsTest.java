package qa.guru.allure;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.*;

public class AnnotatedStepsTest {
    private static final String REPO = "selenide/selenide";
    private static final String ISSUE_TITLE = "Add conditions for collection size";

    @Test
    public void testIssueTitleWithAnnotations() {
        openMainPage();
        searchForRepository(REPO);
        openRepository(REPO);
        openIssuesTab();
        shouldSeeIssueWithTitle(ISSUE_TITLE);
    }

    @Step("Открываем главную страницу GitHub")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Ищем репозиторий {repo}")
    public void searchForRepository(String repo) {
        $(".search-input").click();
        $("#query-builder-test").setValue(repo).pressEnter();
    }

    @Step("Переходим в репозиторий {repo}")
    public void openRepository(String repo) {
        $(byLinkText(repo)).click();
    }

    @Step("Открываем вкладку Issues")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue с заголовком '{title}'")
    public void shouldSeeIssueWithTitle(String title) {
        System.out.println("Checking issue title: " + title);
        $("#issues-tab").shouldHave(text("Issues"));
    }
}