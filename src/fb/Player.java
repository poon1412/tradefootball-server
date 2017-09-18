package fb;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

@Path("createPLayer")
public class Player {

	@POST
	@Path("/create") 
	@Produces(MediaType.TEXT_PLAIN)  
	public String create(@FormParam("number") String number, @FormParam("name") String name, @FormParam("lname") String lname
			, @FormParam("age") String age, @FormParam("weight") String weight, @FormParam("height") String height
			, @FormParam("D_M_Y") String D_M_Y, @FormParam("team") String team, @FormParam("position") String position) {   
		
		DB db = new Connect().mongo();
		DBCollection collection = db.getCollection("player");
		
		System.out.println(number);
		System.out.println(name);
		System.out.println(lname);
		
		BasicDBObject document = new BasicDBObject();
		document.put("number", number);
		document.put("name", name);
		document.put("age", age);
		document.put("weight", weight);
		document.put("height", height);
		document.put("D_M_Y", D_M_Y);
		document.put("team", team);
		document.put("position", position);

		collection.insert(document);
		
		return "true";  
	}
	
	@POST
	@Path("/update") 
	@Produces(MediaType.TEXT_PLAIN)  
	public String update(@FormParam("id") String id, @FormParam("number") String number, @FormParam("name") String name, @FormParam("lname") String lname
			, @FormParam("age") String age, @FormParam("weight") String weight, @FormParam("height") String height
			, @FormParam("D_M_Y") String D_M_Y, @FormParam("team") String team, @FormParam("position") String position) {   
		
		DB db = new Connect().mongo();
		DBCollection collection = db.getCollection("player");
		
		BasicDBObject document = new BasicDBObject();
		document.put("number", number);
		document.put("name", name);
		document.put("age", age);
		document.put("weight", weight);
		document.put("height", height);
		document.put("D_M_Y", D_M_Y);
		document.put("team", team);
		document.put("position", position);
		BasicDBObject setQuery = new BasicDBObject();
        setQuery.put("$set", document);
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("_id", new ObjectId(id));

		collection.update(searchQuery, setQuery);
		
		return "true";  
	} 
	
	@GET
	@Path("/delete/{id}") 
	@Produces(MediaType.TEXT_PLAIN)  
	public String delete(@PathParam("id") String id) {   
		System.out.println(id);
		DB db = new Connect().mongo();
		DBCollection collection = db.getCollection("player");
		
		DBObject document = collection.findOne(new ObjectId(id));
		collection.remove(document);
		
		return "true";  
	}
	
	@GET
	@Path("/getUpdate/{id}") 
	@Produces(MediaType.TEXT_XML)  
	public String getUpdate(@PathParam("id") String id) {   
		
		DB db = new Connect().mongo();
		DBCollection table = db.getCollection("player");
		
		DBObject object = table.findOne(new ObjectId(id));
		
		// create xml
		String xml = "<?xml version=\"1.0\"?>";
		xml += "<return>";
			xml += "<player>";
				xml += "<id>"+object.get("_id").toString()+"</id>";
				xml += "<number>"+object.get("number").toString()+"</number>";
				xml += "<name>"+object.get("name").toString()+"</name>";
				xml += "<Jones>"+object.get("Jones").toString()+"</Jones>";
				xml += "<age>"+object.get("age").toString()+"</age>";
				xml += "<weight>"+object.get("weight").toString()+"</weight>";
				xml += "<height>"+object.get("height").toString()+"</height>";
				xml += "<D_M_Y>"+object.get("D_M_Y").toString()+"</D_M_Y>";
				xml += "<team>"+object.get("team").toString()+"</team>";
				xml += "<position>"+object.get("position").toString()+"</position>";
			xml += "</player>";
		xml += "</return>";
		
		return xml;  
	} 
	
}
