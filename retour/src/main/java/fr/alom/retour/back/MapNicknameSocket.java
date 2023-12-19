package fr.alom.retour.back;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MapNicknameSocket {
    private Map<String, Socket> mapNameSocket;

    public MapNicknameSocket(){
        this.mapNameSocket = new HashMap<String, Socket>();
    }

    public Socket getSocketFromNickname(String nickname){
        return this.mapNameSocket.get(nickname);
    }

    public void add(String nickname, Socket socket){
        this.mapNameSocket.put(nickname, socket);
    }
}
