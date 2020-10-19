import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FormatFile {

    public FormatFile(File file)
    {
        try {
            //File file = new File("C:\\Users\\brandon\\AppData\\Local\\Temp\\Temp3_gmdata.zip\\61672113\\message.json");
            Scanner scanner = new Scanner(file);
            StringBuilder sb = new StringBuilder();

            while(scanner.hasNextLine())
            {
                String data = scanner.nextLine();
                String[] pieces = data.split("\\{\"attachments\"");
                for(int i = 0; i < pieces.length; i++)
                {
                    String[] morePieces = pieces[i].split(",");
                    for(int z = 0; z < morePieces.length; z++)
                    {
                        if((morePieces[z].contains("[]")) && (!morePieces[z].contains("\"favorited_by\"")) && (!morePieces[z].contains("\"text\"")))
                        {
                            sb.append("{\"attachments\"" + morePieces[z] + "\n");
                        }
                        else if((!morePieces[z].contains("[]")) && (!morePieces[z].contains("\"loci\"")) && morePieces[z].contains(":[{") && (!morePieces[z].contains("\"text\"")))
                        {
                            sb.append("{\"attachments\"" + morePieces[z] + "," + morePieces[z+1] + "\n");
                        }
                        else if(morePieces[z].contains("\"loci\"") && (!morePieces[z].contains("\"text\"")))
                        {
                            sb.append("{\"attachments\"" + morePieces[z] + "," + morePieces[z+1] + "," + morePieces[z+2] + "," + morePieces[z+3] + "\n");
                        }
                        else if(morePieces[z].contains("\"type\"") && (!morePieces[z].contains("\"text\"")) || morePieces[z].contains("\"user_ids\"") && (!morePieces[z].contains("\"text\"")) || morePieces[z].contains("\"url\"") && (!morePieces[z].contains("\"text\"")) || morePieces[z].length() < 5)
                        {
                            continue;
                        }
                        else
                        {
                            if(z + 1 < morePieces.length && morePieces[z+1].length() < 12 && (!morePieces[z+1].contains("text")) && (!morePieces[z+1].contains("name")))
                            {
                                sb.append(morePieces[z] + ",");
                            }
                            else if(z + 1 < morePieces.length && morePieces[z+1].contains("\"group_id\""))
                            {
                                sb.append(morePieces[z] + "\n");
                            }
                            else
                            {
                                sb.append(morePieces[z] + "\n");
                            }
                        }
                    }
                    sb.append("\n");
                }
            }

            FileWriter writer = new FileWriter("C:\\Users\\brandon\\Desktop\\GroupMeOrganizedData.json");
            writer.write(sb.toString());
            scanner.close();
            writer.close();
        }
        catch(IOException e) {
            System.out.println("Didn't work");
        }
    }
}
