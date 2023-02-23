package locationThings;

public class LocationFixer
{

    private int xPos, yPos;
    private Integer fixedX = null;
    private Integer fixedY = null;

    public LocationFixer(int xPos, int yPos, BuildingResearcher builder, RoadResearcher roader)
    {
        this.xPos = xPos;
        this.yPos = yPos;

        BuildingDetector buildingDetector = new BuildingDetector(builder, xPos, yPos);
        buildingDetector.detect();

        if (buildingDetector.isHere())
        {

        }
        else
        {

        }

    }

}
