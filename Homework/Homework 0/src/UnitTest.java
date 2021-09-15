

import org.junit.Test;

import static org.junit.Assert.*;


public class UnitTest {
    private static final long TIMEOUT = 100;


    @Test(timeout = TIMEOUT)
    public void testBuildingArea() {
        Building office = new Building(200, 400, 600, 560);
        assertEquals(80000, office.calcBuildingArea());
    }


    @Test(timeout = TIMEOUT)
    public void testBuildingLotArea() {
        Building office = new Building(200, 400, 600, 560);
        assertEquals(336000, office.calcLotArea());
    }


    @Test(timeout = TIMEOUT)
    public void testBuildingSetAndGet() {
        //int length, int width, int lotLength, int lotWidth
        Building b1 = new Building(10, 20, 30, 40);
        assertEquals(10, b1.getLength());
        assertEquals(20, b1.getWidth());
        assertEquals(30, b1.getLotLength());
        assertEquals(40, b1.getLotWidth());
        b1.setLength(1);
        b1.setWidth(2);
        b1.setLotLength(3);
        b1.setLotWidth(4);
        assertEquals(1, b1.getLength());
        assertEquals(2, b1.getWidth());
        assertEquals(3, b1.getLotLength());
        assertEquals(4, b1.getLotWidth());
    }

    // OFFICE TESTS


    @Test
    public void testOfficeString() throws Exception {
        int i = Office.getTotalOffices() + 1;
        int count = i + 10;

        for (; i <= count; i++) {
            new Office(20, 40, 56, 60);
        }

        String expected = "A 1X1 Building; Business: unoccupied (total offices: " + (count + 1) + ")";
        assertEquals(expected, (new Office(1, 1, 1, 1)).toString().trim());
    }


    @Test(timeout = TIMEOUT)
    public void testOfficeArea() {
        Office office = new Office(200, 400, 600, 560, "Bridgestone/Firestone", 100);
        assertEquals(80000, office.calcBuildingArea());
    }


    @Test(timeout = TIMEOUT)
    public void testOfficeLotArea() {
        Office office = new Office(200, 400, 600, 560, "Bridgestone/Firestone", 100);
        assertEquals(336000, office.calcLotArea());
    }


    @Test(timeout = TIMEOUT)
    public void testOfficeConstructor() {
        Office emptyOffice = new Office(20, 40, 56, 60);

        assertEquals(0, emptyOffice.getParkingSpaces());
    }


    @Test(timeout = TIMEOUT)
    public void testSetSpaces() {
        Office caterpillar = new Office(200, 400, 560, 600, "Caterpillar");
        caterpillar.setParkingSpaces(100);
        assertEquals(100, caterpillar.getParkingSpaces());
    }

    @Test(timeout = TIMEOUT)
    public void testOfficeEquals() {
        Office o1 = new Office(200, 400, 560, 600, "o1");
        Office o2 = new Office(200, 400, 600, 560, "o2");
        assertEquals(o1, o2);
    }

    // HOUSE TESTS


    @Test(timeout = TIMEOUT)
    public void testHouseOwner() {
        House washington = new House(20, 40, 56, 60);
        washington.setOwner("George Washington");
        assertEquals("George Washington", washington.getOwner());
    }

    @Test(timeout = TIMEOUT)
    public void testHousePool() {
        House home = new House(20, 40, 60, 80);
        assertFalse(home.hasPool());
        home.setPool(true);
        assertTrue(home.hasPool());
        home.setPool(false);
        assertFalse(home.hasPool());
    }


    @Test(timeout = TIMEOUT)
    public void testHouseAreas() {
        House adams = new House(10, 12, 100, 200, "John Adams");
        assertEquals(120, adams.calcBuildingArea());
        assertEquals(20000, adams.calcLotArea());
    }


    @Test(timeout = TIMEOUT)
    public void testHouseToString() {
        House adams = new House(10, 12, 100, 200, "John Adams");
        String str = adams.toString().trim();
        String expected = "A 10X12 Building; Owner: John Adams; has a big open space";
        assertEquals(expected, str);
    }


    @Test(timeout = TIMEOUT)
    public void testHouseEquals() {
        House jefferson = new House(20, 40, 60, 56, "Thomas Jefferson", true);
        House madison = new House(20, 40, 56, 60, "James Madison", true);
        House h3 = new House(1, 1, 1, 1);
        assertEquals(jefferson, madison);
        assertNotEquals(h3, jefferson);
    }

    // COTTAGE TESTS


    @Test(timeout = TIMEOUT)
    public void testCottageSecondFloor() {
        Cottage c = new Cottage(1, 2, 3, "name", true);
        assertTrue(c.hasSecondFloor());
    }


    @Test(timeout = TIMEOUT)
    public void testCottageSingleFloor() {
        Cottage c = new Cottage(1, 2, 3, "name", false);
        assertFalse(c.hasSecondFloor());
    }

    // NEIGHBORHOOD TESTS


    @Test(timeout = TIMEOUT)
    public void testNeighborhoodArea() {

        House[] house = new House[2];
        Office[] office = new Office[3];
        for (int i = 0; i < 2; ++i) {
            house[i] = new House(i + 1, i + 1, i + 1, i + 1);
        }
        for (int i = 0; i < 3; ++i) {
            office[i] = new Office(i + 1, i + 1, i + 1, i + 1);
        }

        assertEquals(5, Neighborhood.calcArea(house));
        assertEquals(14, Neighborhood.calcArea(office));
    }

    @Test(timeout = TIMEOUT)
    public void testNeighborhoodGetInfo() {

        House[] house = new House[2];
        Office[] office = new Office[3];
        for (int i = 0; i < 2; ++i) {
            house[i] = new House(i + 1, i + 1, i + 1, i + 1,null,(i%2 == 0));
        }
        for (int i = 0; i < 3; ++i) {
            office[i] = new Office(i + 1, i + 1, i + 1, i + 1);
        }

        Building buildings[] = new Building[5];
        buildings[0] = house[0];
        buildings[1] = house[1];
        buildings[2] = office[0];
        buildings[3] = office[1];
        buildings[4] = office[2];
        /*
        Note sTotalOffices > 3 since the counter is static and remains in memory for all unit tests.
        */

        String expected[] = {
                "A 1X1 Building; Owner: n/a; has a pool",
                "A 2X2 Building; Owner: n/a",
                "A 1X1 Building; Business: unoccupied (total offices: 21)",
                "A 2X2 Building; Business: unoccupied (total offices: 21)",
                "A 3X3 Building; Business: unoccupied (total offices: 21)"
        };


        assertArrayEquals(expected, Neighborhood.getInfo(buildings));

    }



}
