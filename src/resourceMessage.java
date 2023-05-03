import java.io.Serializable;

public class resourceMessage implements Serializable {
        private String resource;
        private int player;
        public resourceMessage(String r, int p) {
            this.resource = r;
            this.player = p;
        }
        public String getResource() {
            return resource;
        }
        public int getPlayer() {
            return player;
        }
}
