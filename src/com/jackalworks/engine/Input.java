package jackalworks.engine;

import static org.lwjgl.glfw.GLFW.*;

import java.nio.DoubleBuffer;
import java.util.ArrayList;
import org.lwjgl.BufferUtils;

public class Input {

    private static final int MAX_KEY_CODE = 512;
    private static final int MAX_MOUSE_CODE = 7;

    private static boolean[] keyStatesCurrentFrame = new boolean[MAX_KEY_CODE];
    private static boolean[] keyStatesPreviousFrame = new boolean[MAX_KEY_CODE];

    private static boolean[] mouseButtonsCurrentFrame =
        new boolean[MAX_MOUSE_CODE];
    private static boolean[] mouseButtonsPreviousFrame =
        new boolean[MAX_MOUSE_CODE];

    private static ArrayList<Integer> downKeys = new ArrayList<>();
    private static ArrayList<Integer> upKeys = new ArrayList<>();

    private static ArrayList<Integer> downButtons = new ArrayList<>();
    private static ArrayList<Integer> upButtons = new ArrayList<>();

    private static DoubleBuffer xpos = BufferUtils.createDoubleBuffer(1);
    private static DoubleBuffer ypos = BufferUtils.createDoubleBuffer(1);

    public static void update() {
        long window = Window.getHandle();
        if (window == 0) return;
        glfwPollEvents();
        glfwGetCursorPos(window, xpos, ypos);
        System.arraycopy(
            keyStatesCurrentFrame,
            0,
            keyStatesPreviousFrame,
            0,
            MAX_KEY_CODE
        );
        System.arraycopy(
            mouseButtonsCurrentFrame,
            0,
            mouseButtonsPreviousFrame,
            0,
            MAX_MOUSE_CODE
        );
        upKeys.clear();
        downKeys.clear();
        for (int i = 0; i < MAX_KEY_CODE; i++) {
            boolean pressed =
                glfwGetKey(window, i) == GLFW_PRESS ||
                glfwGetKey(window, i) == GLFW_REPEAT;
            keyStatesCurrentFrame[i] = pressed;
            boolean wasPressed = keyStatesPreviousFrame[i];
            if (pressed && !wasPressed) {
                downKeys.add(i);
            } else if (!pressed && wasPressed) {
                upKeys.add(i);
            }
        }
        upButtons.clear();
        downButtons.clear();
        for (int i = 0; i < MAX_MOUSE_CODE; i++) {
            boolean pressed =
                glfwGetMouseButton(window, i) == GLFW_PRESS ||
                glfwGetMouseButton(window, i) == GLFW_REPEAT;
            mouseButtonsCurrentFrame[i] = pressed;
            boolean wasPressed = mouseButtonsPreviousFrame[i];
            if (pressed && !wasPressed) {
                downButtons.add(i);
            } else if (!pressed && wasPressed) {
                upButtons.add(i);
            }
        }
    }

    public static boolean getKey(int keyCode) {
        if (keyCode >= 0 && keyCode < MAX_KEY_CODE) {
            return keyStatesCurrentFrame[keyCode];
        }
        return false;
    }

    public static boolean getKeyDown(int keyCode) {
        return downKeys.contains(keyCode);
    }

    public static boolean getKeyUp(int keyCode) {
        return upKeys.contains(keyCode);
    }

    public static boolean getMouse(int mouseButton) {
        if (mouseButton >= 0 && mouseButton < MAX_KEY_CODE) {
            return keyStatesCurrentFrame[mouseButton];
        }
        return false;
    }

    public static boolean getMouseDown(int mouseButton) {
        return downButtons.contains(mouseButton);
    }

    public static boolean getMouseUp(int mouseButton) {
        return upButtons.contains(mouseButton);
    }

    public static Vector2f getMousePosition() {
        return new Vector2f(xpos.get(0), ypos.get(0));
    }
}
