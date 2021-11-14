package app.handlers.implementations;

import app.entity.Complaint;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.AnswerTransferModels.GetCommentListAtm;
import app.models.DataTransferModels.GetCommentListDto;
import app.services.ComplaintService;

import java.util.ArrayList;
import java.util.List;

public class GetCommentListHandler extends RequestHandler<GetCommentListDto,List<GetCommentListAtm>> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.getCommentList;
    }

    @Override
    protected List<GetCommentListAtm> execute(GetCommentListDto getCommentListDto) throws Exception {

        ComplaintService complaintService = new ComplaintService();
        List<Complaint> complaints = complaintService.findAll();
        List<GetCommentListAtm> commentListAtms = new ArrayList<>();
        for (Complaint complaint : complaints) {
            GetCommentListAtm getCommentListAtm = new GetCommentListAtm();
            getCommentListAtm.setCommentID(complaint.getId());
            getCommentListAtm.setMessage(complaint.getMessage());
            commentListAtms.add(getCommentListAtm);
        }
        return commentListAtms;
    }
}
