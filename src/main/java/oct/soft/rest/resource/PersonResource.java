package oct.soft.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import oct.soft.dao.PersonDAO;
import oct.soft.db.util.MyDataSource;

@Path("person")
public class PersonResource {
	private PersonDAO dao = null;

	public PersonResource() {
		dao = new PersonDAO(MyDataSource.getDataSource());
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response all() {
		return Response.ok(dao.getAll()).build();
	}
}
