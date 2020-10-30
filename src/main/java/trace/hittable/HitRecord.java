package trace.hittable;

import trace.geometry.Ray3;
import trace.geometry.Vec3;
import trace.material.Material;

/**
 * A class used for storing information about ray-intersections.
 *
 * @author Kevin Lee
 */
public class HitRecord {

    private double t;
    private Vec3 point;
    private Vec3 normal;
    private boolean frontFace;
    private Material material;

    public double getT() {
        return t;
    }

    public Vec3 getPoint() {
        return point;
    }

    public Vec3 getNormal() {
        return normal;
    }

    public Material getMaterial() {
        return material;
    }

    public boolean isFrontFace() {
        return frontFace;
    }

    public void record(
            Ray3 ray, double t, Vec3 point, Vec3 normal, Material material) {

        this.t = t;
        this.point = point;
        this.material = material;
        this.frontFace = ray.getDirection().dot(normal) < 0.0;
        this.normal = (frontFace) ? normal : normal.neg();
    }

}
