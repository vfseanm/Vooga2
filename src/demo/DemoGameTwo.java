package demo;

import java.awt.Graphics2D;

import sprite.AnimatedGameSprite;

public class DemoGameTwo extends PlatformGame{
    
    
    @Override
    public void initResources()
    {
        loadLevel("level2");
        
        for(AnimatedGameSprite s: sprites)
        {
            System.out.println(s);
        }
       
    }

    @Override
    public void render(Graphics2D pen)
    {
        for(AnimatedGameSprite s: sprites)
        {
            s.render(pen);
        }
    }

    @Override
    public void update(long elapsedTime)
    {
        for(AnimatedGameSprite s: sprites)
        {
            s.update(elapsedTime);
        }
    }
}
