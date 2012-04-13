package demo;

import java.awt.Color;
import java.awt.Graphics2D;

import platforms.AbstractPlatform;

import sprite.AnimatedGameSprite;

public class DemoGame extends PlatformGame{
    
    
    @Override
    public void initResources()
    {
        loadLevel("level2");
        for(AnimatedGameSprite s: mySprites)
        {
            System.out.println(s);
        }  
    }

    @Override
    public void render(Graphics2D pen)
    {
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getWidth(), getHeight());
        for(AnimatedGameSprite s:  mySprites)
        {
           s.render(pen);
        }   
    }

    @Override
    public void update(long elapsedTime)
    {
        for(AnimatedGameSprite s: mySprites)
        {
            
            s.update(elapsedTime);
        }
    }
}

