package platforms;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import fighter.*;

@SuppressWarnings("serial")
public abstract class DecoratedPlatform extends AbstractPlatform {
      protected AbstractPlatform myDecoratorComponent;
      
      
      public DecoratedPlatform(BufferedImage[] im, double x, double y, ArrayList<String> images, Fighter fighter) {
          super(im, x, y, images, fighter);
      }
      
      //Testing to see if the decorator pattern works properly now
      
//      public DecoratedPlatform(BufferedImage[] im, double x, double y, ArrayList<String> images, Fighter fighter, AbstractPlatform decoratorComponent) {
//          super(im, x, y, images, fighter);
//          this.myDecoratorComponent = decoratorComponent;
//      }
      
      public DecoratedPlatform(AbstractPlatform decoratorComponent) {
          myDecoratorComponent = decoratorComponent;  
      };
}
