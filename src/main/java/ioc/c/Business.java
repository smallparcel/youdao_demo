package ioc.c;

/**
 * Created by Administrator on 2015/12/19.
 */
//�߲�Ӧ����
public class Business {
    private DeviceWriter deviceWriter;  //�ӿڶ���һ�����󡡡���̬��Ӧ��
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
