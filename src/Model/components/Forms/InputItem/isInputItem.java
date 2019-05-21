package Model.components.Forms.InputItem;

import Model.components.Forms.NotValidInput;

public interface isInputItem {

    String getInput() throws NotValidInput;
    boolean isValid();

}
