package ru.ctf.focusstart.kopylov.logic.user;

public interface UserAuthorizationListener {
    void handleAuthorizationCompleteEvent();
    void handleAuthorizationFailedEvent();
}
