package app.handlers;

import app.connection.ClientRequest;
import app.connection.ServerResponse;
import app.enums.HANDLER_TYPE;

public abstract class RequestHandler<ModelIn, ModelOut> {
    public ServerResponse<ModelOut> handleRequest(ClientRequest<ModelIn> clientRequest){
        ServerResponse<ModelOut> response = new ServerResponse<>();
        try {
            response.setData(execute(clientRequest.getData()));
            response.setStatus(true);
        }catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(false);
        }

        return response;
    }

    protected abstract ModelOut execute(ModelIn model) throws Exception;
    public abstract HANDLER_TYPE handlerType();
}