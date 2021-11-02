package app.models.holder;

import app.models.AnswerTransferModels.UserAtm;
import app.models.DataTransferModels.LoginDto;

public final class UserHolder {

    private UserAtm user;
    private final static UserHolder INSTANCE = new UserHolder();

    private UserHolder() {}

    public static UserHolder getInstance() {
        return INSTANCE;
    }

    public void setUser(UserAtm u) {
        this.user = u;
    }

    public UserAtm getUser() {
        return this.user;
    }
}