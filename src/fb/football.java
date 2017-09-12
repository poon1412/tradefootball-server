package fb;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.types.ObjectId;

import connect.Connect;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
@Path("player")
public class football {
	@GET
	@Path("/findplayer") 
	@Produces(MediaType.TEXT_XML)  
	public String findplayer(@PathParam("name") String name) {
		DB db = new Connect().mongo();
		DBCollection table = db.getCollection("player");
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("name", name);
		
		DBCursor cursor = table.find(searchQuery);
		List<DBObject> myList = cursor.toArray();
		
		String xml = "<?xml version=\"1.0\"?>";
		xml += "<return>";
		for (DBObject object : myList) {
			xml += "<player>";
				xml += "<number>"+object.get("_id").toString()+"</number>";
				xml += "<name>"+object.get("course_id").toString()+"</name>";
				xml += "<name>"+object.get("name").toString()+"</name>";
				xml += "<description>"+object.get("description").toString()+"</description>";
			xml += "</player>";
		}
		xml += "</return>";
		
		System.out.println(xml);
		
		return xml;  
	}
	
	
}
