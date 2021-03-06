package animal.handler;

import java.awt.Color;
import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.util.StringTokenizer;
import java.util.Vector;

import translator.AnimalTranslator;
import animal.animator.Rotation;
import animal.animator.ScaleParams;
import animal.graphics.PTGraphicObject;
import animal.graphics.PTPolygon;
import animal.misc.MSMath;
import animal.misc.MessageDisplay;

/**
 * This class provides the operations that can be performed on polylines.
 */
public class PolygonHandler extends GraphicObjectHandler {
  public Vector<String> getMethods(PTGraphicObject ptgo, Object obj) {
    Vector<String> result = new Vector<String>();

    // only works if the passed object is a PTPolyline!
    if (!(ptgo instanceof PTPolygon))
      return result;
    if (obj instanceof Point) {
      result.addElement("translate");
      result.addElement("translateNodes...");
      result.addElement("translateWithFixedNodes...");
      int max = ((PTPolygon) ptgo).getNodeCount();
      for (int i = 0; i < max; i++) {
        result.addElement("translate #" + i);
      }
    }
    if (obj instanceof ScaleParams) {
      result.addElement("scale");
    }
		if (obj instanceof Rotation) {
			result.addElement("rotate");
		}
    if (obj instanceof Color) {
      result.addElement("color");
      result.addElement("fillColor");
      result.addElement("colors: color, fillColor");
    }
    if (obj instanceof Boolean) {
      result.addElement("show");
      result.addElement("hide");
    }

    if (obj instanceof String) {
      result.addElement("fill");
      result.addElement("unfill");
    }
    addExtensionMethodsFor(ptgo, obj, result);
    return result;
  }

  public void propertyChange(PTGraphicObject ptgo, PropertyChangeEvent e) {
    // only works if the passed object is a PTPolyline!
    PTPolygon shape = null;
    if (ptgo instanceof PTPolygon)
      shape = (PTPolygon) ptgo;
    String what = e.getPropertyName();
    if ("translate".equalsIgnoreCase(what)) {
      Point old = (Point) e.getOldValue(), now = (Point) e.getNewValue(), diff = MSMath
          .diff(now, old);
      shape.translate(diff.x, diff.y);
    } else if (what.startsWith("translate #")) {
      int num = Integer.parseInt(what.substring(11));
      Point old = (Point) e.getOldValue(), now = (Point) e.getNewValue(), diff = MSMath
          .diff(now, old);
      shape.translate(num - 1, diff.x, diff.y);
    } else if (what.startsWith("translateNodes ")
        || what.startsWith("translateWithFixedNodes ")) {
      boolean moveMode = (what.startsWith("translateNodes"));
      StringTokenizer stringTok = new StringTokenizer(what
          .substring((moveMode) ? 15 : 24));
      int nodeCount = shape.getNodeCount();
      boolean[] map = new boolean[nodeCount];
      if (!moveMode)
        for (int i = 0; i < nodeCount; i++)
          map[i] = true;
      int currentNode = 0;
      while (stringTok.hasMoreTokens()) {
        currentNode = Integer.parseInt(stringTok.nextToken());
        if (currentNode > 0 && currentNode <= nodeCount)
          map[currentNode - 1] = moveMode;
      }
      Point old = (Point) e.getOldValue(), now = (Point) e.getNewValue();
      Point diff = MSMath.diff(now, old);
      shape.translate(map, diff.x, diff.y);
		} else if (what.equalsIgnoreCase("rotate")) {
			Rotation r = (Rotation) e.getNewValue();
			shape.rotate(r.getAngle() - ((Rotation) e.getOldValue()).getAngle(), r
					.getCenter());
    } else if ("scale".equalsIgnoreCase(what)) {
      ScaleParams s = (ScaleParams) e.getNewValue();
      double xFactor = s.getXScaleFactor()
          / ((ScaleParams) e.getOldValue()).getXScaleFactor();
      double yFactor = s.getYScaleFactor()
          / ((ScaleParams) e.getOldValue()).getYScaleFactor();
      if (xFactor != yFactor)
        MessageDisplay.errorMsg("Must scale by common factor for shape!",
            MessageDisplay.RUN_ERROR);
      else {
        shape.translate(-s.getCenter().getX(), -s.getCenter().getY());
        shape.scale(xFactor, yFactor);
        shape.translate(s.getCenter().getX(), s.getCenter().getY());
      }
    } else if (what.equalsIgnoreCase("color"))
      shape.setColor((Color) e.getNewValue());
    else if (what.equalsIgnoreCase("fillColor"))
      shape.setFillColor((Color) e.getNewValue());
    else if (what.startsWith("colors:")) {
      StringTokenizer stringTokenizer = new StringTokenizer(what, ":, ");
      stringTokenizer.nextToken(); // skip "colors:" token!
      while (stringTokenizer.hasMoreTokens()) {
        String currentToken = stringTokenizer.nextToken();
        if (currentToken.equalsIgnoreCase("color"))
          shape.setColor((Color) e.getNewValue());
        else if (currentToken.equalsIgnoreCase("fillColor"))
          shape.setFillColor((Color) e.getNewValue());
        else
          MessageDisplay.message("unparsedHandlerToken",
              new String[] {
                  AnimalTranslator.translateMessage("polylineColor"),
                  currentToken });
      }
    } else if (what.equalsIgnoreCase("fill"))
      shape.setFilled(true);
    else if (what.equalsIgnoreCase("unfill"))
      shape.setFilled(false);
    else
      super.propertyChange(ptgo, e);
  }
}
