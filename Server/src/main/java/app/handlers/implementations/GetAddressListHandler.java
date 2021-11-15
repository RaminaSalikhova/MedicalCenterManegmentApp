package app.handlers.implementations;

import app.entity.Address;
import app.entity.District;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.AnswerTransferModels.GetAddressListAtm;
import app.models.DataTransferModels.GetAddressListDto;
import app.services.AddressService;
import app.services.DistrictService;

import java.util.ArrayList;
import java.util.List;

public class GetAddressListHandler extends RequestHandler<GetAddressListDto, List<GetAddressListAtm>> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.getAddressList;
    }

    @Override
    protected List<GetAddressListAtm> execute(GetAddressListDto getAddressListDto) throws Exception {
        List<GetAddressListAtm> getAddressListAtms=new ArrayList<>();

        AddressService addressService=new AddressService();
        List<Address> addresses=addressService.findAll();

        for(Address address: addresses){
            GetAddressListAtm getAddressListAtm=new GetAddressListAtm();

            DistrictService districtService=new DistrictService();
            District district=districtService.findById(address.getDistrictId());

            getAddressListAtm.setDistrictName(district.getName());
            getAddressListAtm.setAddressName(address.getName());
            getAddressListAtm.setAddressHouse(String.valueOf(address.getHouseNumber()));
            getAddressListAtm.setAddressFlat(String.valueOf(address.getFlatNumber()));

            getAddressListAtms.add(getAddressListAtm);
        }
        return getAddressListAtms;
    }
}
