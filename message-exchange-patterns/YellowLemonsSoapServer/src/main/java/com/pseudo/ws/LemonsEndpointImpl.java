package com.pseudo.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "com.pseudo.ws.LemonsEndpoint")
public class LemonsEndpointImpl implements LemonsEndpoint {

    public String getLemons() {
        return "lemons";
    }

    public String hitTheWallWithLemons(@WebParam String name) {
        return name;
    }
}
