package ioc.c;

/**
 * Created by Administrator on 2015/12/19.
 */
public class Business {
    private DeviceWriter deviceWriter;

    /*//��ʽһ:������
    public Business(DeviceWriter deviceWriter) {
        this.deviceWriter = deviceWriter;
    }*/

    //��ʽ��:getter����
    public void setDeviceWriter(DeviceWriter deviceWriter) {
        this.deviceWriter = deviceWriter;
    }

    public void write(){
        deviceWriter.writeToDevice();
    }
}
