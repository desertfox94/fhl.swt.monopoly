package monopoly.Gui;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.utils.FXTestUtils;
import javafx.scene.Parent;
import javafx.stage.Stage;
import monopoly.App;




public class MonopolyGuiTest  {

	public static class TestApp extends App  {
		private static Stage primaryStage;
		
		public TestApp () {
			super();
		}
	
		@Override
		public void start(Stage primaryStage) {
			//primaryStage.initStyle(StageStyle.UNDECORATED);
		    super.start(primaryStage);
		}
	
		public static Stage getStage() {
			return primaryStage;
		}	
		
	}

	public static GuiTest controller;

	  @Before
	  public void setup() throws Exception {

		    FXTestUtils.launchApp(TestApp.class);   
		    controller = new GuiTest()
		    {
		        @Override
		        protected Parent getRootNode()
		        {
		            return TestApp.getStage().getScene().getRoot();
		        }
		    };  	
		  Thread.sleep(2000);
	  }
	  
	@Test
	public void testExists() {		
		assertTrue(GuiTest.exists("Neues Spiel"));
		assertTrue(GuiTest.exists("Spielstand laden"));	
		assertTrue(GuiTest.exists("Spiel"));	
	
	}
	
	@Test
	public void testClick() {	
		
		controller.click("Neues Spiel"); // doesnt work TODO
		controller.click("Spielstand laden");  // doesnt work TODO
		controller.click("Spiel");
		controller.click("Neues Spiel!");
		controller.click("Hilfe");
		controller.click("Regelwerk");
		controller.click("Spiel");
		controller.click("Beenden");
		assertTrue(GuiTest.exists("Cancel"));
		controller.click("Cancel");
	}

}
