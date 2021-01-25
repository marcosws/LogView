package logview.mdi.utils;

public class SizeWindow {
	
    private static double width;
    private static double height;

    public static double getWidth() {
        return width;
    }
    public static void setWidth(double width) {
    	SizeWindow.width = Math.abs(width - 18);
    }
    public static double getHeight() {
        return height;
    }
    public static void setHeight(double height) {
    	SizeWindow.height = Math.abs(height - 82);
    }

}
