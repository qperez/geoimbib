package geoimbib.IO;

import org.usb4java.BufferUtils;
import org.usb4java.DeviceHandle;
import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

/**
 * Created by quentin on 07/03/16.
 */
public class IO_SyncBulkTransfer implements Runnable{
    /** The vendor ID of the OLIMEX. */
    private static final short VENDOR_ID = 0x03eb;

    /** The vendor ID of the OLIMEX. */
    private static final short PRODUCT_ID = 0x2044;

    /** The interface
     *  number of the device. */
    private static final byte INTERFACE = 1;

    /** The ADB input endpoint of the Samsung Galaxy Nexus. */
    private static final byte IN_ENDPOINT = (byte) 0x83;

    /** The ADB output endpoint of the Samsung Galaxy Nexus. */
    private static final byte OUT_ENDPOINT = 0x00;

    /** The communication timeout in milliseconds. */
    private static final int TIMEOUT = 5000;

    private volatile double masse;

    public IO_SyncBulkTransfer(){}

    /**
     * Writes some data to the device.
     *
     * @param handle
     *            The device handle.
     * @param data
     *            The data to send to the device.
     */
    public static void write(DeviceHandle handle, byte[] data)
    {
        ByteBuffer buffer = BufferUtils.allocateByteBuffer(data.length);
        buffer.put(data);
        IntBuffer transferred = BufferUtils.allocateIntBuffer();
        int result = LibUsb.bulkTransfer(handle, OUT_ENDPOINT, buffer,
                transferred, TIMEOUT);
        if (result != LibUsb.SUCCESS)
        {
            throw new LibUsbException("Unable to send data", result);
        }
        System.out.println(transferred.get() + " bytes sent to device");
    }

    /**
     * Reads some data from the device.
     *
     * @param handle
     *            The device handle.
     * @param size
     *            The number of bytes to read from the device.
     * @return The read data.
     */
    public static ByteBuffer read(DeviceHandle handle, int size)
    {
        ByteBuffer buffer = BufferUtils.allocateByteBuffer(size).order(
                ByteOrder.LITTLE_ENDIAN);
        IntBuffer transferred = BufferUtils.allocateIntBuffer();
        int result = LibUsb.bulkTransfer(handle, IN_ENDPOINT, buffer,
                transferred, TIMEOUT);
        if (result != LibUsb.SUCCESS)
        {
            throw new LibUsbException("Unable to read data", result);
        }
        System.out.println(transferred.get() + " bytes read from device");
        //int stringLength = data.getInt();

        return buffer;
    }

    public static String byteBufferToString(ByteBuffer buffer){
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        return new String(bytes);
    }

    public static Double stringTotalMessageToDouble(String message){
        return new Double(message.substring(0,7));
    }

    public static short getVendorId() {
        return VENDOR_ID;
    }

    public static int getTIMEOUT() {
        return TIMEOUT;
    }

    public static byte getOutEndpoint() {
        return OUT_ENDPOINT;
    }

    public static byte getInEndpoint() {
        return IN_ENDPOINT;
    }

    public static byte getINTERFACE() {
        return INTERFACE;
    }

    public static short getProductId() {
        return PRODUCT_ID;
    }

    public double getMasse() {
        return masse;
    }

    public void run(){
        int result = LibUsb.init(null);
        if (result != LibUsb.SUCCESS)
        {
            throw new LibUsbException("Unable to initialize libusb", result);
        }

        // Open test device (Samsung Galaxy Nexus)
        DeviceHandle handle = LibUsb.openDeviceWithVidPid(null, getVendorId(),
                getProductId());
        if (handle == null)
        {
            System.err.println("Test device not found.");
        }

        //Detach kernelk driver
        result = LibUsb.detachKernelDriver(handle, 1);
        if (result != LibUsb.SUCCESS &&
                result != LibUsb.ERROR_NOT_SUPPORTED &&
                result != LibUsb.ERROR_NOT_FOUND)
        {
            throw new LibUsbException("Unable to detach kernel driver",
                    result);
        }

        // Claim the ADB interface
        result = LibUsb.claimInterface(handle, getINTERFACE());

        if (result != LibUsb.SUCCESS)
        {
            throw new LibUsbException("Unable to claim interface", result);
        }

        ByteBuffer data = null;
        String messageFromBalance = null;
        //@SuppressWarnings("unused")
        for(int i=0;i<3;i++){
            data = read(handle, 25);
            messageFromBalance = byteBufferToString(data);
            System.out.println("Message de la balance = " + messageFromBalance);
        }
        masse = stringTotalMessageToDouble(messageFromBalance);

        // Release the ADB interface
        result = LibUsb.releaseInterface(handle, getINTERFACE());
        if (result != LibUsb.SUCCESS)
        {
            throw new LibUsbException("Unable to release interface", result);
        }

        // Close the device
        LibUsb.close(handle);

        // Deinitialize the libusb context
        LibUsb.exit(null);

    }
}
