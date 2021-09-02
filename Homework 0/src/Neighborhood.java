public class Neighborhood {
    static String [] getInfo(Building buildings[]) {
        String[] returnString = new String[buildings.length];
        for (int i = 0; i < buildings.length; i++){
            returnString[i] = buildings[i].toString();
        }

        return returnString;
    }

    static int calcArea(Building buildings[]) {
        int totalLotArea = 0;
        for (int i = 0; i < buildings.length; i++){
            totalLotArea += buildings[i].calcLotArea();
        }

        return totalLotArea;
    }
}
