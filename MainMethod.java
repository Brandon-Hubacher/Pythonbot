import java.io.File;

public class MainMethod {
    public static void main(String[] args)
    {
        //File file = new File("C:\\Users\\brandon\\Desktop\\Python bot groupme data\\63363295\\message.json"); //"C:\\Users\\brandon\\Desktop\\gmdata\\61672113\\message.json"
        //FormatFile formatFileInstance = new FormatFile(file);
        
        File f = new File("C:\\Users\\brandon\\Desktop\\PythonOrganizedData.json"); //"C:\\Users\\brandon\\Desktop\\GroupMeOrganizedData.json"
        LikeMapMaker l = new LikeMapMaker();
        l.setMap(f);

        File convoFile = new File("C:\\Users\\brandon\\Desktop\\Python bot groupme data\\63363295\\conversation.json"); //"C:\\Users\\brandon\\Desktop\\gmdata\\61672113\\conversation.json"
        IDAndName IDAndNameInstance = new IDAndName();
        IDAndNameInstance.setPersonMap(convoFile);
    }
}
