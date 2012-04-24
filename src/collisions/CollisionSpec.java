package collisions;

import java.util.ArrayList;

import java.util.HashMap;

import sprite.AnimatedGameSprite;

public class CollisionSpec {
	
	HashMap<String, ArrayList<String>> actionMap = new HashMap <String, ArrayList<String>>(); 
	
	public CollisionAction getActionInstance (AnimatedGameSprite sprite){
		Class<? extends CollisionAction> caction =  sprite.getActionClass();
		if (caction == null)
			return null; 
		else{
			try{
				return caction.newInstance();
			}
			catch (Exception e){
				System.out.println ("");
			}
		}
		return null;
	}
	
	public void addActMap (String spriteTag, String action){
		if (actionMap.get(spriteTag)==null){
			ArrayList<String> actionList = new ArrayList<String>();
			actionList.add(action);
			actionMap.put(spriteTag, actionList);
		}
		else{
			actionMap.get(spriteTag).add(action);
		}
	}
	
	public HashMap<String, ArrayList<String>> returnActMap (){
		return actionMap;
	}
	
}
