package mygame.Config;

import com.jme3.input.KeyInput;

public enum Keybindings {
    PlayerLeft(KeyInput.KEY_A, "PlayerLeft"),
    PlayerRight(KeyInput.KEY_D, "PlayerRight"),
    PlayerUp(KeyInput.KEY_W, "PlayerUp"),
    PlayerShoot(KeyInput.KEY_SPACE, "PlayerShoot");
     
    private int keyInput;
    private String name;
    private Keybindings(int keyInput, String name) {
        this.keyInput = keyInput;
        this.name = name;
    }

    public int getKeyInput() {
        return keyInput;
    }

    public String getName() {
        return name;
    }
}
