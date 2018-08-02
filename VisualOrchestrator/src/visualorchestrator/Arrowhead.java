/*
Copyright (c) 2018 William H. Beebe, Jr.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package visualorchestrator;

import javafx.geometry.Point2D;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

/**
 * This is a highly specialized arrowhead class. It will produce a filled
 * arrowhead pointing to the right 90 degrees at the end of a line (curve).
 * 
 * @author William
 */
public class Arrowhead extends Polygon {
    public static final double[] ARROW_SHAPE_RIGHT = new double[] { 0,0,8,15,-8,15 };
    private final CubicCurve curve;
    private final Rotate rotateZ;

    /**
     * 
     * @param curve - The CubicCurve to which this arrowhead will be "attached"
     * @param shape - A Double array that defines the arrowhead.
     */
    public Arrowhead(CubicCurve curve, double... shape) {
        super(shape);
        this.curve = curve;
        setFill(curve.getStroke());
        rotateZ = new Rotate();
        rotateZ.setAxis(Rotate.Z_AXIS);
        getTransforms().addAll(rotateZ);
        update();
    }

    public Arrowhead(CubicCurve curve) {
        this(curve, ARROW_SHAPE_RIGHT);
    }

    /**
     * The value 2 added to getEndX() is to make sure that the pointed end
     * (the nose) of the arrow makes full contact with the side of the node it's
     * pointing to. Adding nothing leaves a peculiar visual 'gap' between the
     * end of the arrowhead and the side of the drawn node.
     * 
     * Essentially get the end of the line (curve), add 2 to get to the very
     * end, move (translate) the arrowhead to the end of the curve, and
     * rotate it 90 degrees to point to the right.
     */
    public final void update() {
        Point2D nose = new Point2D(curve.getEndX()+2, curve.getEndY());
        setTranslateX(nose.getX());
        setTranslateY(nose.getY());
        rotateZ.setAngle(90);
    }
}
