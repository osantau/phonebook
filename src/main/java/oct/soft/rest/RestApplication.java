package oct.soft.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import oct.soft.rest.resource.BranchResource;
import oct.soft.rest.resource.OfficeResource;
import oct.soft.rest.resource.PersonResource;

@ApplicationPath("/rest")
public class RestApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	
	public RestApplication() {
		this.singletons.add(new BranchResource());
		this.singletons.add(new OfficeResource());		
		this.singletons.add(new PersonResource());
	
	}
	@Override
	public Set<Object> getSingletons() {	
		return this.singletons;
	}
}
