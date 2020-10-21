public class Person {
    private String name;
    private int numberOfLikesReceived;
    private int LRRank;
    private int numberOfLikesGiven;
    private int LGRank;
    private double fractionOfLRPerLG;
    private int LRPerLGRank;
    private double fractionOfLRPerMS;
    private int LRPerMSRank;

    public Person(String name)
    {
        this.name = name;
        numberOfLikesReceived = 0;
        LRRank = 0;
        numberOfLikesGiven = 0;
        LGRank = 0;
        fractionOfLRPerLG = 0.0;
        LRPerLGRank = 0;
        fractionOfLRPerMS = 0.0;
        LRPerMSRank = 0;
    }

    public Person(String name, int a, int b, int c, int d, int e, int f, int g, int h)
    {
        this.name = name;
        numberOfLikesReceived = a;
        LRRank = b;
        numberOfLikesGiven = c;
        LGRank = d;
        fractionOfLRPerLG = e;
        LRPerLGRank = f;
        fractionOfLRPerMS = g;
        LRPerMSRank = h;
    }

    public double getFractionOfLRPerLG() {
        return fractionOfLRPerLG;
    }

    public double getFractionOfLRPerMS() {
        return fractionOfLRPerMS;
    }

    public int getLGRank() {
        return LGRank;
    }

    public int getLRPerLGRank() {
        return LRPerLGRank;
    }

    public int getLRPerMSRank() {
        return LRPerMSRank;
    }

    public int getLRRank() {
        return LRRank;
    }

    public int getNumberOfLikesGiven() {
        return numberOfLikesGiven;
    }

    public int getNumberOfLikesReceived() {
        return numberOfLikesReceived;
    }
    
    public String getName() {
        return name;
    }

    public void setFractionOfLRPerLG(double fractionOfLRPerLG) {
        this.fractionOfLRPerLG = fractionOfLRPerLG;
    }

    public void setFractionOfLRPerMS(double fractionOfLRPerMS) {
        this.fractionOfLRPerMS = fractionOfLRPerMS;
    }

    public void setLGRank(int LGRank) {
        this.LGRank = LGRank;
    }

    public void setLRPerLGRank(int LRPerLGRank) {
        this.LRPerLGRank = LRPerLGRank;
    }

    public void setLRPerMSRank(int LRPerMSRank) {
        this.LRPerMSRank = LRPerMSRank;
    }

    public void setLRRank(int LRRank) {
        this.LRRank = LRRank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfLikesGiven(int numberOfLikesGiven) {
        this.numberOfLikesGiven = numberOfLikesGiven;
    }

    public void setNumberOfLikesReceived(int numberOfLikesReceived) {
        this.numberOfLikesReceived = numberOfLikesReceived;
    }

    public void incrementNumberOfLikesGiven()
    {
        numberOfLikesGiven++;
    }
}
