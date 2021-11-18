package app.handlers.implementations;

import app.entity.*;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.AnswerTransferModels.GetPatientListAppointmentAtm;
import app.models.AnswerTransferModels.GetPatientListOfVisitedAppointmentAtm;
import app.models.DataTransferModels.GetPatientListOfVisitedAppointmentDto;
import app.services.*;

import java.util.ArrayList;
import java.util.List;

public class GetPatientListOfVisitedAppointmentHandler extends RequestHandler<GetPatientListOfVisitedAppointmentDto, List<GetPatientListOfVisitedAppointmentAtm>> {

    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.getPatientListOfVisitedAppointment;
    }

    @Override
    protected List<GetPatientListOfVisitedAppointmentAtm> execute(GetPatientListOfVisitedAppointmentDto getPatientListOfVisitedAppointmentDto) throws Exception {
        List<GetPatientListOfVisitedAppointmentAtm> getPatientListOfVisitedAppointmentAtms=new ArrayList<>();

        PatientService patientService=new PatientService();
        Patient patient= patientService.findPatientByUserID(getPatientListOfVisitedAppointmentDto.getUserID());

        AppointmentService appointmentService=new AppointmentService();
        List<Appointment> appointments=appointmentService.findAllByPatientIDAndVisited(patient.getId());

        SpecializationService specializationService=new SpecializationService();

        for(Appointment appointment:appointments) {
            if (appointment.getIsVisited() == 1) {
                GetPatientListOfVisitedAppointmentAtm getPatientListOfVisitedAppointmentAtm = new GetPatientListOfVisitedAppointmentAtm();

                DoctorService doctorService = new DoctorService();
                Doctor doctor = doctorService.findById(appointment.getDoctorId());

                UserService userService = new UserService();
                User user = userService.findById(doctor.getId());

                getPatientListOfVisitedAppointmentAtm.setVisitedAppointmentID(appointment.getId());
                getPatientListOfVisitedAppointmentAtm.setVisitedAppointmentFirst_name(user.getFirstName());
                getPatientListOfVisitedAppointmentAtm.setVisitedAppointmentLast_name(user.getLastName());
                getPatientListOfVisitedAppointmentAtm.setVisitedAppointmentPatronymic(user.getPatronymic());

                Specialization specialization = specializationService.findById(doctor.getSpecializationId());

                getPatientListOfVisitedAppointmentAtm.setVisitedAppointmentSpecialization(specialization.getName());
                getPatientListOfVisitedAppointmentAtm.setVisitedAppointmentDiagnosis(appointment.getDiagnosis());
                getPatientListOfVisitedAppointmentAtm.setVisitedAppointmentReport(appointment.getReport());
                getPatientListOfVisitedAppointmentAtm.setVisitedAppointmentRecommendation(appointment.getRecommendation());
                getPatientListOfVisitedAppointmentAtm.setVisitedAppointmentDateTime(appointment.getDateTimeOfAppointment().toString());

                getPatientListOfVisitedAppointmentAtms.add(getPatientListOfVisitedAppointmentAtm);
            }
        }
        return getPatientListOfVisitedAppointmentAtms;
    }

}
