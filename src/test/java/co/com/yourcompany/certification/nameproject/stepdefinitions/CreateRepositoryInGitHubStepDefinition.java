package co.com.yourcompany.certification.nameproject.stepdefinitions;

import co.com.yourcompany.certification.nameproject.questions.TheCurrent;
import co.com.yourcompany.certification.nameproject.questions.TheRepository;
import co.com.yourcompany.certification.nameproject.tasks.CreateRepository;
import co.com.yourcompany.certification.nameproject.tasks.Start;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static co.com.yourcompany.certification.nameproject.models.builders.RepositoryBuilder.name;
import static co.com.yourcompany.certification.nameproject.models.builders.UserBuilder.theUser;
import static co.com.yourcompany.certification.nameproject.models.enumerables.GitIgnore.JAVA;
import static co.com.yourcompany.certification.nameproject.models.enumerables.License.MIT;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.core.Is.is;

public class CreateRepositoryInGitHubStepDefinition {

    private static final String CESAR = "Cesar";
    private static final String GITHUB_USER = System.getProperty("github-user");
    private static final String SECRET = System.getProperty("password");
    public static final String REPOSITORY_NAME = "TEST_BDD";
    private static final String BASE_URL_OF_REPOSITORY_CREATED = String.format("https://github.com/%s/%s", GITHUB_USER, REPOSITORY_NAME);

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^Cesar wants to start versioning$")
    public void startVersioning() {
        theActorCalled(CESAR).wasAbleTo(
                Start.authenticating(theUser(GITHUB_USER)
                .withPassword(SECRET)));
    }

    @When("^Cesar creates a repository$")
    public void createRepository() {
        theActorInTheSpotlight().attemptsTo(
                CreateRepository.withTheFollowingData(
                        name(REPOSITORY_NAME)
                        .description("repository for bdd tests")
                        .initializeWithREADME()
                        .gitIgnore(JAVA)
                        .license(MIT)
                )
        );
    }

    @Then("^Cesar should see the repository created$")
    public void shouldSeeTheRepositorioCreated() {
        theActorInTheSpotlight().should(seeThat(TheRepository.name(), is(REPOSITORY_NAME)));
        theActorInTheSpotlight().should(seeThat(TheCurrent.url(), is(BASE_URL_OF_REPOSITORY_CREATED)));
    }

}