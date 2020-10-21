import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class LikeMapMaker {
    public static HashMap<String, ArrayList<List<String>>> senderIDAndLikesReceived;

    public LikeMapMaker() {
        senderIDAndLikesReceived = new HashMap<>();
    }

    public void setMap(File f) {
        try {
            Scanner scan = new Scanner(f);
            ArrayList<String> favList = new ArrayList<String>();

            while(scan.hasNextLine())
            {
                String favoritedLine = scan.nextLine();
                if(favoritedLine.contains("\"favorited_by\""))
                {
                    if(!(favoritedLine.contains("[]")) && favoritedLine.contains("\"favorited_by\""))
                    {
                        String[] favoritedArray = favoritedLine.split("\"");
                        List<String> favoritedList = Arrays.asList(favoritedArray);
                        ArrayList<String> interAList = new ArrayList<String>(favoritedList);
                        favList = interAList;
                        favList.remove(0);
                        favList.remove(0);
                        favList.remove(0);
                        int size = favList.size();
                        int position = 0;
                        int traverse = 0;
                        while(traverse != size)
                        {
                            if(favList.get(position).length() < 5)
                            {
                                favList.remove(position);
                                traverse++;
                            }
                            else
                            {
                                position++;
                                traverse++;
                            }
                        }
                    }
                    else if(favoritedLine.contains("[]") && favoritedLine.contains("\"favorited_by\""))
                    {
                        ArrayList<String> interAList = new ArrayList<String>(0);
                        favList = interAList;
                    }
                }
                if(favoritedLine.contains("\"name\""))
                {
                    String line = scan.nextLine();
                    String[] parse = line.split("\"");
                    if(!(senderIDAndLikesReceived.containsKey(parse[parse.length - 1])))
                    {
                        if(favList.contains(parse[parse.length - 1]))
                        {
                            favList.remove(parse[parse.length - 1]);
                        }
                        senderIDAndLikesReceived.put(parse[parse.length - 1], new ArrayList<List<String>>());
                        senderIDAndLikesReceived.get(parse[parse.length - 1]).add(favList);

                    }
                    else if(senderIDAndLikesReceived.containsKey(parse[parse.length - 1]))
                    {
                        if(favList.contains(parse[parse.length - 1]))
                        {
                            favList.remove(parse[parse.length - 1]);
                        }
                        senderIDAndLikesReceived.get(parse[parse.length - 1]).add(favList);
                    }

                }
            }
            scan.close();
        }
        catch(IOException e) {
            System.out.println("Didn't work");
        }
    }
}
