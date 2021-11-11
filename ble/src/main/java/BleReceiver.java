import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import java.io.DataInputStream;
import java.io.IOException;

public class BleReceiver {
    static final String serverUUID = "0000110100001000800000805F9B34FB";

    public static void main(String[] args) throws IOException {

        LocalDevice localDevice = LocalDevice.getLocalDevice();

        localDevice.setDiscoverable(DiscoveryAgent.GIAC); // Advertising the service

        String url = "btspp://localhost:" + serverUUID + ";name=BlueToothServer";
        StreamConnectionNotifier server = (StreamConnectionNotifier) Connector.open(url);

        StreamConnection connection = server.acceptAndOpen(); // Wait until client connects
        //=== At this point, two devices should be connected ===//
        DataInputStream dis = connection.openDataInputStream();

        char c;
        while (true) {
            c = dis.readChar();
            if (c == 'x') {
                break;
            }
        }

        connection.close();
    }

}
