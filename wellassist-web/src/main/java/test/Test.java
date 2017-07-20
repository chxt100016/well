package test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by Administrator on 2017/6/28.
 */
public class Test {
    public static void main(String [] args) throws IOException {
        URL website = new URL("http://opz8xidtr.bkt.clouddn.com/upload/20170718/02d37de035694d61af7f94030c93e258");
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream("D://xxx.xlsx");//例如：test.txt
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

    }
}
