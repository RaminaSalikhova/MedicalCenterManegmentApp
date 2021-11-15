package app.handlers.implementations;

import app.entity.Address;
import app.entity.District;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.DataTransferModels.UpdateDistrictDto;
import app.services.AddressService;
import app.services.DistrictService;

import java.util.Optional;

public class UpdateDistrictHandler extends RequestHandler<UpdateDistrictDto, String> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.updateDistrict;
    }

    @Override
    protected String execute(UpdateDistrictDto updateDistrictDto) throws Exception {
        String response="";

//        DistrictService districtService=new DistrictService();
//        Optional<District> district=
//                Optional.ofNullable(districtService.findById(updateDistrictDto.getDistrictID()));
//
//        if(district.isPresent()){
//            districtService.update(district.get());
//        }else {
//            response="Такого участка не сущетсвует";
//        }

        AddressService addressService=new AddressService();
        Optional<Address> address=Optional.ofNullable(addressService.findById(updateDistrictDto.getAddressID()));
        address.get().setDistrictId(updateDistrictDto.getDistrictID());
        address.get().setName(updateDistrictDto.getAddressName());
        address.get().setHouseNumber(Integer.parseInt(updateDistrictDto.getAddressHouse()));
        address.get().setFlatNumber(Integer.parseInt(updateDistrictDto.getAddressFlat()));
        if(address.isPresent()){
            addressService.update(address.get());
        }else {
            response="Такого адреса не сущетсвует";
        }

        return response;
    }
}
