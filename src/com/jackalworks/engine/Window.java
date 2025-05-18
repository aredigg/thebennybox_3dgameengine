package jackalworks.engine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.nio.IntBuffer;
import org.lwjgl.opengl.*;

public class Window {

    private static long window = 0;

    public static void createWindow(int width, int height, String title) {
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        window = glfwCreateWindow(width, height, title, 0, 0);
        if (window == 0) {
            throw new RuntimeException("Failed to create window");
        }
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glfwShowWindow(window);
    }

    public static void render() {
        glfwPollEvents();
        glfwSwapBuffers(window);
    }

    public static void dispose() {
        glfwDestroyWindow(window);
    }

    public static boolean isCloseRequested() {
        return glfwWindowShouldClose(window);
    }

    public static int getWidth() {
        IntBuffer width = IntBuffer.allocate(1);
        IntBuffer height = IntBuffer.allocate(1);
        glfwGetWindowSize(window, width, height);
        return width.get(0);
    }

    public static int getHeight() {
        IntBuffer width = IntBuffer.allocate(1);
        IntBuffer height = IntBuffer.allocate(1);
        glfwGetWindowSize(window, width, height);
        return height.get(0);
    }

    public static String getTitle() {
        return glfwGetWindowTitle(window);
    }

    public static long getHandle() {
        return window;
    }
}
