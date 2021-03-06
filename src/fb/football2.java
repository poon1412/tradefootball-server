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
@Path("player2")
public class football2 {
	@GET
	@Path("/findplayer2/{position}") 
	@Produces(MediaType.TEXT_XML)  
	public String findplayer(@PathParam("position") String name) {
		DB db = new Connect().mongo();
		DBCollection table = db.getCollection("player");
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("position", name);
		
		DBCursor cursor = table.find(searchQuery);
		List<DBObject> myList = cursor.toArray();
		
		String xml = "<?xml version=\"1.0\"?>";
		xml += "<return>";
		for (DBObject object : myList) {
			xml += "<player>";
				xml += "<name>"+object.get("name").toString()+"</name>";
				xml += "<lname>"+object.get("lname").toString()+"</lname>";
				xml += "<age>"+object.get("age").toString()+"</age>";
				xml += "<weight>"+object.get("weight").toString()+"</weight>";
				xml += "<height>"+object.get("height").toString()+"</height>";
				xml += "<D_M_Y>"+object.get("D_M_Y").toString()+"</D_M_Y>";
				xml += "<team>"+object.get("team").toString()+"</team>";
				xml += "<position>"+object.get("position").toString()+"</position>";
				xml += "<number>"+object.get("number").toString()+"</number>";
				xml += "<country>"+object.get("country").toString()+"</country>";
				xml += "<_idteam>"+object.get("_idteam").toString()+"</_idteam>";
			xml += "</player>";
		}
		xml += "</return>";
		
		System.out.println(xml);
		
		return xml;  
	}
}
