package trace.render;

import trace.geometry.Ray3;
import trace.geometry.Vec3;

/**
 * A 3D camera for rendering scenes.
 *
 * @author Kevin Lee
 */
public class Camera {

    private final int width;
    private final int height;
    private final Vec3 origin;
    private final Vec3 horizontal;
    private final Vec3 vertical;
    private final Vec3 lowerLeftCorner;

    public Camera(int width, int height) {
        double aspectRatio = (double) width / height;
        double viewportHeight = 2.0;
        double viewportWidth = aspectRatio * viewportHeight;
        double focalLength = 1.0;

        this.origin = new Vec3(0, 0, 0);
        this.horizontal = new Vec3(viewportWidth, 0, 0);
        this.vertical = new Vec3(0, viewportHeight, 0);

        Vec3 lowerLeftCorner = origin.sub(horizontal.mul(0.5));
        lowerLeftCorner = lowerLeftCorner.sub(vertical.mul(0.5));
        lowerLeftCorner = lowerLeftCorner.sub(new Vec3(0, 0, focalLength));
        this.lowerLeftCorner = lowerLeftCorner;

        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getAspectRatio() {
        return (double) width / height;
    }

    public Ray3 buildRay(int x, int y) {
        double u = (double) x / (width - 1);
        double v = (double) y / (height - 1);

        Vec3 direction = lowerLeftCorner.add(horizontal.mul(u));
        direction = direction.add(vertical.mul(v));
        direction = direction.sub(origin);

        return new Ray3(origin, direction);
    }

}
