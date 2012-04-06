package platforms;

public class PlatformDecorator extends AbstractPlatform {
      private AbstractPlatform myDecoratorComponent;
      
      public PlatformDecorator(AbstractPlatform decoratorComponent) {
            this.myDecoratorComponent = decoratorComponent;
      }
}
