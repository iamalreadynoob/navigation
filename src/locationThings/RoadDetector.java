package locationThings;

import dataset.Lists;

public class RoadDetector implements IDetection
{
    private RoadResearcher roader;
    private int requestedX, requestedY;
    private Integer fixedX = null;
    private Integer fixedY = null;
    private boolean isHere = false;
    private boolean isCrossed = false;
    private String address = null;

    public RoadDetector(RoadResearcher roader, int requestedX, int requestedY)
    {
        this.roader = roader;
        this.requestedX = requestedX;
        this.requestedY = requestedY;
    }

    @Override
    public void detect()
    {
        for(int i = 0; i < roader.getData(Lists.NAME).size(); i++)
        {
            int borders[] = getBorders(i);
            //borders: smallX, bigX, smallY, bigY

            if (requestedX >= borders[0] && requestedX <= borders[1]
            && requestedY >= borders[2] && requestedY <= borders[3])
            {
                address = roader.getName().get(i);
                isHere = true;

                if (isVertical(i))
                {
                    //fixed x fixed y
                }
                else
                {

                }

            }

            //blind point detection

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

    private int[] crossed(int passId, boolean isVertical)
    {
        int[] diagonal = new int[2];

        for (int i = 0; i < roader.getData(Lists.NAME).size(); i++)
        {
            if (i != passId)
            {
                if (roader.getData(Lists.XINITIALIZE).get(passId) == roader.getData(Lists.XINITIALIZE).get(i)
                && roader.getData(Lists.YINITIALIZE).get(passId) == roader.getData(Lists.YINITIALIZE).get(i)
                && isVertical)
                {
                    //x-axis
                    if (roader.getData(Lists.XINITIALIZE).get(passId) > roader.getData(Lists.XFINAL).get(i))
                    {
                        diagonal[0] = roader.getData(Lists.XINITIALIZE).get(passId) + roader.getData(Lists.R).get(passId);
                    }
                    else
                    {
                        diagonal[0] = roader.getData(Lists.XINITIALIZE).get(passId) - roader.getData(Lists.R).get(passId);
                    }

                    //y-axis
                    if (roader.getData(Lists.YINITIALIZE).get(passId) > roader.getData(Lists.YFINAL).get(passId))
                    {
                        diagonal[1] = roader.getData(Lists.YINITIALIZE).get(passId) + roader.getData(Lists.R).get(i);
                    }
                    else
                    {
                        diagonal[1] = roader.getData(Lists.YINITIALIZE).get(passId) - roader.getData(Lists.R).get(i);
                    }
                }

                else if (roader.getData(Lists.XINITIALIZE).get(passId) == roader.getData(Lists.XFINAL).get(i)
                && roader.getData(Lists.YINITIALIZE).get(passId) == roader.getData(Lists.YFINAL).get(i)
                && isVertical)
                {
                    //x-axis
                    if (roader.getData(Lists.XINITIALIZE).get(passId) > roader.getData(Lists.XINITIALIZE).get(i))
                    {
                        diagonal[0] = roader.getData(Lists.XINITIALIZE).get(passId) + roader.getData(Lists.R).get(passId);
                    }
                    else
                    {
                        diagonal[0] = roader.getData(Lists.XINITIALIZE).get(passId) - roader.getData(Lists.R).get(passId);
                    }

                    //y-axis
                    if (roader.getData(Lists.YINITIALIZE).get(passId) > roader.getData(Lists.YFINAL).get(passId))
                    {
                        diagonal[1] = roader.getData(Lists.YINITIALIZE).get(passId) + roader.getData(Lists.R).get(i);
                    }
                    else
                    {
                        diagonal[1] = roader.getData(Lists.YINITIALIZE).get(passId) - roader.getData(Lists.R).get(i);
                    }
                }

                else if (roader.getData(Lists.XFINAL).get(passId) == roader.getData(Lists.XINITIALIZE).get(i)
                && roader.getData(Lists.YFINAL).get(passId) == roader.getData(Lists.YINITIALIZE).get(i)
                && isVertical)
                {
                    //x-axis
                    if (roader.getData(Lists.XFINAL).get(passId) > roader.getData(Lists.XFINAL).get(i))
                    {
                        diagonal[0] = roader.getData(Lists.XFINAL).get(passId) + roader.getData(Lists.R).get(passId);
                    }
                    else
                    {
                        diagonal[0] = roader.getData(Lists.XFINAL).get(passId) - roader.getData(Lists.R).get(passId);
                    }

                    //y-axis
                    if (roader.getData(Lists.YFINAL).get(passId) > roader.getData(Lists.YINITIALIZE).get(passId))
                    {
                        diagonal[1] = roader.getData(Lists.YFINAL).get(passId) + roader.getData(Lists.R).get(i);
                    }
                    else
                    {
                        diagonal[1] = roader.getData(Lists.YFINAL).get(passId) - roader.getData(Lists.R).get(i);
                    }
                }

                else if (roader.getData(Lists.XFINAL).get(passId) == roader.getData(Lists.XFINAL).get(i)
                && roader.getData(Lists.YFINAL).get(passId) == roader.getData(Lists.YFINAL).get(i)
                && isVertical)
                {
                    //x-axis
                    if (roader.getData(Lists.XFINAL).get(passId) > roader.getData(Lists.XINITIALIZE).get(i))
                    {
                        diagonal[0] = roader.getData(Lists.XFINAL).get(passId) + roader.getData(Lists.R).get(passId);
                    }
                    else
                    {
                        diagonal[0] = roader.getData(Lists.XFINAL).get(passId) - roader.getData(Lists.R).get(passId);
                    }

                    //y-axis
                    if (roader.getData(Lists.YFINAL).get(passId) > roader.getData(Lists.YINITIALIZE).get(passId))
                    {
                        diagonal[1] = roader.getData(Lists.YFINAL).get(passId) + roader.getData(Lists.R).get(i);
                    }
                    else
                    {
                        diagonal[1] = roader.getData(Lists.YFINAL).get(passId) - roader.getData(Lists.R).get(i);
                    }
                }
            }
        }

        return diagonal;
    }

    private boolean isVertical(int index)
    {
        if (roader.getData(Lists.XINITIALIZE).get(index) == roader.getData(Lists.XFINAL).get(index)) return true;
        else return false;
    }

    private int[] getBorders(int index)
    {
        int[] borders = new int[4];
        //borders[]: smallX, bigX, smallY, bigY
        if (isVertical(index))
        {
            borders[0] = roader.getData(Lists.XINITIALIZE).get(index) - roader.getData(Lists.R).get(index);
            borders[1] = roader.getData(Lists.XINITIALIZE).get(index) + roader.getData(Lists.R).get(index);

            if (roader.getData(Lists.YINITIALIZE).get(index) > roader.getData(Lists.YFINAL).get(index))
            {
                borders[2] = roader.getData(Lists.YFINAL).get(index);
                borders[3] = roader.getData(Lists.YINITIALIZE).get(index);
            }
            else
            {
                borders[2] = roader.getData(Lists.YINITIALIZE).get(index);
                borders[3] = roader.getData(Lists.YFINAL).get(index);
            }

        }

        else
        {
            if (roader.getData(Lists.XINITIALIZE).get(index) > roader.getData(Lists.XFINAL).get(index))
            {
                borders[0] = roader.getData(Lists.XFINAL).get(index);
                borders[1] = roader.getData(Lists.XINITIALIZE).get(index);
            }
            else
            {
                borders[0] = roader.getData(Lists.XINITIALIZE).get(index);
                borders[1] = roader.getData(Lists.XFINAL).get(index);
            }

            borders[3] = roader.getData(Lists.YINITIALIZE).get(index) - roader.getData(Lists.R).get(index);
            borders[4] = roader.getData(Lists.YINITIALIZE).get(index) + roader.getData(Lists.R).get(index);
        }

        return borders;
    }
}
