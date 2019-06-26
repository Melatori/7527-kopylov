package ru.ctf.focusstart.kopylov.user.builder;

import ru.ctf.focusstart.kopylov.Message;
import ru.ctf.focusstart.kopylov.user.User;
import ru.ctf.focusstart.kopylov.user.receiver.ReceiverAuthMessageListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class UserBuilder implements ReceiverAuthMessageListener {
    private User user;
    private LinkedBlockingQueue<User> userList;

    private List<UserLoginListener> listeners = new ArrayList<>();

    public UserBuilder(User user, LinkedBlockingQueue<User> userList) {
        this.user = user;
        this.user.addAuthMessageListener(this);
        this.userList = userList;
    }

    private boolean isUsernameAllowed(String username) {
        for (User existsUser : userList) {
            if (existsUser.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void handleAuthMessageReceivedEvent(Message message) {
        if (isUsernameAllowed(message.getMessage())) {
            user.setUsername(message.getMessage());
            notifyLoginListeners();
        } else {
            user.usernameWasOccupied();
        }
    }

    public void addLoginListener(UserLoginListener listener) {
        listeners.add(listener);
    }

    private void notifyLoginListeners() {
        for (UserLoginListener listener : listeners) {
            listener.usernameHasSet(user);
        }
    }
}
