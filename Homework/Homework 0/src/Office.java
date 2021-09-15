import java.util.Objects;

public class Office extends Building{
    private String mBusinessName;
    private int mParkingSpaces;
    static private int sTotalOffices = 0;

    public Office(int length, int width, int lotLength, int lotWidth){
        super(length, width, lotLength, lotWidth);
        sTotalOffices++;
    }

    public Office(int length, int width, int lotLength, int lotWidth, String businessName){
        this(length, width, lotLength, lotWidth);
        mBusinessName = businessName;
    }

    public Office(int length, int width, int lotLength, int lotWidth, String businessName, int parkingSpaces){
        this(length, width, lotLength, lotWidth, businessName);
        mParkingSpaces = parkingSpaces;
    }

    public String getBusinessName() {
        return mBusinessName;
    }

    public int getParkingSpaces() {
        return mParkingSpaces;
    }

    public static int getTotalOffices() {
        return sTotalOffices;
    }

    public void setParkingSpaces(int mParkingSpaces) {
        this.mParkingSpaces = mParkingSpaces;
    }

    public void setBusinessName(String mBusinessName) {
        this.mBusinessName = mBusinessName;
    }

    @Override
    public String toString() {
        String returnString = "; Business: ";
        if (mBusinessName == null)
            returnString += "unoccupied";
        else
            returnString += mBusinessName;

        if (mParkingSpaces > 0){
            returnString += ";has " + mParkingSpaces + " parking spaces";
        }

        returnString += " (total offices: " + sTotalOffices +")";

        return super.toString() + returnString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Office office = (Office) o;
        return mParkingSpaces == office.mParkingSpaces && this.calcBuildingArea() == office.calcBuildingArea();
    }
}
