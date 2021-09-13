package ru.stqa.pft.soap.tests;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

@Test
  public void testMyIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("178.70.147.31");
    Assert.assertTrue(ipLocation.contains("<Country>RU</Country>"));
  }
}
