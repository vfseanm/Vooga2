package collisions;

import java.util.ArrayList;
import java.util.HashMap;

public class CollisionSpec {
	
	HashMap<String, ArrayList<String>> actionMap = new HashMap <String, ArrayList<String>>(); 
	HashMap<String, Class<? extends CollisionAction>> classMap = new HashMap <String, Class<? extends CollisionAction>>();
	
	public void addClass (String spriteTag, Class <? extends CollisionAction> spriteAction){
		classMap.put(spriteTag, spriteAction);
	}
	
	public CollisionAction getActionInstance (String spriteTag){
		Class<? extends CollisionAction> ca = classMap.get(spriteTag);
		if (ca!=null){
			try{
				return ca.newInstance();
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
