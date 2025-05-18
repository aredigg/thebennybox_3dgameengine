package jackalworks.engine;

import static org.lwjgl.glfw.GLFW.*;

public class Main {

    public void start() {
        run();
    }

    public void stop() {}

    public void run() {
        while (!Window.isCloseRequested()) {
            render();
        }
    }

    public void render() {
        Window.render();
    }

    public void cleanup() {}

    public static void main(String[] args) {
        glfwInit();
        Window.createWindow(800, 600, "3D Game Engine");
        Main game = new Main();
        game.start();
        glfwTerminate();
    }
}
