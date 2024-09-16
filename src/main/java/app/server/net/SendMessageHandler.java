package app.server.net;

import app.IO;
import app.server.message.MessageService;
import app.server.session.Session;
import app.server.session.SessionService;
import app.server.session.Token;
import app.transport.Transport;
import app.transport.TransportException;
import app.transport.message.Message;
import app.transport.message.storage.SendMessageRequest;
import app.transport.message.storage.SendMessageResponse;

import java.util.List;

public class SendMessageHandler extends Handler{
    private final SessionService sessionService;
    private final List<Transport> transports;
    private final MessageService messageService;
    public SendMessageHandler(Transport transport, IO io, SessionService sessionService, MessageService messageService, List<Transport> transports) {
        super(transport, io);
        this.sessionService = sessionService;
        this.messageService = messageService;
        this.transports = transports;
    }

    @Override
    public void handle(Message message) {
        var req = (SendMessageRequest) message;
        Token token = Token.fromText(req.getToken());
        var username = sessionService.get(token).getString(Session.USERNAME);
        messageService.Create(username, req.getMessage());

        transport.send(new SendMessageResponse(username, req.getMessage(), req.getTime()));

        for(var newTransport : transports){
            System.out.println("sending to " + newTransport);
            if(newTransport != transport){
                try {
                    newTransport.send(new SendMessageResponse(username, req.getMessage(), req.getTime()));
                    io.println("send message to " + newTransport);
                } catch (TransportException e) {
                    // TODO remove from transports via iterators
                }
            }
        }
        io.println(String.format("user <%s> send message <<%s>> time %s", username, req.getMessage(), req.getTime()));
    }
}
