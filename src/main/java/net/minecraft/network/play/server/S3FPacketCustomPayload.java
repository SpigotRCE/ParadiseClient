package net.minecraft.network.play.server;

import dev.isnow.paradise.Paradise;
import dev.isnow.paradise.helper.ChatHelper;

import java.io.IOException;
import java.nio.charset.Charset;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S3FPacketCustomPayload implements Packet<INetHandlerPlayClient>
{
    private String channel;
    private PacketBuffer data;

    public S3FPacketCustomPayload()
    {
    }

    public S3FPacketCustomPayload(String channelName, PacketBuffer dataIn)
    {
        this.channel = channelName;
        this.data = dataIn;

        if (dataIn.writerIndex() > 1048576)
        {
            throw new IllegalArgumentException("Payload may not be larger than 1048576 bytes");
        }
    }

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.channel = buf.readStringFromBuffer(20);
        int i = buf.readableBytes();

        if (i >= 0 && i <= 1048576)
        {
            this.data = new PacketBuffer(buf.readBytes(i));
        }
        else
        {
            throw new IOException("Payload may not be larger than 1048576 bytes");
        }
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer buf) throws IOException
    {
        buf.writeString(this.channel);
        buf.writeBytes(this.data);
    }


    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(INetHandlerPlayClient handler)
    {

        handler.handleCustomPayload(this);
        try {
            if(channel.equals("REGISTER")) {
                for(String splitted : getBufferData().toString(Charset.defaultCharset()).split("\0")) {
                    ChatHelper.printMessage("&fCHANNEL: &d" + splitted);
                }
            } else {
                try {
                    ChatHelper.printMessage("&fCHANNEL: &d" + this.channel + " &fDATA: &d" + getBufferData().toString(Charset.defaultCharset()));
                } catch (Exception ignored) {
                    ChatHelper.printMessage("&fCHANNEL: &d" + this.channel);
                }
            }
        } catch (Exception ignored) {
            ChatHelper.printMessage("&fFailed to read channels &4" + ignored.getMessage());
        }
    }

    public String getChannelName()
    {
        return this.channel;
    }

    public PacketBuffer getBufferData()
    {
        return this.data;
    }
}
