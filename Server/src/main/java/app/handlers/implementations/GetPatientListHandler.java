package app.handlers.implementations;

import app.entity.*;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.AnswerTransferModels.GetPatientListAtm;
import app.models.DataTransferModels.GetPatientListDto;
import app.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GetPatientListHandler extends RequestHandler<GetPatientListDto, List<GetPatientListAtm>> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.getPatientList;
    }

    @Override
    protected List<GetPatientListAtm> execute(GetPatientListDto getPatientListDto) throws Exception {

        List<GetPatientListAtm> getPatientListAtmList = new ArrayList<>();
        DoctorService doctorService = new DoctorService();
        Doctor searchedDoctor = doctorService.findAllByUserID(getPatientListDto.getUserID());

        PatientService patientService = new PatientService();
        List<Patient> patientList = patientService.findAll();

        for (Patient patient : patientList) {

            AppointmentService appointmentService = new AppointmentService();
            List<Appointment> appointments = appointmentService.findAllByPatientIDAndDoctorID(patient.getId(), searchedDoctor.getId());
            for (Appointment appointment : appointments) {
                GetPatientListAtm getPatientListAtm = new GetPatientListAtm();
                UserService userService = new UserService();
                var searchedUser = userService.findById(patient.getUserId());

                AddressService addressService = new AddressService();
                Optional<Address> searchedAddress = Optional.ofNullable(addressService.findById(patient.getId()));
                String strAddress="Не указан";
                if(searchedAddress.isPresent()) {
                    DistrictService districtService = new DistrictService();
                    District searchedDistrict = districtService.findById(searchedAddress.get().getDistrictId());
                    strAddress.replace("Не указан", searchedAddress.get().getName() + " " + searchedAddress.get().getHouseNumber().toString()
                            + " " + searchedAddress.get().getFlatNumber().toString() + " /" + searchedDistrict.getName());
              }

//                 strAddress.concat(" ");
//                strAddress.concat(searchedAddress.getHouseNumber().toString());
//                strAddress.concat(" ");
//                strAddress.concat(searchedAddress.getFlatNumber().toString());
//                strAddress.concat(" /");
//                strAddress.concat(searchedDistrict.getName().toString());

                getPatientListAtm.setPatientID(patient.getId());
                getPatientListAtm.setAppointmentID(appointment.getId());
                getPatientListAtm.setFirst_name(searchedUser.getFirstName());
                getPatientListAtm.setLast_name(searchedUser.getLastName());
                getPatientListAtm.setPatronymic(searchedUser.getPatronymic());

                getPatientListAtm.setAddress(strAddress);
                getPatientListAtm.setSex(patient.getSex());

                getPatientListAtm.setTime(appointment.getDateTimeOfAppointment().toString());
                getPatientListAtmList.add(getPatientListAtm);
            }
        }
        return getPatientListAtmList;
    }
}
