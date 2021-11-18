package app.handlers.implementations;

import app.entity.Address;
import app.entity.District;
import app.entity.Doctor;
import app.entity.Patient;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.DataTransferModels.UpdateDistrictDto;
import app.services.AddressService;
import app.services.DistrictService;
import app.services.PatientService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UpdateDistrictHandler extends RequestHandler<UpdateDistrictDto, String> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.updateDistrict;
    }

    @Override
    protected String execute(UpdateDistrictDto updateDistrictDto) throws Exception {
        String response = "";

//        DistrictService districtService=new DistrictService();
//        Optional<District> district=
//                Optional.ofNullable(districtService.findById(updateDistrictDto.getDistrictID()));
//
//        if(district.isPresent()){
//            districtService.update(district.get());
//        }else {
//            response="Такого участка не сущетсвует";
//        }

        DistrictService districtService = new DistrictService();
        District district = districtService.findByName(updateDistrictDto.getDistrictName());

        AddressService addressService = new AddressService();
//        Optional<Address> address=Optional.ofNullable(addressService.findById(updateDistrictDto.getAddressID()));
//        address.get().setDistrictId(district.getId());
//        address.get().setName(updateDistrictDto.getAddressName());
//        address.get().setHouseNumber(Integer.parseInt(updateDistrictDto.getAddressHouse()));
//        address.get().setFlatNumber(Integer.parseInt(updateDistrictDto.getAddressFlat()));

        List<Address> addressList = addressService.findEqualsHouseAndStreetAddress(updateDistrictDto);
        for (Address el : addressList) {
            Address address = new Address();
            address.setId(el.getId());
            address.setDistrictId(district.getId());
            address.setDistrictByDistrictId(district);
            address.setName(el.getName());
            address.setHouseNumber(el.getHouseNumber());
            address.setFlatNumber(el.getFlatNumber());

            PatientService patientService=new PatientService();
            Collection<Patient> patients = patientService.findAll();
            Collection<Patient> patientCollection = patientService.findAll();
            for (Patient el1 : patients) {
                Optional<Long> elid = Optional.ofNullable(el1.getAddressId());
                Optional<Long> id = Optional.of(address.getId());
                if (elid.isPresent()) {
                    if (id.isPresent()) {
                        if (elid.equals(id)) {
                            patientCollection.add(el1);
                        }
                    }
                }
            }
            address.setPatientsById(patientCollection);
            addressService.update(address);
        }
//        if(address.isPresent()){
//            addressService.update(address.get());
//        }else {
//            response="Такого адреса не сущетсвует";
//        }

        return response;
    }
}
