package ru.ctf.focusstart.kopylov.user.manager.monitor;

import ru.ctf.focusstart.kopylov.user.User;

public interface MonitorFoundDeadUserListener {
    void handleFoundDeadUser(User user);
}
