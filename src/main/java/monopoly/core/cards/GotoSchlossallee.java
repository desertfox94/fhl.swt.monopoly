package monopoly.core.cards;

import monopoly.core.CircleList;
import monopoly.core.fields.Field;
import monopoly.model.Player;

public class GotoSchlossallee extends Card{
    public GotoSchlossallee() {
        super("Rücke vor bis zur Schlossallee", "Rücke vor bis zur Schlossallee. Wenn \"los\" Überschritten wird, dann ziehe das entsprechende Geld ein. ");
    }

    @Override
    public boolean execute(Player player) {
        CircleList<Field> fields = player.getGame().getEdition().getFields();
        Field schlossAllee = fields.get(fields.size()-1);
        player.getGame().movePlayerToField(player, schlossAllee);
        return true;
    }


}
