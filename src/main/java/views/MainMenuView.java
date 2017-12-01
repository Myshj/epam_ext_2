package views;

import strings.MainMenuStrings;

/**
 * View for the main menu.
 */
public class MainMenuView extends View {

    @Override
    public void display() {
        System.out.println(MainMenuStrings.HEADER);
        System.out.println(MainMenuStrings.LIST_ALL);
        System.out.println(MainMenuStrings.LIST_BY_PUBLISHER);
        System.out.println(MainMenuStrings.LIST_BY_AUTHOR);
        System.out.println(MainMenuStrings.LIST_PUBLISHED_AFTER);
        System.out.println(MainMenuStrings.SORT_BY_PUBLISHER);
        System.out.println(MainMenuStrings.SAVE);
        System.out.println(MainMenuStrings.LOAD);
        System.out.println(MainMenuStrings.QUIT);
    }

    public void requestPublisher() {
        System.out.println(MainMenuStrings.REQUEST_PUBLISHER);
    }

    public void requestYear() {
        System.out.println(MainMenuStrings.REQUEST_YEAR);
    }

    public void requestAuthor() {
        System.out.println(MainMenuStrings.REQUEST_AUTHOR);
    }
}
