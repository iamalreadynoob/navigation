package locationThings;

import dataset.Lists;

public class BuildingDetector implements IDetection
{
    private BuildingResearcher researcher;
    private int requestedX, requestedY, corner;
    private Integer fixedX = null;
    private Integer fixedY = null;
    private boolean isHere = false;
    private String address = null;

    protected BuildingDetector(BuildingResearcher researcher, int requestedX, int requestedY)
    {
        this.researcher = researcher;
        this.requestedX = requestedX;
        this.requestedY = requestedY;
    }

    @Override
    public void detect()
    {

        for (int i = 0; i < researcher.getData(Lists.CORNER1X).size(); i++)
        {

            if (researcher.getData(Lists.CORNER4X).get(i) == 0 && researcher.getData(Lists.CORNER4Y).get(i) == 0)
            {
                double slope = slope(i);
                double constant;

                switch (corner)
                {
                    case 1:
                        constant = researcher.getData(Lists.CORNER2Y).get(i) - (slope * researcher.getData(Lists.CORNER2X).get(i));

                        if (researcher.getData(Lists.CORNER1Y).get(i) == researcher.getData(Lists.CORNER2Y).get(i))
                        {
                            triangleDetector(Lists.CORNER1X, Lists.CORNER1Y, Lists.CORNER2X, i, slope, constant);
                        }
                        else triangleDetector(Lists.CORNER1X, Lists.CORNER1Y, Lists.CORNER3X, i, slope, constant);

                        break;

                    case 2:
                        constant = researcher.getData(Lists.CORNER3Y).get(i) - (slope * researcher.getData(Lists.CORNER3X).get(i));

                        if (researcher.getData(Lists.CORNER2Y).get(i) == researcher.getData(Lists.CORNER1Y).get(i))
                        {
                            triangleDetector(Lists.CORNER2X, Lists.CORNER2Y, Lists.CORNER1X, i, slope, constant);
                        }
                        else triangleDetector(Lists.CORNER2X, Lists.CORNER2Y, Lists.CORNER3X, i, slope, constant);

                        break;

                    case 3:
                        constant = researcher.getData(Lists.CORNER1Y).get(i) - (slope * researcher.getData(Lists.CORNER1X).get(i));

                        if (researcher.getData(Lists.CORNER3Y).get(i) == researcher.getData(Lists.CORNER1Y).get(i))
                        {
                            triangleDetector(Lists.CORNER3X, Lists.CORNER3Y, Lists.CORNER1X, i, slope, constant);
                        }
                        else triangleDetector(Lists.CORNER3X, Lists.CORNER3Y, Lists.CORNER2X, i, slope, constant);

                        break;
                }

            }

            else
            {
                if (requestedX > researcher.getData(Lists.CORNER1X).get(i) && requestedX < researcher.getData(Lists.CORNER2X).get(i) && requestedY < researcher.getData(Lists.CORNER1Y).get(i) && requestedY > researcher.getData(Lists.CORNER3Y).get(i))
                {
                    success(i);
                }
            }

            if (isHere == true) break;

        }

    }

    @Override
    public boolean isHere()
    {
        return isHere;
    }

    @Override
    public String getAddress()
    {
        return address;
    }

    @Override
    public Integer getFixedX()
    {
        return fixedX;
    }

    @Override
    public Integer getFixedY()
    {
        return fixedY;
    }

    private double slope(int index)
    {
        double slope = 0;

        if ((researcher.getData(Lists.CORNER1X).get(index) == researcher.getData(Lists.CORNER2X).get(index) && researcher.getData(Lists.CORNER1Y).get(index) == researcher.getData(Lists.CORNER3Y).get(index)) ||
                (researcher.getData(Lists.CORNER1X).get(index) == researcher.getData(Lists.CORNER3X).get(index) && researcher.getData(Lists.CORNER1Y).get(index) == researcher.getData(Lists.CORNER2Y).get(index)))
        {
            slope = (researcher.getData(Lists.CORNER2Y).get(index) - researcher.getData(Lists.CORNER3Y).get(index)) / (researcher.getData(Lists.CORNER2X).get(index) - researcher.getData(Lists.CORNER3X).get(index));
            corner = 1;
        }

        else if ((researcher.getData(Lists.CORNER2X).get(index) == researcher.getData(Lists.CORNER1X).get(index) && researcher.getData(Lists.CORNER2Y).get(index) == researcher.getData(Lists.CORNER3Y).get(index)) ||
                (researcher.getData(Lists.CORNER2X).get(index) == researcher.getData(Lists.CORNER3X).get(index) && researcher.getData(Lists.CORNER2Y).get(index) == researcher.getData(Lists.CORNER1Y).get(index)))
        {
            slope = (researcher.getData(Lists.CORNER1Y).get(index) - researcher.getData(Lists.CORNER3Y).get(index)) / (researcher.getData(Lists.CORNER1X).get(index) - researcher.getData(Lists.CORNER3X).get(index));
            corner = 2;
        }

        else if ((researcher.getData(Lists.CORNER3X).get(index) == researcher.getData(Lists.CORNER2X).get(index) && researcher.getData(Lists.CORNER3Y).get(index) == researcher.getData(Lists.CORNER1Y).get(index)) ||
                (researcher.getData(Lists.CORNER3X).get(index) == researcher.getData(Lists.CORNER1X).get(index) && researcher.getData(Lists.CORNER3Y).get(index) == researcher.getData(Lists.CORNER2Y).get(index)))
        {
            slope = (researcher.getData(Lists.CORNER2Y).get(index) - researcher.getData(Lists.CORNER1Y).get(index)) / (researcher.getData(Lists.CORNER2X).get(index) - researcher.getData(Lists.CORNER1X).get(index));
            corner = 3;
        }

        return slope;
    }

    private void success(int index)
    {
        isHere = true;
        address = researcher.getName().get(index);
        fixedX = researcher.getData(Lists.ENTRANCEX).get(index);
        fixedY = researcher.getData(Lists.ENTRANCEY).get(index);
    }

    private void triangleDetector(Lists cornerX, Lists cornerY, Lists horizontalX, int index, double slope, double constant)
    {
        if (researcher.getData(cornerX).get(index) < researcher.getData(horizontalX).get(index)
                && requestedX > researcher.getData(cornerX).get(index)
                && requestedX < researcher.getData(horizontalX).get(index))
        {
            double ceil = (slope * requestedX) + constant;

            if (researcher.getData(cornerY).get(index) < ceil
                    && requestedY > researcher.getData(cornerY).get(index)
                    && requestedY < ceil) success(index);

            else if (ceil < researcher.getData(cornerY).get(index)
                    && requestedY > ceil
                    && requestedY < ceil) success(index);

        }

        else if (researcher.getData(horizontalX).get(index) < researcher.getData(cornerX).get(index)
                && requestedX > researcher.getData(horizontalX).get(index)
                && requestedX < researcher.getData(cornerX).get(index))
        {
            double ceil = (slope * requestedX) + constant;

            if (researcher.getData(cornerY).get(index) < ceil
                    && requestedY > researcher.getData(cornerY).get(index)
                    && requestedY < ceil) success(index);

            else if (ceil < researcher.getData(cornerY).get(index)
                    && requestedY > ceil
                    && requestedY < ceil) success(index);

        }
    }

}
