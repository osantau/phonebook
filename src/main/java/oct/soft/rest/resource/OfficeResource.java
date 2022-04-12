package oct.soft.rest.resource;

import javax.ws.rs.Path;

import oct.soft.dao.OfficeDAO;
import oct.soft.db.util.MyDataSource;

@Path("office")
public class OfficeResource {
	private OfficeDAO dao = null;

	public OfficeResource() {
		this.dao = new OfficeDAO(MyDataSource.getDataSource());
	}
}
