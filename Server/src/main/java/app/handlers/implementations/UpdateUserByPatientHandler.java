package app.handlers.implementations;

import app.entity.Address;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.DataTransferModels.UpdateUserByPatientDto;
import app.services.AddressService;
import app.services.UserService;

public class UpdateUserByPatientHandler extends RequestHandler<UpdateUserByPatientDto, String> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.updateUserByPatient;
    }

    @Override
    protected String execute(UpdateUserByPatientDto updateUserByPatientDto) {
        UserService userService = new UserService();
        userService.updateUsernameAndPhone(updateUserByPatientDto);

        return "Обвновлено успешно";
    }


}
