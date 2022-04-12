package oct.soft.rest.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import oct.soft.dao.BranchDAO;
import oct.soft.db.util.MyDataSource;
import oct.soft.model.Office;

@Path("branch")
public class BranchResource {
	private BranchDAO dao = null;

	public BranchResource() {
		this.dao = new BranchDAO(MyDataSource.getDataSource());
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response all() {
		List<Office> branchList = dao.branchList();
		
		if(branchList.size() > 0)
		return Response.ok(branchList).build();
		
		return Response.noContent().build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Office branch) {
		
		Office newBranch = dao.saveOrUpdate(branch);
		if (newBranch !=null)
		return Response.ok(newBranch).status(Status.CREATED).build();
		
		return Response.noContent().build();
	}
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response edit(@PathParam("id") Integer id)
	{
		Office branch = dao.getBranch(id);
		return branch != null ? Response.ok(branch).build() : Response.noContent().build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Integer id, Office branch)
	{
		branch.setIdoffice(id);
		Office changed = dao.saveOrUpdate(branch);
		return changed != null ? Response.ok(changed).build() : Response.noContent().build();
	}
}
