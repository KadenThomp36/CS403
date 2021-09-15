public class Cottage extends House{
    private boolean mSecondFloor;

    public Cottage(int dimension, int lotLength, int lotWidth){
        super(dimension, dimension, lotLength, lotWidth);
    }

    public Cottage(int dimension, int lotLength, int lotWidth, String owner, boolean secondFloor){
        this(dimension, lotLength, lotWidth);
        this.setOwner(owner);
        mSecondFloor = secondFloor;
    }

    public boolean hasSecondFloor() {
        return mSecondFloor;
    }

    @Override
    public String toString() {
        String returnString = ";is a cottage";
        if (mSecondFloor){
            returnString += ";is a two story cottage";
        }
        return super.toString() + returnString;
    }
}
