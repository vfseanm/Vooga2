package platforms;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import fighter.*;

@SuppressWarnings("serial")
public abstract class DecoratedPlatform extends AbstractPlatform {
      protected AbstractPlatform myDecoratorComponent;
      
      
      public DecoratedPlatform(AbstractPlatform decoratorComponent) {
          myDecoratorComponent = decoratorComponent;  
      };
}
