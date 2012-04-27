package collisions;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import sprite.AnimatedGameSprite;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;


public class GameCollisionManager extends CollisionGroup
{
    private List<CollisionSpec> specList = new ArrayList<CollisionSpec>();
    
    public GameCollisionManager()
    {
        specList = new ArrayList<CollisionSpec>();
    }

    public void collided (Sprite sprite1, Sprite sprite2)
    {
        CollisionSpec cspec = traverseSpec((AnimatedGameSprite) sprite1, (AnimatedGameSprite) sprite2);  
        performAction(cspec, (AnimatedGameSprite) sprite1, (AnimatedGameSprite) sprite2, getCollisionSide());


    }
    
    public void addSpecList(List<CollisionSpec> gs){
        specList.addAll(gs);
    }
    
    private CollisionSpec traverseSpec (AnimatedGameSprite sprite1, AnimatedGameSprite sprite2){
        for (CollisionSpec cs: specList){
            Set<String> spriteKeys = cs.returnActMap().keySet();
            if (spriteKeys.contains(sprite1.getGroup()) && spriteKeys.contains(sprite2.getGroup())){
                return cs;
            }
         
        }
        return null;
    }
    
    private void performAction (CollisionSpec cspec, AnimatedGameSprite sp1, AnimatedGameSprite sp2, int side){
        if (cspec!=null){
            ArrayList<String> tempList1 = cspec.returnActMap().get(sp1.getGroup());
            ArrayList<String> tempList2 = cspec.returnActMap().get(sp2.getGroup());

            CollisionContext ccntext = new CollisionContext();
            ccntext.addSprite1(sp1); ccntext.addSprite2(sp2); ccntext.addSide(side);
            Object args[] = new Object[2];
            args[0] = ccntext; args[1] = cspec;

            CollisionAction caSp1 = cspec.getActionInstance(sp1);
            CollisionAction caSp2 = cspec.getActionInstance(sp2);

            if (caSp1!=null){
                caSp1.setSprite(sp1); 

                for (String action1: tempList1){
                    if ((action1 == null ) || (action1.trim()=="")){
                        continue;
                    }
                    try{
                        Method mc = caSp1.getClass().getMethod(action1, ccntext.getClass(), cspec.getClass());
                        mc.invoke(caSp1, args);
                    }
                    catch (Exception e){
                    }
                }
            }
            if (caSp2 != null){
                caSp2.setSprite(sp2);

                for (String action2: tempList2){
                    if ((action2 == null ) || (action2.trim()=="")){
                        continue;
                    }
                    try{
                        Method mc = caSp2.getClass().getMethod(action2, ccntext.getClass(), cspec.getClass());
                        mc.invoke(caSp2, args);
                    }
                    catch (Exception e){
                    }
                }

            }

        }
    }   
    
    
}
