package app.handlers.implementations;

import app.entity.Address;
import app.entity.District;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.AnswerTransferModels.GetDistrictsAndAddressesForAdminAtm;
import app.models.AnswerTransferModels.GetDistrictsForAdminAtm;
import app.models.DataTransferModels.GetDistrictsForAdminDto;
import app.services.AddressService;
import app.services.DistrictService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GetDistrictsNameForAdminHandler extends RequestHandler<GetDistrictsForAdminDto, List<GetDistrictsForAdminAtm>> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.getDistrictsNameForAdmin;
    }

    @Override
    protected List<GetDistrictsForAdminAtm> execute(GetDistrictsForAdminDto getDistrictsForAdminDto) throws Exception {
        List<GetDistrictsForAdminAtm> getDistrictsForAdminAtms = new ArrayList<>();

        DistrictService districtService = new DistrictService();
        List<District> districts = districtService.findAll();
        for (District district:districts) {
            GetDistrictsForAdminAtm getDistrictsForAdminAtm=new GetDistrictsForAdminAtm();
            getDistrictsForAdminAtm.setDistrictID(district.getId());
            getDistrictsForAdminAtm.setName(district.getName());

            getDistrictsForAdminAtms.add(getDistrictsForAdminAtm);
        }

        return getDistrictsForAdminAtms;
}


                }
