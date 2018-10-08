package seedu.address.model.user;
/**
 * UserSession represent a logged in user session.
 */
public class UserSession {

    private User loggedInUser;
    private boolean isLoggedIn;

    public UserSession() {
    }

    public UserSession(User toLogin) {
        loggedInUser = toLogin;
        this.isLoggedIn = true;
    }

    public void setUserSession(User toLogin) {
        this.loggedInUser = toLogin;
        this.isLoggedIn = true;
    }

    public boolean isUserAlreadyLoggedIn() {
        return this.isLoggedIn;
    }

    public void logOutUser() {
        this.isLoggedIn = false;
    }

    public User getLoggedInUserDetails() {
        return this.loggedInUser;
    }






}
