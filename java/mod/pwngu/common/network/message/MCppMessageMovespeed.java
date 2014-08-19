package mod.pwngu.common.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

import java.util.UUID;

public class MCppMessageMovespeed implements IMessage, IMessageHandler<MCppMessageMovespeed, IMessage> {

    private UUID uuid;
    private String playerName;

    @Override
    public void fromBytes(ByteBuf buf) {

        uuid = new UUID(buf.readLong(), buf.readLong());
        byte[] bytes = new byte[buf.readInt()];
        buf.readBytes(bytes);
        playerName = new String(bytes);
    }

    @Override
    public void toBytes(ByteBuf buf) {

        buf.writeLong(uuid.getMostSignificantBits());
        buf.writeLong(uuid.getLeastSignificantBits());

        byte[] bytes = playerName.getBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
    }

    @Override
    public IMessage onMessage(MCppMessageMovespeed message, MessageContext ctx) {
        return null;
    }
}
