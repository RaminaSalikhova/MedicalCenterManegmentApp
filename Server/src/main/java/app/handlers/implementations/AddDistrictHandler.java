package app.handlers.implementations;

import app.entity.District;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.DataTransferModels.AddDistrictDto;
import app.services.DistrictService;

import java.util.Optional;

public class AddDistrictHandler extends RequestHandler<AddDistrictDto, String> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.addDistrict;
    }

    @Override
    protected String execute(AddDistrictDto addDistrictDto) throws Exception {

        DistrictService districtService=new DistrictService();

        Optional<District> district=Optional.ofNullable(districtService.findByName(addDistrictDto.getDistrictName()));

        if(district.isPresent()){
            return "Такой участок уже существует";
        }else {
            district= Optional.ofNullable(new District());
            district.get().setName(addDistrictDto.getDistrictName());
            districtService.save(district.get());
            return "Успешно";

        }
    }


}
