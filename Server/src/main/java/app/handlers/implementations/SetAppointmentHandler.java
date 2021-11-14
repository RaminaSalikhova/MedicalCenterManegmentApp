package app.handlers.implementations;

import app.entity.Appointment;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.DataTransferModels.SetAppointmentDto;
import app.services.AppointmentService;
import app.services.DoctorService;
import app.services.PatientService;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SetAppointmentHandler extends RequestHandler<SetAppointmentDto, String> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.setAppointment;
    }

    @Override
    protected String execute(SetAppointmentDto setAppointmentDto) throws Exception {
        DoctorService doctorService = new DoctorService();
        var searchedDoctor = doctorService.findById(setAppointmentDto.getDoctorID());
        PatientService patientService = new PatientService();
        var searchedPatient = patientService.findPatientByUserID(setAppointmentDto.getUserID());

        Appointment appointment = new Appointment();
        appointment.setDoctorByDoctorId(searchedDoctor);
        appointment.setPatientByPatientId(searchedPatient);
        String ddate = setAppointmentDto.getDate();
        String dtime = setAppointmentDto.getTime();
        String dd = ddate + " " + dtime;
        Date dateDate = new SimpleDateFormat("dd.MM hh:mm").parse(dd);
        dateDate = DateUtils.setYears(dateDate, 2021);
        Timestamp ts = new Timestamp(dateDate.getTime());
        appointment.setDateTimeOfAppointment(ts);
        appointment.setIsVisited((byte) 0);

        AppointmentService appointmentService = new AppointmentService();
        appointmentService.save(appointment);

        return "Успешно";
    }
}
