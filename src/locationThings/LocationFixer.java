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
        RoadDetector roadDetector;
        buildingDetector.detect();

        if (buildingDetector.isHere()) roadDetector = new RoadDetector(roader, buildingDetector.getFixedX(), buildingDetector.getFixedY());
        else roadDetector = new RoadDetector(roader, xPos, yPos);

        fixedX = roadDetector.getFixedX();
        fixedY = roadDetector.getFixedY();

    }

    public Integer getFixedX() {return fixedX;}
    public Integer getFixedY() {return fixedY;}

}
