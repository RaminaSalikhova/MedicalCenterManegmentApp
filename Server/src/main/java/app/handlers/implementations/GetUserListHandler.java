package app.handlers.implementations;

import app.entity.User;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.AnswerTransferModels.GetUserListAtm;
import app.models.DataTransferModels.GetUserListDto;
import app.services.UserService;

import java.util.ArrayList;
import java.util.List;

public class GetUserListHandler extends RequestHandler<GetUserListDto,List<GetUserListAtm>> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.getUserList;
    }

    @Override
    protected List<GetUserListAtm> execute(GetUserListDto getUserListDto) throws Exception {
        UserService userService = new UserService();
        List<User> userList = userService.findAll();
        List<GetUserListAtm> userListAtms = new ArrayList<>();
        for (User user : userList) {
            GetUserListAtm getUserListAtm = new GetUserListAtm();
            getUserListAtm.setUserID(user.getId());
            getUserListAtm.setFirst_name(user.getFirstName());
            getUserListAtm.setLast_name(user.getLastName());
            getUserListAtm.setPatronymic(user.getPatronymic());
            getUserListAtm.setLogin(user.getLogin());
            getUserListAtm.setRole(user.getRole());
            getUserListAtm.setPhoneNum(user.getPhoneNum());
            getUserListAtm.setStatus(user.getStatus());
            userListAtms.add(getUserListAtm);
        }
        return userListAtms;
    }
}
