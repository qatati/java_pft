package ru.stqa.pft.sandbox;

import static ru.stqa.pft.sandbox.Point.distance1;

public class DistanceCalc {

  public static void main(String[] args){
    Point point1 = new Point(2,4);
    Point point2 = new Point(2,4);

    System.out.println("Расстояние между двумя точками " + point1.x + " и " + point1.y + " = " + distance1(point1));
    System.out.println("Расстояние между двумя точками " + point2.x + " и " + point2.y + " = " + point2.distance2());
  }
}