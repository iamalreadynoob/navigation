package locationThings;

public class Location
{

    private int xPos, yPos;
    Integer xFixedPos = null;
    Integer yFixedPos = null;

    public Location(int xPos, int yPos)
    {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public boolean isFixed()
    {
        if (xFixedPos != null && yFixedPos != null) return true;
        else return false;
    }

    public int getXPos() {return xPos;}
    public int getYPos() {return yPos;}
    public Integer getXFixedPos() {return xFixedPos;}
    public Integer getYFixedPos() {return yFixedPos;}

}
