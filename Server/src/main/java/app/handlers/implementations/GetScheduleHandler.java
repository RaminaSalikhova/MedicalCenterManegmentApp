package app.handlers.implementations;

import app.entity.Doctor;
import app.entity.Patient;
import app.entity.Schedule;
import app.entity.Specialization;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.AnswerTransferModels.GetScheduleListAtm;
import app.models.DataTransferModels.GetScheduleListDto;
import app.services.*;

import java.util.ArrayList;
import java.util.List;

public class GetScheduleHandler extends RequestHandler<GetScheduleListDto, List<GetScheduleListAtm>> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.getSchedule;
    }

    @Override
    protected List<GetScheduleListAtm> execute(GetScheduleListDto getScheduleListDto) throws Exception {
        List<GetScheduleListAtm> getScheduleListAtms = new ArrayList<>();

        DoctorService doctorService = new DoctorService();
        List<Doctor> doctorList = doctorService.findAllNotNull();

        for (Doctor doctor : doctorList) {
            GetScheduleListAtm scheduleListAtm = new GetScheduleListAtm();
            UserService userService = new UserService();
            var searchedUser = userService.findById(doctor.getUserId());

            SpecializationService specializationService= new SpecializationService();
            Specialization specialization= specializationService.findById(doctor.getSpecializationId());

            ScheduleService scheduleService = new ScheduleService();
            Schedule schedule = scheduleService.findById(doctor.getScheduleId());

            scheduleListAtm.setFirst_name(searchedUser.getFirstName());
            scheduleListAtm.setLast_name(searchedUser.getLastName());
            scheduleListAtm.setSpecialization(specialization.getName());
            scheduleListAtm.setStartTime(schedule.getStartTime().toString());
            scheduleListAtm.setEndTime(schedule.getEndTime().toString());
            scheduleListAtm.setRoomNumber(schedule.getRoomNumber());
            getScheduleListAtms.add(scheduleListAtm);
        }
        return getScheduleListAtms;
    }


}
