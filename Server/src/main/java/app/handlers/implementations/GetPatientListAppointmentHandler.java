package app.handlers.implementations;

import app.entity.*;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.AnswerTransferModels.GetPatientListAppointmentAtm;
import app.models.AnswerTransferModels.GetPatientListOfVisitedAppointmentAtm;
import app.models.DataTransferModels.GetPatientListAppointmentDto;
import app.models.DataTransferModels.GetPatientListOfVisitedAppointmentDto;
import app.services.*;

import java.util.ArrayList;
import java.util.List;

public class GetPatientListAppointmentHandler extends RequestHandler<GetPatientListAppointmentDto, List<GetPatientListAppointmentAtm>> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.getPatientListAppointment;
    }

    @Override
    protected List<GetPatientListAppointmentAtm> execute(GetPatientListAppointmentDto getPatientListAppointmentDto) throws Exception {
        List<GetPatientListAppointmentAtm> getPatientListAppointmentAtms=new ArrayList<>();

        PatientService patientService=new PatientService();
        Patient patient= patientService.findPatientByUserID(getPatientListAppointmentDto.getUserID());

        AppointmentService appointmentService=new AppointmentService();
        List<Appointment> appointments=appointmentService.findAllByPatientIDAndVisited(patient.getId());

        SpecializationService specializationService=new SpecializationService();

        for(Appointment appointment:appointments){
            if(appointment.getIsVisited()==0) {
                GetPatientListAppointmentAtm patientListAppointmentAtm = new GetPatientListAppointmentAtm();

                DoctorService doctorService = new DoctorService();
                Doctor doctor = doctorService.findById(appointment.getDoctorId());

                UserService userService = new UserService();
                User user = userService.findById(doctor.getId());

                patientListAppointmentAtm.setAppointmentID(appointment.getId());
                patientListAppointmentAtm.setFirst_name(user.getFirstName());
                patientListAppointmentAtm.setLast_name(user.getLastName());
                patientListAppointmentAtm.setPatronymic(user.getPatronymic());

                Specialization specialization = specializationService.findById(doctor.getSpecializationId());

                patientListAppointmentAtm.setSpecialization(specialization.getName());
                patientListAppointmentAtm.setDateTime(appointment.getDateTimeOfAppointment().toString());

                getPatientListAppointmentAtms.add(patientListAppointmentAtm);
            }
        }

        return getPatientListAppointmentAtms;
    }
}
