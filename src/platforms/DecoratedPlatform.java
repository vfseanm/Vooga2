package platforms;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import fighter.*;

@SuppressWarnings("serial")
public abstract class DecoratedPlatform extends AbstractPlatform {
      protected AbstractPlatform myDecoratorComponent;
      
      
      public DecoratedPlatform(AbstractPlatform decoratorComponent) {
          myDecoratorComponent = decoratorComponent;  
          setX(myDecoratorComponent.getX());
          setY(myDecoratorComponent.getY());
          setImages(myDecoratorComponent.getImages());
          setImageNames(myDecoratorComponent.getImageNames());
          
          
      }
      
      
}
