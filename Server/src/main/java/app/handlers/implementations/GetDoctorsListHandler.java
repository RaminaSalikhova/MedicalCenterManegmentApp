package app.handlers.implementations;

import app.entity.Doctor;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.AnswerTransferModels.GetDoctorListAtm;
import app.models.DataTransferModels.GetDoctorListDto;
import app.services.DoctorService;

import java.util.ArrayList;
import java.util.List;

public class GetDoctorsListHandler extends RequestHandler<GetDoctorListDto, List<GetDoctorListAtm>> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.getDoctorsList;
    }


    @Override
    protected List<GetDoctorListAtm> execute(GetDoctorListDto getDoctorListDto) throws Exception {
        DoctorService doctorService = new DoctorService();
        List<Doctor> doctorList = doctorService.findAllNotNull();
        List<GetDoctorListAtm> doctorListAtmArrayList = new ArrayList<>();
        for (Doctor doctor : doctorList) {
            GetDoctorListAtm getDoctorListAtm = new GetDoctorListAtm();
            getDoctorListAtm.setId(doctor.getId());
            getDoctorListAtm.setFirst_name(doctor.getUserByUserId().getFirstName());
            getDoctorListAtm.setLast_name(doctor.getUserByUserId().getLastName());
            getDoctorListAtm.setPatronymic(doctor.getUserByUserId().getPatronymic());
            getDoctorListAtm.setExperience(doctor.getExperience());
            getDoctorListAtm.setRoomNumber(doctor.getScheduleByScheduleId().getRoomNumber());
            getDoctorListAtm.setSpecialization(doctor.getSpecializationBySpecializationId().getName());
            doctorListAtmArrayList.add(getDoctorListAtm);
        }
        return doctorListAtmArrayList;
    }


}
