package app.handlers.implementations;

import app.entity.Address;
import app.entity.District;
import app.entity.User;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.DataTransferModels.UpdateDistrictDto;
import app.models.DataTransferModels.UpdateUserAddressDto;
import app.services.AddressService;
import app.services.DistrictService;
import app.services.PatientService;
import app.services.UserService;

import java.util.List;
import java.util.Optional;

public class UpdateUserAddressHandler extends RequestHandler<UpdateUserAddressDto, String> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.updateUserAddress;
    }

    @Override
    protected String execute(UpdateUserAddressDto updateUserAddressDto) throws Exception {

        UserService userService = new UserService();
        User user = userService.findById(updateUserAddressDto.getUserID());

        UpdateDistrictDto updateDistrictDto=new UpdateDistrictDto();
        updateDistrictDto.setAddressName(updateUserAddressDto.getAddressName());
        updateDistrictDto.setAddressHouse(updateUserAddressDto.getHouse());

        PatientService patientService = new PatientService();

        AddressService addressService = new AddressService();
        Optional<Address> address = Optional.ofNullable(addressService.findAddress(updateUserAddressDto));
        if (address.isPresent()) {
            patientService.updateAddress(user.getId(), address.get().getId());
        } else {
            Optional<List<Address>> streetAndHome = Optional.ofNullable(addressService.findEqualsHouseAndStreetAddress(updateDistrictDto));
            if (streetAndHome.isPresent()) {
                Address newAddress = new Address();
                newAddress.setName(updateUserAddressDto.getAddressName());
                newAddress.setHouseNumber(Integer.valueOf(updateUserAddressDto.getHouse()));
                newAddress.setFlatNumber(Integer.valueOf(updateUserAddressDto.getFlat()));

                DistrictService districtService=new DistrictService();
                District district=districtService.findById(streetAndHome.get().get(0).getDistrictId());
                newAddress.setDistrictByDistrictId(district);
                newAddress.setDistrictId(streetAndHome.get().get(0).getDistrictId());

                addressService.save(newAddress);

                Address searched= addressService.findAddress(updateUserAddressDto);

                patientService.updateAddress(user.getId(), searched.getId());
            }
        }
        return "Успешно";
    }
}
