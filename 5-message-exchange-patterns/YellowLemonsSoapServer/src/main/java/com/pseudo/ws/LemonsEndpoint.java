package com.pseudo.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface LemonsEndpoint {

    @WebMethod
    String getLemons();

    @WebMethod
    String hitTheWallWithLemons(@WebParam String name);
}
