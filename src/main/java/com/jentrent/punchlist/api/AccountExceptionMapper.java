package com.jentrent.punchlist.api;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jentrent.punchlist.service.AccountException;

@Provider
public class AccountExceptionMapper implements ExceptionMapper<AccountException>{

	public Response toResponse(AccountException ae){

		return Response.status(Status.BAD_REQUEST).entity(ae.getMessage()).type(MediaType.APPLICATION_JSON).build();
	}

}
