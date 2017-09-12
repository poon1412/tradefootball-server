package fb;
import javax.jws.WebParam;
import javax.ws.rs.FormParam; 
import javax.ws.rs.GET; 
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;
public class football {
	
	@Produces(MediaType.TEXT_PLAIN)   
	public String nottingcoming() 
	{      
		return "Hello I Think You Forgot SomeThing/";    
	} 

}
