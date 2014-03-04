package nl.tudelft.bsg.utopolis.server.game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonObject.Member;
import com.eclipsesource.json.JsonValue;

import nl.tudelft.bsg.utopolis.server.model.City;
import nl.tudelft.bsg.utopolis.server.model.Structure;
import nl.tudelft.bsg.utopolis.server.model.StructureType;

public class QuestEngine {
	
	public Set<Integer> calculateCompletedQuests(City city){
		// This has to be done less retarded >_<

		HashMap<StructureType, HashMap<String, Integer>> questRequirements = getQuestRequirements(city);

		Set<Integer> completed = new HashSet<Integer>();		
		HashMap<StructureType, Integer> count = countStructures(city);

		
		// Quest 0
		if((count.get(StructureType.house) >= 3) &&
			(count.get(StructureType.civic_center) >= 1) &&
			(count.get(StructureType.farm) >= 1)){

			completed.add(0);
		}

		// Quest 1
		if((count.get(StructureType.tower) >= 4)){

			completed.add(1);
		}

		// Quest 2
		if((count.get(StructureType.corral) >= 1)){

			completed.add(2);
		}
		
		return completed;
	}

	private List<Quest> getQuestRequirements(City city) {
		HashMap<String, HashMap<String, Integer>> questRequirements;
		FileReader reader;
		try {
		
			reader = new FileReader( "src/main/config/quests.json");
			JsonObject all = JsonObject.readFrom(reader);
		
			for(JsonValue quest : all.get("quests").asArray()){
				JsonObject reqDefs = quest.asObject().get("requirements").asObject();
				for(Member reqDef : reqDefs){
					reqDef.getName();
					reqDef.getValue();				
				}
			}
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** Basically performs a count of the structures in a city, grouped by structuretype 
	 * @param city of which to count the structures of
	 * @return amount of structures in a city, grouped by structuretype */
	private HashMap<StructureType, Integer> countStructures(City city) {
		HashMap<StructureType, Integer> count = new HashMap<StructureType, Integer>();

		// Populate with zeroes
		for (StructureType type : StructureType.values()) {
			count.put(type, 0);
		}
		
		for(Structure struct : city.getStructures()){
			StructureType type = struct.getStructType();
			//if(count.containsKey(type)){
			count.put(type, count.get(type).intValue() + 1);
			//}
			//else
			//	count.put(type, 1);
			//}
		}
		return count;
	}
}
