package tdl.client.abstractions;

import tdl.client.audit.Auditable;
import tdl.client.serialization.JsonRpcRequest;
import tdl.client.transport.StringMessage;

/**
 * Created by julianghionoiu on 20/06/2015.
 */
public class Request implements Auditable {
    private StringMessage originalMessage;
    private JsonRpcRequest requestData;

    public Request(StringMessage originalMessage, JsonRpcRequest requestData) {
        this.originalMessage = originalMessage;
        this.requestData = requestData;
    }

    public StringMessage getOriginalMessage() {
        return originalMessage;
    }

    public String getId() {
        return requestData.getId();
    }

    public String getMethodName() {
        return requestData.getMethod();
    }

    public String[] getParams() {
        return requestData.getParams();
    }


    //~~~ Pretty print

    @Override
    public String getAuditText() {
        return String.format("id = %s, req = %s(%s)",
                getId(), getMethodName(), paramsToString(getParams()));
    }

    private static String paramsToString(String[] params) {
        StringBuilder sb = new StringBuilder();
        for (String param : params) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(param);
        }
        return sb.toString();
    }
}
