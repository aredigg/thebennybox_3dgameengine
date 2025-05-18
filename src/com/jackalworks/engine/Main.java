package jackalworks.engine;

import static org.lwjgl.glfw.GLFW.*;

public class Main {

    public static final int MAIN_WIDTH = 800;
    public static final int MAIN_HEIGHT = 600;
    public static final String MAIN_TITLE = "3D Game Engine";
    public static final double FRAME_CAP = 5000.0;

    private boolean isRunning;

    private Game game;

    public Main() {
        isRunning = false;
        game = new Game();
    }

    public void start() {
        if (isRunning) return;
        run();
    }

    public void stop() {
        if (!isRunning) return;
        isRunning = false;
    }

    private void run() {
        isRunning = true;

        int frames = 0;
        long frameCounter = 0;

        final double frameTime = 1.0 / FRAME_CAP;

        long lastTime = Time.getTime();
        double unprocessedTime = 0;

        while (isRunning) {
            boolean render = false;

            long startTime = Time.getTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime / (double) Time.SECOND;
            frameCounter += passedTime;

            while (unprocessedTime > frameTime) {
                render = true;

                unprocessedTime -= frameTime;

                if (Window.isCloseRequested()) stop();

                Time.setDelta(frameTime);
                Input.update();

                game.input();
                game.update();

                if (frameCounter >= Time.SECOND) {
                    System.out.println(frames);
                    frames = 0;
                    frameCounter = 0;
                }
            }

            if (render) {
                render();
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {}
            }
        }
        cleanup();
    }

    private void render() {
        game.render();
        Window.render();
    }

    private void cleanup() {
        Window.dispose();
    }

    public static void main(String[] args) {
        glfwInit();
        Window.createWindow(MAIN_WIDTH, MAIN_HEIGHT, MAIN_TITLE);
        Main game = new Main();
        game.start();
        glfwTerminate();
    }
}
