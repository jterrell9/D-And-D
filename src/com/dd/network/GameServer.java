package com.dd.network;

import com.dd.command_util.CommandHandler;
import com.dd.dd_util.ByteUtility;
import com.dd.entities.Player;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.*;

public class GameServer extends Thread{
    private ServerGameState gameState;
    private Player localClientPlayer;
    private PipeCommChannel localClientPipe;
    private Selector selector;
    private List<SelectionKey> connectedPlayerList = new ArrayList<SelectionKey>();
    private List<SelectionKey> connectionsToTerminate = new ArrayList<SelectionKey>();
    private SelectionKey serverKey;
    private SelectionKey localClientInKey, localClientOutKey;
    private ServerSocket serverSocket;
    private int port;

    public GameServer(ServerGameState gameState, Player localClientPlayer, PipeCommChannel localClientPipe, int port){
        this.gameState = gameState;
        this.localClientPlayer = localClientPlayer;
        this.localClientPipe = localClientPipe;
        this.port = port;
    }

    @Override
    public void run(){
        try{
            selector = Selector.open();
            serverSocket = new ServerSocket(port);
            serverKey = serverSocket.getChannel().register(selector, SelectionKey.OP_ACCEPT);
            localClientInKey = localClientPipe.getInputChannel().register(selector, SelectionKey.OP_READ);
            localClientInKey.attach(localClientPlayer);
            localClientOutKey = localClientPipe.getOutputChannel().register(selector, SelectionKey.OP_WRITE);
            localClientOutKey.attach(localClientPlayer);
        }
        catch(IOException e){
            //failed to setup server
        }

        //Create thread to handle cleaning up

        while(true){
            try{
                for(SelectionKey key : connectedPlayerList){
                    if(!key.isValid()){
                        //remove player from activePlayerList in GameState
                        //and send out new gamestate
                    }
                }
                int numKeys = selector.select();
                if(!localClientInKey.isValid()
                        || !localClientOutKey.isValid()
                        || !serverKey.isValid()) {
                    //Server teardown
                }
                if(numKeys == 0)
                    continue;

                MulticastDescriptor multicastDesc = new MulticastDescriptor();

                //Receive all incoming message and new connections
                for(SelectionKey key : selector.selectedKeys()){
                    if(key == serverKey){
                        //Accept new incoming connection
                        SocketCommChannel commChannel = new SocketCommChannel(((ServerSocketChannel)serverKey.channel()).socket().accept());
                        PlayerConnection playerConn = new PlayerConnection(null, commChannel);
                        SelectionKey newKey = commChannel.getSocket().getChannel().register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        newKey.attach(playerConn);
                        connectedPlayerList.add(newKey);
                        //Player must now be validated
                    }
                    else{
                        PlayerConnection playerConn = (PlayerConnection)key.attachment();
                        if(key.isReadable()){
                            if(playerConn.getPlayer() == null){
                                //Validation
                            }
                            else{
                                //read message and use it to build MulticastDescriptor
                                //as well as to modify the GameState
                            }
                        }
                    }
                }

                //Build individual messages from MulticastDescriptor

                //Send out all outgoing messages
                for(SelectionKey key : selector.selectedKeys()){
                    if(key.isWritable()){
                        PlayerConnection playerConn = (PlayerConnection)key.attachment();
                        playerConn.commChannel.write(playerConn.flushOutBuffer());
                    }
                }

                for(SelectionKey key : connectionsToTerminate){
                    PlayerConnection playerConn = (PlayerConnection)key.attachment();
                    playerConn.commChannel.close();
                }
                connectionsToTerminate.clear();
            }
            catch (IOException e){

            }
        }
    }

    public class PlayerConnection{
        private Player player;
        private NetworkCommChannel commChannel;
        private byte[] inHolder = null;
        private List<Byte> outBuffer = new ArrayList<Byte>();

        public PlayerConnection(Player player, NetworkCommChannel commChannel){
            this.player = player;
            this.commChannel = commChannel;
        }

        public Player getPlayer(){
            return player;
        }

        public void setPlayer(Player player){
            this.player = player;
        }

        public NetworkCommChannel getCommChannel(){
            return commChannel;
        }

        public void setCommChannel(NetworkCommChannel commChannel){
            this.commChannel = commChannel;
        }

        public void appendToOutBuffer(byte[] outData){
            for(byte b : outData){
                outBuffer.add(b);
            }
        }

        public byte[] flushOutBuffer(){
            byte[] outArr = ByteUtility.byteArrayFromByteList(outBuffer);
            outBuffer.clear();
            return outArr;
        }
    }
}
