package geoimbib;

/**
 * Created by quentin on 19/02/16.
 */
/*
 * Copyright (C) 2014 Klaus Reimer <k@ailis.de>
 * See LICENSE.txt for licensing information.
 */


import org.usb4java.BufferUtils;
import org.usb4java.DeviceHandle;
import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.channels.Pipe;

/**
 * Demonstrates how to do synchronous bulk transfers. This demo sends some
 * hardcoded data to an Android Device (Samsung Galaxy Nexus) and receives some
 * data from it.
 *
 * If you have a different Android device then you can get this demo working by
 * changing the vendor/product id, the interface number and the endpoint
 * addresses.
 *
 * @author Klaus Reimer <k@ailis.de>
 */
public class SyncBulkTransfer
{
    /** The vendor ID of the OLIMEX. */
    private static final short VENDOR_ID = 0x03eb;

    /** The vendor ID of the OLIMEX. */
    private static final short PRODUCT_ID = 0x2044;

    /** The ADB interface
     *  number of the Samsung Galaxy Nexus. */
    private static final byte INTERFACE = 1;

    /** The ADB input endpoint of the Samsung Galaxy Nexus. */
    private static final byte IN_ENDPOINT = (byte) 0x83;

    /** The ADB output endpoint of the Samsung Galaxy Nexus. */
    private static final byte OUT_ENDPOINT = 0x00;

    /** The communication timeout in milliseconds. */
    private static final int TIMEOUT = 5000;

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

    /**
     * Main method.
     *
     * @param args
     *            Command-line arguments (Ignored)
     * @throws Exception
     *             When something goes wrong.
     */
    public static void main(String[] args) throws Exception
    {
        // Initialize the libusb context
        int result = LibUsb.init(null);
        if (result != LibUsb.SUCCESS)
        {
            throw new LibUsbException("Unable to initialize libusb", result);
        }

        // Open test device (Samsung Galaxy Nexus)
        DeviceHandle handle = LibUsb.openDeviceWithVidPid(null, VENDOR_ID,
                PRODUCT_ID);
        if (handle == null)
        {
            System.err.println("Test device not found.");
            System.exit(1);
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
        result = LibUsb.claimInterface(handle, INTERFACE);

        if (result != LibUsb.SUCCESS)
        {
            throw new LibUsbException("Unable to claim interface", result);
        }

        @SuppressWarnings("unused")
        //for(int i=0;i<10;i++){
            ByteBuffer data = read(handle, 25);
            System.out.println(byteBufferToString(data));
        //}


        // Release the ADB interface
        result = LibUsb.releaseInterface(handle, INTERFACE);
        if (result != LibUsb.SUCCESS)
        {
            throw new LibUsbException("Unable to release interface", result);
        }

        // Close the device
        LibUsb.close(handle);

        // Deinitialize the libusb context
        LibUsb.exit(null);
        System.out.println("Test messageToDouble" + stringTotalMessageToDouble("24.00071?3"));
    }
}
