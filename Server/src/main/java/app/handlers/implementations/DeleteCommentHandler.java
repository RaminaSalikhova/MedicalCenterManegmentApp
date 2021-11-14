package app.handlers.implementations;

import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.DataTransferModels.DeleteCommentDto;
import app.services.ComplaintService;

public class DeleteCommentHandler extends RequestHandler<DeleteCommentDto, String> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.deleteComment;
    }

    @Override
    protected String execute(DeleteCommentDto deleteCommentDto) throws Exception {
        ComplaintService complaintService = new ComplaintService();
        complaintService.deleteByID(deleteCommentDto.getCommentID());
        return "Успешно";
    }
}
