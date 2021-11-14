package app.handlers.implementations;

import app.entity.Complaint;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.DataTransferModels.SendComplaintDto;
import app.services.ComplaintService;

public class SendComplaintHandler extends RequestHandler<SendComplaintDto, String> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.sendComplaint;
    }

    @Override
    protected String execute(SendComplaintDto sendComplaintDto) throws Exception {
        ComplaintService complaintService = new ComplaintService();
        Complaint complaint = new Complaint();
        complaint.setMessage(sendComplaintDto.getMessage());
        complaintService.save(complaint);
        return "Успешно отправлено";
    }
}
