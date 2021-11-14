package app.handlers.implementations;

import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.DataTransferModels.DeleteUserDto;
import app.services.UserService;

public class DeleteUserHandler extends RequestHandler<DeleteUserDto, String>{

    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.deleteUser;
    }

    @Override
    protected String execute(DeleteUserDto deleteUserDto) throws Exception {
        UserService userService = new UserService();
        userService.updateUserStatus(deleteUserDto.getUserID());
        return "Успешно";
    }
}
