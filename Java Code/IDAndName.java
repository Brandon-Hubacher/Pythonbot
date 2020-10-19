import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class IDAndName {
    //LikeMapMaker LMM = new LikeMapMaker();
    public HashMap<String, Person> personMap;

    public IDAndName()
    {
        personMap = new HashMap<>();
    }

    public void setPersonMap(File convoFile)
    {
        try {
            //File file = new File("C:\\Users\\brandon\\Desktop\\gmdata\\61672113\\conversation.json");
            Scanner scan = new Scanner(convoFile);


            while(scan.hasNextLine())
            {
                String data = scan.nextLine();
                String[] lineArray = data.split("\\{\"user_id\":");
                for(int i = 1; i < lineArray.length; i++)
                {
                    String[] variableArray = lineArray[i].split("\"");
                    //System.out.println(variableArray[5]);
                    if(!(personMap.containsKey(variableArray[1])))
                    {
                        personMap.put(variableArray[1], new Person(variableArray[5]));
                    }
                }

                for(String str : LikeMapMaker.senderIDAndLikesReceived.keySet())
                {
                    int sum = 0;
                    for(List<String> innerList : LikeMapMaker.senderIDAndLikesReceived.get(str))
                    {
                        sum += innerList.size();
                    }

                    if(personMap.get(str) == null)
                    {
                        continue;
                    }
                    else
                    {
                        personMap.get(str).setNumberOfLikesReceived(sum);
                    }
                    sum = 0;

                }

                for(String str : LikeMapMaker.senderIDAndLikesReceived.keySet())
                {
                    for(List<String> innerList : LikeMapMaker.senderIDAndLikesReceived.get((str)))
                    {
                        for(String idnum : innerList)
                        {
                            personMap.get(idnum).incrementNumberOfLikesGiven();
                        }
                    }
                }

                int[] rank = new int[personMap.size()];
                int tracker = 0;
                for(String string : personMap.keySet())
                {
                    rank[tracker] = personMap.get(string).getNumberOfLikesReceived();
                    tracker++;
                }
                Arrays.sort(rank);
                for(int i = rank.length - 1; i >= 0; i--)
                {
                    for(String str : personMap.keySet())
                    {
                        if(personMap.get(str).getNumberOfLikesReceived() == rank[i])
                        {
                            personMap.get(str).setLRRank(rank.length - i);
                        }
                    }
                }

                //int[] rank = new int[personMap.size()];
                tracker = 0;
                for(String string : personMap.keySet())
                {
                    rank[tracker] = personMap.get(string).getNumberOfLikesGiven();
                    tracker++;
                }
                Arrays.sort(rank);
                for(int i = rank.length - 1; i >= 0; i--)
                {
                    for(String str : personMap.keySet())
                    {
                        if(personMap.get(str).getNumberOfLikesGiven() == rank[i])
                        {
                            personMap.get(str).setLGRank(rank.length - i);
                        }
                    }
                }

                for(String str : personMap.keySet())
                {
                    double entry = (float) personMap.get(str).getNumberOfLikesReceived()/ (float) personMap.get(str).getNumberOfLikesGiven();
                    personMap.get(str).setFractionOfLRPerLG(entry);
                }

                double[] rankDouble = new double[personMap.size()];
                tracker = 0;
                for(String string : personMap.keySet())
                {
                    rankDouble[tracker] = personMap.get(string).getFractionOfLRPerLG();
                    tracker++;
                }
                Arrays.sort(rankDouble);
                for(int i = rankDouble.length - 1; i >= 0; i--)
                {
                    for(String str : personMap.keySet())
                    {
                        if(personMap.get(str).getFractionOfLRPerLG() == rankDouble[i])
                        {
                            personMap.get(str).setLRPerLGRank(rankDouble.length - i);
                        }
                    }
                }

                for(String str : personMap.keySet())
                {
                    if(personMap.get(str) == null || (!LikeMapMaker.senderIDAndLikesReceived.containsKey(str)))
                    {
                        continue;
                    }
                    else
                    {
                        double entry = (float) personMap.get(str).getNumberOfLikesReceived() / (float) LikeMapMaker.senderIDAndLikesReceived.get(str).size();
                        personMap.get(str).setFractionOfLRPerMS(entry);
                    }
                }

                //double[] rank = new double[personMap.size()];
                tracker = 0;
                for(String string : personMap.keySet())
                {
                    rankDouble[tracker] = personMap.get(string).getFractionOfLRPerMS();
                    tracker++;
                }
                Arrays.sort(rankDouble);
                for(int i = 0; i < rankDouble.length; i++)
                {
                    for(String str : personMap.keySet())
                    {
                        if(personMap.get(str).getFractionOfLRPerMS() == rankDouble[i])
                        {
                            personMap.get(str).setLRPerMSRank(rankDouble.length - i);
                        }
                    }
                }

                StringBuilder stringB = new StringBuilder();
                for(String str : personMap.keySet())
                {
                    stringB.append(str+","+personMap.get(str).getName()+","+personMap.get(str).getNumberOfLikesReceived()+","+personMap.get(str).getLRRank()+","+personMap.get(str).getNumberOfLikesGiven()+","+personMap.get(str).getLGRank()+","+personMap.get(str).getFractionOfLRPerLG()+","+personMap.get(str).getLRPerLGRank()+","+personMap.get(str).getFractionOfLRPerMS()+","+personMap.get(str).getLRPerMSRank()+"\n");
                }

                FileWriter writer = new FileWriter("C:\\Users\\brandon\\Desktop\\FinalPersonData.json"); //"C:\\Users\\brandon\\Desktop\\GroupMeOrganizedData.json"
                writer.write(stringB.toString());
                writer.close();

            }



            /*

            public void setNumberOflikes()
            {
                int sum = 0;
                for(String str : LMM.senderIDAndLikesReceived.keySet())
                {
                    for(List<String> innerList : LMM.senderIDAndLikesReceived.get(str))
                    {
                        sum += innerList.size();
                    }
                    personMap.get(str).setNumberOfLikesReceived(sum);
                }
            }

            public void setNumberOfLikesGiven()
            {
                for(String str : LMM.senderIDAndLikesReceived.keySet())
                {
                    for(List<String> innerList : LMM.senderIDAndLikesReceived.get((str)))
                    {
                        for(String idnum : innerList)
                        {
                            personMap.get(idnum).incrementNumberOfLikesGiven();
                        }
                    }
                }
            }

            public void setRankNumberOfLikesReceived()
            {
                int[] rank = new int[personMap.size()];
                int tracker = 0;
                for(String string : personMap.keySet())
                {
                    rank[tracker] = personMap.get(string).getNumberOfLikesReceived();
                    tracker++;
                }
                Arrays.sort(rank);
                for(int i = rank.length - 1; i >= 0; i--)
                {
                    for(String str : personMap.keySet())
                    {
                        if(personMap.get(str).getNumberOfLikesReceived() == rank[i])
                        {
                            personMap.get(str).setLRRank(rank.length - i);
                        }
                    }
                }
            }

            public void setRankNumberOfLIkesGiven()
            {
                int[] rank = new int[personMap.size()];
                int tracker = 0;
                for(String string : personMap.keySet())
                {
                    rank[tracker] = personMap.get(string).getNumberOfLikesGiven();
                    tracker++;
                }
                Arrays.sort(rank);
                for(int i = rank.length - 1; i >= 0; i--)
                {
                    for(String str : personMap.keySet())
                    {
                        if(personMap.get(str).getNumberOfLikesGiven() == rank[i])
                        {
                            personMap.get(str).setLGRank(rank.length - i);
                        }
                    }
                }
            }

            public void setFractionOfLRPerLG()
            {
                for(String str : personMap.keySet())
                {
                    double entry = (float) personMap.get(str).getNumberOfLikesReceived()/ (float) personMap.get(str).getNumberOfLikesGiven();
                    personMap.get(str).setFractionOfLRPerLG(entry);
                }
            }

            public void setRankLRPerLG()
            {
                double[] rank = new double[personMap.size()];
                int tracker = 0;
                for(String string : personMap.keySet())
                {
                    rank[tracker] = personMap.get(string).getFractionOfLRPerLG();
                    tracker++;
                }
                Arrays.sort(rank);
                for(int i = rank.length - 1; i >= 0; i--)
                {
                    for(String str : personMap.keySet())
                    {
                        if(personMap.get(str).getFractionOfLRPerLG() == rank[i])
                        {
                            personMap.get(str).setLRPerLGRank(rank.length - i);
                        }
                    }
                }
            }

            public void setLRPerMS()
            {
                for(String str : personMap.keySet())
                {
                    double entry = (float) personMap.get(str).getNumberOfLikesReceived()/ (float) LMM.senderIDAndLikesReceived.get(str).size();
                    personMap.get(str).setFractionOfLRPerMS(entry);
                }
            }

            public void setRankLRPerMS()
            {
                double[] rank = new double[personMap.size()];
                int tracker = 0;
                for(String string : personMap.keySet())
                {
                    rank[tracker] = personMap.get(string).getFractionOfLRPerMS();
                    tracker++;
                }
                Arrays.sort(rank);
                for(int i = rank.length - 1; i >= 0; i--)
                {
                    for(String str : personMap.keySet())
                    {
                        if(personMap.get(str).getFractionOfLRPerMS() == rank[i])
                        {
                            personMap.get(str).setLRPerLGRank(rank.length - i);
                        }
                    }
                }
            }

             */
        }
        catch(IOException e) {
            System.out.println("IDAndName didn't work");
        }
    }
}
