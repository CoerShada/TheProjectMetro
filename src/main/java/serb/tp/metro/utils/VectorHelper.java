package serb.tp.metro.utils;

import java.lang.reflect.Field;
import net.minecraft.util.Vec3;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public abstract class VectorHelper {
  static final Vector3f X_AXIS = new Vector3f(1.0F, 0.0F, 0.0F);
  
  static final Vector3f Y_AXIS = new Vector3f(0.0F, 1.0F, 0.0F);
  
  static final Vector3f Z_AXIS = new Vector3f(0.0F, 0.0F, 1.0F);
  
  public static final float toDegrees = 57.29578F;
  
  public static final float toRadians = 0.017453292F;
  
  public static Vec3 add(Vec3 one, Vec3 other) {
    return one.addVector(other.xCoord, other.yCoord, other.zCoord);
  }
  
  public static double[] rotate(double x, double y, double radians) {
    double cos = Math.cos(radians);
    double sin = Math.sin(radians);
    double newX = x * cos - y * sin;
    double newY = y * cos + x * sin;
    return new double[] { newX, newY };
  }
  
  public static Vector3f createOrNull(Number x, Number y, Number z) {
    if (x == null || y == null || z == null)
      return null; 
    return new Vector3f(x.floatValue(), y.floatValue(), z.floatValue());
  }
  
  public static Vec3 getVecRotatedAround(Vec3 me, Vec3 orbit, float xr, float yr, float zr) {
    Vec3 temp = orbit.crossProduct(me);
    temp.rotateAroundX(xr);
    temp.rotateAroundY(yr);
    temp.rotateAroundZ(zr);
    return add(temp, orbit);
  }
  
  public static Vec3 rotateInReverse(Vec3 me, Vec3 orbit, float xr, float yr, float zr) {
    Vec3 temp = orbit.crossProduct(me);
    temp.rotateAroundZ(zr);
    temp.rotateAroundY(yr);
    temp.rotateAroundX(xr);
    return add(temp, orbit);
  }
  
  public static Vec3 rotateTest(Vec3 me, Vec3 orbit, float xr, float yr, float zr) {
    Vec3 temp = orbit.crossProduct(me);
    temp.rotateAroundZ(zr);
    temp.rotateAroundX(xr);
    temp.rotateAroundY(yr);
    return add(temp, orbit);
  }
  
  public static void print(Vec3 target) {
    System.out.println("Vector x = " + target.xCoord);
    System.out.println("Vector y = " + target.yCoord);
    System.out.println("Vector z = " + target.zCoord);
  }
  
  public static void print(Object target) {
    System.out.println(target);
  }
  
  public static void printAlternate(Matrix4f target) {
    Field[] fields = target.getClass().getFields();
    System.out.println("MATRIX DATA");
    System.out.println("~~~Standard Print~~~");
    print(target);
    System.out.println("~~~In-Depth Print~~~");
    for (int i = 0; i < fields.length; i++) {
      Field f = fields[i];
      String descript = f.getName() + " = ";
      try {
        descript = descript + f.getFloat(target);
      } catch (Exception e) {
        descript = descript + "ERROR";
      } 
      System.out.println(descript);
    } 
  }
  
  public static Matrix4f matrix4FromLocRot(float xl, float yl, float zl, float xr, float yr, float zr) {
    Vector3f loc = new Vector3f(xl, yl, zl);
    Matrix4f part1 = new Matrix4f();
    part1.translate(loc);
    part1.rotate(zr, Z_AXIS);
    part1.rotate(yr, Y_AXIS);
    part1.rotate(xr, X_AXIS);
    return part1;
  }
  
  public static Matrix4f matrix4FromFloatArray(float[] vals) {
    return matrix4FromLocRot(vals[0], vals[1], vals[2], vals[3], vals[4], vals[5]);
  }
  
  public static Matrix4f matrix4fFromFloat(float val) {
    return matrix4FromLocRot(val, val, val, val, val, val);
  }
  
  public static Vector4f mul(Vector4f target, float factor, Vector4f dest) {
    if (dest == null)
      dest = new Vector4f(); 
    target.x *= factor;
    target.y *= factor;
    target.z *= factor;
    target.w *= factor;
    return dest;
  }
  
  public static Matrix4f mul(Matrix4f target, float factor, Matrix4f dest) {
    if (dest == null)
      dest = new Matrix4f(); 
    target.m00 *= factor;
    target.m01 *= factor;
    target.m02 *= factor;
    dest.m02 = target.m03 * factor;
    target.m10 *= factor;
    target.m11 *= factor;
    target.m12 *= factor;
    target.m13 *= factor;
    target.m20 *= factor;
    target.m21 *= factor;
    target.m22 *= factor;
    target.m23 *= factor;
    target.m30 *= factor;
    target.m31 *= factor;
    target.m32 *= factor;
    target.m33 *= factor;
    return target;
  }
  
  public static float[] getLoc(Matrix4f target) {
    return new float[] { target.m30, target.m31, target.m32 };
  }
  
  public static Vector3f v3LocFromM4(Matrix4f target) {
    return new Vector3f(target.m30, target.m31, target.m32);
  }
  
  public static Vector3f getInverse(Vector3f target) {
    return new Vector3f(-target.x, -target.y, -target.z);
  }
  
  public static Vector3f vec3fFromStrings(String x, String y, String z) {
    float xl = Float.parseFloat(x);
    float yl = Float.parseFloat(y);
    float zl = Float.parseFloat(z);
    return new Vector3f(xl, yl, zl);
  }
  
  public static Vector4f copyVector4f(Vector4f src) {
    return new Vector4f(src.x, src.y, src.z, src.w);
  }
}
