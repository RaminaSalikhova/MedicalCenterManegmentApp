package app.handlers.implementations;

import app.entity.Address;
import app.entity.District;
import app.entity.Patient;
import app.entity.User;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.AnswerTransferModels.GetAddressListAtm;
import app.models.AnswerTransferModels.GetUserAddressAtm;
import app.models.DataTransferModels.GetUserAddressDto;
import app.services.AddressService;
import app.services.DistrictService;
import app.services.PatientService;
import app.services.UserService;

import java.util.List;
import java.util.Optional;

public class GetUserAddressHandler extends RequestHandler<GetUserAddressDto, GetUserAddressAtm> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.getUserAddress;
    }

    @Override
    protected GetUserAddressAtm execute(GetUserAddressDto getUserAddressDto) throws Exception {
//        UserService userService=new UserService();
//        User user=userService.findById(getUserAddressDto.getUserID());
        GetUserAddressAtm getUserAddressAtm = new GetUserAddressAtm();

        PatientService patientService = new PatientService();
        Optional<Patient> patient = Optional.ofNullable(patientService.findPatientByUserID(getUserAddressDto.getUserID()));
        if (patient.isPresent()) {
            AddressService addressService = new AddressService();
            Optional<Long> id = Optional.ofNullable(patient.get().getAddressId());
            if (id.isPresent()) {
                Optional<Address> address = Optional.ofNullable(addressService.findById(id.get()));

                if (address.isPresent()) {

                    DistrictService districtService = new DistrictService();
                    District district = districtService.findById(address.get().getDistrictId());

                    getUserAddressAtm.setDistrictName(district.getName());
                    getUserAddressAtm.setAddressName(address.get().getName());
                    getUserAddressAtm.setAddressHouse(String.valueOf(address.get().getHouseNumber()));
                    getUserAddressAtm.setAddressFlat(String.valueOf(address.get().getFlatNumber()));
                }
            }else {
                return getUserAddressAtm;
            }
        }
        return getUserAddressAtm;
    }
}
