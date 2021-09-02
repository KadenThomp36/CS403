import java.util.Objects;

public class House extends Building {
    private String mOwner;
    private boolean mPool;

    public House(int length, int width, int lotLength, int lotWidth){
        super(length, width, lotLength, lotWidth);
        mOwner = null;
        mPool = false;
    }

    public House(int length, int width, int lotLength, int lotWidth, String owner){
        this(length, width, lotLength, lotWidth);
        mOwner = owner;
    }

    public House(int length, int width, int lotLength, int lotWidth, String owner, boolean pool){
        this(length, width, lotLength, lotWidth, owner);
        mPool = pool;
    }

    public String getOwner() {
        return mOwner;
    }

    public boolean hasPool() {
        return mPool;
    }

    public void setOwner(String mOwner) {
        this.mOwner = mOwner;
    }

    public void setPool(boolean mPool) {
        this.mPool = mPool;
    }

    @Override
    public String toString() {
        //create return string
        String returnString = "; Owner: ";
        if (mOwner == null)
            returnString += "n/a";
        else
            returnString += mOwner;

        if (mPool)
            returnString += "; has a pool";

        if (this.calcLotArea() > this.calcBuildingArea())
            returnString += "; has a big open space";

        return super.toString() + returnString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return mPool == house.mPool && this.calcBuildingArea() == house.calcBuildingArea();
    }

}
