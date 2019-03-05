package expenditures;

public abstract class Expenditures
{

    private String type;
    private String day;
    private String month;
    private String year;
    private int value;

    public Expenditures(String type, String day, String month, String year, int value)
    {
        this.type = type;
        this.day = day;
        this.month = month;
        this.year = year;
        this.value = value;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setDay(String day)
    {
        this.day = day;
    }

    public void setMonth(String month)
    {
        this.month = month;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public String getDay()
    {
        return day;
    }

    public String getMonth()
    {
        return month;
    }

    public String getYear()
    {
        return year;
    }

    public int getValue()
    {
        return value;
    }
}
