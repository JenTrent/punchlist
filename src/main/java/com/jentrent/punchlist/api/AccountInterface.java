package com.jentrent.punchlist.api;

import java.util.Base64;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.jentrent.punchlist.entity.Account;
import com.jentrent.punchlist.service.AccountException;
import com.jentrent.punchlist.service.AccountService;
import com.jentrent.punchlist.service.SpringUtil;

@Path("/account")
public class AccountInterface{

	private AccountService accountService;

	public AccountInterface(){

		accountService = (AccountService) SpringUtil.getBean("accountService");

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create")
	public Response createAccount(String json) throws AccountException{

		Account account = parseAccount(json);
		Account created = accountService.createAccount(account);
		created.setAccountId(null);
		return Response.status(200).entity(created).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/read")
	public Response readAccount(@HeaderParam("authorization")
	String authHeader) throws AccountException{

		Account account = validateUserAndRetrieveAccount(authHeader);
		account.setAccountId(null);
		return Response.status(200).entity(account).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response updateAccount(String json, @HeaderParam("authorization")
	String authHeader) throws AccountException{

		Account account = validateUserAndRetrieveAccount(authHeader);

		Account incoming = parseAccount(json);

		incoming.setAccountId(account.getAccountId());

		Account updated = accountService.updateAccount(incoming);
		updated.setAccountId(null);
		return Response.status(200).entity(updated).build();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	public Response deleteAccount(@HeaderParam("authorization")
	String authHeader) throws AccountException{

		Account account = validateUserAndRetrieveAccount(authHeader);
		Integer deleted = accountService.deleteAccount(account.getAccountId());

		if(deleted == 1){
			return Response.status(200).entity("Account " + account.getEmail() + " deleted").build();
		}else{
			throw new AccountException("Account " + " not deleted");
		}

	}

	private Account validateUserAndRetrieveAccount(String authHeader) throws AccountException{

		try{

			byte[] bytes = Base64.getDecoder().decode(authHeader.split("\\s+")[1]);
			String[] auth = new String(bytes).split(":");

			return accountService.validateUser(auth[0], auth[1]);

		}catch(Exception e){
			throw new AccountException(e.getMessage());
		}

	}

	private Account parseAccount(String json) throws AccountException{

		try{
			Account account = new Account();
			JSONObject jo = new JSONObject(json);
			account.setEmail(jo.getString("email"));
			account.setFirstName(jo.getString("firstName"));
			account.setLastName(jo.getString("lastName"));
			account.setPassword(jo.getString("password"));
			return account;
		}catch(Exception e){
			throw new AccountException(e.getMessage());
		}

	}

}
