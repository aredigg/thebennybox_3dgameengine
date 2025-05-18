package jackalworks.engine;

import static org.lwjgl.glfw.GLFW.*;

public class Game {

    public Game() {}

    public void input() {
        if (Input.getKeyDown(GLFW_KEY_UP)) System.out.println("We pressed up!");
        if (Input.getKeyUp(GLFW_KEY_UP)) System.out.println("We released up!");
        if (Input.getMouseDown(GLFW_MOUSE_BUTTON_LEFT)) System.out.println(
            "We clicked left mouse! at " + Input.getMousePosition()
        );
        if (Input.getMouseUp(GLFW_MOUSE_BUTTON_LEFT)) System.out.println(
            "We released left mouse! at " + Input.getMousePosition()
        );
        if (Input.getMouseDown(GLFW_MOUSE_BUTTON_RIGHT)) System.out.println(
            "We clicked right mouse! at " + Input.getMousePosition()
        );
        if (Input.getMouseUp(GLFW_MOUSE_BUTTON_RIGHT)) System.out.println(
            "We released right mouse! at " + Input.getMousePosition()
        );
    }

    public void update() {}

    public void render() {}
}
