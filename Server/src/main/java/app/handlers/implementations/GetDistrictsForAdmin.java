package app.handlers.implementations;

import app.entity.Address;
import app.entity.District;
import app.entity.Doctor;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.AnswerTransferModels.GetDistrictsAndAddressesForAdminAtm;
import app.models.DataTransferModels.GetDistrictsAndAddressesForAdminDto;
import app.services.AddressService;
import app.services.DistrictService;

import java.util.*;
import java.util.stream.Collectors;

public class GetDistrictsForAdmin extends RequestHandler<GetDistrictsAndAddressesForAdminDto, List<GetDistrictsAndAddressesForAdminAtm>> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.getDistrictsForAdmin;
    }

    @Override
    protected List<GetDistrictsAndAddressesForAdminAtm> execute(GetDistrictsAndAddressesForAdminDto getDistrictsAndAddressesForAdminDto) throws Exception {
        List<GetDistrictsAndAddressesForAdminAtm> getDistrictsAndAddressesForAdminAtms=new ArrayList<>();

        AddressService addressService=new AddressService();
//        List<Address> addresses=addressService.findAll();
        List<Address> addresses=addressService.findAllGroupByHouseNumber();

        for(Address address: addresses){
            GetDistrictsAndAddressesForAdminAtm getDistrictsAndAddressesForAdminAtm=new GetDistrictsAndAddressesForAdminAtm();

            getDistrictsAndAddressesForAdminAtm.setAddressID(address.getId());
            getDistrictsAndAddressesForAdminAtm.setAddressName(address.getName());

            Optional<Integer> house=Optional.ofNullable(address.getHouseNumber());
            if(house.isPresent()){
                getDistrictsAndAddressesForAdminAtm.setAddressHouse(house.get().toString());
            }else {
                getDistrictsAndAddressesForAdminAtm.setAddressHouse("Не установлено");
            }

//            Optional<Integer> flat=Optional.ofNullable(address.getFlatNumber());
//            if(house.isPresent()){
//                getDistrictsAndAddressesForAdminAtm.setAddressFlat(flat.get().toString());
//            }else {
//                getDistrictsAndAddressesForAdminAtm.setAddressFlat("Не установлено");
//            }

            DistrictService districtService=new DistrictService();
            Optional<Long> districtID=Optional.ofNullable(address.getDistrictId());
            if(districtID.isPresent()){
                District district=districtService.findById(address.getDistrictId());
                getDistrictsAndAddressesForAdminAtm.setDistrictName(district.getName());
            }else {
                getDistrictsAndAddressesForAdminAtm.setDistrictName("Не установлено");
            }

            getDistrictsAndAddressesForAdminAtms.add(getDistrictsAndAddressesForAdminAtm);
        }

        return getDistrictsAndAddressesForAdminAtms;
    }
}
