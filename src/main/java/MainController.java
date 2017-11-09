import controllers.MainMenuController;
import data.ExampleData;
import views.MainMenuView;

/**
 * Launches main menu with the example data and view.
 */
public class MainController {
    public void run() {
        new MainMenuController(
                System.in,
                new MainMenuView(),
                ExampleData.REPOSITORY
        ).run();
    }
}
