package connect;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class Connect {
	
	public DB mongo() {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("footballDB");
		return db;
	}
	
}
