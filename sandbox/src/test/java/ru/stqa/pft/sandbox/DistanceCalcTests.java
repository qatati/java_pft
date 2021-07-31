package ru.stqa.pft.sandbox;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceCalcTests {

  @Test
  public void testDistancePositivePoint() {
    Point p1 = new Point(1,9);
    Point p2 = new Point(10,5);
    Assert.assertEquals(p1.distance(p2), 9.848857801796104);
  }

  @Test
  public void testDistanceNegativePoint() {
    Point p1 = new Point(-10,-1);
    Point p2 = new Point(-6,-11);
    Assert.assertEquals(p1.distance(p2), 10.770329614269007);
  }

  @Test
  public void testDistanceMixPoint() {
    Point p1 = new Point(-10,12);
    Point p2 = new Point(6,0);
    Assert.assertEquals(p1.distance(p2), 20.0);
  }

  @Test
  public void testDistanceNullPoint() {
    Point p1 = new Point(0,0);
    Point p2 = new Point(0,0);
    Assert.assertEquals(p1.distance(p2), 0.0);
  }
}