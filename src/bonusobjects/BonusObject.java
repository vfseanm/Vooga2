package bonusobjects;

import java.lang.reflect.Type;
import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import collisions.CollisionAction;
import editor.json.Jsonable;
import editor.json.SpriteJsonData;

import sprite.AnimatedGameSprite;
import attributes.Attribute;
import attributes.Flying;

@SuppressWarnings("serial")
public class BonusObject extends AnimatedGameSprite {

    protected List<Attribute>		myAttributes;
    protected List<Attribute>		myAttributesToOffer;

    public BonusObject(double x, double y, List<String> image) {
        super(x, y, image);
        myAttributes = new ArrayList<Attribute>();
        myAttributesToOffer = new ArrayList<Attribute>();
    }

    // returns attributes to be added to sprite that collects this bonus object
    public List<Attribute> getAttributesToOffer() {
    	return Collections.unmodifiableList(myAttributesToOffer);
    }
    
    public String getGroup(){
    	return "BONUSOBJECT";
    }
    
    public void addAttribute(Attribute attributeToAdd) {
    	myAttributes.add(attributeToAdd);
    }
    
    public void addAttributeToOffer(Attribute attributeToAdd) {
    	myAttributesToOffer.add(attributeToAdd);
    }
    
    public Object clone()
    {
        List<String> imageNames = new ArrayList<String>();
        imageNames.addAll(this.getImageNames());
        BonusObject c = new BonusObject(this.getX(), this.getY(),imageNames);
        for(Attribute a: myAttributes)
        {
            c.addAttribute(a);
        }
        for(Attribute a: myAttributesToOffer)
        {
            c.addAttributeToOffer(a);
        }
        return c;
    }
    
    public Class<? extends CollisionAction> getActionClass (){
    	return BonusObjectAction.class; 
    }
    
    public String toJson()
    {
        Gson gson = new Gson();
        
        List<String> paramList = new ArrayList<String>();
        Map<String, String> attributeList = new HashMap<String, String>();
        for (Attribute a : myAttributes)
        {
            attributeList.put(a.getClass().toString(), a.toJson());
        }
        paramList.add(gson.toJson(attributeList));
        Map<String, String> attributeToOfferList = new HashMap<String, String>();
        for (Attribute a : myAttributesToOffer)
        {
            attributeToOfferList.put(a.getClass().toString(), a.toJson());
        }
        paramList.add(gson.toJson(attributeToOfferList));
        String additionalInformation = gson.toJson(paramList);
        return gson.toJson(new SpriteJsonData(this, additionalInformation));

    }
    
    
}
