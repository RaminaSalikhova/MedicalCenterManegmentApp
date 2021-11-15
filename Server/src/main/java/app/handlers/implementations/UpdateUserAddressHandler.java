package app.handlers.implementations;

import app.entity.Address;
import app.entity.User;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.DataTransferModels.UpdateUserAddressDto;
import app.services.AddressService;
import app.services.PatientService;
import app.services.UserService;

public class UpdateUserAddressHandler extends RequestHandler<UpdateUserAddressDto, String > {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.updateUserAddress;
    }

    @Override
    protected String execute(UpdateUserAddressDto updateUserAddressDto) throws Exception {

        UserService userService=new UserService();
        User user=userService.findById(updateUserAddressDto.getUserID());

        AddressService addressService=new AddressService();
        Address address=addressService.findAddress(updateUserAddressDto);

        PatientService patientService=new PatientService();
        patientService.updateAddress(user.getId(), address.getId());

        return "Успешно";
    }
}
