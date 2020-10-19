import java.io.File;

public class MainMethod {
    public static void main(String[] args)
    {
        File f = new File("C:\\Users\\brandon\\Desktop\\GroupMeOrganizedData.json");
        LikeMapMaker l = new LikeMapMaker();
        l.setMap(f); //"C:\\Users\\brandon\\Desktop\\GroupMeOrganizedData.json"

        //File file = new File("C:\\Users\\brandon\\Desktop\\gmdata\\61672113\\message.json");
        //FormatFile formatFileInstance = new FormatFile(file);

        File convoFile = new File("C:\\Users\\brandon\\Desktop\\gmdata\\61672113\\conversation.json");
        IDAndName IDAndNameInstance = new IDAndName();
        IDAndNameInstance.setPersonMap(convoFile);
    }
}
