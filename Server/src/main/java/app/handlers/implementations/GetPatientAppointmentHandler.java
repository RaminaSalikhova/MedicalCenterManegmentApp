package app.handlers.implementations;

import app.entity.Appointment;
import app.entity.Patient;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.AnswerTransferModels.DoctorAppointmentPatientAtm;
import app.models.DataTransferModels.DoctorAppointmentPatientDto;
import app.services.AppointmentService;
import app.services.PatientService;

public class GetPatientAppointmentHandler extends RequestHandler<DoctorAppointmentPatientDto, DoctorAppointmentPatientAtm> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.getPatientAppointment;
    }

    @Override
    protected DoctorAppointmentPatientAtm execute(DoctorAppointmentPatientDto doctorAppointmentPatientDto) throws Exception {
        DoctorAppointmentPatientAtm doctorAppointmentPatientAtm = new DoctorAppointmentPatientAtm();

        AppointmentService appointmentService = new AppointmentService();
        Appointment appointment = appointmentService.findById(doctorAppointmentPatientDto.getAppointmentID());

        doctorAppointmentPatientAtm.setPatientID(doctorAppointmentPatientDto.getPatientID());
        doctorAppointmentPatientAtm.setDiagnose(appointment.getDiagnosis());
        doctorAppointmentPatientAtm.setRecommendation(appointment.getRecommendation());
        doctorAppointmentPatientAtm.setReport(appointment.getReport());

        PatientService patientService = new PatientService();
        Patient patient = patientService.findById(doctorAppointmentPatientDto.getPatientID());

        doctorAppointmentPatientAtm.setHeight(patient.getHeight());
        doctorAppointmentPatientAtm.setWeight(patient.getWeight());
        doctorAppointmentPatientAtm.setVisited(Boolean.parseBoolean(appointment.getIsVisited().toString()));
        return doctorAppointmentPatientAtm;
    }
}
