package app.handlers.implementations;

import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.DataTransferModels.UpdateAppointmentDto;
import app.services.AppointmentService;
import app.services.PatientService;

public class UpdateAppointmentHandler extends RequestHandler<UpdateAppointmentDto, String> {

    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.updateAppointmentDto;
    }

    @Override
    protected String execute(UpdateAppointmentDto updateAppointmentDto) throws Exception {
        PatientService patientService = new PatientService();
        patientService.updateWeightAndHeightAndSex(updateAppointmentDto.getWeight(), updateAppointmentDto.getHeight(), updateAppointmentDto.getPatientID(), updateAppointmentDto.getSex());
        AppointmentService appointmentService = new AppointmentService();
        appointmentService.updateVisit(updateAppointmentDto);
        return "Успешно";
    }
}
