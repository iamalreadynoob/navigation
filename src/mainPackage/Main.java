package mainPackage;
import locationThings.RoadResearcher;
import locationThings.BuildingResearcher;
import locationThings.LocationFixer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        String docPaths = "src/dataset";
        String buildingFile = "buildings.csv";
        String roadsFile = "roads.csv";

        Path docPath = Paths.get(docPaths);
        Path buildingPath = docPath.resolve(buildingFile);
        Path roadsPath = docPath.resolve(roadsFile);

        BuildingResearcher buildingResearcher = new BuildingResearcher(buildingPath);
        RoadResearcher roadResearcher = new RoadResearcher(roadsPath);

        int posX, posY, destX, destY;

        System.out.print("What is your location on x coordinate? ");
        posX = new Scanner(System.in).nextInt();

        System.out.print("What is your location on y coordinate? ");
        posY = new Scanner(System.in).nextInt();

        System.out.print("Where are you off to on x coordinate? ");
        destX = new Scanner(System.in).nextInt();

        System.out.print("Where are you off to on Y coordinate? ");
        destY = new Scanner(System.in).nextInt();

        LocationFixer initializePoint = new LocationFixer(posX, posY, buildingResearcher, roadResearcher);

    }

}
