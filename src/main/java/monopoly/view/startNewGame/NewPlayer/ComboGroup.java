package monopoly.view.startNewGame.NewPlayer;

import java.util.ArrayList;
import java.util.List;

public class ComboGroup {

	private List<GroupCombobox> members = new ArrayList<GroupCombobox>();

	public void add(GroupCombobox comboBox) {
		members.add(comboBox);
	}

	public List<GroupCombobox> getMembers() {
		return members;
	}

	public void notify(GroupCombobox source) {
		for (GroupCombobox comboBox : members) {
			if (comboBox != source) {
				comboBox.refreshItems();
			}
		}
	}

}
