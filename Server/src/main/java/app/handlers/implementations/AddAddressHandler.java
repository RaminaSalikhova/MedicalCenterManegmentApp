package app.handlers.implementations;

import app.entity.Address;
import app.entity.District;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.DataTransferModels.AddAddressDto;
import app.services.AddressService;
import app.services.DistrictService;

import java.util.List;
import java.util.Optional;

public class AddAddressHandler extends RequestHandler<AddAddressDto, String> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.addAddress;
    }

    @Override
    protected String execute(AddAddressDto addAddressDto) throws Exception {
        AddressService addressService=new AddressService();

        Address address=new Address();

        List<Address> addresses=addressService.findAll();
        for (Address el: addresses){
            if(el.getHouseNumber().toString().equals(addAddressDto.getAddressHouse()) &&
//            el.getFlatNumber().toString().equals(addAddressDto.getAddressFlat()) &&
                    el.getName().equals(addAddressDto.getAddressName())){
                return "Адрес уже существует";
            }
        }

        DistrictService districtService=new DistrictService();
        Optional<District> district=Optional.ofNullable(districtService.findByName(addAddressDto.getDistrictName()));

        if(district.isPresent()){
            address.setDistrictId(district.get().getId());
            address.setDistrictByDistrictId(district.get());
            address.setName(addAddressDto.getAddressName());
            address.setHouseNumber(Integer.valueOf(addAddressDto.getAddressHouse()));
//            address.setFlatNumber(Integer.valueOf(addAddressDto.getAddressFlat()));

            addressService.save(address);
        }else {
            return "Вы ввели несуществующий участок";
        }
        return "Успешно";
    }


}
