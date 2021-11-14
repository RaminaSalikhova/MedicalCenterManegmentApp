package app.handlers.implementations;

import app.entity.Appointment;
import app.entity.Doctor;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.AnswerTransferModels.GetAvailableTimeListAtm;
import app.models.DataTransferModels.GetAvailableTimeListDto;
import app.services.AppointmentService;
import app.services.DoctorService;
import app.services.ScheduleService;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetAvailableTimeHandler extends RequestHandler<GetAvailableTimeListDto, List<GetAvailableTimeListAtm>> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.getAvailableTime;
    }

    @Override
    protected List<GetAvailableTimeListAtm> execute(GetAvailableTimeListDto getAvailableTimeListDto) throws Exception {
        List<GetAvailableTimeListAtm> getAvailableTimeListAtms = new ArrayList<>();

        DoctorService doctorService = new DoctorService();
        Doctor doctor = doctorService.findById(getAvailableTimeListDto.getId());

        ScheduleService scheduleService = new ScheduleService();
        var searchedSchedule = scheduleService.findById(doctor.getScheduleId());

        AppointmentService appointmentService = new AppointmentService();
        List<Appointment> appointments = appointmentService.findAllByDoctorID(getAvailableTimeListDto.getId());

        Date doctorEndTime = searchedSchedule.getEndTime();
//                        if (appointments.size() == 0) {
        Date startDate = new Date();
        startDate = DateUtils.setSeconds(startDate, 0);
        startDate = DateUtils.setMilliseconds(startDate, 0);

        for (int i = 0; i < 7; i++) {
            Date doctorStartTime = searchedSchedule.getStartTime();

            if (startDate.getHours() > doctorStartTime.getHours()) {
                startDate = DateUtils.setHours(startDate, startDate.getHours());
            } else {
                startDate = DateUtils.setHours(startDate, doctorStartTime.getHours());
            }

            if (startDate.getMinutes() < 30 && startDate.getMinutes() != 0) {
                startDate = DateUtils.setMinutes(startDate, 30);
            } else if (startDate.getMinutes() != 0) {
                startDate = DateUtils.setMinutes(startDate, 0);
                startDate = DateUtils.addHours(startDate, 1);
            }

            while (startDate.getHours() < doctorEndTime.getHours() ||
                    (startDate.getHours() == doctorEndTime.getHours() &&
                            startDate.getHours() < doctorEndTime.getHours())) { // если надо, то сюда можно добавить проверку на Субботу/Вс

                boolean timeReserved = false;
                for (var appointment : appointments) {
                    var appTime = new Date(appointment.getDateTimeOfAppointment().getTime());
                    if (appTime.getDate() == startDate.getDate() &&
                            appTime.getHours() == startDate.getHours() &&
                            appTime.getMinutes() == startDate.getMinutes()) {
                        timeReserved = true;
                        break;
                    }
                }

                if (!timeReserved) {
                    GetAvailableTimeListAtm getAvailableTimeListAtm = new GetAvailableTimeListAtm();

                    getAvailableTimeListAtm.setDate(new SimpleDateFormat("dd.MM").format(startDate));
                    getAvailableTimeListAtm.setTime(new SimpleDateFormat("HH:mm").format(startDate));

                    getAvailableTimeListAtms.add(getAvailableTimeListAtm);
                }

                startDate = DateUtils.addMinutes(startDate, 30);
            }
            startDate = DateUtils.addDays(startDate, 1);
            startDate = DateUtils.setHours(startDate, 0);
        }
        return getAvailableTimeListAtms;
    }


}
