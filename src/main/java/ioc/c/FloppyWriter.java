package ioc.c;

/**
 * Created by Administrator on 2015/12/19.
 */
//�ײ�ģ����
public class FloppyWriter implements DeviceWriter {
    @Override
    public void writeToDevice() {
        System.out.println("save to floppy...");
    }
}
