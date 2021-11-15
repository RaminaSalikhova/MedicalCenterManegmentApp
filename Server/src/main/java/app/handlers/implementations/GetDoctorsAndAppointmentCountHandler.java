package app.handlers.implementations;

import app.entity.Appointment;
import app.entity.Doctor;
import app.entity.User;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.AnswerTransferModels.GetDoctorsAndAppointmentCountAtm;
import app.models.DataTransferModels.GetDoctorsAndAppointmentCountDto;
import app.services.AppointmentService;
import app.services.DoctorService;
import app.services.UserService;

import java.util.ArrayList;
import java.util.List;

public class GetDoctorsAndAppointmentCountHandler extends RequestHandler<GetDoctorsAndAppointmentCountDto, List<GetDoctorsAndAppointmentCountAtm>> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.getDoctorsAndAppointmentCountDto;
    }

    @Override
    protected List<GetDoctorsAndAppointmentCountAtm> execute(GetDoctorsAndAppointmentCountDto getDoctorsAndAppointmentCountDto) throws Exception {
        DoctorService doctorService = new DoctorService();
        List<Doctor> doctorList = doctorService.findAll();

        List<GetDoctorsAndAppointmentCountAtm> getDoctorsAndAppointmentCountAtmList = new ArrayList<>();
        for (Doctor doctor : doctorList) {
            GetDoctorsAndAppointmentCountAtm getDoctorsAndAppointmentCountAtm = new GetDoctorsAndAppointmentCountAtm();

            UserService userService = new UserService();
            User user = userService.findById(doctor.getUserId());

            AppointmentService appointmentService = new AppointmentService();
            List<Appointment> appointmentList = appointmentService.findAllByDoctorID(doctor.getId());

            String name = user.getFirstName();
            name.concat(" ");
            name.concat(user.getLastName());
            name.concat(" ");
            name.concat(user.getPatronymic());
            getDoctorsAndAppointmentCountAtm.setDoctorName(name);
            getDoctorsAndAppointmentCountAtm.setAppointmentsCount(appointmentList.size());

            getDoctorsAndAppointmentCountAtmList.add(getDoctorsAndAppointmentCountAtm);

        }
        return getDoctorsAndAppointmentCountAtmList;
    }
}
