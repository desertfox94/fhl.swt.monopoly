package monopoly.model;

import monopoly.core.CircleList;
import monopoly.core.fields.Field;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author Jake Stradling
 */
public class TestEdition {

    @Test
    public void testGetAndSetName(){
        Edition edition = new Edition();
        assertTrue(edition.getName() == null);
        String name = "Testname";
        edition.setName(name);
        assertTrue(edition.getName().equals(name));
    }

    @Test
    public void testGetAndSetFields(){
        Edition edition = new Edition();
        assertTrue(edition.getFields() == null);
        CircleList<Field> list = new CircleList<>();
        edition.setFields(list);
        assertTrue(edition.getFields().equals(list));
    }

    @Test
    public void testGetAndSetMaxAmountOfPlayers(){
        Edition edition = new Edition();
        assertTrue(edition.getMaxAmountOfPlayers() == 0);
        int amount = Integer.MAX_VALUE;
        edition.setMaxAmountOfPlayers(amount);
        assertTrue(edition.getMaxAmountOfPlayers() == amount);
    }

    @Test
    public void testGetAndSetCurrency(){
        Edition edition = new Edition();
        assertTrue(edition.getCurrency() == null);
        String currency = "Euro";
        edition.setCurrency(currency);
        assertTrue(edition.getCurrency().equals(currency));
    }

    @Test
    public void testGetAndSetCurrencyFactor(){
        Edition edition = new Edition();
        assertTrue(edition.getCurrencyFactor() == 0);
        int factor = 4;
        edition.setCurrencyFactor(factor);
        assertTrue(edition.getCurrencyFactor() == factor);
    }

    @Test
    public void testGetAndSetFigures(){
        Edition edition = new Edition();
        assertTrue(edition.getFigures().isEmpty());
        List<Figure> list = new ArrayList<>();
        edition.setFigures(list);
        assertTrue(edition.getFigures().equals(list));
    }

    @Test
    public void testAddFigure(){
        Edition edition = new Edition();
        Figure figure = new Figure(null,"Testfigure");
        edition.addFigure(figure);
        assertTrue(edition.getFigures().contains(figure));
    }

    @Test
    public void testGetAndSetBackground(){
        Edition edition = new Edition();
        assertTrue(edition.getBackground() == null);
        String background = "TestBackground";
        edition.setBackground(background);
        assertTrue(edition.getBackground().equals(background));
    }

    @Test
    public void testToString(){
        Edition edition = new Edition();
        String name = "TestName";
        edition.setName(name);
        assertTrue(edition.toString().equals(name));
    }
}
